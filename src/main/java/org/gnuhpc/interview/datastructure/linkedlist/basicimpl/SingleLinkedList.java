package org.gnuhpc.interview.datastructure.linkedlist.basicimpl;

import lombok.NoArgsConstructor;

import java.util.NoSuchElementException;

import static java.lang.System.out;

/**
 * Created by IntelliJ IDEA.
 *
 * @author: ramswaroop
 * @date: 6/16/15
 * @time: 1:00 PM
 */

@NoArgsConstructor
public class SingleLinkedList<E extends Comparable<E>> implements LinkedList<E> {

    public SingleLinkedNode<E> head;

    @Override
    public boolean add(E item) {
        SingleLinkedNode<E> newNode = new SingleLinkedNode<>(item, null);
        if (head == null) { // list empty
            head = newNode;
        } else { // add to the end of list
            SingleLinkedNode<E> curr = head;
            while (curr.next != null) {
                curr = curr.next;
            }
            curr.next = newNode;
        }
        return true;
    }

    @Override
    public boolean add(int index, E item) {
        isPositionIndex(index);

        if (index == 0) { // add at first
            addFirst(item);
        } else { // add at any other location
            SingleLinkedNode<E> nodeAtPrevIndex = getPredecessorNode(index);
            SingleLinkedNode<E> newNode = new SingleLinkedNode<>(item, nodeAtPrevIndex.next);
            nodeAtPrevIndex.next = newNode;
        }
        return true;
    }

    @Override
    public void addFirst(E item) {
        SingleLinkedNode<E> newNode = new SingleLinkedNode<>(item, head);
        head = newNode;
    }

    @Override
    public void addLast(E item) {
        add(item);
    }

    @Override
    public void clear() {
        // Clearing all of the links between nodes is "unnecessary", but:
        // - helps a generational GC if the discarded nodes inhabit
        //   more than one generation
        // - is sure to free memory even if there is a reachable Iterator
        for (SingleLinkedNode<E> node = head; node != null; ) {
            SingleLinkedNode<E> next = node.next;
            node.item = null;
            node.next = null;
            node = next;
        }
        head = null;
    }

    @Override
    public LinkedList<E> clone() {
        return null;
    }

    @Override
    public boolean contains(E item) {
        return getNode(item) != null;
    }

    @Override
    public E get(int index) {
        return getNode(index).item;
    }

    @Override
    public E getFirst() {
        isLinkedListEmpty();
        return head.item;
    }

    @Override
    public E getLast() {
        return getNode(size() - 1).item;
    }

    public E getNthFromEnd(int idx) {
        SingleLinkedNode<E> left = head, right = head;

        for (int i = 0; i < idx; i++) {
            if (right == null) {
                throw new IllegalArgumentException();
            }
            right = right.next;
        }

        while (right != null) {
            left = left.next;
            right = right.next;
        }

        return left.item;
    }

    @Override
    public E remove() {
        isLinkedListEmpty();

        E item = head.item;
        head = head.next;
        return item;
    }

    @Override
    public E remove(int index) {
        isLinkedListEmpty();

        SingleLinkedNode<E> prevNode = getPredecessorNode(index);
        SingleLinkedNode<E> delNode;
        if (prevNode == null) { // index = 0
            delNode = head;
            head = head.next;
            return delNode.item;
        } else {
            delNode = prevNode.next;
            prevNode.next = delNode.next;
            return delNode.item;
        }
    }

    public SingleLinkedNode<E> removeBefore(SingleLinkedNode head, E value) {
        while (head != null && head.item == value) {
            head = head.next;
            return head;
        }

        if (head == null) {
            return null;
        }

        SingleLinkedNode prev = head;
        // Loop invariant: list nodes from head up to prev has been
        // processed. (Nodes with profits equal to val are deleted.)
        while (prev.next != null) {
            if (prev.next.item == value) {
                // delete it
                prev.next = prev.next.next;
            } else {
                prev = prev.next;
            }
        }

        return prev.next;
    }

    @Override
    public boolean removeItem(E item) {
        isLinkedListEmpty();

        if (!contains(item)) return false;

        SingleLinkedNode<E> prevNode = getPredecessorNode(item);
        if (prevNode == null) { // index = 0
            head = head.next;
        } else {
            prevNode.next = prevNode.next.next;
        }
        return true;
    }

    @Override
    public E set(int index, E item) {
        SingleLinkedNode<E> node = getNode(index);
        node.item = item;
        return node.item;
    }

    @Override
    public int size() {
        SingleLinkedNode p = head;
        int size = 0;
        while (p != null) {
            size++;
            p = p.next;
        }

        return size;
    }

    @Override
    public void printList() {
        printList(head);
    }

    public static <E extends Comparable<E>> void printList(SingleLinkedNode<E> node) {
        SingleLinkedNode<E> curr = node;
        out.print("[");
        if (curr == null) {
            out.println("]");
            return;
        }
        while (curr.next != null) {
            out.print(curr.item.toString() + ",");
            curr = curr.next;
        }
        out.println(curr.item.toString() + "]");
    }

    public static <E extends Comparable<E>> SingleLinkedList<E> getLinkedList(SingleLinkedNode<E> node) {
        SingleLinkedList<E> linkedList = new SingleLinkedList<>();
        // set head
        linkedList.head = node;
        // set size
        SingleLinkedNode<E> curr = node;
        while (curr != null) {
            curr = curr.next;
        }
        return linkedList;
    }

    private SingleLinkedNode<E> getPredecessorNode(int index) {
        return index > 0 ? getNode(index - 1) : null;
    }

    private SingleLinkedNode<E> getPredecessorNode(E item) {
        SingleLinkedNode<E> prev = null;
        SingleLinkedNode<E> curr = head;
        if (item == null) {
            while (curr != null) {
                if (curr.item == item) { // when item is null, use == rather than equals()
                    return prev;
                }
                prev = curr;
                curr = curr.next;
            }
        } else {
            while (curr != null) {
                if (curr.item.equals(item)) {
                    return prev;
                }
                prev = curr;
                curr = curr.next;
            }
        }
        return null;
    }

    public SingleLinkedNode<E> getNode(int index) {
        isElementIndex(index);

        SingleLinkedNode<E> curr = head;
        int i = 0;
        while (i < index) {
            curr = curr.next;
            i++;
        }
        return curr;
    }

    public SingleLinkedNode<E> getNode(E item) {
        SingleLinkedNode<E> curr = head;
        if (item == null) {
            while (curr != null) { // when item is null, use == rather than equals()
                if (curr.item == item) {
                    return curr;
                }
                curr = curr.next;
            }
        } else {
            while (curr != null) {
                if (curr.item.equals(item)) {
                    return curr;
                }
                curr = curr.next;
            }
        }
        return null;
    }

    private void isLinkedListEmpty() {
        if (head == null) {
            throw new NoSuchElementException("LinkedList empty");
        }
    }

    /**
     * Tells if the argument is the index of an existing element.
     */
    private void isElementIndex(int index) {
        if (index < 0 || index >= size()) {
            throw new IndexOutOfBoundsException("Index [" + index + "] must be less than size [" + size() + "]");
        }
    }

    /**
     * Tells if the argument is the index of a valid position for an
     * iterator or an add operation.
     */
    private void isPositionIndex(int index) {
        if (index < 0 || index > size()) {
            throw new IndexOutOfBoundsException("Index [" + index + "] must be less than or equal to size [" + size() + "]");
        }
    }


    public SingleLinkedList(int[] values) {
        SingleLinkedNode dummyHead = new SingleLinkedNode(-1);
        SingleLinkedNode p = dummyHead;

        for (int i = 0; i < values.length; i++) {
            SingleLinkedNode node = new SingleLinkedNode(values[i]);
            p.next = node;
            p = node;
        }

        head = dummyHead.next;
    }

    public void reverse() {
        head = reverseHelper(head);
    }

    private SingleLinkedNode<E> reverseHelper(SingleLinkedNode<E> head) {
        if (head == null || head.next == null)
            return head;
        SingleLinkedNode<E> prev = reverseHelper(head.next);
        head.next.next = head;
        head.next = null;
        return prev;
    }

    public void reverseNonRecusive() {
        if (head == null || head.next == null)
            return;

        SingleLinkedNode<E> next = null;
        SingleLinkedNode<E> temp = null;


        while (head != null) {
            next = head.next;
            head.next = temp;
            temp = head;
            head = next;
        }

        head = temp;
    }

}
