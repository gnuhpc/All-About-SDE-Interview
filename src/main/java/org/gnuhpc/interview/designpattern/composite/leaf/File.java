package org.gnuhpc.interview.designpattern.composite.leaf;

import org.gnuhpc.interview.designpattern.composite.component.AbstractFile;

/**
 * Created by luisburgos on 18/07/15.
 */
public class File extends AbstractFile {

    public File(String name) {
        this.name = name;
    }

    @Override
    public void showInfo() {
        System.out.print(ident.toString() + "-Simple AbstractFile: " + getName() + "\n");
    }
}
