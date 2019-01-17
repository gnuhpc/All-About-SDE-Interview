package org.gnuhpc.bigdata.designpattern.composite.component;

/**
 * Created by luisburgos on 18/07/15.
 */
public abstract class AbstractFile {

    protected String name;
    protected static StringBuffer ident = new StringBuffer();

    //注意，不是abstract方法
    public void add(AbstractFile component) {
        throw new UnsupportedOperationException();
    }

    public void remove(AbstractFile component) {
        throw new UnsupportedOperationException();
    }

    public String getName() {
        return name;
    }

    public void showInfo() {
        throw new UnsupportedOperationException();
    }

}
