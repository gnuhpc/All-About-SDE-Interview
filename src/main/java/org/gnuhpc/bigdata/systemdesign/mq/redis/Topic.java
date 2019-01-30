package org.gnuhpc.bigdata.systemdesign.mq.redis;

import redis.clients.jedis.Jedis;
import redis.clients.jedis.Transaction;

public class Topic {
    private static final String COLON = ":";
    private StringBuilder sb;
    private String key;
    private Jedis jedis;

    public Topic(String key, Jedis jedis) {
        this.key = key;
        this.jedis = jedis;
    }

    public String key() {
        prefix();
        String generatedKey = sb.toString();
        generatedKey = generatedKey.substring(0, generatedKey.length() - 1);
        sb = null;
        return generatedKey;
    }

    private void prefix() {
        if (sb == null) {
            sb = new StringBuilder();
            sb.append(key);
            sb.append(COLON);
        }
    }

    public Topic cat(String field) {
        prefix();
        sb.append(field);
        sb.append(COLON);
        return this;
    }

    // Redis Common Operations
    public String setLastMsgID(String value) {
        Jedis jedis = getResource();
        String set = jedis.set(key(), value);
        returnResource(jedis);
        return set;
    }

    public int getEndOffSet() {
        Jedis jedis = getResource();
        String offsetStr = jedis.get(key());
        returnResource(jedis);
        if (offsetStr != null)
            return Integer.valueOf(offsetStr);
        else
            return 0;
    }


    private void returnResource(final Jedis jedis) {
    }

    private Jedis getResource() {
        return jedis;
    }


    public Long publish(String message) {
        Jedis jedis = getResource();
        Long result = jedis.publish(key(), message);
        returnResource(jedis);
        return result;
    }

    public String getMsgContent() {
        Jedis jedis = getResource();
        String result = jedis.get(key());
        returnResource(jedis);
        return result;
    }

    public String watch() {
        Jedis jedis = getResource();
        String result = jedis.watch(key());
        returnResource(jedis);
        return result;
    }

    public Transaction multi() {
        Jedis jedis = getResource();
        Transaction multi = jedis.multi();
        returnResource(jedis);
        return multi;
    }

    public String getNextMessageId() {
        return (getEndOffSet() + 1) + "";
    }
}
