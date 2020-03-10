package org.gnuhpc.interview.datastructure.graph.dijkstra;

import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
public class Vertex implements Comparable<Vertex> {

    private String name;
    private List<Edge> adjacenciesList;
    private boolean visited;
    private Vertex predecessor;
    private double minDistance = Double.MAX_VALUE;

    public Vertex(String name) {
        this.name = name;
        this.adjacenciesList = new ArrayList<>();
    }

    public void addNeighbour(Edge edge) {
        this.adjacenciesList.add(edge);
    }

    @Override
    public String toString() {
        return this.name;
    }

    @Override
    public int compareTo(Vertex otherVertex) {
        return Double.compare(this.minDistance, otherVertex.getMinDistance());
    }
}
