package org.gnuhpc.interview.leetcode.solutions;


import javafx.scene.control.SpinnerValueFactory;

import java.util.*;

/**
 * Copyright gnuhpc 2020/9/11
 */
public class ExclusiveTime636 {
    class Task {
        int id = 0;
        int time = 0;
        boolean isStart = true;

        Task(String[] split) {
            this.id = Integer.valueOf(split[0]);
            this.time = Integer.valueOf(split[2]);
            this.isStart = split[1].equals("start");
        }
    }

    public int[] exclusiveTime(int n, List<String> logs) {
        Stack<Task> stack = new Stack<>();
        int[] ans = new int[n];
        for (String log : logs) {
            Task task = new Task(log.split("\\:"));
            if (task.isStart) {
                stack.push(task);
            } else {
                Task last = stack.pop();
                int duration = task.time - last.time + 1;
                ans[task.id] += duration;
                // 栈若不空，说明之前还有任务没有执行完成，那个任务的执行时间需要减去当前任务的执行时间
                if (!stack.isEmpty()) {
                    ans[stack.peek().id] -= duration;
                }
            }
        }

        return ans;
    }
}
