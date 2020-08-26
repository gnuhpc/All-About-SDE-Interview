package org.gnuhpc.interview.datastructure.tree.multitree;

import java.util.ArrayList;
import java.util.List;

public class MultiTreeNode {

    private String name;
    private MultiTreeNode parent;
    private List<MultiTreeNode> children = new ArrayList<>();

    public MultiTreeNode(String name, MultiTreeNode parent) {
        this.name = name;
        this.parent = parent;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public MultiTreeNode getParent() {
        return parent;
    }

    public void setParent(MultiTreeNode parent) {
        this.parent = parent;
    }

    public List<MultiTreeNode> getChildren() {
        return children;
    }

    public void addChild(MultiTreeNode child) {
        children.add(child);
    }
}
