package org.gnuhpc.interview.designpattern.composite;

import org.gnuhpc.interview.designpattern.composite.component.AbstractFile;
import org.gnuhpc.interview.designpattern.composite.composite.Directory;
import org.gnuhpc.interview.designpattern.composite.leaf.File;

/*
 解决的是树形操作中的遍历问题，树中的所有节点（不管它是不是叶子节点）都实现一个共同的接口，这个接口定义了共同的方法，
 这样在递归遍历的时候就可以对同一个方法进行调用了。

 */
public class MainApp {

    public static void main(String[] args) {

        //Dummy linux file system.

        AbstractFile home = new Directory("home");
        AbstractFile opt = new Directory("opt");
        AbstractFile usr = new Directory("usr");

        AbstractFile root = new Directory("find");

        root.add(home);
        root.add(opt);
        root.add(usr);

        usr.add(new File("bin"));
        usr.add(new File("lib"));

        opt.add(new File("google"));
        opt.add(new File("idea"));
        opt.add(new File("spotify"));

        home.add(new File("luisburgos"));

        root.showInfo();
    }
}
