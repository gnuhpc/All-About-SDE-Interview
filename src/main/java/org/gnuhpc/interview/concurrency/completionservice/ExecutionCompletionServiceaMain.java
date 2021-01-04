package org.gnuhpc.interview.concurrency.completionservice;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.*;

/*
As you can see with the output, All four tasks started simuntaneously
but We retrieved the results based on the completion order
with the help of executorCompletionService.take().get()
rather than calling get() method on future object.
 */
public class ExecutionCompletionServiceaMain {
    public static void main(String[] args) {
        MultiplyingTask multiplyingTask1= new MultiplyingTask("Task 1",10,20,2000l);
        MultiplyingTask multiplyingTask2= new MultiplyingTask("Task 2",30,40,4000l);
        MultiplyingTask multiplyingTask3= new MultiplyingTask("Task 3",40,50,3000l);
        MultiplyingTask multiplyingTask4= new MultiplyingTask("Task 4",50,60,1000l);

        ExecutorService executorService = Executors.newFixedThreadPool(4);
        CompletionService<Integer> executorCompletionService= new ExecutorCompletionService<>(executorService);
        List<Future<Integer>> futures = new ArrayList<>();
        futures.add(executorCompletionService.submit(multiplyingTask1));
        futures.add(executorCompletionService.submit(multiplyingTask2));
        futures.add(executorCompletionService.submit(multiplyingTask3));
        futures.add(executorCompletionService.submit(multiplyingTask4));

        for (int i=0; i<futures.size(); i++) {
            try {
                Integer result = executorCompletionService.take().get();
                System.out.println("Result: " +result);
            } catch (InterruptedException e) {
                e.printStackTrace();
            } catch (ExecutionException e) {
                e.printStackTrace();
            }
        }
        executorService.shutdown();
    }
}
