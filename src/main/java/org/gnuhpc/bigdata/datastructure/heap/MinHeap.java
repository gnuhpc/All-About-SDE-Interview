package org.gnuhpc.bigdata.datastructure.heap;


// Class for implementing Priority Queue
public class MinHeap extends Heap
{

    // constructor: use default initial capacity of vector
    public MinHeap() {
        super();
    }

    // constructor: set custom initial capacity for vector
    public MinHeap(int capacity) {
        super(capacity);
    }

    public MinHeap(int[] sources){
        super(sources);
    }


    protected void heapify_down(int i)
    {
        // get left and right child of node at index i
        int left = LEFT(i);
        int right = RIGHT(i);

        int smallest = i;

        // compare storage.get(i) with its left and right child
        // and find smallest value
        if (left < size() && storage.get(left) < storage.get(i))
            smallest = left;

        if (right < size() && storage.get(right) < storage.get(smallest))
            smallest = right;

        if (smallest != i)
        {
            // swap with child having lesser value
            swap(i, smallest);

            // call heapify-down on the child
            heapify_down(smallest);
        }
    }

    // Recursive Heapify-up procedure, 向上整理堆
    protected void heapify_up(int i)
    {
        // check if node at index i and its PARENT violates
        // the heap property
        if (i > 0 && storage.get(PARENT(i)) > storage.get(i))
        {
            // swap the two if heap property is violated
            swap(i, PARENT(i));

            // call Heapify-up on the PARENT
            heapify_up(PARENT(i));
        }
    }

    // Program for Min Heap Implementation in Java
    public static void main (String[] args)
    {
        // create a Priority Queue of initial capacity 10
        // Priority of an element is decided by element's value
        MinHeap pq = new MinHeap(10);

        pq.build(new int[]{1,5,4,6,8,9});
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

        pq.build(new int[]{45,5,4});

        // construct array containing all elements present in the queue
        Integer[] I = pq.toArray();
        System.out.print("\nPrinting array: ");
        for (int i : I)
            System.out.print(i + " ");

        System.out.println("\n\nElement with highest priority is " + pq.poll());
        System.out.println("Element with highest priority is " + pq.peek());
    }
}
