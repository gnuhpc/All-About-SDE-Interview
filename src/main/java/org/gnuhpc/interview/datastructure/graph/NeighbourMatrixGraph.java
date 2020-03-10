package org.gnuhpc.interview.datastructure.graph;

import lombok.Data;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Stack;

class NMNode {
    int data;
    boolean visited;

    NMNode(int data) {
        this.data = data;
    }

    @Override
    public String toString() {
        return "Node{" +
                "data=" + data +
                '}';
    }
}

@Data
public class NeighbourMatrixGraph {
    private int[][] adjMatrix;
    private ArrayList<NMNode> nodes = new ArrayList<>();
    private Queue<NMNode> queue = new LinkedList<>();

    public NeighbourMatrixGraph(int[][] adjMatrix) {
        this.adjMatrix = adjMatrix;
    }

    // find neighbors of node using adjacency matrix
    // if adjacency_matrix[i][j]==1, then nodes at index i and index j are connected
    public ArrayList<NMNode> findNeighbours(NMNode x) {
        int nodeIndex = -1;

        ArrayList<NMNode> neighbours = new ArrayList<>();
        for (int i = 0; i < nodes.size(); i++) {
            if (nodes.get(i).equals(x)) {
                nodeIndex = i;
                break;
            }
        }

        if (nodeIndex != -1) {
            for (int j = 0; j < adjMatrix[nodeIndex].length; j++) {
                if (adjMatrix[nodeIndex][j] == 1) {
                    neighbours.add(nodes.get(j));
                }
            }
        }
        return neighbours;
    }


    // Recursive DFS
    public void dfs(NMNode node) {
        System.out.print(node.data + " ");
        ArrayList<NMNode> neighbours = findNeighbours(node);
        node.visited = true;
        for (NMNode n : neighbours) {
            if (n != null && !n.visited) {
                dfs(n);
            }
        }
    }

    // Iterative DFS using stack
    public void dfsUsingStack(NMNode node) {
        Stack<NMNode> stack = new Stack<>();
        stack.add(node);
        node.visited = true;
        while (!stack.isEmpty()) {
            NMNode element = stack.pop();
            System.out.print(element.data + " ");

            ArrayList<NMNode> neighbours = findNeighbours(element);
            for (int i = 0; i < neighbours.size(); i++) {
                NMNode n = neighbours.get(i);
                if (n != null && !n.visited) {
                    stack.add(n);
                    n.visited = true;

                }
            }
        }
    }

    public void bfs(NMNode node) {
        queue.add(node);
        node.visited = true;
        while (!queue.isEmpty()) {
            NMNode element = queue.remove();
            System.out.print(element.data + " ");
            ArrayList<NMNode> neighbours = findNeighbours(element);
            for (int i = 0; i < neighbours.size(); i++) {
                NMNode n = neighbours.get(i);
                if (n != null && !n.visited) {
                    queue.add(n);
                    n.visited = true;
                }
            }
        }
    }

    public static void main(String[] arg) {
        NMNode node40 = new NMNode(40);
        NMNode node10 = new NMNode(10);
        NMNode node20 = new NMNode(20);
        NMNode node30 = new NMNode(30);
        NMNode node60 = new NMNode(60);
        NMNode node50 = new NMNode(50);
        NMNode node70 = new NMNode(70);

        int[][] adjacency_matrix = {
                {0, 1, 1, 0, 0, 0, 0},  // NMNode 1: 40
                {0, 0, 0, 1, 0, 0, 0},  // NMNode 2 :10
                {0, 1, 0, 1, 1, 1, 0},  // NMNode 3: 20
                {0, 0, 0, 0, 1, 0, 0},  // NMNode 4: 30
                {0, 0, 0, 0, 0, 0, 1},  // NMNode 5: 60
                {0, 0, 0, 0, 0, 0, 1},  // NMNode 6: 50
                {0, 0, 0, 0, 0, 0, 0},  // NMNode 7: 70
        };

        NeighbourMatrixGraph graph = new NeighbourMatrixGraph(adjacency_matrix);
        graph.getNodes().add(node40);
        graph.getNodes().add(node10);
        graph.getNodes().add(node20);
        graph.getNodes().add(node30);
        graph.getNodes().add(node60);
        graph.getNodes().add(node50);
        graph.getNodes().add(node70);

        System.out.println("The DFS traversal of the graph using stack ");
        graph.dfsUsingStack(node40);

        graph.clearVisitedFlags();
        System.out.println("\nThe DFS traversal of the graph using recursion ");
        graph.dfs(node40);

        graph.clearVisitedFlags();
        System.out.println("\nThe BFS traversal of the graph using queue");
        graph.bfs(node40);

    }

    private void clearVisitedFlags() {
        for (NMNode node : nodes) {
            node.visited = false;
        }
    }
}
