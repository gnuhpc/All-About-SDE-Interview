package org.gnuhpc.bigdata.leetcode;

import org.gnuhpc.bigdata.leetcode.utils.ListNode;
import org.junit.Test;

public class IsPalindromeLinkedList {
    @Test
    public void test(){
        ListNode n1 = new ListNode(1);
        ListNode n2 = new ListNode(2);
        ListNode n3 = new ListNode(1);
        ListNode n4 = new ListNode(1);

        n1.next = n2;
        n2.next = n3;
        n3.next = n4;
        n4.next = null;

        System.out.println(isPalindrome(n1));
    }

    public boolean isPalindrome(ListNode head) {
        boolean result = false;
        if (head==null||head.next==null){
            return true;
        }

        ListNode middleNode = visitMiddle(head);
        ListNode secondHead = reverseList(middleNode);

        ListNode c1 = head;
        ListNode c2 = secondHead;

        while (c1!=null && c2!=null){
            if (c1.val != c2.val){
                return false;
            }

            c1 = c1.next;
            c2 = c2.next;
        }

        reverseList(middleNode);

        return true;

    }

    private ListNode visitMiddle(ListNode head) {
        ListNode fast = head;
        ListNode slow = head;
        boolean pace = true;

        while(fast.next != null){
            if (pace){
                fast = fast.next;
                slow = slow.next;
            } else{
                fast = fast.next;
            }

            pace = !pace;

        }

        return slow;
    }

    public ListNode reverseList(ListNode head) {
        if(head==null||head.next ==null)
            return head;

        ListNode next = null;
        ListNode temp = null;


        while (head!=null){
            next = head.next;
            head.next = temp;
            temp = head;
            head = next;
        }

        return temp;
    }
}
