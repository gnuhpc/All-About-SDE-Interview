package org.gnuhpc.interview.datastructure.graph;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.gnuhpc.interview.datastructure.unionfind.QuickUnion;
import org.junit.Test;

import java.util.*;


//无向图判断有环
/*
1. 定义顶点，注意增加一个visited状态
2. 构造图
3. 判断有环： 对每个没有visited的顶点进行DFS, 传入null表示其父节点（初始为null）
4. DFS具体实现：
	首先将此节点设置为已经访问
	然后对这个节点的邻接节点进行遍历
		注意要除去邻接节点中是父节点的
		如果不是visited，则进一步DFS深入,parent为当前节点
		如果已经visited，说明绕回来的了，有环！
 */
/*
BFS:
For every visited vertex ‘v’, if there is an adjacent ‘u’ such that u is already visited and u is not parent of v,
then there is a cycle in graph. If we don’t find such an adjacent for any vertex,
we say that there is no cycle.
The assumption of this approach is that there are no parallel edges between any two vertices.

We use a parent array to keep track of parent vertex for a vertex
so that we do not consider visited parent as cycle.
 */
public class UnDirectedGraphCycleDetection {

    public void detectCycleByBFS(List<NLNode> graph) {
        for (NLNode vertex : graph) {
            if (!vertex.visited) {
                bfs(graph, vertex);
            }
        }
    }

    private void bfs(List<NLNode> graph, NLNode vertex) {
        // Set parent vertex
        Map<NLNode, NLNode> parent = new HashMap<>();
        Queue<NLNode> queue = new LinkedList<>();
        vertex.visited = true;
        queue.add(vertex);

        while (!queue.isEmpty()) {
            NLNode node = queue.poll();
            System.out.println("visiting vertex " + node + " recursively...");

            for (NLNode v : node.neighbours) {
                if (!v.visited) {
                    v.visited = true;
                    queue.offer(v);
                    parent.put(v, node);
                } else {
                    //注意get和put是对着的
                    if (parent.get(node) != v) {
                        System.out.println("Backward edge ... so there is a cycle");
                    }
                }
            }
        }
    }

    public void detectCycleByDFS(List<NLNode> graph) {
        for (NLNode vertex : graph) {
            if (!vertex.visited) {
                dfs(vertex, null);
            }
        }
    }

    private void dfs(NLNode vertex, NLNode parent) {
        vertex.visited = true;

        for (NLNode v : vertex.neighbours) {
            // 这部分是标准的DFS
            if (v == parent) continue;
            if (!v.visited) { //如果不是最终状态，则进入递归
                System.out.println("visiting vertex " + v + " recursively...");
                dfs(v, vertex);
            } else {
                System.out.println("Backward edge ... so there is a cycle");
            }
        }
    }


    public void detectCycleByUF(List<NLNode> graph) {
        QuickUnion qu = new QuickUnion(graph.size() + 1);
        //先将邻接矩阵换为edge表示
        Set<Edge> edges = makeEdges(graph);

        for (Edge edge : edges) {
            System.out.println("Detect edge " + edge.start + "--" + edge.end);
            if (qu.isConnected(edge.start, edge.end)) {
                System.out.println("Backward edge ... so there is a cycle");
            } else {
                qu.union(edge.start, edge.end);
            }
        }
    }

    private Set<Edge> makeEdges(List<NLNode> graph) {
        Set<Edge> res = new HashSet<>();
        for (NLNode vertex : graph) {
            for (NLNode v : vertex.neighbours) {
                int start = Math.min(vertex.data, v.data);
                int end = Math.max(vertex.data, v.data);
                res.add(new Edge(start, end));
            }
        }
        return res;
    }

    @Test
    public void test() {
        NLNode vertex1 = new NLNode(1);
        NLNode vertex2 = new NLNode(2);
        NLNode vertex3 = new NLNode(3);
        NLNode vertex4 = new NLNode(4);
        NLNode vertex5 = new NLNode(5);

        vertex1.addNeighbour(vertex2);
        vertex2.addNeighbour(vertex1);

        vertex2.addNeighbour(vertex3);
        vertex3.addNeighbour(vertex2);

        //Add these two lines to add a cycle
        vertex3.addNeighbour(vertex1);
        vertex1.addNeighbour(vertex3);

        vertex4.addNeighbour(vertex1);
        vertex1.addNeighbour(vertex4);

        vertex4.addNeighbour(vertex5);
        vertex5.addNeighbour(vertex4);

        vertex5.addNeighbour(vertex1);
        vertex1.addNeighbour(vertex5);


        List<NLNode> graph = new ArrayList<>();
        graph.add(vertex1);
        graph.add(vertex2);
        graph.add(vertex3);
        graph.add(vertex4);
        graph.add(vertex5);


        UnDirectedGraphCycleDetection cycleDetection = new UnDirectedGraphCycleDetection();
//		cycleDetection.detectCycleByDFS(graph);
        System.out.println("==========================");
//		cycleDetection.detectCycleByBFS(graph);
        System.out.println("==========================");
        cycleDetection.detectCycleByUF(graph);

    }

    @AllArgsConstructor
    @EqualsAndHashCode
    private class Edge {
        public int start;
        public int end;
    }
}
