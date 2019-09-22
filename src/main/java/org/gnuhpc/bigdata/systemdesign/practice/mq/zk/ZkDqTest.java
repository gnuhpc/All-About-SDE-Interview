package org.gnuhpc.bigdata.systemdesign.practice.mq.zk;

import org.junit.Test;

public class ZkDqTest {

    @Test
    public void testDistributedQueue() throws Throwable {
        ZkDqQueuer queuer = new ZkDqQueuer();
        queuer.queueMessages();
    }

}
