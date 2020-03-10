package org.gnuhpc.interview.leetcode.solutions;

import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;
import redis.clients.jedis.JedisPoolConfig;

import java.time.Duration;

public class LRUCache146_4 {
    private final JedisPool jedisPool;
    private int capacity;

    private static final String ZSETKEY = "MyLRU";
    private static final String HASHKEY = "LRUHASH";

    public LRUCache146_4(int capacity) {
        this.capacity = capacity;
        final JedisPoolConfig poolConfig = buildPoolConfig();
        this.jedisPool = new JedisPool(poolConfig, "localhost");
        //For demonstration purpose
        this.jedisPool.getResource().flushAll();
    }

    private JedisPoolConfig buildPoolConfig() {
        final JedisPoolConfig poolConfig = new JedisPoolConfig();
        poolConfig.setMaxTotal(128);
        poolConfig.setMaxIdle(128);
        poolConfig.setMinIdle(16);
        poolConfig.setTestOnBorrow(true);
        poolConfig.setTestOnReturn(true);
        poolConfig.setTestWhileIdle(true);
        poolConfig.setMinEvictableIdleTimeMillis(Duration.ofSeconds(60).toMillis());
        poolConfig.setTimeBetweenEvictionRunsMillis(Duration.ofSeconds(30).toMillis());
        poolConfig.setNumTestsPerEvictionRun(3);
        poolConfig.setBlockWhenExhausted(true);
        return poolConfig;
    }

    public int get(int key) {
        String strKey = String.valueOf(key);
        try (Jedis jedis = jedisPool.getResource()) {
            if (!jedis.hexists(HASHKEY, strKey)) {
                return -1;
            }
            String mruKey = jedis.zrevrange(ZSETKEY, 0, 0).iterator().next();
            double highestScore = jedis.zscore(ZSETKEY, mruKey);
            jedis.zadd(ZSETKEY, highestScore + 1, strKey);
            return Integer.parseInt(jedis.hget(HASHKEY, strKey));
        }
    }

    public void put(int key, int value) {
        String strKey = String.valueOf(key);
        try (Jedis jedis = jedisPool.getResource()) {
            //get will update the use frequency;
            if (get(key) != -1) {
                jedis.hset(HASHKEY, strKey, String.valueOf(value));
                return;
            }
            if (jedis.hlen(HASHKEY) == capacity) {
                String lruKey = jedis.zrange(ZSETKEY, 0, 0).iterator().next();
                jedis.hdel(HASHKEY, lruKey);
                jedis.zrem(ZSETKEY, lruKey);
            }

            jedis.hset(HASHKEY, strKey, String.valueOf(value));
            double highestScore = 0;
            if (jedis.zcard(ZSETKEY) != 0) {
                String mruKey = jedis.zrevrange(ZSETKEY, 0, 0).iterator().next();
                highestScore = jedis.zscore(ZSETKEY, mruKey);
            }
            jedis.zadd(ZSETKEY, highestScore + 1, strKey);
        }
    }

    public static void main(String[] args) {
        LRUCache146_3 cache = new LRUCache146_3(2 /* 缓存容量 */);

        cache.put(1, 1);
        cache.put(2, 2);
        System.out.println(cache.get(1));       // 返回  1
        cache.put(3, 3);    // 该操作会使得密钥 2 作废
        System.out.println(cache.get(2));       // 返回 -1 (未找到)
        cache.put(4, 4);    // 该操作会使得密钥 1 作废
        System.out.println(cache.get(1));       // 返回 -1 (未找到)
        System.out.println(cache.get(3));       // 返回  3
        System.out.println(cache.get(4));       // 返回  4
    }
}
