package org.gnuhpc.bigdata.systemdesign.practice.distributedlock;

import org.apache.curator.RetryPolicy;
import org.apache.curator.framework.CuratorFramework;
import org.apache.curator.framework.CuratorFrameworkFactory;
import org.apache.curator.framework.recipes.cache.PathChildrenCache;
import org.apache.curator.framework.recipes.cache.PathChildrenCacheEvent;
import org.apache.curator.framework.recipes.cache.PathChildrenCacheListener;
import org.apache.curator.retry.RetryNTimes;
import org.apache.zookeeper.CreateMode;
import org.apache.zookeeper.ZooDefs;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.concurrent.CountDownLatch;

// 尝试编写一个基于ZooKeeper能力实现分布式锁的工具类
public class Distributed_Lock {

    final static Logger log = LoggerFactory.getLogger(Distributed_Lock.class);

    public CuratorFramework client = null;
    public static final String zkServerIpPort = "127.0.0.1:2181";

    //使用CountDownLatch让本次请求挂起countDown.await()，
    // 等上一次请求结束后delete node释放分布式锁,watcher通知唤醒本次请求继续执行。
    public static CountDownLatch waitLockLatch = new CountDownLatch(1);

    //PARENT_LOCK_PATH是所有分布式锁的根节点，PARENT_LOCK_PATH + PROJECT1_LOCK_PATH是本项目使用的节点;
    public final static String PARENT_LOCK_PATH = "/distributed-lock";
    public final static String PROJECT1_LOCK_PATH = "/project1-lock";

    //构造方法
    public Distributed_Lock(CuratorFramework client) {
        this.client = client;
    }

    //客户端调用者使用该方法连接ZooKeeper服务端，判断所有分布式锁的父节点如果不存在则创建，在该父节点加Watcher监听
    public void init() {
        try {
            //断链重连最大重试3次，间隔5秒
            RetryPolicy retryPolicy = new RetryNTimes(3, 5000);
            client = CuratorFrameworkFactory.builder()
                    .connectString(zkServerIpPort)
                    .sessionTimeoutMs(10000).retryPolicy(retryPolicy)
                    //所有节点都在此命名空间下，而不是ZooKeeper的根节点下
                    .namespace("workspace").build();
            client.start();

            //如果节点path PARENT_LOCK_PATH不存在则创建
            if (client.checkExists().forPath(PARENT_LOCK_PATH) == null) {
                client.create()
                        //所有分布式锁的根节点，永久存在
                        .withMode(CreateMode.PERSISTENT)
                        //生产环境这个地方应该用digest权限安全防护起来
                        .withACL(ZooDefs.Ids.OPEN_ACL_UNSAFE)
                        .forPath(PARENT_LOCK_PATH);
            }

            //给PARENT_LOCK_PATH，所有分布式锁的父节点addWatcherToLock()监听
            addWatcherToLock(PARENT_LOCK_PATH);

        } catch (Exception e) {
            log.error("客户端连接ZooKeeper服务端错误，请重试！");
        }
    }

    //在所有分布式锁的父节点parent_lock_path上，基于PathChildrenCache加Watcher监听
    public void addWatcherToLock(String parent_lock_path) throws Exception {
        //监控父节点，可持续获取所有子节点的触发事件event，并且可以区分子节点的增、删、改操作。
        //cacheData: 是否缓存该节点的数据状态信息stat
        final PathChildrenCache childrenCache = new PathChildrenCache(client, parent_lock_path, true);
        //StartMode可使用POST_INITIALIZED_EVENT：异步初始化
        childrenCache.start(PathChildrenCache.StartMode.POST_INITIALIZED_EVENT);

        // 添加监听事件
        childrenCache.getListenable().addListener(new PathChildrenCacheListener() {
            public void childEvent(CuratorFramework client, PathChildrenCacheEvent event) throws Exception {
                //子节点，该项目的分布式锁 被删除时，能监听到该事件
                if (event.getType().equals(PathChildrenCacheEvent.Type.CHILD_REMOVED)) {
                    String path = event.getData().getPath();
                    log.warn("上次请求会话已断开或上次请求会话已释放分布式锁：" + path);
                    if (path.contains(PARENT_LOCK_PATH)) {
                        waitLockLatch.countDown();
                        log.warn("释放线程计数器，各线程请求尝试获取分布式锁...");
                    }
                }
            }
        });
    }

    public void getLock() {
        //循环尝试获得分布式锁，直到成功获得
        while (true) {
            try {
                //能成功创建临时节点PARENT_LOCK_PATH + PROJECT1_LOCK_PATH，就是成功获得分布式锁
                client.create()
                        .creatingParentsIfNeeded()
                        //注意：每个项目的分布式锁需要设置成临时节点，这样该连接断链后，
                        // ZooKeeper服务端可以自动删除该分布式锁节点，避免死锁
                        .withMode(CreateMode.EPHEMERAL)
                        //生产环境这个地方应该用digest权限安全防护起来
                        .withACL(ZooDefs.Ids.OPEN_ACL_UNSAFE)
                        .forPath(PARENT_LOCK_PATH + PROJECT1_LOCK_PATH);
                log.warn("获得分布式锁成功！");
                return;
            } catch (Exception e) {
                log.warn("获得分布式锁失败！");
                try {
                    //如果创建临时节点失败，就是没获取到分布式锁，检查CountDownLatch是否需要重新设置
                    if (waitLockLatch.getCount() <= 0) {
                        waitLockLatch = new CountDownLatch(1);
                    }
                    // 阻塞本线程，等待其他请求释放分布式锁后的watcher通知
                    waitLockLatch.await();
                } catch (InterruptedException ie) {
                    ie.printStackTrace();
                }
            }
        }
    }

    //释放分布式锁
    public boolean releaseLock() {
        try {
            if (client.checkExists().forPath(PARENT_LOCK_PATH + PROJECT1_LOCK_PATH) != null) {
                client.delete().forPath(PARENT_LOCK_PATH + PROJECT1_LOCK_PATH);
            }
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
        log.warn("分布式锁" + PARENT_LOCK_PATH + PROJECT1_LOCK_PATH + "已释放！");
        return true;
    }

    public void closeZKClient() {
        if (client != null) {
            this.client.close();
        }
    }
}


