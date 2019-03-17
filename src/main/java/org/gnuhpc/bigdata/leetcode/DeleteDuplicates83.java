package org.gnuhpc.bigdata.leetcode;

import org.gnuhpc.bigdata.leetcode.utils.ListNode;
import org.junit.Test;

import java.util.HashSet;
import java.util.Set;

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

    /**
     * 不考虑原链表是否有序，都适用
     * @param head
     * @return
     */
    public ListNode deleteDuplicates2(ListNode head) {
        if(head == null || head.next == null) return head;
        ListNode pre = head;
        ListNode cur = head.next;

        Set<Integer> set = new HashSet<>();
        set.add(pre.val);
        while(cur != null){
            if(set.contains(cur.val)) {
                cur = cur.next;
                pre.next = cur;
                //pre = cur;
            }else {
                set.add(cur.val);
                pre = cur;
                cur = cur.next;
            }
        }
        return head;
    }


    public ListNode deleteDuplicates3(ListNode head) {
        if(head == null || head.next == null) return head;
        ListNode pre = head;
        ListNode cur = head.next;

        while(cur != null){
            if(cur.val == pre.val){
                cur = cur.next;
                pre.next = cur;
            }else{
                pre = cur;
                cur = cur.next;
            }

        }
        return head;
    }
}
