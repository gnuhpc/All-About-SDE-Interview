package org.gnuhpc.interview.leetcode.solutions;

import org.gnuhpc.interview.datastructure.linkedlist.basicimpl.ListNode;

public class MergeInBetween1669 {
    public ListNode mergeInBetween(ListNode list1, int a, int b, ListNode list2) {
        ListNode head = list1;
        for(int i = 0;i<a-1;i++){
            head = head.next;
        }

        ListNode tail = head;
        for(int i = 0;i<b-a+2;i++){
            tail = tail.next;
        }

        head.next = list2;
        ListNode cur = list2;
        while (cur.next != null){
            cur = cur.next;
        }
        cur.next = tail;
        return list1;

    }
}
