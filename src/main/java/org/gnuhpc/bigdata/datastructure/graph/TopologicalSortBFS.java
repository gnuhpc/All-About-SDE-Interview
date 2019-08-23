package org.gnuhpc.bigdata.datastructure.graph;

import org.junit.Test;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

/*
Topological Sort:
A topological sort or topological ordering of a directed graph is
a linear ordering of its vertices such that for every directed edge uv
from vertex u to vertex v, u comes before v in the ordering.
A topological ordering is possible if and only if the graph has no directed cycles,
that is, if it is a directed acyclic graph (DAG).

Please note that there can be more than one solution for topological sort.

https://algorithms.tutorialhorizon.com/topological-sort/
 */

/*
    BFS: https://www.geeksforgeeks.org/topological-sorting-indegree-based-solution/

    Step-1: Compute in-degree (number of incoming edges) for each of the vertex present in the DAG and initialize the count of visited nodes as 0.

    Step-2: Pick all the vertices with in-degree as 0 and add them into a queue (Enqueue operation)

    Step-3: Remove a vertex from the queue (Dequeue operation)
    and then Increment count of visited nodes by 1.
    Decrease in-degree by 1 for all its neighboring nodes.
    If in-degree of a neighboring nodes is reduced to zero, then add it to the queue.

    Step 4: Repeat Step 3 until the queue is empty.

    Step 5: If count of visited nodes is not equal to the number of nodes in the graph then the topological sort is not possible for the given graph.
 */

public class TopologicalSortBFS {

    // prints a Topological Sort of the complete graph
    public void topologicalSort(Graph g) {
        int countV = g.getCountV();
        LinkedList<Integer>[] adj = g.getAdj();

        //Step1
        // Create a array to store indegrees of all
        // vertices. Initialize all indegrees as 0.
        int[] indegree = new int[countV];

        // Traverse adjacency lists to fill indegrees of
        // vertices. This step takes O(V+E) time
        for (int i = 0; i < countV; i++) {
            LinkedList<Integer> temp = adj[i];
            for (int node : temp) {
                indegree[node]++;
            }
        }

        //Step2
        // Create a queue and enqueue all vertices with indegree 0
        Queue<Integer> q = new LinkedList<>();
        for (int i = 0; i < countV; i++) {
            if (indegree[i] == 0)
                q.add(i);
        }

        //Step3
        // Initialize count of visited vertices
        int cnt = 0;
        //store result (A topological ordering of the vertices)
        List<Integer> res = new ArrayList<>();
        while (!q.isEmpty()) {
            // Extract front of queue (or perform dequeue)
            // and add it to topological order
            int n = q.poll();
            res.add(n);

            // Iterate through all its neighbouring nodes
            // of dequeued node u and decrease their in-degree
            // by 1
            for (int node : adj[n]) {
                // If in-degree becomes zero, add it to queue
                if (--indegree[node] == 0)
                    q.add(node);
            }
            cnt++;
        }

        // Check if there was a cycle
        if (cnt != countV) {
            System.out.println("There exists a cycle in the graph");
            return;
        }

        // Print topological order
        System.out.print(res);
    }

    @Test
    public void test(){
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

