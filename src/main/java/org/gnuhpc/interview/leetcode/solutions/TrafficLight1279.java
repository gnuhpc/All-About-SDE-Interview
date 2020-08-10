package org.gnuhpc.interview.leetcode.solutions;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * Copyright gnuhpc 2020/8/8
 */
public class TrafficLight1279 {
    private final Lock lock = new ReentrantLock();
    private boolean lightStatus = true; //一开始就是A路绿灯

    public TrafficLight1279() {

    }

    public void carArrived(
            int carId,           // ID of the car
            int roadId,          // ID of the road the car travels on. Can be 1 (road A) or 2 (road B)
            int direction,       // Direction of the car
            Runnable turnGreen,  // Use turnGreen.run() to turn light to green on current road
            Runnable crossCar    // Use crossCar.run() to make car cross the intersection
    ) {

        lock.lock();
        try {
            if ((roadId == 1 && lightStatus == false) || (roadId == 2 && lightStatus == true)) {
                lightStatus = !lightStatus;
                turnGreen.run();
            }
            crossCar.run();
        } finally {
            lock.unlock();
        }
    }
}
