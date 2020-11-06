package org.gnuhpc.interview.leetcode.solutions;

import java.util.HashMap;
import java.util.Map;

/**
 * Copyright gnuhpc 2020/10/17
 */
public class ParkingSystem1603 {
    Map<Integer, Integer> slots;

    public ParkingSystem1603(int big, int medium, int small) {
        slots = new HashMap<>();
        slots.put(1, big);
        slots.put(2, medium);
        slots.put(3, small);
    }

    public boolean addCar(int carType) {
        int count = slots.get(carType);
        if (count > 0) {
            count--;
            slots.put(carType, count);
            return true;
        } else {
            return false;
        }
    }
}
