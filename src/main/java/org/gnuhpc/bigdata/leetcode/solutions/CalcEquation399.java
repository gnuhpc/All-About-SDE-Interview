package org.gnuhpc.bigdata.leetcode.solutions;

import org.gnuhpc.bigdata.datastructure.unionfind.StringRatioUnionFind;
import org.junit.Test;

import java.util.*;

public class CalcEquation399 {
    //Method 1: DFS  有权有向图DFS
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
            }
            else {
                ans[i] = divide(x, y, new HashSet<>());
            }
        }

        return ans;
    }

    //有权有向图构建
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


    // Method 2: BFS  有权有向图BFS
    public double[] calcEquation2(List<List<String>> equations,
                                  double[] values,
                                  List<List<String>> queries) {
        buildGraph(equations, values);

        double[] ans = new double[queries.size()];

        for (int i = 0; i < ans.length; i++) {
            ans[i] = divide2(queries.get(i).get(0), queries.get(i).get(1));
        }

        return ans;
    }

    public double divide2(String strX, String strY) {
        if (g.get(strX) == null || g.get(strY) == null) {
            return -1;
        }

        Queue<String> queue = new LinkedList<>();   //queue uesd for bfs
        // KeyName <-> Accumulated Value
        Map<String, Double> value = new HashMap<>();    //distance from strX
        Set<String> visited = new HashSet<>();   //check if the vertex has been in the queue

        //init
        queue.add(strX);
        visited.add(strX);
        value.put(strX, 1d);

        String currentNode, nextNode;
        while (!queue.isEmpty()) {
            currentNode = queue.poll();
            for (Map.Entry<String, Double> arc : g.get(currentNode).entrySet()) {
                nextNode = arc.getKey();
                value.put(nextNode, value.get(currentNode) * arc.getValue());
                if (nextNode.equals(strY)) {
                    return value.get(strY);
                }
                else if (!visited.contains(nextNode)) {
                    queue.add(nextNode);
                    visited.add(nextNode);
                }
            }
        }
        return -1;
    }

    //Method 3: Union Find
    public double[] calcEquation3(List<List<String>> equations, double[] values,
                                  List<List<String>> queries) {

        StringRatioUnionFind u = new StringRatioUnionFind();

        for (int i = 0; i < equations.size(); ++i)
            u.union(equations.get(i).get(0), equations.get(i).get(1), values[i]);

        double[] ans = new double[queries.size()];

        for (int i = 0; i < queries.size(); ++i) {
            StringRatioUnionFind.Node rx = u.find(queries.get(i).get(0));
            StringRatioUnionFind.Node ry = u.find(queries.get(i).get(1));

            if (rx == null || ry == null || !rx.parent.equals(ry.parent))
                ans[i] = -1.0;
            else
                ans[i] = rx.ratio / ry.ratio;
        }

        return ans;
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
