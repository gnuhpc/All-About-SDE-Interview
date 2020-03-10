package org.gnuhpc.interview.leetcode.solutions;

import org.junit.Test;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

//Time complexity: O(n)
public class GetHint299 {
    public String getHint(String secret, String guess) {
        if (secret.length() == 0) return "";

        //计数map
        Map<Integer, Integer> secretMap = new HashMap<>();

        char[] guessChars = guess.toCharArray();
        char[] secretChars = secret.toCharArray();
        for (int i = 0; i < secret.length(); i++) {
            int n = secretChars[i] - '0';

            if (secretMap.containsKey(n)) {
                secretMap.put(n, secretMap.get(n) + 1);
            } else {
                secretMap.put(n, 1);
            }
        }

        int countA = 0, countB = 0;

        //先算A，然后剔除计数
        for (int i = 0, j = 0; i < secret.length(); i++, j++) {
            int n1 = secretChars[i] - '0';
            int n2 = guessChars[j] - '0';

            if (n1 == n2) {
                countA++;
                int count = secretMap.get(n1);
                secretMap.put(n1, count - 1);
            }
        }

        //先算B，计算count大于0的才算B
        for (int i = 0, j = 0; i < secret.length(); i++, j++) {
            int n1 = secretChars[i] - '0';
            int n2 = guessChars[j] - '0';

            if (n1 != n2) {
                if (secretMap.containsKey(n2)) {
                    int count = secretMap.get(n2);
                    if (count > 0) {
                        countB++;
                        secretMap.put(n2, count - 1);
                    }
                }
            }
        }

        return countA + "A" + countB + "B";
    }

    @Test
    public void test() {
        System.out.println(getHint("1123", "0111"));
        System.out.println(getHint("1807", "7810"));
        System.out.println(getHint("1122", "1222"));
    }
}
