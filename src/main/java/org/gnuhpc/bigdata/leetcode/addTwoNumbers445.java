package org.gnuhpc.bigdata.leetcode;

import org.gnuhpc.bigdata.leetcode.utils.ListNode;
import org.junit.Test;

import java.util.Deque;
import java.util.LinkedList;
import java.util.Queue;

public class addTwoNumbers445 {
    public ListNode addTwoNumbers(ListNode l1, ListNode l2) {
        int l1Size = getLinkedListSize(l1);
        int l2Size = getLinkedListSize(l2);
        int lSize,sSize;
        ListNode lList, sList;

        if (l1Size>=l2Size){
            lSize = l1Size;
            sSize = l2Size;
            lList = l1;
            sList = l2;

        } else {
            lSize = l2Size;
            sSize = l1Size;
            lList = l2;
            sList = l1;
        }

        int diff = lSize - sSize;
        ListNode cur = sList;
        for (int i = 0; i < diff; i++) {
            ListNode newNode = new ListNode(0);
            newNode.next = cur;
            cur = newNode;
        }

        sList = cur;

        Deque<Integer> sums = new LinkedList<>();

        for (int i = 0; i < lSize; i++) {
            sums.push(sList.val + lList.val);
            sList = sList.next;
            lList = lList.next;
        }

        int carry=0,value=0;
        ListNode head = null, tail = null;
        while (!sums.isEmpty()){
            int pop = sums.pop();
            value = (pop + carry) %10;
            ListNode newNode = new ListNode(value);
            newNode.next = tail;
            tail = newNode;
            carry = (pop + carry) /10;
        }

        if (carry!=0){
            ListNode newNode = new ListNode(carry);
            newNode.next = tail;
            tail = newNode;
        }

        return tail;

    }

    private int getLinkedListSize(ListNode head){
        if (head==null){
            return 0;
        }

        ListNode cur = head;
        int size = 0;

        while (cur!=null){
            size++;
            cur = cur.next;
        }

        return size;
    }

    @Test
    public void test(){
       ListNode.print(addTwoNumbers(ListNode.createList(new int[]{1}),ListNode.createList(new int[]{9,9})));
    }
}
