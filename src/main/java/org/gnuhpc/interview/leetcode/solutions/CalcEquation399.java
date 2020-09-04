package org.gnuhpc.interview.leetcode.solutions;

import org.gnuhpc.interview.datastructure.unionfind.StringRatioUnionFind;
import org.junit.Test;

import java.util.*;

public class CalcEquation399 {
    //Method 1: DFS  有权有向图DFS
    //https://www.cnblogs.com/handsomelixinan/p/10346065.html

    Map<String, List<Adam>> map = new HashMap<>();

    public double[] calcEquation(List<List<String>> equations, double[] values, List<List<String>> queries) {


        double[] num = new double[queries.size()];

        int i = 0;
        for (List<String> equ : equations) {
            String start = equ.get(0);
            String end = equ.get(1);
            if (!map.containsKey(start))
                map.put(start, new ArrayList<>());
            map.get(start).add(new Adam(end, values[i]));
            if (!map.containsKey(end))
                map.put(end, new ArrayList<>());
            map.get(end).add(new Adam(start, 1 / values[i]));
            i++;
        }

        i = 0;
        for (List<String> q : queries) {
            String start = q.get(0);
            String end = q.get(1);
            num[i] = findPath(start, end, 1.0, new HashSet()); //注意，每次寻找新的路径时需要新建一个hashset
        }
        return num;
    }

    public double findPath(String start, String end, double val, Set<String> visited) {
        if (visited.contains(start)) return -1.0;
        if (!map.containsKey(start)) return -1.0;

        if (start.equals(end)) return val;
        visited.add(start);
        for (Adam next : map.get(start)) {
            double sub = findPath(next.s, end, val * next.dis, visited);
            if (sub != -1.0) return sub;
        }

        return -1.0;

    }

    class Adam {
        String s;
        double dis;

        Adam(String s, double dis) {
            this.s = s;
            this.dis = dis;
        }
    }


    // Method 2: BFS  有权有向图BFS
    Map<String, Map<String, Double>> g = new HashMap<>();

    private void buildGraph(List<List<String>> equations, double[] values) {
        for (int i = 0; i < equations.size(); ++i) {
            String x = equations.get(i).get(0);
            String y = equations.get(i).get(1);
            double k = values[i];
            g.computeIfAbsent(x, l -> new HashMap<>()).put(y, k);
            g.computeIfAbsent(y, l -> new HashMap<>()).put(x, 1.0 / k);
        }
    }

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
                } else if (!visited.contains(nextNode)) {
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

        for (double r : calcEquation(equations, values, queries)) {
            System.out.print(r + ", ");
        }

    }

}
