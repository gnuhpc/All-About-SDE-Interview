package org.gnuhpc.bigdata.leetcode;

import org.junit.Test;

import java.util.*;

import static org.junit.Assert.assertEquals;

public class SimplifyPath71 {
    public String simplifyPath(String path) {
        Deque<String> stack = new LinkedList<>();
        Set<String> skip = new HashSet<>(Arrays.asList("..", ".", ""));
        for (String dir : path.split("/")) {
            if (dir.equals("..") && !stack.isEmpty()) stack.pop();
            else if (!skip.contains(dir)) stack.push(dir);
        }
        String res = "";
        while (!stack.isEmpty()) res = "/" + stack.pop() + res;
        return res.isEmpty() ? "/" : res;
    }

    @Test
    public void test(){
        assertEquals(simplifyPath("/home//gnuhpc/"), "/home/gnuhpc");
        assertEquals(simplifyPath("/.."), "/");
        assertEquals(simplifyPath("/"), "/");
        assertEquals(simplifyPath("/a/./b/../../c/"), "/c");
        assertEquals(simplifyPath("../"), "/");
    }
}
