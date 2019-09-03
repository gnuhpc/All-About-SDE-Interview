package org.gnuhpc.bigdata.systemdesign.practice.mq.zk;

import org.apache.curator.framework.CuratorFramework;
import org.apache.curator.framework.recipes.queue.QueueConsumer;
import org.apache.curator.framework.state.ConnectionState;

public class ZkDqConsumer implements QueueConsumer<ZkDqWork> {

    public void stateChanged(CuratorFramework framework, ConnectionState state) {
        System.out.println("State [" + state + "]");

    }

    public void consumeMessage(ZkDqWork work) throws Exception {
        System.out.println("Consuming (" + work + ")");
    }


}
