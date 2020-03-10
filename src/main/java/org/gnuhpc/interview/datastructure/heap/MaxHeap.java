package org.gnuhpc.interview.datastructure.heap;


//最大堆，完全二叉树，要求根节点的值既大于或等于左子树的值，又大于或等于右子树的值。
public class MaxHeap extends Heap {

    public MaxHeap() {
        super();
    }

    public MaxHeap(int capacity) {
        super(capacity);
    }

    public MaxHeap(int[] sources) {
        super(sources);
    }

    protected void heapify_down(int i) {
        // get left and right child of node at index i
        int left = LEFT(i);
        int right = RIGHT(i);

        int largest = i;

        // compare storage.get(i) with its left and right child
        // and find largest value
        if (left < size() && storage.get(left) > storage.get(i))
            largest = left;

        if (right < size() && storage.get(right) > storage.get(largest))
            largest = right;

        if (largest != i) {
            // swap with child having greater value
            swap(i, largest);

            // call heapify-down on the child
            heapify_down(largest);
        }
    }

    // Recursive Heapify-up procedure
    protected void heapify_up(int i) {
        // check if node at index i and its PARENT violates ，大于父节点就交换，然后沿着父节点接着向上走
        // the heap property
        if (i > 0 && storage.get(PARENT(i)) < storage.get(i)) {
            // swap the two if heap property is violated
            swap(i, PARENT(i));

            // call Heapify-up on the PARENT
            heapify_up(PARENT(i));
        }
    }


    // Program for Max Heap Implementation in Java
    public static void main(String[] args) {
        // create a Priority Queue of initial capacity 10
        // Priority of an element is decided by element's value
        MaxHeap pq = new MaxHeap(10);

        pq.build(new int[]{1, 5, 4, 6, 8, 9});
        System.out.println("The initiating PQ is now:");
        pq.print();

        // insert three integers
        System.out.println("Adding three elements in this PQ...");
        pq.add(3);
        pq.add(2);
        pq.add(15);

        // print Priority Queue size
        System.out.println("Priority Queue Size is " + pq.size());
        System.out.println("The added PQ is now:");
        pq.print();

        // search 2 in Priority Queue
        Integer searchKey = 2;

        if (pq.contains(searchKey))
            System.out.println("Priority Queue contains " + searchKey + "\n");

        // empty queue
        pq.clear();

        if (pq.isEmpty())
            System.out.println("Queue is Empty");

        System.out.println("\nCalling remove operation on an empty heap");
        System.out.println("Element with highest priority is " + pq.poll());

        System.out.println("\nCalling peek operation on an empty heap");
        System.out.println("Element with highest priority is " + pq.peek());

        // again insert three integers

        pq.build(new int[]{45, 5, 4});

        // construct array containing all elements present in the queue
        Integer[] I = pq.toArray();
        System.out.print("\nPrinting array: ");
        for (int i : I)
            System.out.print(i + " ");

        System.out.println("\n\nElement with highest priority is " + pq.poll());
        System.out.println("Element with highest priority is " + pq.peek());
    }

}
