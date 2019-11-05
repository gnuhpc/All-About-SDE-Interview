package org.gnuhpc.bigdata.leetcode;

import org.junit.Test;

import java.util.*;

public class FileSystem588 {
    enum Type {
        F, D
    }

    class INode {
        private final String path;
        private final Type type;
        private Map<String, INode> children;
        private String content;

        public INode(String path, Type type) {
            this.path = path;
            this.type = type;
            if (type == Type.D) {
                this.children = new HashMap<>();
            } else {
                this.content = "";
            }
        }
    }

    INode root;

    public FileSystem588() {
        root = new INode("", Type.D);
    }

    public List<String> ls(String path) {
        List<String> res = new ArrayList<>();
        String[] parsedPath = path.split("/");
        if (parsedPath.length == 0) res.addAll(root.children.keySet());
        else {
            INode node = root;
            for (int i = 1; i < parsedPath.length; i++) {
                String p = parsedPath[i];
                if (node.children.containsKey(p)) {
                    node = node.children.get(p);
                    if (i == parsedPath.length - 1) {
                        if (node.type == Type.F) res.add(node.path);
                        else res.addAll(node.children.keySet());
                    }
                } else return null;
            }
        }

        Collections.sort(res);
        return res;
    }

    public void mkdir(String path) {
        String[] parsedPath = path.split("/");
        if (parsedPath.length == 0) return;
        INode node = root;
        for (int i = 1; i < parsedPath.length; i++) {
            INode nextDir;
            if (node.children.containsKey(parsedPath[i])){
                nextDir = node.children.get(parsedPath[i]);
            } else {
                nextDir = new INode(parsedPath[i], Type.D);
            }
            node.children.put(parsedPath[i], nextDir);
            node = nextDir;
        }
    }

    public void addContentToFile(String filePath, String content) {

    }

    public String readContentFromFile(String filePath) {
        return null;
    }

    @Test
    public void test() {
        FileSystem588 fs = new FileSystem588();
        System.out.println(fs.ls("/"));
        fs.mkdir("/a/d/e");
        fs.mkdir("/a/b/c");
        fs.mkdir("/a/f/k");
        fs.mkdir("/a/g/c");
        System.out.println(fs.ls("/a"));
        System.out.println(fs.ls("/a/b"));
        System.out.println(fs.ls("/a/d/e"));
    }
}
