package org.gnuhpc.bigdata.leetcode;

import org.gnuhpc.bigdata.leetcode.utils.ListNode;
import org.junit.Test;

import java.util.List;

public class RemoveElements203 {
    public ListNode removeElements(ListNode head, int val) {
        ListNode dummy = new ListNode(0);
        dummy.next = head;

        ListNode cur = dummy;

        while (cur.next!=null){
            if (cur.next.val == val){
                cur.next = cur.next.next;
            }
            else{
                cur = cur.next;
            }
        }

        return dummy.next;
    }

    public ListNode removeElementsRecursively(ListNode head, int val) {
        if (head == null) return null;
        head.next = removeElements(head.next, val);
        return head.val == val ? head.next : head;
    }

    @Test
    public void test(){
        ListNode head = ListNode.createList(new int[]{1,1});

        removeElements(head,1);
    }
}
