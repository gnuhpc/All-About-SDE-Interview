package org.gnuhpc.interview.leetcode.solutions;

import java.util.*;

public class CanFinish207 {
    /*
    Method1 : BFS 拓扑排序
     */
    //参考TopologicalSortBFS.java
    public boolean canFinish(int numCourses, int[][] prerequisites) {
        if (numCourses == 0 || prerequisites.length == 0) return true;

        //[A,B] means A depends on B -> B-->A
        //That is prerequisites[i][0] represents A, so the indegree of A is 1

        // Convert graph presentation from edges to indegree of adjacent list.
        // Step1
        int[] indegree = new int[numCourses];
        for (int i = 0; i < prerequisites.length; i++) // Indegree - how many prerequisites are needed.
            indegree[prerequisites[i][0]]++;

        //Step2
        Queue<Integer> queue = new LinkedList<>();
        for (int i = 0; i < numCourses; i++)
            if (indegree[i] == 0)
                queue.add(i);

        // How many courses don't need prerequisites.
        int canFinishCount = queue.size();
        while (!queue.isEmpty()) { //Step 4
            int prerequisite = queue.remove(); // Already finished this prerequisite course.
            for (int i = 0; i < prerequisites.length; i++) {
                if (prerequisites[i][1] == prerequisite) {
                    indegree[prerequisites[i][0]]--; //Step3
                    if (indegree[prerequisites[i][0]] == 0) {
                        canFinishCount++;
                        queue.add(prerequisites[i][0]);
                    }
                }
            }
        }

        return (canFinishCount == numCourses); //Step5
    }

    /*
    Method2: DFS
     */
    public boolean canFinish2(int numCourses, int[][] prerequisites) {
        if (prerequisites == null) {
            throw new IllegalArgumentException("illegal prerequisites array");
        }

        int len = prerequisites.length;

        if (numCourses == 0 || len == 0) {
            return true;
        }

        //track visited courses
        int[] visit = new int[numCourses];

        // use the map to store what courses depend on a course
        Map<Integer, List<Integer>> map = new HashMap<>();
        for (int[] a : prerequisites) {
            if (map.containsKey(a[1])) {
                map.get(a[1]).add(a[0]);
            } else {
                List<Integer> l = new ArrayList<>();
                l.add(a[0]);
                map.put(a[1], l);
            }
        }

        for (int i = 0; i < numCourses; i++) {
            if (!canFinishDFS(map, visit, i))
                return false;
        }

        return true;
    }

    private boolean canFinishDFS(Map<Integer, List<Integer>> map, int[] visit, int i) {
        if (visit[i] == -1)
            return false;
        if (visit[i] == 1) // 可以不加,这个是优化
            return true;

        visit[i] = -1; // -1 表示正在被访问
        if (map.containsKey(i)) {
            for (int j : map.get(i)) {
                if (!canFinishDFS(map, visit, j))
                    return false;
            }
        }

        visit[i] = 1; // 1 表示访问完毕

        return true;
    }
}
