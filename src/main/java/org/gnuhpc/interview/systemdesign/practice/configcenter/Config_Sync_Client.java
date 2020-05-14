package org.gnuhpc.interview.systemdesign.practice.configcenter;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.TypeReference;
import org.apache.curator.RetryPolicy;
import org.apache.curator.framework.CuratorFramework;
import org.apache.curator.framework.CuratorFrameworkFactory;
import org.apache.curator.framework.recipes.cache.ChildData;
import org.apache.curator.framework.recipes.cache.PathChildrenCache;
import org.apache.curator.framework.recipes.cache.PathChildrenCacheEvent;
import org.apache.curator.framework.recipes.cache.PathChildrenCacheListener;
import org.apache.curator.retry.RetryNTimes;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;
import java.util.concurrent.CountDownLatch;

public class Config_Sync_Client {

    final static Logger log = LoggerFactory.getLogger(Config_Sync_Client.class);

    public CuratorFramework client = null;
    public static final String zkServerIpPort = "127.0.0.1:2181";

    public Config_Sync_Client() {
        //断链重连最大重试3次，间隔5秒
        RetryPolicy retryPolicy = new RetryNTimes(3, 5000);
        client = CuratorFrameworkFactory.builder()
                .connectString(zkServerIpPort)
                .sessionTimeoutMs(10000).retryPolicy(retryPolicy)
                .namespace("workspace").build();
        client.start();
    }

    public void closeZKClient() {
        if (client != null) {
            this.client.close();
        }
    }

    //实际生产中，给每个要同步的项目分配一个SUB_PATH，例如 "/ww/config-sync/redis-config";
    public final static String CONFIG_NODE_PATH = "/ww/config-sync";
    public final static String SUB_PATH = "/rcs-config";

    //为方便看结果，使用CountDownLatch让主线程等待，持续监听
    public static CountDownLatch countDown = new CountDownLatch(1);

    public static void main(String[] args) throws Exception {
        Config_Sync_Client cto = new Config_Sync_Client();
        log.warn("client 启动成功...");

        //监控父节点CONFIG_NODE_PATH，可持续获取所有子节点的触发事件event，并且可以区分子节点的增、删、改操作。
        //cacheData: 是否缓存该节点的数据状态信息stat
        final PathChildrenCache childrenCache = new PathChildrenCache(cto.client, CONFIG_NODE_PATH, true);
        //StartMode这次使用BUILD_INITIAL_CACHE：同步初始化，childrenCache会立即获取到节点信息
        childrenCache.start(PathChildrenCache.StartMode.BUILD_INITIAL_CACHE);

        List<ChildData> childDataList = childrenCache.getCurrentData();
        log.warn("当前数据节点的子节点数据列表：");
        for (ChildData childData : childDataList) {
            log.warn(new String(childData.getData()));
        }

        // 添加监听事件
        childrenCache.getListenable().addListener(new PathChildrenCacheListener() {
            public void childEvent(CuratorFramework client, PathChildrenCacheEvent event) throws Exception {
                //childrenCache的初始化方式为StartMode.POST_INITIALIZED_EVENT时，能监听到该事件
                if (event.getType().equals(PathChildrenCacheEvent.Type.INITIALIZED)) {
                    log.warn("子节点初始化ok...");
                }
                // 监听节点修改事件
                else if (event.getType().equals(PathChildrenCacheEvent.Type.CHILD_UPDATED)) {
                    String configNodePath = event.getData().getPath();
                    if (configNodePath.equals(CONFIG_NODE_PATH + SUB_PATH)) {
                        log.warn("监听到节点" + configNodePath + "配置已被修改！");

                        // 读取节点数据
                        String jsonConfig = new String(event.getData().getData());
                        log.warn("节点" + CONFIG_NODE_PATH + "修改后的数据为: " + jsonConfig);

                        // String转为从json转换配置

                        Config_Bean config_obj = JSON.parseObject(jsonConfig, new TypeReference<Config_Bean>() {
                        });


                        // 配置不为空则进行相应操作
                        if (config_obj != null) {
                            Long config_uuid = config_obj.getConfig_uuid();
                            String change_type = config_obj.getChange_type();
                            String download_url = config_obj.getDownload_url();
                            String change_remark = config_obj.getChange_remark();
                            // 判断事件
                            if (change_type.equals("add")) {
                                log.warn("监听到新增的配置，ID:" + config_uuid + "，准备下载...");
                                // ... 连接ftp服务器，根据url找到相应的配置
                                Thread.sleep(1000);
                                log.warn("开始下载新增配置，下载路径为<" + download_url + ">");
                                // ... 下载配置到指定的目录
                                Thread.sleep(1000);
                                log.warn("下载成功，已经添加到项目中。");
                                // ... 拷贝文件到项目目录
                                log.warn("记录操作日志：" + change_remark);

                            } else if (change_type.equals("update")) {
                                log.warn("监听到更新的配置，ID:" + config_uuid + "，准备下载...");
                                // ... 连接ftp服务器，根据url找到相应的配置
                                Thread.sleep(1000);
                                log.warn("开始下载修改配置，下载路径为<" + download_url + ">");
                                // ... 下载配置到你指定的目录
                                Thread.sleep(1000);
                                log.warn("下载成功...");
                                log.warn("删除项目中原配置文件...");
                                Thread.sleep(1000);
                                // ... 删除原文件
                                log.warn("拷贝配置文件到项目目录...");
                                // ... 拷贝文件到项目目录
                                log.warn("记录操作日志：" + change_remark);

                            } else if (change_type.equals("delete")) {
                                log.warn("监听到需要删除配置，ID:" + config_uuid);
                                log.warn("删除项目中原配置文件...");
                                Thread.sleep(1000);
                                log.warn("记录操作日志：" + change_remark);
                            }
                        }
                    }
                }
            }
        });

        countDown.await();

        cto.closeZKClient();
    }

}
