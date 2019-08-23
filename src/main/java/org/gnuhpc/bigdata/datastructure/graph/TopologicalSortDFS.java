package org.gnuhpc.bigdata.datastructure.graph;

import org.junit.Test;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.Stack;

/*
Topological Sort:
A topological sort or topological ordering of a directed graph is
a linear ordering of its vertices such that for every directed edge uv
from vertex u to vertex v, u comes before v in the ordering.
A topological ordering is possible if and only if the graph has no directed cycles,
that is, if it is a directed acyclic graph (DAG).

Please note that there can be more than one solution for topological sort.

https://algorithms.tutorialhorizon.com/topological-sort/
https://www.geeksforgeeks.org/topological-sorting/
 */

/*
 We can modify DFS to find Topological Sorting of a graph.
 In DFS, we start from a vertex, we first print it and then recursively call DFS for its adjacent vertices. '
 In topological sorting, we use a temporary stack.
  We don’t print the vertex immediately,
  we first recursively call topological sorting for all its adjacent vertices,
  then push it to a stack. Finally, print contents of stack.
  Note that a vertex is pushed to stack only when all of its adjacent vertices
  (and their adjacent vertices and so on) are already in stack.
 */


public class TopologicalSortDFS {
    // A recursive function used by topologicalSort
    private void topologicalSortUtil(int v, boolean[] visited,
                                     Stack<Integer> stack, LinkedList<Integer>[] adj) {
        // Mark the current node as visited.
        visited[v] = true;
        Integer i;

        // Recur for all the vertices adjacent to this vertex
        for (Integer integer : adj[v]) {
            i = integer;
            if (!visited[i])
                topologicalSortUtil(i, visited, stack,adj);
        }

        // Push current vertex to stack which stores result
        stack.push(v);
    }

    // The function to do Topological Sort. It uses
    // recursive topologicalSortUtil()
    public void topologicalSort(Graph g) {
        int vCount = g.getCountV();
        Stack<Integer> stack = new Stack<>();

        // Mark all the vertices as not visited
        boolean[] visited = new boolean[vCount];
        Arrays.fill(visited, false);

        // Call the recursive helper function to store
        // Topological Sort starting from all vertices
        // one by one
        for (int i = 0; i < vCount; i++)
            if (!visited[i])
                topologicalSortUtil(i, visited, stack, g.getAdj());

        // Print contents of stack
        while (!stack.empty())
            System.out.print(stack.pop() + " ");
    }

    @Test
    public void test() {
        // Create a graph given in the above diagram
        Graph g = new Graph(6);
        g.addEdge(5, 2);
        g.addEdge(5, 0);
        g.addEdge(4, 0);
        g.addEdge(4, 1);
        g.addEdge(2, 3);
        g.addEdge(3, 1);

        System.out.println("Following is a Topological " +
                "sort of the given graph");
        topologicalSort(g);
    }
}

