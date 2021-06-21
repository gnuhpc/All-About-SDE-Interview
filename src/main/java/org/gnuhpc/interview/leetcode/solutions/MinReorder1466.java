package org.gnuhpc.interview.leetcode.solutions;

import java.util.*;

public class MinReorder1466 {
    public int minReorder(int n, int[][] connections) {
        Set<Integer> set=new HashSet<>();
        set.add(0);
        int ans=0;
        while(set.size()!=n){
            for(int i=0;i<connections.length;i++){
                if(set.contains(connections[i][1]))set.add(connections[i][0]);
                else if(set.contains(connections[i][0])){
                    ans++;
                    set.add(connections[i][1]);
                }
            }
        }
        return ans;
    }

    //TODO BFS分析
    public int minReorder2(int n, int[][] connections) {
        List<List<Integer>> in = new ArrayList<>();
        for(int i = 0; i < n; ++i) {
            in.add(new ArrayList<>());
        }
        List<List<Integer>> out = new ArrayList<>();
        for(int i = 0; i < n; ++i) {
            out.add(new ArrayList<>());
        }
        boolean[] v = new boolean[n];
        for(int[] connection: connections) {
            out.get(connection[0]).add(connection[1]);
            in.get(connection[1]).add(connection[0]);
        }
        Queue<Integer> queue = new LinkedList<>();
        queue.offer(0);
        v[0] = true;
        int ans = 0;
        while(!queue.isEmpty()) {
            int cur = queue.poll();
            for(int i: in.get(cur)) {
                if(!v[i]) {
                    queue.offer(i);
                    v[i] = true;
                }
            }
            for(int i: out.get(cur)) {
                if(!v[i]) {
                    queue.offer(i);
                    ans++;
                    v[i] = true;
                }
            }
        }

        return ans;
    }

    Set<Integer> visit = new HashSet<>();
    int ans = 0;

    public int minReorder3(int n, int[][] connections) {
        // 无向图
        Map<Integer,List<Integer>> map = new HashMap<>();
        // 有向图
        Map<Integer,HashSet<Integer>> map2 = new HashMap<>();

        for (int i = 0; i < n; i++) {
            map.put(i,new ArrayList<>());
            map2.put(i,new HashSet<>());
        }
        // 初始化有向图 和 无向图
        for (int[] net : connections) {
            map.get(net[1]).add(net[0]);
            map.get(net[0]).add(net[1]);
            map2.get(net[0]).add(net[1]);
        }

        Reorder(0,map,map2);


        return ans;
    }


    public void Reorder(int cur, Map<Integer, List<Integer>> map,
                        Map<Integer,HashSet<Integer>> map2){
        visit.add(cur);
        for (Integer next : map.get(cur)) {
            if (!visit.contains(next)) {
                if (map2.get(cur).contains(next)){
                    ans++;
                }
                Reorder(next,map,map2);
            }

        }
    }

}
