package org.gnuhpc.bigdata.systemdesign.practice.objectpool.core;


public interface Pool<T> {
    /**
     * Returns an instance from the pool.
     * The call may be a blocking one or a non-blocking one
     * and that is determined by the internal implementation.
     * <p>
     * If the call is a blocking call,
     * the call returns immediately with a valid object
     * if available, else the thread is made to wait
     * until an object becomes available.
     * In case of a blocking call,
     * it is advised that clients react
     * to {@link InterruptedException} which might be thrown
     * when the thread waits for an object to become available.
     * <p>
     * If the call is a non-blocking one,
     * the call returns immediately irrespective of
     * whether an object is available or not.
     * If any object is available the call returns it
     * else the call returns < code >null< /code >.
     * <p>
     * The validity of the objects are determined using the
     * {@link Validator} interface, such that
     * an object < code >o< /code > is valid if
     * < code > Validator.isValid(o) == true < /code >.
     *
     * @return T one of the pooled objects.
     */
    T get();

    /**
     * Releases the object and puts it back to the pool.
     * <p>
     * The mechanism of putting the object back to the pool is
     * generally asynchronous,
     * however future implementations might differ.
     *
     * @param t the object to return to the pool
     */

    void release(T t);

    /**
     * Shuts down the pool. In essence this call will not
     * accept any more requests
     * and will release all resources.
     * Releasing resources are done
     * via the < code >invalidate()< /code >
     * method of the {@link Validator} interface.
     */

    void shutdown();

    /*
    上面这个接口定义了一个检验对象的方法，以及一个把对象置为无效的方法。
    当准备废弃一个对象并清理内存的时候，invalidate方法就派上用场了。
    值得注意的是这个接口本身没有任何意义，只有当它在对象池里使用的时候才有意义，
    所以我们把这个接口定义到Pool接口里面。这和Java集合库里的Map和Map.Entry是一样的
     */
    public static interface Validator <T>
    {
        /**
         * Checks whether the object is valid.
         *
         * @param t the object to check.
         *
         * @return <code>true</code>
         * if the object is valid else <code>false</code>.
         */
        public boolean isValid(T t);

        /**
         * Performs any cleanup activities
         * before discarding the object.
         * For example before discarding
         * database connection objects,
         * the pool will want to close the connections.
         * This is done via the
         * <code>invalidate()</code> method.
         *
         * @param t the object to cleanup
         */

        public void invalidate(T t);
    }
}
