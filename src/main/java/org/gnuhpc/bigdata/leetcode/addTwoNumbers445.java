package org.gnuhpc.bigdata.leetcode;

import org.gnuhpc.bigdata.leetcode.utils.ListNode;
import org.junit.Test;

import java.util.Deque;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Stack;

public class addTwoNumbers445 {
    public ListNode addTwoNumbers(ListNode l1, ListNode l2) {
        int l1Size = getLinkedListSize(l1);
        int l2Size = getLinkedListSize(l2);
        int lSize,sSize;
        ListNode lList, sList;

        if (l1Size>=l2Size){
            lSize = l1Size;
            sSize = l2Size;
            lList = l1;
            sList = l2;

        } else {
            lSize = l2Size;
            sSize = l1Size;
            lList = l2;
            sList = l1;
        }

        int diff = lSize - sSize;
        ListNode cur = sList;
        for (int i = 0; i < diff; i++) {
            ListNode newNode = new ListNode(0);
            newNode.next = cur;
            cur = newNode;
        }

        sList = cur;

        Deque<Integer> sums = new LinkedList<>();

        for (int i = 0; i < lSize; i++) {
            sums.push(sList.val + lList.val);
            sList = sList.next;
            lList = lList.next;
        }

        int carry=0,value=0;
        ListNode head = null, tail = null;
        while (!sums.isEmpty()){
            int pop = sums.pop();
            value = (pop + carry) %10;
            ListNode newNode = new ListNode(value);
            newNode.next = tail;
            tail = newNode;
            carry = (pop + carry) /10;
        }

        if (carry!=0){
            ListNode newNode = new ListNode(carry);
            newNode.next = tail;
            tail = newNode;
        }

        return tail;

    }

    private int getLinkedListSize(ListNode head){
        if (head==null){
            return 0;
        }

        ListNode cur = head;
        int size = 0;

        while (cur!=null){
            size++;
            cur = cur.next;
        }

        return size;
    }

    @Test
    public void test(){
       ListNode.print(addTwoNumbers(ListNode.createList(new int[]{1}),ListNode.createList(new int[]{9,9})));
    }

    //用2个栈，同时注意结果链表的顺序
    public ListNode addTwoNumbers2(ListNode l1, ListNode l2) {
        if (l1 == null)
            return l2;
        if (l2 == null)
            return l1;
        Stack<Integer> s1 = new Stack<>();
        Stack<Integer> s2 = new Stack<>();
        while (l1 != null) {
            s1.push(l1.val);
            l1 = l1.next;
        }
        while (l2 != null) {
            s2.push(l2.val);
            l2 = l2.next;
        }
        int sum = 0;
        // head的下一个结点为curNode
        ListNode curNode = new ListNode(0);
        while (!s1.isEmpty() || !s2.isEmpty()) {
            if (!s1.isEmpty())
                sum += s1.pop();
            if (!s2.isEmpty())
                sum += s2.pop();
            // head.val存储进制位，head.val可能为0
            ListNode head = new ListNode(sum / 10);
            // curNode存储结果
            curNode.val = sum % 10;
            head.next = curNode;
            // curNode往前移动，指向head
            curNode = head;
            // 此时sum存储的是进制位
            // 下次计算需要用到
            sum /= 10;
        }
        // 前导0的情况,
        // curNode为head的引用，可能为0
        if (curNode.val == 0)
            curNode = curNode.next;
        return curNode;

    }

    // 用3个栈，与上面比较，结果先用栈存起来，然后转换成链表203
    public ListNode addTwoNumbers3(ListNode l1, ListNode l2) {
        if (l1 == null || l2 == null) {
            return l1 == null ? l2 : l1;
        }
        Stack<ListNode> s1 = new Stack<ListNode>();
        Stack<ListNode> s2 = new Stack<ListNode>();
        Stack<ListNode> result = new Stack<ListNode>();
        while (l1 != null) {
            s1.push(l1);
            l1 = l1.next;
        }
        while (l2 != null) {
            s2.push(l2);
            l2 = l2.next;
        }

        // sum up
        int carry = 0;
        while (!s1.isEmpty() || !s2.isEmpty()) {
            int sum = carry;
            if (!s1.isEmpty()) {
                sum += s1.pop().val;
            }
            if (!s2.isEmpty()) {
                sum += s2.pop().val;
            }
            carry = sum / 10;
            sum = sum % 10;
            result.push(new ListNode(sum));
        }
        if (carry != 0) {
            result.push(new ListNode(carry));
        }

        // Convert to list
        ListNode node = new ListNode(-1);
        ListNode dummy = node;
        while (!result.isEmpty()) {
            node.next = result.pop();
            node = node.next;
        }
        return dummy.next;
    }
}
