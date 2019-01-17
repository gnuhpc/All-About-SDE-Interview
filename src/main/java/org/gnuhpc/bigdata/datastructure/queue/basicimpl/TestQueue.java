package org.gnuhpc.bigdata.datastructure.queue.basicimpl;

import org.junit.Test;

public class TestQueue
{

    /*
    * Sample - front
    * B
    * C
    * D
    * E - back
    *
    *
    * */
    //q.remove()
    //q.remove()
    //q.add(F)
    //q.add(G)

    /*
    * F
    * G - back
    * C - front
    * D
    * E
    * */
    @Test
    public void test(){
    	int queueSize = 10;

        String numbers[] = new String[queueSize];
        
        for (int i = 0 ; i < numbers.length ; i++ )
        	numbers[i] = String.format("%02d", ((int)(Math.random()*100)));
    	
        Queue queue = new CircularArrayQueue();
        
        // Fill the Circular Queue
        for (String number : numbers)
        	queue.add(number);
        
        System.out.println("Queue Size: " + queue.size());

        queue.print();
        
        String var = "";
        
        var = (String) queue.remove();
        
        System.out.println("Dequeue: " + var + " : new queue size: " + queue.size());
        
        queue.add(var);
        System.out.println("Re-enqueue: " + var + " : new queue size: " + queue.size());
        
        // Clear the Circular Queue
        while (queue.size() > 1)
            System.out.println("Dequeue: " + queue.remove() + " : New queue Size: " + queue.size());

        System.out.println("Final Queue Size: " + queue.size());
    }
}

