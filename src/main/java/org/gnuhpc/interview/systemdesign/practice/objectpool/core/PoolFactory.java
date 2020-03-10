package org.gnuhpc.interview.systemdesign.practice.objectpool.core;

public class PoolFactory {
    private PoolFactory() {
    }

    /**
     * Creates a and returns a new object pool,
     * that is an implementation of the {@link BlockingPool},
     * whose size is limited by
     * the <tt> size </tt> parameter.
     *
     * @param size      the number of objects in the pool.
     * @param factory   the factory to create new objects.
     * @param validator the validator to
     *                  validate the re-usability of returned objects.
     * @return a blocking object pool
     * bounded by <tt> size </tt>
     */
    public static <T> Pool<T> newBoundedBlockingPool(
            int size,
            ObjectFactory<T> factory,
            Pool.Validator<T> validator) {
        return new BoundedBlockingPool<>(
                size,
                validator,
                factory);
    }

    /*
     * Creates a and returns a new object pool,
     * that is an implementation of the {@link Pool}
     * whose size is limited
     * by the <tt> size </tt> parameter.
     *
     * @param size the number of objects in the pool.
     * @param factory the factory to create new objects.
     * @param validator the validator to validate
     * the re-usability of returned objects.
     *
     * @return an object pool bounded by <tt> size </tt>
     */
    public static <T> Pool<T> newBoundedNonBlockingPool(
            int size,
            ObjectFactory<T> factory,
            Pool.Validator<T> validator) {
        return new BoundedNonBlockingPool<T>(size, validator, factory);
    }
}
