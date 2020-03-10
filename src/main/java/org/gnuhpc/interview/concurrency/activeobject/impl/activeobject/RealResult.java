package org.gnuhpc.interview.concurrency.activeobject.impl.activeobject;

class RealResult<T> extends Result<T> {
    private final T resultValue;

    public RealResult(T resultValue) {
        this.resultValue = resultValue;
    }

    public T getResultValue() {
        return resultValue;
    }
}
