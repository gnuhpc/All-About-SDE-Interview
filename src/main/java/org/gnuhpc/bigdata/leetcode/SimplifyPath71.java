package org.gnuhpc.bigdata.leetcode;

import junit.framework.Assert;
import org.junit.Test;

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
}
