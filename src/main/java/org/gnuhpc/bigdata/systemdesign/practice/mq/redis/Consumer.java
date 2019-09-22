package org.gnuhpc.bigdata.systemdesign.practice.mq.redis;

import redis.clients.jedis.Jedis;

public class Consumer {
    private Topic topic;
    private Subscriber subscriber;
    private String id;

    public Consumer(final Jedis jedis, final String id, final String topic) {
        this.topic = new Topic("topic:" + topic, jedis);
        this.subscriber = new Subscriber(this.topic.cat("subscribers").key(), jedis);
        this.id = id;
    }

    private void waitForMessages() {
        try {
            Thread.sleep(500);
        } catch (InterruptedException ignored) {
        }
    }

    public void consume(Callback callback) {
        while (true) {
            String message = readUntilEnd();
            if (message != null)
                callback.onMessage(message);
            else
                waitForMessages();
        }
    }

    public String consume() {
        return readUntilEnd();
    }

    private String readUntilEnd() {
        while (unreadMessages() > 0) {
            String message = read();
            commit();
            if (message != null)
                return message;
        }

        return null;
    }

    private void commit() {
        subscriber.zincrby(1, id);
    }

    private int getLastReadMessageID() {
        Double lastMessageRead = subscriber.zscore(id);
        if (lastMessageRead == null) {
            return 0;
        }
        return lastMessageRead.intValue();
    }

    private int getTopicSize() {
        return topic.getEndOffSet();
    }

    //Peek the first without commit
    public String read() {
        String nextReadMessageID = (getLastReadMessageID() + 1) + "";
        return topic.cat("message").cat(nextReadMessageID).getMsgContent();
    }

    public int unreadMessages() {
        return getTopicSize() - getLastReadMessageID();
    }
}