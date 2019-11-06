package org.gnuhpc.bigdata.leetcode;

import org.junit.Test;
import scala.collection.concurrent.CNode;

import java.util.*;

public class FileSystem588 {
    enum Type {
        F, D
    }

    class Inode {
        private final String       path;
        private final Type         type;
        private Map<String, Inode> children;
        private String             content;

        public Inode(String path, Type type) {
            this.path = path;
            this.type = type;
            if (type == Type.D) {
                this.children = new HashMap<>();
            } else {
                this.content = "";
            }
        }
    }

    Inode root;

    public FileSystem588() {
        root = new Inode("", Type.D);
    }

    public List<String> ls(String path) {
        List<String> res = new ArrayList<>();
        String[] parsedPath = path.split("/");
        if (parsedPath.length == 0) res.addAll(root.children.keySet());
        else {
            Inode node = root;
            for (int i = 1; i < parsedPath.length; i++) {
                String p = parsedPath[i];
                if (node.children.containsKey(p)) {
                    node = node.children.get(p);
                    if (i == parsedPath.length - 1) {
                        if (node.type == Type.F) res.add(node.path);
                        else res.addAll(node.children.keySet());
                    }
                }  else return null;
            }
        }

        Collections.sort(res);
        return res;
    }

    public void mkdir(String path) {
        String[] parsedPath = path.split("/");
        if (parsedPath.length == 0) return;
        Inode node = root;
        for (int i = 1; i < parsedPath.length; i++) {
            Inode nextDir;
            if (node.children.containsKey(parsedPath[i])){
                nextDir = node.children.get(parsedPath[i]);
            } else {
                nextDir = new Inode(parsedPath[i], Type.D);
            }
            node.children.put(parsedPath[i], nextDir);
            node = nextDir;
        }
    }

    public void addContentToFile(String filePath, String content) {
        String[] parsedPath = filePath.split("/");
        if (parsedPath.length == 0) return;
        Inode node = root;
        for (int i = 1; i < parsedPath.length ; i++) {
            if (node.children.containsKey(parsedPath[i])){
                node = node.children.get(parsedPath[i]);

                if (i == parsedPath.length - 1){
                    node.content += content;
                }
            }else {
                if (i == parsedPath.length - 1) {
                    Inode newNode = new Inode(parsedPath[i], Type.F);
                    newNode.content = content;
                    node.children.put(parsedPath[i], newNode);
                }
            }
        }
    }

    public String readContentFromFile(String filePath) {
        String[] parsedPath = filePath.split("/");
        if (parsedPath.length == 0) return "";
        Inode node = root;
        for (int i = 1; i < parsedPath.length ; i++) {
            if (node.children.containsKey(parsedPath[i])){
                node = node.children.get(parsedPath[i]);

                if (i == parsedPath.length - 1){
                    if (node.type == Type.F) return node.content;
                    else return "";
                }
            }
        }

        return "";
    }

    @Test
    public void test() {
        FileSystem588 fs = new FileSystem588();
        System.out.println(fs.ls("/"));
        fs.mkdir("/a");
        fs.mkdir("/a/b/c");
        fs.mkdir("/a/f/k");
        fs.mkdir("/a/g/c");
        System.out.println(fs.ls("/a"));
        System.out.println(fs.ls("/a/b"));
        System.out.println(fs.ls("/a/d/e"));

        fs.addContentToFile("/a/b/d", "hello");
        System.out.println(fs.readContentFromFile("/a/b/d"));
    }
}
