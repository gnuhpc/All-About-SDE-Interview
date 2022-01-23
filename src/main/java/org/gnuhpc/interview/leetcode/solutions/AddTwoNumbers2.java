package org.gnuhpc.interview.leetcode.solutions;

import org.gnuhpc.interview.datastructure.linkedlist.basicimpl.ListNode;
import org.junit.Test;

/*
## Time complexity

`O(max(m,n))` where `m` and `n` are length of `l1` and `l2` separately.

## Space complexity

`O(1)`
 */

//DONE
public class AddTwoNumbers2 {
    public ListNode addTwoNumbers(ListNode l1, ListNode l2) {
        int carry = 0;
        ListNode dummy = new ListNode(0);
        ListNode p1 = l1, p2 = l2, p3 = dummy;
        while (p1 != null || p2 != null) {
            if (p1 != null) {
                carry += p1.val;
                p1 = p1.next;
            }
            if (p2 != null) {
                carry += p2.val;
                p2 = p2.next;
            }
            p3.next = new ListNode(carry % 10);
            p3 = p3.next;
            carry /= 10;
        }

        //还有一个剩余进位
        if (carry == 1)
            p3.next = new ListNode(1);
        return dummy.next;
    }

    //Method: in-place modified
    public ListNode addTwoNumbers2(ListNode l1, ListNode l2) {
        addTwoNumbers(l1, l2, 0);
        return l1;
    }

    public void addTwoNumbers(ListNode l1, ListNode l2, int ll) {
        ll = l1.val + l2.val + ll;
        l1.val = ll < 10 ? ll : ll - 10;
        if (l1.next != null && l2.next != null) {
            addTwoNumbers(l1.next, l2.next, ll / 10);
            return;
        }
        if (l2.next != null) {
            l1.next = l2.next;
        }
        while (l1.next != null && ll > 9) {
            l1 = l1.next;
            ll = l1.val + 1;
            l1.val = ll < 10 ? ll : ll - 10;
        }
        if (ll > 9) {
            l1.next = new ListNode(1);
        }
    }


    @Test
    public void test() {
        ListNode.print(addTwoNumbers(ListNode.createList(new int[]{0}), ListNode.createList(new int[]{0})));
    }
}
