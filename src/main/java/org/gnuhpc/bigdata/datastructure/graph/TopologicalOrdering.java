package org.gnuhpc.bigdata.datastructure.graph;

import lombok.Data;

import java.util.*;

@Data
class Vertex {

    private String data;
    private boolean visited;
    private List<Vertex> neighbourList;

    public Vertex(String data) {
        this.data = data;
        this.neighbourList = new ArrayList<>();
    }

    public void addNeighbour(Vertex vertex) {
        this.neighbourList.add(vertex);
    }

    @Override
    public String toString() {
        return data;
    }
}

//https://www.coursera.org/lecture/algorithms-graphs-data-structures/topological-sort-yeKm7
//算法演示请见：https://www.cs.usfca.edu/~galles/visualization/TopoSortDFS.html

public class TopologicalOrdering {

    private Stack<Vertex> stack;

    public TopologicalOrdering() {
        this.stack = new Stack<>();
    }

    public void dfs(Vertex vertex) {

        vertex.setVisited(true);

        for (Vertex v : vertex.getNeighbourList()) {
            if (!v.isVisited()) {
                dfs(v);
            }
        }

        stack.push(vertex);
    }

    public Stack<Vertex> getStack() {
        return this.stack;
    }

    public static void main(String[] args) {

        TopologicalOrdering topologicalOrdering = new TopologicalOrdering();

        List<Vertex> graph = new ArrayList<>();

        graph.add(new Vertex("0"));
        graph.add(new Vertex("1"));
        graph.add(new Vertex("2"));
        graph.add(new Vertex("3"));
        graph.add(new Vertex("4"));
        graph.add(new Vertex("5"));

        graph.get(2).addNeighbour(graph.get(3));

        graph.get(3).addNeighbour(graph.get(1));

        graph.get(4).addNeighbour(graph.get(0));
        graph.get(4).addNeighbour(graph.get(1));

        graph.get(5).addNeighbour(graph.get(0));
        graph.get(5).addNeighbour(graph.get(2));

        //遍历一遍保证即使有不连通的部分也能被覆盖到
        for (int i = 0; i < graph.size(); i++) {
            if (!graph.get(i).isVisited()) {
                topologicalOrdering.dfs(graph.get(i));
            }
        }

        Stack<Vertex> stack = topologicalOrdering.getStack();

        for (int i = 0; i < graph.size(); i++) {
            Vertex vertex = stack.pop();
            System.out.print(vertex);
            if (i!=graph.size()-1){
                System.out.print("->");
            }
        }

        System.out.println();

        System.out.println(topSort(graph));
    }

	/*
	BFS
	 */

    public static List<Vertex> topSort(List<Vertex> graph) {
        // write your code here
        List<Vertex> result = new ArrayList<>();
        Map<Vertex, Integer> map = new HashMap<>();
        for (Vertex node : graph) {
            for (Vertex neighbor : node.getNeighbourList()) {
                if (map.containsKey(neighbor)) {
                    map.put(neighbor, map.get(neighbor) + 1);
                } else {
                    map.put(neighbor, 1);
                }
            }
        }
        Queue<Vertex> q = new LinkedList<>();
        for (Vertex node : graph) {
            if (!map.containsKey(node)) {
                q.offer(node);
                result.add(node);
            }
        }
        while (!q.isEmpty()) {
            Vertex node = q.poll();
            for (Vertex n : node.getNeighbourList()) {
                map.put(n, map.get(n) - 1);
                if (map.get(n) == 0) {
                    result.add(n);
                    q.offer(n);
                }
            }
        }
        if (result.size() == graph.size())
            return result;
        else //有环，不存在拓扑排序
            return null;
    }
}
