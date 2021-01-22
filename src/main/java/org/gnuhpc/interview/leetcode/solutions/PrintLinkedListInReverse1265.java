package org.gnuhpc.interview.leetcode.solutions;

/**
 * Copyright gnuhpc 2021/1/14
 */
public class PrintLinkedListInReverse1265 {
    class ImmutableListNode {
        public ImmutableListNode getNext() {
            return null;
        }

        public void printValue() {
        }
    }

    public void printLinkedListInReverse(ImmutableListNode head) {
        if (head.getNext() == null) {
            head.printValue();
            return;
        }

        printLinkedListInReverse(head.getNext());
        head.printValue();
    }
}
