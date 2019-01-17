package org.gnuhpc.bigdata.designpattern.composite.composite;

import org.gnuhpc.bigdata.designpattern.composite.component.AbstractFile;

import java.util.ArrayList;

/**
 * Created by luisburgos on 18/07/15.
 */
public class Directory extends AbstractFile {

    private ArrayList<AbstractFile> files;

    public Directory (String name) {
        this.name = name;
        files = new ArrayList<>();
    }

    @Override
    public void add(AbstractFile file) {
        files.add(file);
    }

    @Override
    public void remove(AbstractFile file) {
        files.remove(file);
    }

    @Override
    public void showInfo() {
        System.out.print(ident.toString() + "* Directory: " + getName() + "\n");
        ident.append("   ");
        for(AbstractFile file : files){
            file.showInfo();
        }
        ident.setLength(ident.length() - 3);
    }


}
