package org.gnuhpc.interview.leetcode.solutions;

import org.gnuhpc.interview.datastructure.linkedlist.basicimpl.ListNode;

import java.util.Random;

/**
 * Copyright gnuhpc 2019/10/2
 */

/*
Reservoir Sampling for follow-up
 */
public class RandomLinkedList3822 {

    /*
    这题属于数学题，如何在长度未知的序列（数据流）中随机选择一个元素出来？

结论：当你遇到第 i 个元素时，应该有 1/i 的概率选择该元素，1 - 1/i 的概率保持原有的选择。
     */
    ListNode head;
    Random r = new Random();

    public RandomLinkedList3822(ListNode head) {
        this.head = head;
    }

    /* 返回链表中一个随机节点的值 */
    int getRandom() {
        int i = 0, res = 0;
        ListNode p = head;
        // while 循环遍历链表
        while (p != null) {
            i++;
            // 生成一个 [0, i) 之间的整数
            // 这个整数等于 0 的概率就是 1/i
            if (0 == r.nextInt(i)) {
                res = p.val;
            }
            p = p.next;
        }
        return res;
    }


}
