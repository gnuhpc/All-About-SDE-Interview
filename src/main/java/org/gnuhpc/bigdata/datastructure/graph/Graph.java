package org.gnuhpc.bigdata.datastructure.graph;

import lombok.Data;

import java.util.LinkedList;

@Data
public class Graph {

    private int countV;// No. of vertices

    //An Array of List which contains
    //references to the Adjacency List of
    //each vertex
    private LinkedList<Integer>[] adj;

    public Graph(int countV)// Constructor
    {
        this.countV = countV;
        adj = new LinkedList[countV];
        for (int i = 0; i < countV; i++)
            adj[i] = new LinkedList<>();
    }

    // function to add an edge to graph
    public void addEdge(int u, int v) {
        adj[u].add(v);
    }
}
