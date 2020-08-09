package org.gnuhpc.interview.concurrency.waitnotify;

/*
Wait
Simply put, when we call wait() – this forces the current thread to wait until some other thread invokes notify() or notifyAll() on the same object.

For this, the current thread must own the object's monitor.
 */
public class Data {
    private String packet;
    
    // True if receiver should wait
    // False if sender should wait
    private boolean transfer = true;
 
    public synchronized String receive() {
        while (transfer) {
            try {
                wait();
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt(); 
                System.out.println("Thread Interrupted");
            }
        }
        transfer = !transfer;

        notifyAll();
        return packet;
    }
 
    public synchronized void send(String packet) {
        while (!transfer) {
            try { 
                wait();
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt(); 
                System.out.println("Thread Interrupted");
            }
        }
        //Here transfer is true
        transfer = !transfer;
        
        this.packet = packet;
        notifyAll();
    }
}