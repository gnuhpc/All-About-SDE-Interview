package org.gnuhpc.interview.leetcode.solutions;

import java.util.*;

public class CloneGraph133 {
    class UndirectedGraphNode {
        int label;
        List<UndirectedGraphNode> neighbors;

        UndirectedGraphNode(int x) {
            label = x;
            neighbors = new ArrayList<>();
        }
    }


    /*
    Method 1: DFS
     */

    HashMap<Integer, UndirectedGraphNode> map = new HashMap<>();

    public UndirectedGraphNode cloneGraph(UndirectedGraphNode node) {
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

  /*  public Node cloneGraph(Node node) {
        if(node == null) return null ;
        HashMap<Node,Node> hm = new HashMap<>();
        Node head = new Node(node.val);
        hm.put(node,head);
        dfs(hm,node);
        return head ;
    }
    public void dfs(HashMap<Node,Node> hm , Node node)
    {
        if(node == null)
            return ;
        for(Node aneighbor : node.neighbors)
        {
            if(!hm.containsKey(aneighbor))
            {
                Node nd = new Node(aneighbor.val);
                hm.put(aneighbor,nd);
                dfs(hm,aneighbor);
            }
            hm.get(node).neighbors.add(hm.get(aneighbor));
        }
    }
*/
    /*
    Method2 : BFS
     */

    public UndirectedGraphNode cloneGraph2(UndirectedGraphNode node) {
        if (node == null) return null;

        UndirectedGraphNode newNode = new UndirectedGraphNode(node.label); //new node for return
        Map<Integer, UndirectedGraphNode> map = new HashMap<>(); //store visited nodes

        map.put(newNode.label, newNode); //add first node to HashMap

        Queue<UndirectedGraphNode> queue = new LinkedList<>(); //to store **original** nodes need to be visited
        queue.offer(node); //add first **original** node to queue

        while (!queue.isEmpty()) { //if more nodes need to be visited
            UndirectedGraphNode n = queue.poll(); //search first node in the queue
            for (UndirectedGraphNode neighbor : n.neighbors) {
                if (!map.containsKey(neighbor.label)) { //add to map and queue if this node hasn't been searched before
                    map.put(neighbor.label, new UndirectedGraphNode(neighbor.label));
                    queue.add(neighbor);
                }
                map.get(n.label).neighbors.add(map.get(neighbor.label)); //add neighbor to new created nodes
            }
        }

        return newNode;
    }
}
