package org.gnuhpc.bigdata.leetcode;

import org.gnuhpc.bigdata.leetcode.utils.ListNode;

public class PartitionList86 {
    public ListNode partition(ListNode head, int x) {
        ListNode dummy1 = new ListNode(0), dummy2 = new ListNode(0);  //dummy heads of the 1st and 2nd queues
        ListNode curr1 = dummy1, curr2 = dummy2;      //current tails of the two queues;
        while (head!=null){
            if (head.val<x) {
                curr1.next = head;
                curr1 = head;
            }else {
                curr2.next = head;
                curr2 = head;
            }
            head = head.next;
        }
        curr2.next = null;          //important! avoid cycle in linked list. otherwise u will get TLE.
        curr1.next = dummy2.next;
        return dummy1.next;
    }


    public ListNode partition2(ListNode head, int x) {
        if(head ==null || head.next == null) return head;
        ListNode dummy = new ListNode(0);
        dummy.next = head;
        ListNode cur = dummy;
        while(cur.next.val < x ){
            cur = cur.next;
            if(cur.next == null) return head;
        }
        ListNode pre = cur;
        ListNode post = cur.next;
        ListNode rhead = post;
        cur = post.next;
        while(cur != null){
            if(cur.val < x) {
                pre.next = cur;
                pre = cur;
            }else{
                post.next = cur;
                post = cur;
            }
            cur = cur.next;
        }
        pre.next = rhead;
        post.next = null;  // 注意尾指针必须将post指向null，否则memory limit
        return dummy.next;
    }

    public ListNode partition3(ListNode head, int x) {
        if (head == null) {
            return null;
        }

        ListNode leftDummy = new ListNode(0);
        ListNode rightDummy = new ListNode(0);
        ListNode left = leftDummy, right = rightDummy;

        while (head != null) {
            if (head.val < x) {
                left.next = head;
                left = head;
            } else {
                right.next = head;
                right = head;
            }
            head = head.next;
        }

        right.next = null;
        left.next = rightDummy.next;
        return leftDummy.next;
    }
}
