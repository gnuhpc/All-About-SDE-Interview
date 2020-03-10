package org.gnuhpc.interview.systemdesign.practice.mq.zk;

import org.apache.curator.ensemble.fixed.FixedEnsembleProvider;
import org.apache.curator.framework.CuratorFramework;
import org.apache.curator.framework.CuratorFrameworkFactory;
import org.apache.curator.framework.recipes.queue.DistributedQueue;
import org.apache.curator.framework.recipes.queue.QueueBuilder;
import org.apache.curator.retry.RetryOneTime;

public class ZkDqQueuer {
    DistributedQueue<ZkDqWork> queue;

    public ZkDqQueuer() throws Exception {
        CuratorFramework client = CuratorFrameworkFactory.builder()
                .retryPolicy(new RetryOneTime(10)).namespace("ZkDqTest")
                .ensembleProvider(new FixedEnsembleProvider("localhost:2181"))
                .build();
        client.start();

        ZkDqConsumer consumer = new ZkDqConsumer();
        ZkDqSerializer serializer = new ZkDqSerializer();
        QueueBuilder<ZkDqWork> builder = QueueBuilder.builder(client,
                consumer, serializer, "/com/zq/test");
        queue = builder.buildQueue();
        queue.start();

    }

    public void queueMessages() throws Exception {
        for (int i = 0; i < 10; i++) {
            ZkDqWork work = new ZkDqWork("testWork [" + i + "]");
            this.queue.put(work);
            System.out.println("Queued [" + i + "]");
        }
        Thread.sleep(5000);
    }

}
