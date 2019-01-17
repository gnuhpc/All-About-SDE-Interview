package org.gnuhpc.bigdata.leetcode;

import org.gnuhpc.bigdata.leetcode.utils.ListNode;
import org.junit.Test;

public class DeleteDuplicates82 {
    public ListNode deleteDuplicates(ListNode head) {
        //设置起始节点
        ListNode start = new ListNode(0);
        start.next = head;
        head  = start;

        while(head.next != null && head.next.next != null){
            if(head.next.val == head.next.next.val){
                int val = head.next.val;
                while (head.next != null && head.next.val == val) {
                    head.next = head.next.next;
                }
            }else{
                head = head.next;
            }
        }

        return start.next;
    }

    @Test
    public void test(){
        ListNode head = ListNode.createList(new int[]{1,1});

        ListNode.print(deleteDuplicates(head));
    }
}
