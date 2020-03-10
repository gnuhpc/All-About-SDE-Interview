package org.gnuhpc.interview.systemdesign.practice.mq.redis;

import org.junit.Before;
import org.junit.Test;
import redis.clients.jedis.Jedis;

import java.io.IOException;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;

public class ProducerConsumerTest {
    @Before
    public void setUp() throws IOException {
        Jedis jedis = new Jedis("localhost");
        jedis.flushAll();
        jedis.disconnect();
    }

    @Test
    public void publishAndConsume() {
        Producer p = new Producer(new Jedis("localhost"), "foo");
        Consumer c = new Consumer(new Jedis("localhost"), "a subscriber", "foo");
        Consumer c1 = new Consumer(new Jedis("localhost"), "a subscriber 2", "foo");

        p.publish("hello world1!");
        p.publish("hello world2!");
        p.publish("hello world3!");
        assertEquals("hello world1!", c.consume());
        assertEquals(2, c.unreadMessages());
        assertEquals("hello world1!", c1.consume());
        assertEquals("hello world2!", c1.consume());
        assertEquals(1, c1.unreadMessages());
    }

    @Test
    public void publishAndRead() {
        Producer p = new Producer(new Jedis("localhost"), "foo");
        Consumer c = new Consumer(new Jedis("localhost"), "a subscriber", "foo");

        p.publish("hello world!");
        assertEquals("hello world!", c.read());
        assertEquals("hello world!", c.read());
    }

    @Test
    public void unreadMessages() {
        Producer p = new Producer(new Jedis("localhost"), "foo");
        Consumer c = new Consumer(new Jedis("localhost"), "a subscriber", "foo");

        assertEquals(0, c.unreadMessages());
        p.publish("hello world!");
        assertEquals(1, c.unreadMessages());
        p.publish("hello world!");
        assertEquals(2, c.unreadMessages());
        c.consume();
        assertEquals(1, c.unreadMessages());
    }

    @Test
    public void raceConditionsWhenPublishing() throws InterruptedException {
        Producer slow = new SlowProducer(new Jedis("localhost"), "foo");
        Consumer c = new Consumer(new Jedis("localhost"), "a subscriber", "foo");

        slow.publish("a");
        Thread t = new Thread(() -> {
            Producer fast = new Producer(new Jedis("localhost"), "foo");
            fast.publish("b");
        });
        t.start();
        t.join();

        assertEquals("a", c.consume());
        assertEquals("b", c.consume());
    }


    class SlowProducer extends Producer {
        private long sleep;

        public SlowProducer(Jedis jedis, String topic) {
            this(jedis, topic, 500L);
        }

        public SlowProducer(Jedis jedis, String topic, long sleep) {
            super(jedis, topic);
            this.sleep = sleep;
        }

        @Override
        public void publish(String message) {
            sleep(sleep);
            super.publish(message);
        }
    }

    class SlowConsumer extends Consumer {
        private long sleep;

        public SlowConsumer(Jedis jedis, String id, String topic, long sleep) {
            super(jedis, id, topic);
            this.sleep = sleep;
        }

        @Override
        public String consume() {
            sleep(sleep);
            return super.consume();
        }

        @Override
        public void consume(Callback callback) {
            sleep(sleep);
            super.consume(callback);
        }
    }

    @Test
    public void expiredMessages() throws InterruptedException {
        Consumer c = new SlowConsumer(new Jedis("localhost"), "a consumer",
                "foo", 2000L);
        Producer p = new Producer(new Jedis("localhost"), "foo");
        p.publish("un mensaje", 1);
        assertNull(c.consume());
    }

    private void sleep(long sleep) {
        try {
            Thread.sleep(sleep);
        } catch (InterruptedException ignored) {
        }
    }

    @Test
    public void firstMessageExpired() throws InterruptedException {
        Consumer c = new SlowConsumer(new Jedis("localhost"), "a consumer",
                "foo", 2000L);
        Producer p = new Producer(new Jedis("localhost"), "foo");
        p.publish("1", 1);
        p.publish("2", 0);

        assertEquals("2", c.consume());
    }
}