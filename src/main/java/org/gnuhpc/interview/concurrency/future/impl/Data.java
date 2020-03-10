package org.gnuhpc.interview.concurrency.future.impl;

import java.util.concurrent.ExecutionException;

public interface Data {
    public abstract String getContent() throws ExecutionException;
}
