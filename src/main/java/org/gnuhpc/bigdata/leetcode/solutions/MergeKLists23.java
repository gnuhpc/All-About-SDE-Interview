package org.gnuhpc.bigdata.leetcode.solutions;

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
        return helper(lists, 0, lists.length - 1);
    }

    public ListNode helper(ListNode[] lists, int l, int h) {
        if (l < h) {
            int mid = (l + h) / 2;
            return merge(helper(lists, l, mid), helper(lists, mid + 1, h));
        }
        return lists[l];
    }

    public ListNode merge(ListNode l1, ListNode l2) {
        ListNode dummy = new ListNode(0);
        ListNode cur = dummy;
        while (l1 != null && l2 != null) {
            if (l1.val < l2.val) {
                cur.next = l1;
                cur = cur.next;
                l1 = l1.next;
            }
            else {
                cur.next = l2;
                cur = cur.next;
                l2 = l2.next;
            }
        }
        if (l1 != null) {
            cur.next = l1;
        }
        if (l2 != null) {
            cur.next = l2;
        }
        return dummy.next;
    }

    /*
    Method 2: Use heap. Suppose we have k lists, and for each list, there are at most n elements.
    Maintain a list of k elements. And always remove the first element from the list.
    After removing an element, add the element after it to the heap.
    We have to insert at most k*n nodes, and for each node to be in order in the heap, we need log(k) time (“Bottom Up Insertion”).
    So total time complexity is also O(nklogk).
    这也是Spark SortShuffleWriter 的基本原理.我们可以先考虑一个问题，假如我有 100亿条数据，但是我们的内存只有1M，但是我们磁盘很大，
    我们现在要对这100亿条数据进行排序，是没法把所有的数据一次性的load进行内存进行排序的，这就涉及到一个外部排序的问题，
    我们的1M内存只能装进1亿条数据，每次都只能对这 1亿条数据进行排序，排好序后输出到磁盘，总共输出100个文件，
    最后怎么把这100个文件进行merge成一个全局有序的大文件。我们可以每个文件（有序的）都取一部分头部数据最为一个 buffer，
    并且把这 100个 buffer放在一个堆里面，进行堆排序，比较方式就是对所有堆元素（buffer）的head元素进行比较大小，
    然后不断的把每个堆顶的 buffer 的head 元素 pop 出来输出到最终文件中， 然后继续堆排序，继续输出。
    如果哪个buffer 空了，就去对应的文件中继续补充一部分数据。最终就得到一个全局有序的大文件。

    如果你能想通我上面举的例子，就差不多搞清楚sortshufflewirter的实现原理了，因为解决的是同一个问题。
     */

    public ListNode mergeKLists2(ListNode[] lists) {
        Queue<ListNode> heap = new PriorityQueue<>(10, Comparator.comparingInt(l -> l.val));

        for (int i = 0; i < lists.length; i++) {
            ListNode temp = lists[i];
            if (temp != null) {
                heap.offer(temp);
            }
        }
        ListNode dummy = new ListNode(0);
        ListNode cur = dummy;

        while (heap.size() != 0) {
            ListNode n = heap.poll();
            cur.next = n;
            cur = cur.next;
            if (n.next != null) {
                heap.offer(n.next);
            }
        }
        return dummy.next;
    }


}
