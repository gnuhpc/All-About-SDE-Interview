package org.gnuhpc.bigdata.leetcode;


import org.junit.Test;

public class ReverseList206 {
    public ListNode reverseList(ListNode head) {
        if(head==null||head.next ==null)
            return head;

        ListNode cur = head;
        ListNode pre = null, next = null;

        while (cur!=null){
            next = cur.next;
            cur.next = pre;
            pre = cur;
            cur = next;
        }

        return pre;
    }

    @Test
    public void test(){

        /*for(int i = 0;i<1;i++){
            System.out.println(i);
        }*/
        ListNode head = new ListNode(1);

        head.next = new ListNode(2);
        head.next.next = new ListNode(3);
        System.out.println(head.val);
        System.out.println(head.next.val);
        System.out.println(head.next.next.val);
        head = head.next;
        System.out.println(head.val);

    }

    public class ListNode {
        int val;
        ListNode next;
        ListNode(int x) { val = x; }
    }

}



