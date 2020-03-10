package org.gnuhpc.interview.concurrency.activeobject.jdk.activeobject;

public class ActiveObjectFactory {
    public static ActiveObject createActiveObject() {
        return new ActiveObjectImpl();
    }
}
