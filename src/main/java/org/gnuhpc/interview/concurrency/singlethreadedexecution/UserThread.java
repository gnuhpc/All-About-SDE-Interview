package org.gnuhpc.interview.concurrency.singlethreadedexecution;

public class UserThread extends Thread {
    private final Gate gate;
    private final String myname;
    private final String myaddress;

    public UserThread(Gate gate, String myname, String myaddress) {
        this.gate = gate;
        this.myname = myname;
        this.myaddress = myaddress;
    }

    public void run() {
        System.out.println(myname + " BEGIN");
        while (true) {
            try {
                gate.pass(myname, myaddress);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            gate.passHash(myname, myaddress);
        }
    }
}
