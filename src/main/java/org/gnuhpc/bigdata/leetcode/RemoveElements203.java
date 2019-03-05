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

    // dummyNode，非递归，无需对头结点是否是val做特殊处理
    public ListNode removeElements2(ListNode head, int val) {
        if(head==null) return head;
        ListNode dummy = new ListNode(0);
        ListNode pre = dummy;
        dummy.next = head;
        ListNode cur = head;

        while(cur != null){
            if(cur.val == val){
                cur = cur.next;
                pre.next = cur;
            }else{
                pre = cur;
                cur = cur.next;
            }
        }

        return dummy.next;


    }

    // 相比上一个方法，减少一个指针，但个人认为上面的结构更通用
    public ListNode removeElements3(ListNode head, int val) {
        if(head==null) return head;
        ListNode dummy = new ListNode(0);
        //ListNode pre = dummy;
        dummy.next = head;
        ListNode cur = dummy;

        while(cur.next != null){
            if(cur.next.val == val){
                cur.next = cur.next.next;
                //pre.next = cur;
            }else{
                //pre = cur;
                cur = cur.next;
            }
        }

        return dummy.next;

    }
}
