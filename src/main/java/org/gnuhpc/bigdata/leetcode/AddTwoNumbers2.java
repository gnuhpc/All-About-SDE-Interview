package org.gnuhpc.bigdata.leetcode;

import org.gnuhpc.bigdata.leetcode.utils.ListNode;
import org.junit.Test;

import java.math.BigInteger;

public class AddTwoNumbers2 {
    public ListNode addTwoNumbers(ListNode l1, ListNode l2) {
        int carry=0;
        ListNode listNode=new ListNode(0);
        ListNode p1=l1,p2=l2,p3=listNode;
        while(p1!=null||p2!=null){
            if(p1!=null){
                carry+=p1.val;
                p1=p1.next;
            }
            if(p2!=null){
                carry+=p2.val;
                p2=p2.next;
            }
            p3.next=new ListNode(carry%10);
            p3=p3.next;
            carry/=10;
        }
        if(carry==1)
            p3.next=new ListNode(1);
        return listNode.next;
    }

    @Test
    public void test(){
        ListNode.print(addTwoNumbers(ListNode.createList(new int[]{0}),ListNode.createList(new int[]{0})));
    }

    public ListNode addTwoNumbers2(ListNode l1, ListNode l2) {
        if(l1 == null && l2 == null) {
            return null;
        }

        ListNode head = new ListNode(0);
        ListNode point = head;
        int carry = 0;
        while(l1 != null && l2!=null){
            int sum = carry + l1.val + l2.val;
            point.next = new ListNode(sum % 10);
            carry = sum / 10;
            l1 = l1.next;
            l2 = l2.next;
            point = point.next;
        }

        while(l1 != null) {
            int sum =  carry + l1.val;
            point.next = new ListNode(sum % 10);
            carry = sum /10;
            l1 = l1.next;
            point = point.next;
        }

        while(l2 != null) {
            int sum =  carry + l2.val;
            point.next = new ListNode(sum % 10);
            carry = sum /10;
            l2 = l2.next;
            point = point.next;
        }

        if (carry != 0) {
            point.next = new ListNode(carry);
        }
        return head.next;
    }
}
