package org.gnuhpc.bigdata.systemdesign.practice.objectpool.core;


import java.util.concurrent.*;

public class BoundedBlockingPool<T> extends AbstractPool<T> implements BlockingPool<T> {

    private int size;
    private BlockingQueue<T> objects;
    private Validator validator;
    private ObjectFactory<T> objectFactory;
    private ExecutorService executor =
            Executors.newCachedThreadPool();
    private volatile boolean shutdownCalled;

    public BoundedBlockingPool(
            int size,
            Validator validator,
            ObjectFactory objectFactory) {
        super();
        this.objectFactory = objectFactory;
        this.size = size;
        this.validator = validator;
        objects = new LinkedBlockingQueue<>(size);
        initializeObjects();
        shutdownCalled = false;
    }

    public T get(long timeOut, TimeUnit unit) {
        if (!shutdownCalled) {
            T t = null;
            try {
                t = objects.poll(timeOut, unit);
                return t;
            } catch (InterruptedException ie) {
                Thread.currentThread().interrupt();
            }
            return t;
        }
        throw new IllegalStateException("Object pool is already shutdown");
    }

    public T get() {
        if (!shutdownCalled) {
            T t = null;
            try {
                t = objects.take();
            } catch (InterruptedException ie) {
                Thread.currentThread().interrupt();
            }
            return t;
        }

        throw new IllegalStateException(
                "Object pool is already shutdown");
    }

    public void shutdown() {
        shutdownCalled = true;
        executor.shutdownNow();
        clearResources();
    }

    private void clearResources() {
        for (T t : objects) {
            validator.invalidate(t);
        }
    }

    @Override
    protected void returnToPool(T t) {
        if (validator.isValid(t)) {
            executor.submit(new ObjectReturner(objects, t));
        }
    }

    @Override
    protected void handleInvalidReturn(T t) {
    }

    @Override
    protected boolean isValid(T t) {
        return validator.isValid(t);
    }

    private void initializeObjects() {
        for (int i = 0; i < size; i++) {
            objects.add(objectFactory.createNew());
        }
    }

    /*
    唯一比较有意思的方法就是returnToPool。因为内部的存储是一个LinkedBlockingQueue实现的，
    如果我们直接把返回的对象扔进去的话，如果队列已满可能会阻塞住客户端。
    不过我们不希望客户端因为把对象放回池里这么个普通的方法就阻塞住了。
    所以我们把最终将对象插入到队列里的任务作为一个异步的的任务提交给一个Executor来执行，
    以便让客户端线程能立即返回。
     */
    private class ObjectReturner<E>
            implements Callable {
        private BlockingQueue queue;
        private E e;

        public ObjectReturner(BlockingQueue queue, E e) {
            this.queue = queue;
            this.e = e;
        }

        public Void call() {
            while (true) {
                try {
                    queue.put(e);
                    break;
                } catch (InterruptedException ie) {
                    Thread.currentThread().interrupt();
                }
            }
            return null;
        }

    }
}
