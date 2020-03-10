package org.gnuhpc.interview.leetcode.solutions;

import java.util.LinkedList;
import java.util.List;

/**
 * Copyright gnuhpc 2019/12/29
 */
public class AddToArrayForm989 {
    //TODO 加法的通解
    public List<Integer> addToArrayForm(int[] A, int K) {
        LinkedList<Integer> list = new LinkedList<>();
        //如果数组长度大于0或者K大于0就进行相加,每次循环过后,K退十位
        for (int i = A.length - 1; i >= 0 || K > 0; K /= 10) {
            if (i >= 0) {        //排除数组为0的情况
                K += A[i];      //数组有元素的话,每次循环K都会+=数组末位的元素
                i--;            //如果定义在for()中,会出现无意义的自减
            }
            list.addFirst(K % 10);//添加K个位上的值进集合
        }
        if (list.size() == 0) list.add(0);//如果数组为空,K为0,不走循环,但两者相加的值为0
        return list;
    }
}
