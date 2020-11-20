package org.gnuhpc.interview.leetcode.solutions;

import org.gnuhpc.interview.datastructure.linkedlist.basicimpl.ListNode;
import org.junit.Test;

public class OddEvenList328 {

    public ListNode oddEvenList(ListNode head) {
        if (head==null || head.next ==null){
            return head;
        }
        // 分别定义奇偶链表的 虚拟头结点 和 尾结点
        ListNode oddHead = new ListNode();
        ListNode oddTail = oddHead;
        ListNode evenHead = new ListNode();
        ListNode evenTail = evenHead;
        // 遍历原链表，根据 isOdd 标识位决定将当前结点插入到奇链表还是偶链表（尾插法）
        boolean isOdd = true;
        while (head != null) {
            if (isOdd) {
                oddTail.next = head;
                oddTail = oddTail.next;
            } else {
                evenTail.next = head;
                evenTail = evenTail.next;
            }
            head = head.next;
            isOdd = !isOdd;
        }
        // 将奇链表后面拼接上偶链表，并将偶链表的next设置为null
        oddTail.next = evenHead.next;
        evenTail.next = null;
        return oddHead.next;
    }

    @Test
    public void test() {
        ListNode head = ListNode.createList(new int[]{});
        ListNode.print(oddEvenList(head));
    }
}
