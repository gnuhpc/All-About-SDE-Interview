package org.gnuhpc.bigdata.leetcode;

import org.gnuhpc.bigdata.leetcode.utils.ListNode;
import org.junit.Test;

/*这道题是Swap Nodes in Pairs的扩展，Swap Nodes in Pairs其实是这道题k=2的特殊情况，大家可以先练习一下。
不过实现起来还是比较不一样的，因为要处理比较general的情形。
基本思路是这样的，我们统计目前节点数量，如果到达k，就把当前k个结点reverse
这里需要reverse linked list的subroutine。
这里我们需要先往前走，到达k的时候才做reverse，
所以总体来说每个结点会被访问两次。总时间复杂度是O(2*n)=O(n)，空间复杂度是O(1)。*/
public class ReverseKGroup25 {
    @Test
    public void test(){
        ListNode n1 = new ListNode(1);
        ListNode n2 = new ListNode(2);
        ListNode n3 = new ListNode(3);
        ListNode n4 = new ListNode(4);
        ListNode n5 = new ListNode(5);
        ListNode n6 = new ListNode(6);

        n1.next = n2;
        n2.next = n3;
        n3.next = n4;
        n4.next = n5;
        n5.next = n6;
        n6.next = null;

        System.out.println(reverseKGroup(n1,2));
    }

    public ListNode reverseKGroup(ListNode head, int k) {
        if(head == null)
        {
            return null;
        }
        ListNode dummy = new ListNode(0);
        dummy.next = head;
        int count = 0;
        ListNode pre = dummy;
        ListNode cur = head;
        while(cur != null)
        {
            count ++;
            ListNode next = cur.next;
            if(count == k)
            {
                pre = reverse(pre, next);
                count = 0;
            }
            cur = next;
        }
        return dummy.next;
    }
    private ListNode reverse(ListNode pre, ListNode end)
    {
        if(pre==null || pre.next==null)
            return pre;
        ListNode head = pre.next;
        ListNode cur = pre.next.next;
        while(cur!=end)
        {
            ListNode next = cur.next;
            cur.next = pre.next;
            pre.next = cur;
            cur = next;
        }
        head.next = end;
        return head;
    }



}
