package org.gnuhpc.interview.datastructure.unionfind;

import lombok.ToString;

import java.util.HashMap;
import java.util.Map;

public class StringRatioUnionFind {
    @ToString
    public static class Node {
        public String parent;
        public double ratio;

        public Node(String parent, double ratio) {
            this.parent = parent;
            this.ratio = ratio;
        }
    }

    private Map<String, Node> nodeMap = new HashMap<>();

    public Node find(String s) {
        if (!nodeMap.containsKey(s)) return null;
        Node n = nodeMap.get(s);

        if (!n.parent.equals(s)) {
            Node p = find(n.parent);
            n.parent = p.parent;
            n.ratio *= p.ratio;
        }

        return n;
    }

    public void union(String str1, String str2, double ratio) {
        boolean hasNode1 = nodeMap.containsKey(str1);
        boolean hasNode2 = nodeMap.containsKey(str2);

        if (!hasNode1 && !hasNode2) {
            nodeMap.put(str1, new Node(str2, ratio));
            nodeMap.put(str2, new Node(str2, 1.0)); //A/B=ratio， 此时设置B = 1 ，则A = ratio
        } else if (!hasNode2) { //都是以已知节点比例进行记录的
            nodeMap.put(str2, new Node(str1, 1.0 / ratio));
        } else if (!hasNode1) {
            nodeMap.put(str1, new Node(str2, ratio));
        } else {
            Node rS = find(str1);
            Node rP = find(str2);
            rS.parent = rP.parent;
            rS.ratio = ratio / rS.ratio * rP.ratio;
        }
    }
}
