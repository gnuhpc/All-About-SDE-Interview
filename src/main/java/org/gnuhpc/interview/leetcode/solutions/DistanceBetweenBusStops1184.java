package org.gnuhpc.interview.leetcode.solutions;

/**
 * Copyright gnuhpc 2021/3/25
 */
public class DistanceBetweenBusStops1184 {
    public int distanceBetweenBusStops(int[] distance, int start, int destination) {
        int rdistance = 0;
        int ldistance = 0;
        if (start > destination) {
            int tmp = start;
            start = destination;
            destination = tmp;
        }
        for (int i = start; i < destination; i++) {
            rdistance += distance[i];
        }

        for (int i = destination; i < distance.length; i++) {
            ldistance += distance[i];
        }
        for (int i = 0; i < start; i++) {
            ldistance += distance[i];
        }
        return Math.min(rdistance, ldistance);
    }
}
