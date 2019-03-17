package org.gnuhpc.bigdata.datastructure.graph.dijkstra;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class Edge {
    private double weight;
    private Vertex startVertex;
    private Vertex targetVertex;
}
