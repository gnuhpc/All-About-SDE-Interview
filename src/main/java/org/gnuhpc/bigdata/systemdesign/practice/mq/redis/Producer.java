package org.gnuhpc.bigdata.systemdesign.practice.mq.redis;

import java.util.List;

import redis.clients.jedis.Jedis;
import redis.clients.jedis.Transaction;

public class Producer {
    private final Jedis jedis;
    private final Topic topic;

    public Producer(final Jedis jedis, final String topic) {
        this.topic = new Topic("topic:" + topic, jedis);
        this.jedis = jedis;
    }

    public void publish(final String message) {
        publish(message, 0);
    }

    /**
     * @param message message
     * @param seconds expiry time
     */
    public void publish(String message, int seconds) {
        List<Object> exec;
        String lastMessageId;
        do {
            topic.watch();
            lastMessageId = topic.getNextMessageId();
            Transaction trans = topic.multi();
            String msgKey = topic.cat("message").cat(lastMessageId).key();
            trans.set(msgKey, message);
            trans.set(topic.key(), lastMessageId);
            if (seconds > 0)
                trans.expire(msgKey, seconds);
            exec = trans.exec();
        } while (exec == null);
    }
}