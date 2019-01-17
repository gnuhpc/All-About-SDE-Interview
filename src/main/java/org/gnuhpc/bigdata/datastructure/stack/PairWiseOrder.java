package org.gnuhpc.bigdata.datastructure.stack;

import org.junit.Test;

import java.util.LinkedList;
import java.util.Queue;
import java.util.Stack;

public class PairWiseOrder {
    public boolean check(Stack<Integer> s){
        Queue<Integer> q = new LinkedList<>();

        boolean result = true;

        while (!s.isEmpty()){
            q.add(s.pop());
        }

        while (!q.isEmpty()){
            s.add(q.remove());
        }

        while (!s.isEmpty()){
            int n = s.pop();
            q.add(n);

            if (!s.isEmpty()){
                int m = s.pop();
                q.add(m);
                if(Math.abs(n-m)!=1){
                    result = false;
                    break;
                }
            }
        }
        return result;
    }

    @Test
    public void test(){
        int[] array = new int[]{4,5,-2,-3,11,10,5,6,20};
        Stack<Integer> s = new Stack<>();

        for (int i:array){
            s.push(i);
        }

        check(s);
    }
}
