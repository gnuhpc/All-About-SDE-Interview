package org.gnuhpc.bigdata.concurrency.future.jdk;

import java.util.concurrent.ExecutionException;

public interface Data {
    public abstract String getContent() throws ExecutionException;
}
