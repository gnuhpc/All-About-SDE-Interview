package org.gnuhpc.bigdata.systemdesign.simplezk.callback;

public interface CallBack {
    void onChange(String path, String newValue);
}
