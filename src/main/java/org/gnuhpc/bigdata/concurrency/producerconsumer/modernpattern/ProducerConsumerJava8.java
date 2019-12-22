package org.gnuhpc.bigdata.concurrency.producerconsumer.modernpattern;

/**
 * Created by gnuhpc on 2017/6/22.
 */

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;
import java.util.function.Consumer;
import java.util.function.Supplier;

import static java.lang.System.out;
import static java.util.concurrent.TimeUnit.MILLISECONDS;

/**
 * Starts 2 threads, a producer and a consumer
 * <br>
 * Both threads share the same BlockingQueue
 *
 * @author Djallal Serradji
 */
public class ProducerConsumerJava8 {

    private static final int MSG_NBR = 10;

    private final BlockingQueue<String> queue = new ArrayBlockingQueue<>(10);

    public static void main(String[] args) {
        new ProducerConsumerJava8().startEngine();
    }

    public void startEngine() {
        startProducer();
        startConsumer();
    }

    // Producer thread
    private void startProducer() {

        final MyProducer<String> myProducer = new MyProducer<>(queue);
        final Supplier<String> supplier = () -> "Hello World";
        new Thread(() -> {
            for (int i = 0; i < MSG_NBR; i++) {
                myProducer.produce(supplier);
            }
        }).start();
    }

    // Consumer thread
    private void startConsumer() {

        final MyConsumer<String> myConsumer = new MyConsumer<>(queue);
        final Consumer<String> consumer = (s) -> out.println("Consumed message: " + s);
        new Thread(() -> {
            for (int i = 0; i < MSG_NBR; i++)
                myConsumer.consume(consumer);
        }).start();
    }

    static class MyProducer<T> {

        private BlockingQueue<T> queue;

        public MyProducer(BlockingQueue<T> queue) {
            this.queue = queue;
        }

        /**
         * Insert the supplied object in the queue
         *
         * @param supplier Is responsible for supplying the object that will be put
         *                 in the queue
         */
        public void produce(Supplier<T> supplier) {
            final T msg = supplier.get();
            try {
                queue.put(msg);
                out.println("Added message: " + msg);

                // Simulate a long running process
                MILLISECONDS.sleep(900);

            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }
    }

    static class MyConsumer<T> {

        private BlockingQueue<T> queue;

        public MyConsumer(BlockingQueue<T> queue) {
            this.queue = queue;
        }

        /**
         * Retrieves an object from the head of the queue and passes it to the
         * consumer
         *
         * @param consumer Contains the logic on what to do with the retrieved object
         */
        public void consume(Consumer<T> consumer) {
            try {
                consumer.accept(queue.take());

                // Simulate a long running process
                MILLISECONDS.sleep(1250);

            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }
    }
}
