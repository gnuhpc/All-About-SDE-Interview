package org.gnuhpc.bigdata.concurrency.activeobject.impl;

import org.gnuhpc.bigdata.concurrency.activeobject.impl.activeobject.ActivationQueue;
import org.gnuhpc.bigdata.concurrency.activeobject.impl.activeobject.MethodRequest;

class SchedulerThread extends Thread {
    private final ActivationQueue queue;

    public SchedulerThread(ActivationQueue queue) {
        this.queue = queue;
    }

    public void invoke(MethodRequest request) {
        queue.putRequest(request);
    }

    public void run() {
        while (true) {
            MethodRequest request = queue.takeRequest();
            request.execute();
        }
    }
}
