package org.gnuhpc.interview.leetcode.solutions;

/**
 * Copyright gnuhpc 2022/1/9
 */
public class SlowestKey1629 {
    public char slowestKey(int[] releaseTimes, String keysPressed) {
        int ans = 0, m = releaseTimes[0];
        for (int i = 1; i < releaseTimes.length; i++)
            if (releaseTimes[i] - releaseTimes[i - 1] > m) {
                m = releaseTimes[i] - releaseTimes[i - 1];
                ans = i;
            } else if (releaseTimes[i] - releaseTimes[i - 1] == m && keysPressed.charAt(i) > keysPressed.charAt(ans))
                ans = i;
        return keysPressed.charAt(ans);
    }
}
