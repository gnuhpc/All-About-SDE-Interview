package org.gnuhpc.interview.designpattern.retry;

import java.net.SocketTimeoutException;
import java.util.Random;

public class MainApp {
    public static void main(String[] args) {
        String str = ExponentialBackOff.execute(() -> work());
        System.out.println(str);
    }

    private static String work() throws Exception {
        int randomID = new Random().nextInt(100) % 2;
        if (randomID == 1) {
            System.out.println("Opps! Wrong!");
            //throw the exception listed in Backoff Class or it will cause unknown exits.
            //uncomment the line below to test
            //throw new Exception(new IllegalStateException());
            throw new Exception(new SocketTimeoutException());
        } else {
            System.out.println("Get it right!");
            return "Done";
        }
    }
}
