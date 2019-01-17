package org.gnuhpc.bigdata.leetcode;

import org.gnuhpc.bigdata.leetcode.utils.ListNode;
import org.junit.Test;

public class MergeTwoLists21 {
    public ListNode mergeTwoLists(ListNode l1, ListNode l2) {

        ListNode dummy = new ListNode(0);
        ListNode cur = dummy;

        while(l1 != null && l2 != null)
        {
            if(l1.val < l2.val)
            {
                cur.next = l1;
                l1 = l1.next;
            }
            else
            {
                cur.next = l2;
                l2 = l2.next;
            }

            cur = cur.next;
        }

        if(l1 == null)
        {
            cur.next = l2;
        }

        if(l2 == null)
        {
            cur.next = l1;
        }

        return  dummy.next;
    }

    public ListNode mergeTwoListsRecursively(ListNode l1, ListNode l2) {
        if(l1==null){
            return l2;
        }
        if(l2==null){
            return l1;
        }
        ListNode head = null;
        if(l1.val<l2.val){
            head = l1;
            head.next = mergeTwoLists(l1.next, l2);
        }else{
            head = l2;
            head.next = mergeTwoLists(l1, l2.next);
        }
        return head;
    }

    @Test
    public void test(){
        mergeTwoLists(ListNode.createList(new int[]{-9,3}),
                ListNode.createList(new int[]{5,7}));
    }
}
