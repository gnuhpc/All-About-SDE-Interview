package org.gnuhpc.bigdata.datastructure.graph.dijkstra;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@AllArgsConstructor
@EqualsAndHashCode
public class Edge {
    public double weight;
    public Vertex startVertex;
    public Vertex targetVertex;
}
