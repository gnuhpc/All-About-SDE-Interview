package org.gnuhpc.interview.concurrency.activeobject.impl;


import org.gnuhpc.interview.concurrency.activeobject.impl.activeobject.ActiveObject;
import org.gnuhpc.interview.concurrency.activeobject.impl.activeobject.ActiveObjectFactory;

public class Main {
    public static void main(String[] args) {
        ActiveObject activeObject = ActiveObjectFactory.createActiveObject();
        new AddClientThread("Diana", activeObject).start();
    }
}
