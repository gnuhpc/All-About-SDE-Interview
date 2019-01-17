package org.gnuhpc.bigdata.leetcode;

import java.util.*;

public class CloneGraph113 {
    class UndirectedGraphNode {
        int label;
        List<UndirectedGraphNode> neighbors;

        UndirectedGraphNode(int x) {
            label = x;
            neighbors = new ArrayList<>();
        }
    }

    /*
    Method1 :BFS 能够用 BFS 解决的问题，一定 不要用 DFS 去做！!
     */
    public UndirectedGraphNode cloneGraph(UndirectedGraphNode node) {
        if (node == null) {
            return node;
        }

        // use bfs algorithm to traverse the graph and get all nodes.
        ArrayList<UndirectedGraphNode> nodes = getNodes(node);

        // copy nodes, store the old->new mapping information in a hash map
        HashMap<UndirectedGraphNode, UndirectedGraphNode> mapping = new HashMap<>();
        for (UndirectedGraphNode n : nodes) {
            mapping.put(n, new UndirectedGraphNode(n.label));
        }

        // copy neighbors(edges)
        for (UndirectedGraphNode n : nodes) {
            UndirectedGraphNode newNode = mapping.get(n);
            for (UndirectedGraphNode neighbor : n.neighbors) {
                UndirectedGraphNode newNeighbor = mapping.get(neighbor);
                newNode.neighbors.add(newNeighbor);
            }
        }

        return mapping.get(node);
    }

    private ArrayList<UndirectedGraphNode> getNodes(UndirectedGraphNode node) {
        Queue<UndirectedGraphNode> queue = new LinkedList<>();
        HashSet<UndirectedGraphNode> set = new HashSet<>();

        queue.offer(node);
        set.add(node);
        while (!queue.isEmpty()) {
            UndirectedGraphNode head = queue.poll();
            for (UndirectedGraphNode neighbor : head.neighbors) {
                if(!set.contains(neighbor)){
                    set.add(neighbor);
                    queue.offer(neighbor);
                }
            }
        }

        return new ArrayList<>(set);
    }


    /*
    Method 2: DFS
     */

    HashMap<Integer, UndirectedGraphNode> map = new HashMap<>();

    public UndirectedGraphNode cloneGraph2(UndirectedGraphNode node) {
        if (node == null)
            return null;
        UndirectedGraphNode cloned = map.get(node.label);
        if (cloned != null)
            return cloned;
        cloned = new UndirectedGraphNode(node.label);

        map.put(cloned.label, cloned);
        for (UndirectedGraphNode neighbor : node.neighbors) {
            cloned.neighbors.add(cloneGraph(neighbor));
        }
        return cloned;
    }

}
