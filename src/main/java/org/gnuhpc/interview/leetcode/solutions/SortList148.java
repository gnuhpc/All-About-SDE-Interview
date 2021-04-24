package org.gnuhpc.interview.leetcode.solutions;

import org.gnuhpc.interview.datastructure.linkedlist.basicimpl.ListNode;

public class SortList148 {
    //归并排序，
    public ListNode sortList(ListNode head) {
        if (head == null || head.next == null)
            return head;

        // step 1. cut the list to two halves
        ListNode prev = visitMiddlePreOneNode(head);
        ListNode second = prev.next;
        prev.next = null;

        // step 2. sort each half  -- O(nlogn)
        ListNode l1 = sortList(head);
        ListNode l2 = sortList(second);


        // step 3. merge l1 and l2 -- O(nlogn)
        return merge(l1, l2);
    }

    private ListNode visitMiddlePreOneNode(ListNode head) {
        ListNode slow = head, fast = head;
        while (fast != null && fast.next != null && fast.next.next != null) {
            slow = slow.next;
            fast = fast.next.next;
        }

        return slow;
    }

    ListNode merge(ListNode l1, ListNode l2) {
        if (l1 == null) {
            return l2;
        }
        if (l2 == null) {
            return l1;
        }
        ListNode head = null;
        if (l1.val < l2.val) {
            head = l1;
            head.next = merge(l1.next, l2);
        } else {
            head = l2;
            head.next = merge(l1, l2.next);
        }
        return head;
    }

    /*
    Method2: Quicksort (change value)
     */

    public ListNode sortList2(ListNode head) {
        quickSort(head, null);
        return head;
    }

    void quickSort(ListNode head, ListNode tail) {
        if (head == tail || head.next == tail) return;
        int pivot = head.val;
        ListNode left = head, cur = head.next;

        while (cur != tail) {
            if (cur.val < pivot) {
                left = left.next;
                swap(left, cur);
            }
            cur = cur.next;
        }
        swap(head, left);
        quickSort(head, left);
        quickSort(left.next, tail);
    }

    private void swap(ListNode n1, ListNode n2) {
        int tmp = n1.val;
        n1.val = n2.val;
        n2.val = tmp;
    }

    /*
    Method3: Quicksort without exchange value LTE
     */

    public ListNode sortList3(ListNode head) {
        return quickSort(head);
    }

    ListNode quickSort(ListNode head) {
        if (head == null || head.next == null) return head;

        int pivot = head.val;
        // 链表划分
        ListNode ls = new ListNode(-1), rs = new ListNode(-1);
        ListNode l = ls, r = rs, cur = head;

        while (cur != null) {
            if (cur.val < pivot) {
                l.next = cur;
                l = l.next;
            } else {
                r.next = cur;
                r = r.next;
            }
            cur = cur.next;
        }
        l.next = rs.next;
        r.next = null;

        // 递归调用,先重排右边的,再把指针置空,再重排左边的
        // 和归并排序很像的
        ListNode right = quickSort(head.next);
        head.next = null;
        ListNode left = quickSort(ls.next);

        // 拼接左半部分和右半部分
        cur = left;
        while (cur.next != null) {
            cur = cur.next;
        }
        cur.next = right;
        return left;

    }
}
