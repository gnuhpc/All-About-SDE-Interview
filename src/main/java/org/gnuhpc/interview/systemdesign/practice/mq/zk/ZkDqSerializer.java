package org.gnuhpc.interview.systemdesign.practice.mq.zk;

import org.apache.curator.framework.recipes.queue.QueueSerializer;

public class ZkDqSerializer implements QueueSerializer<ZkDqWork> {

    public ZkDqWork deserialize(byte[] buffer) {
        String work = new String(buffer);
        return new ZkDqWork(work);
    }

    public byte[] serialize(ZkDqWork work) {
        return work.toString().getBytes();
    }

}
