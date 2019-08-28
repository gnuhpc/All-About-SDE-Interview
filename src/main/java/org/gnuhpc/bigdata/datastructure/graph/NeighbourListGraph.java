package org.gnuhpc.bigdata.datastructure.graph;

import lombok.Data;

import java.util.*;
/*
什么时候使用BFS：
图 的遍 历 Traversal in Graph
• 层级遍历 Level Order Traversal
• 由点及面 Connected Component
• 拓扑排序 Topological Sorting
最短路径 Shortest Path in Simple Graph
• 仅限简单图求最短路径
• 即，图中每条边长度都是1，且没有方向
 */

@Data
class GraphNode {
    int data;
    boolean visited; //或者有一个HashMap标注visited
    List<GraphNode> neighbours;

    public GraphNode(int data) {
        this.data=data;
        this.neighbours=new ArrayList<>();
    }

    public void addNeighbours(GraphNode neighbourNode){
        this.neighbours.add(neighbourNode);
    }
}

@Data
public class NeighbourListGraph {
    private List<GraphNode> nodes = new ArrayList<>();
    private Queue<GraphNode> queue = new LinkedList<>();;
    // Recursive DFS
    public  void dfs(GraphNode node) {
        System.out.print(node.data + " ");
        node.visited=true;
        for (GraphNode n : node.getNeighbours()) {
            if (n != null && !n.visited) {
                dfs(n);
            }
        }
    }

    // Iterative DFS using stack
    public  void dfsUsingStack(GraphNode node)
    {
        Stack<GraphNode> stack= new Stack<>();
        stack.add(node);
        node.visited=true;
        while (!stack.isEmpty()) {
            GraphNode element=stack.pop();
            System.out.print(element.data + " ");

            for (GraphNode n : element.getNeighbours()) {
                if (n != null && !n.visited) {
                    stack.add(n);
                    n.visited = true;
                }
            }
        }
    }

    public void bfs(GraphNode node) {
        queue.add(node);
        node.visited=true;
        while (!queue.isEmpty())
        {

            GraphNode element=queue.remove();
            System.out.print(element.data +" ");
            for (GraphNode n : element.getNeighbours()) {
                if (n != null && !n.visited) {
                    queue.add(n);
                    n.visited = true;

                }
            }
        }
    }

    public static void main(String[] arg) {
        GraphNode node40 =new GraphNode(40);
        GraphNode node10 =new GraphNode(10);
        GraphNode node20 =new GraphNode(20);
        GraphNode node30 =new GraphNode(30);
        GraphNode node60 =new GraphNode(60);
        GraphNode node50 =new GraphNode(50);
        GraphNode node70 =new GraphNode(70);

        node40.addNeighbours(node10);
        node40.addNeighbours(node20);
        node10.addNeighbours(node30);
        node20.addNeighbours(node10);
        node20.addNeighbours(node30);
        node20.addNeighbours(node60);
        node20.addNeighbours(node50);
        node30.addNeighbours(node60);
        node60.addNeighbours(node70);
        node50.addNeighbours(node70);

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
    }

    public void clearVisitedFlags() {
        for (GraphNode node : nodes) {
            node.visited = false;
        }
    }
}
