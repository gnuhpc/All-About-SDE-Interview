package org.gnuhpc.bigdata.concurrency.activeobject.impl.activeobject;

public abstract class MethodRequest<T> {
    protected final Servant         servant;
    protected final FutureResult<T> future;

    protected MethodRequest(Servant servant, FutureResult<T> future) {
        this.servant = servant;
        this.future = future;
    }

    public abstract void execute();
}
