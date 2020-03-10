package org.gnuhpc.interview.concurrency.activeobject.impl.activeobject;

public class ActiveObjectFactory {
    public static ActiveObject createActiveObject() {
        Servant servant = new Servant();
        ActivationQueue queue = new ActivationQueue();//活推在哪
        SchedulerThread scheduler = new SchedulerThread(queue);//干活的
        Proxy proxy = new Proxy(scheduler, servant);//接活
        scheduler.start();
        return proxy;
    }
}
