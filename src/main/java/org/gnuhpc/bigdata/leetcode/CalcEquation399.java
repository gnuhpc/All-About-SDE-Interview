package org.gnuhpc.bigdata.leetcode;

import org.gnuhpc.bigdata.leetcode.utils.Utils;
import org.junit.Test;

import java.util.*;

public class CalcEquation399 {
    //Method 1: DFS  TODO: 有权有向图DFS
    // g[A][B] = k -> A / B = k
    Map<String, Map<String, Double>> g = new HashMap<>();

    public double[] calcEquation(List<List<String>> equations,
                                 double[] values,
                                 List<List<String>> queries) {
        buildGraph(equations, values);

        double[] ans = new double[queries.size()];

        for (int i = 0; i < queries.size(); ++i) {
            String x = queries.get(i).get(0);
            String y = queries.get(i).get(1);
            if (!g.containsKey(x) || !g.containsKey(y)) {
                ans[i] = -1.0;
            } else {
                ans[i] = divide(x, y, new HashSet<>());
            }
        }

        return ans;
    }

    //TODO: 有权有向图构建
    private void buildGraph(List<List<String>> equations, double[] values) {
        for (int i = 0; i < equations.size(); ++i) {
            String x = equations.get(i).get(0);
            String y = equations.get(i).get(1);
            double k = values[i];
            g.computeIfAbsent(x, l -> new HashMap<>()).put(y, k);
            g.computeIfAbsent(y, l -> new HashMap<>()).put(x, 1.0 / k);
        }
    }

    // get result of x / y
    private double divide(String strX, String strY, Set<String> visited) {
        if (strX.equals(strY)) return 1.0;
        visited.add(strX);
        if (!g.containsKey(strX)) return -1.0;
        for (String z : g.get(strX).keySet()) {
            if (visited.contains(z)) continue;
            visited.add(z);
            // d = z / y
            double d = divide(z, strY, visited);

            // x / y = z / y * x / z
            if (d > 0) return d * g.get(strX).get(z);
        }
        return -1.0;
    }


    // Method 2: BFS  TODO: 有权有向图BFS
    public double[] calcEquation2(List<List<String>> equations,
                                 double[] values,
                                 List<List<String>> queries) {
        buildGraph(equations, values);

        double[] ans = new double[queries.size()];

        for (int i = 0; i < ans.length; i++) {
            ans[i] = getValue(queries.get(i).get(0), queries.get(i).get(1));
        }

        return ans;
    }

    public double getValue(String vexStart, String vexEnd) {
        if (g.get(vexStart) == null || g.get(vexEnd) == null) {
            return -1;
        }

        Queue<String> queue = new LinkedList<>();   //queue uesd for bfs
        // KeyName <-> Accumulated Value
        Map<String, Double> value = new HashMap<>();    //distance from vexStart
        Set<String> visited = new HashSet<>();   //check if the vertex has been in the queue

        //init
        queue.add(vexStart);
        visited.add(vexStart);
        value.put(vexStart, 1d);

        String currentNode, nextNode;
        while (!queue.isEmpty()) {
            currentNode = queue.remove();
            for (Map.Entry<String, Double> arc : g.get(currentNode).entrySet()) {
                nextNode = arc.getKey();
                value.put(nextNode, value.get(currentNode) * arc.getValue());
                if (nextNode.equals(vexEnd)) {
                    return value.get(vexEnd);
                } else if (!visited.contains(nextNode)) {
                    queue.add(nextNode);
                    visited.add(nextNode);
                }
            }
        }
        return -1;
    }

    //Method 3: Union Find
    public double[] calcEquation3(List<List<String>> equations,
                                  double[] values,
                                  List<List<String>> queries) {
        Map<String, String> graph = new HashMap<>(); //TODO Union-Find的字符串表示
        Map<String, Double> ratio = new HashMap<>();
        for (int i = 0; i < equations.size(); i++) { //把能union先union起来, 顺带计算比例
            union(graph, ratio, equations.get(i).get(0), equations.get(i).get(1), values[i]);
        }

        double[] res = new double[queries.size()];
        for (int i = 0; i < queries.size(); i++) {
            String s1 = queries.get(i).get(0), s2 = queries.get(i).get(1);
            if (!graph.containsKey(s1) || !graph.containsKey(s2) || !find(graph, ratio, s1).equals(find(graph, ratio, s2))) {
                res[i] = -1.0d;
            } else {
                res[i] = ratio.get(s1)/ratio.get(s2);
            }
        }
        return res;
    }

    private void union(Map<String, String> graph, Map<String, Double> ratio, String s1, String s2, double value) {
        if (!graph.containsKey(s1)) {// 初始化
            graph.put(s1, s1);
            ratio.put(s1, 1.0d);
        }
        if (!graph.containsKey(s2)) {// 初始化
            graph.put(s2, s2);
            ratio.put(s2, 1.0d);
        }
        String p1 = find(graph, ratio, s1);
        String p2 = find(graph, ratio, s2);
        graph.put(p1, p2);

        ratio.put(p1, value * ratio.get(s2) / ratio.get(s1)); // 这个有点绕,举个例子去看
    }

    private String find(Map<String, String> graph, Map<String, Double> ratio, String str) {
        if (!str.equals(graph.get(str))) {
            String parent = graph.get(str);
            String ancestor = find(graph, ratio, parent);
            graph.put(str, ancestor);
            ratio.put(str, ratio.get(str) * ratio.get(parent)); //记得更新ratio
        }
        return graph.get(str);
    }


    @Test
    public void test() {
        List<List<String>> equations = new ArrayList<>();
        equations.add(Arrays.asList("a", "b"));
        equations.add(Arrays.asList("b", "c"));

        double[] values = new double[]{2.0, 3.0};

        List<List<String>> queries = new ArrayList<>();
        queries.add(Arrays.asList("a", "c"));
        queries.add(Arrays.asList("b", "a"));
        queries.add(Arrays.asList("a", "e"));
        queries.add(Arrays.asList("a", "a"));
        queries.add(Arrays.asList("x", "x"));

        for (double r : calcEquation3(equations, values, queries)) {
            System.out.print(r + ", ");
        }

    }

}
