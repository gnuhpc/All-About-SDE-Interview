package org.gnuhpc.bigdata.concurrency.activeobject.jdk;


import org.gnuhpc.bigdata.concurrency.activeobject.jdk.activeobject.ActiveObject;
import org.gnuhpc.bigdata.concurrency.activeobject.jdk.activeobject.ActiveObjectFactory;

public class Main {
    public static void main(String[] args) {
        ActiveObject activeObject = ActiveObjectFactory.createActiveObject();
        try {
            new AddClientThread("Diana", activeObject).start();
            Thread.sleep(5000);
        }
        catch (InterruptedException e) {
        }
        finally {
            System.out.println("*** shutdown ***");
            activeObject.shutdown();
        }
    }
}
