package org.gnuhpc.bigdata.leetcode;

import org.gnuhpc.bigdata.leetcode.utils.ListNode;
import org.junit.Test;

public class DeleteDuplicates83 {
    public ListNode deleteDuplicates(ListNode head) {
        if (head == null || head.next == null){
            return head;
        }

        ListNode cur = head;
        ListNode next = cur.next;

        while(next!=null){
            while(cur.val == next.val){
                cur.next = next.next;
                next = next.next;
                if (next==null){
                    return head;
                }
            }

            cur = next;
            next = next.next;
        }
        return head;
    }

    @Test
    public void test(){
        ListNode head = ListNode.createList(new int[]{1,1,2,3,3});

        ListNode.print(deleteDuplicates(head));
    }
}
