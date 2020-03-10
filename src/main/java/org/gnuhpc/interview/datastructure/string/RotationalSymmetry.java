package org.gnuhpc.interview.datastructure.string;

import java.util.HashMap;
import java.util.Map;

public class RotationalSymmetry {
    private static Map<Integer, Integer> rsMapping = new HashMap() {
        {
            put(0, 0);
            put(1, 1);
            put(6, 9);
            put(8, 8);
            put(9, 6);
        }
    };

    private static boolean isRotationalSymmetry(String str) throws Exception {
        assert (str != null);
        assert (str.length() > 0);


        for (int i = 0, j = str.length() - 1; i <= j; i++, j--) {
            int head = str.charAt(i) - '0';
            int tail = str.charAt(j) - '0';

            if ((head >= 0 && head <= 9) && (tail >= 0 && tail <= 9)) {
                if (!(head == tail ||
                        (rsMapping.get(head) != null &&
                                head == rsMapping.get(tail)))) {
                    return false;
                }
            } else {
                throw new Exception("Invalid Input String");
            }
        }

        return true;
    }

    public static void main(String[] args) {
        try {
            System.out.println(isRotationalSymmetry("111111"));
            System.out.println(isRotationalSymmetry("161191"));
            System.out.println(isRotationalSymmetry("161111"));
            System.out.println(isRotationalSymmetry("1811"));
            System.out.println(isRotationalSymmetry("0891680"));
            System.out.println(isRotationalSymmetry("089168"));
            System.out.println(isRotationalSymmetry("a8a168"));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
