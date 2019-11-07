package org.gnuhpc.bigdata.datastructure.graph;

import lombok.Data;

import java.util.*;


class NLNode {
    public int data;
    public boolean visited; //或者有一个HashMap标注visited
    public List<NLNode> neighbours;

    public NLNode(int data) {
        this.data=data;
        this.neighbours=new ArrayList<>();
    }

    public void addNeighbour(NLNode neighbourNode){
        this.neighbours.add(neighbourNode);
    }

    @Override
    public String toString() {
        return "Node{" +
                "data=" + data +
                '}';
    }
}

@Data
public class NeighbourListGraph {  // 无向图优先使用邻接矩阵算法
    private List<NLNode> nodes = new ArrayList<>();
    private Queue<NLNode> queue = new LinkedList<>();;
    // Recursive DFS
    public  void dfs(NLNode node) {
        System.out.print(node.data + " ");
        node.visited=true;
        for (NLNode n : node.neighbours) {
            if (n != null && !n.visited) {
                dfs(n);
            }
        }
    }

    // Iterative DFS using stack
    public  void dfsUsingStack(NLNode node)
    {
        Stack<NLNode> stack= new Stack<>();
        stack.add(node);
        node.visited=true;
        while (!stack.isEmpty()) {
            NLNode element=stack.pop();
            System.out.print(element.data + " ");

            for (NLNode n : element.neighbours) {
                if (n != null && !n.visited) {
                    stack.add(n);
                    n.visited = true;
                }
            }
        }
    }

    //注意,注意,注意!!!!!!!
    // 这种表达方式在BFS\DFS中碰到类似如下这种,假设从1开始遍历就走不到所有的节点
    //而邻接矩阵表达方式就不会出现这种情况
    //如果一定要使用邻接列表的方式表达图,则需要对每个节点进行DFS/BFS
    /*
        1
      /
    0
      \
        2

     */
    public void bfs(NLNode node) {
        queue.add(node);
        node.visited=true;
        while (!queue.isEmpty()) {

            NLNode element=queue.remove();
            System.out.print(element.data + " ");
            for (NLNode n : element.neighbours) {
                if (n != null && !n.visited) {
                    queue.add(n);
                    n.visited = true;
                }
            }
        }
    }

    public static void main(String[] arg) {
        NLNode node40 =new NLNode(40);
        NLNode node10 =new NLNode(10);
        NLNode node20 =new NLNode(20);
        NLNode node30 =new NLNode(30);
        NLNode node60 =new NLNode(60);
        NLNode node50 =new NLNode(50);
        NLNode node70 =new NLNode(70);

        node40.addNeighbour(node10);
        node40.addNeighbour(node20);
        node10.addNeighbour(node30);
        node20.addNeighbour(node10);
        node20.addNeighbour(node30);
        node20.addNeighbour(node60);
        node20.addNeighbour(node50);
        node30.addNeighbour(node60);
        node60.addNeighbour(node70);
        node50.addNeighbour(node70);

        NeighbourListGraph graph = new NeighbourListGraph();
        graph.getNodes().add(node10);
        graph.getNodes().add(node20);
        graph.getNodes().add(node30);
        graph.getNodes().add(node40);
        graph.getNodes().add(node50);
        graph.getNodes().add(node60);
        graph.getNodes().add(node70);

        System.out.println("The DFS traversal of the graph using stack ");
        graph.dfsUsingStack(node40);
        graph.clearVisitedFlags();

        System.out.println("\nThe DFS traversal of the graph using recursion ");
        graph.dfs(node40);
        graph.clearVisitedFlags();

        System.out.println("\nThe BFS traversal of the graph");
        graph.bfs(node40);
        NLNode node0 = new NLNode(0);
        NLNode node1 = new NLNode(1);
        NLNode node2 = new NLNode(2);

        node0.neighbours.add(node2);
        node0.neighbours.add(node1);

        NeighbourListGraph graph1 = new NeighbourListGraph();
        graph1.getNodes().add(node2);
        graph1.getNodes().add(node1);
        graph1.getNodes().add(node0);

        System.out.println("\nThe BFS traversal of the graph");
        graph1.bfs(node1);

    }

    public void clearVisitedFlags() {
        for (NLNode node : nodes) {
            node.visited = false;
        }
    }
}
