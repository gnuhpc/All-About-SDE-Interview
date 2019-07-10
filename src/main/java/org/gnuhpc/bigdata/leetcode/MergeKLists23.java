package org.gnuhpc.bigdata.leetcode;

import org.gnuhpc.bigdata.leetcode.utils.ListNode;

import java.util.Comparator;
import java.util.PriorityQueue;
import java.util.Queue;

public class MergeKLists23 {
    /*
    Method1 : MergeSort
    Suppose there are k lists, and there are n elements inside each list.
    The time complexity is O(nklogk).
     */
    public ListNode mergeKLists(ListNode[] lists) {
        if (lists == null || lists.length == 0) return null;
        return helper(lists, 0, lists.length-1);
    }

    public ListNode helper (ListNode[] lists, int l, int h){
        if (l < h){
            int mid = (l + h)/2;
            return merge(helper(lists, l, mid), helper(lists, mid+1, h));
        }
        return lists[l];
    }

    public ListNode merge(ListNode l1, ListNode l2){
        ListNode dummy = new ListNode(0);
        ListNode cur = dummy;
        while (l1 != null && l2 != null){
            if (l1.val < l2.val){
                cur.next = l1;
                cur = cur.next;
                l1 = l1.next;
            }else{
                cur.next = l2;
                cur = cur.next;
                l2 = l2.next;
            }
        }
        if (l1 != null){
            cur.next = l1;
        }
        if (l2 != null){
            cur.next = l2;
        }
        return dummy.next;
    }

    /*
    Method 2: Use heap. Suppose we have k lists, and for each list, there are at most n elements.

Maintain a list of k elements. And always remove the first element from the list.
 After removing an element, add the element after it to the heap.

 We have to insert at most k*n nodes, and for each node to be in order in the heap, we need log(k) time (“Bottom Up Insertion”).
  So total time complexity is also knlog(k).

     */


    public ListNode mergeKLists2(ListNode[] lists) {
        Queue<ListNode> heap = new PriorityQueue<ListNode>(10, Comparator.comparingInt(l -> l.val));

        for (int i = 0; i < lists.length; i++){
            ListNode temp = lists[i];
            if (temp != null){
                heap.offer(temp);
            }
        }
        ListNode dummy = new ListNode(0);
        ListNode cur = dummy;

        while (heap.size() != 0){
            ListNode n = heap.poll();
            cur.next = n;
            cur = cur.next;
            if (n.next != null){
                heap.offer(n.next);
            }
        }
        return dummy.next;
    }


}
