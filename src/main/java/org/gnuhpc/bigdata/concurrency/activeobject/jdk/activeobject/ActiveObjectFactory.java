package org.gnuhpc.bigdata.concurrency.activeobject.jdk.activeobject;

public class ActiveObjectFactory {
    public static ActiveObject createActiveObject() {
        return new ActiveObjectImpl();
    }
}
