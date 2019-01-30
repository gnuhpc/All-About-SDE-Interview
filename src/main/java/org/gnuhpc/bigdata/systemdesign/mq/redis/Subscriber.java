package org.gnuhpc.bigdata.systemdesign.mq.redis;

import redis.clients.jedis.Jedis;
import redis.clients.jedis.Transaction;
import redis.clients.jedis.Tuple;

import java.util.List;
import java.util.Map;
import java.util.Set;

public class Subscriber {
    private static final String COLON = ":";
    private StringBuilder sb;
    private String key;
    private Jedis jedis;

    public Subscriber(String key, Jedis jedis) {
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

    public Double zscore(String member) {
        Jedis jedis = getResource();
        Double zadd = jedis.zscore(key(), member);
        returnResource(jedis);
        return zadd;
    }

    public Double zincrby(double score, String member) {
        Jedis jedis = getResource();
        Double zincrby = jedis.zincrby(key(), score, member);
        returnResource(jedis);
        return zincrby;
    }

    private void returnResource(final Jedis jedis) {
    }

    private Jedis getResource() {
        return jedis;
    }

    public String watch() {
        Jedis jedis = getResource();
        String result = jedis.watch(key());
        returnResource(jedis);
        return result;
    }

    public Long publish(String message) {
        Jedis jedis = getResource();
        Long result = jedis.publish(key(), message);
        returnResource(jedis);
        return result;
    }
}
