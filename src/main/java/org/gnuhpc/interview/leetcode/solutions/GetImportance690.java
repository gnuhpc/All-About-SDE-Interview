package org.gnuhpc.interview.leetcode.solutions;

import java.util.*;

class Employee {
    public int id;
    public int importance;
    public List<Integer> subordinates;
};

public class GetImportance690 {
    /*
    Method1: 递归
     */
    public int getImportance1(List<Employee> employees, int id) {
        //根据id找到根节点
        Employee root = null;
        for (Employee e : employees) {
            if (e.id == id) {
                root = e;
                break;
            }
        }
        //累加它的子树和
        int ans = root.importance;
        for (int sub : root.subordinates) {
            ans += getImportance1(employees, sub);
        }
        return ans;
    }

    //BFS
    public int getImportance2(List<Employee> employees, int id) {
        if (employees == null || employees.size() == 0) {
            return 0;
        }
        //这里可以用map先存一下，不然每次都得根据id去搜employee
        Map<Integer, Employee> map = new HashMap<>();
        for (Employee e : employees) {
            map.put(e.id, e);
        }
        //对于BFS，就维护一个队列，每次把出队的节点的孩子给丢进队列即可
        Deque<Employee> queue = new LinkedList<>();
        queue.offer(map.get(id));
        int ans = 0;
        while (!queue.isEmpty()) {
            Employee e = queue.poll();
            ans += e.importance;
            //把根节点孩子丢进队列里
            for (int subordinate : e.subordinates) {
                queue.offer(map.get(subordinate));
            }
        }
        return ans;
    }
}
