package org.gnuhpc.interview.systemdesign.practice.simplezk.callback;

public interface CallBack {
    void onChange(String path, String newValue);
}
