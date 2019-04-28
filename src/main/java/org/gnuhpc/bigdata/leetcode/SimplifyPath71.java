package org.gnuhpc.bigdata.leetcode;

import junit.framework.Assert;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Deque;
import java.util.LinkedList;
import java.util.stream.Collectors;

public class SimplifyPath71 {
    public String simplifyPath(String path) {
        StringBuilder sb = new StringBuilder();
        String[] sArray = path.split("/");
        Deque<String> deque = new LinkedList<>();

        for(String s: sArray){
            if (s.equals("..")) {
                if (!deque.isEmpty()) deque.removeLast(); // handle the situation: /.., nothing got pop
            }
            else if (s.equals(".") || s.isEmpty()) continue;
            else deque.addLast(s);
        }

        return "/"+ String.join("/",
                new LinkedList<>(deque)
        );

//        if (deque.isEmpty()){ // handle the empty stack -> /
//            sb.append("/");
//        } else{
//            while (!deque.isEmpty()){
//                // take advantage of the Deque in Java, a little bit tricky
//                // you can also use another stack to reverse the elements in the original stack
//                sb.append("/").append(deque.pollLast());
//            }
//        }
//
//        return sb.toString();
    }

    @Test
    public void test(){
        Assert.assertEquals(simplifyPath("/home//gnuhpc/"), "/home/gnuhpc");
        Assert.assertEquals(simplifyPath("/.."), "/");
        Assert.assertEquals(simplifyPath("/"),"/");
        Assert.assertEquals(simplifyPath("/a/./b/../../c/"),"/c");
        Assert.assertEquals(simplifyPath("../"),"/");
    }

    public String simplifyPath2(String path) {
        String result = "/";
        String[] stubs = path.split("/+");
        ArrayList<String> paths = new ArrayList<String>();
        for (String s : stubs) {
            if (s.equals("..")) {
                if (paths.size() > 0) {
                    paths.remove(paths.size() - 1);
                }
            }
            else if (!s.equals(".") && !s.equals("")) {
                paths.add(s);
            }
        }
        for (String s : paths) {
            result += s + "/";
        }
        if (result.length() > 1)
            result = result.substring(0, result.length() - 1);
        return result;
    }
}
