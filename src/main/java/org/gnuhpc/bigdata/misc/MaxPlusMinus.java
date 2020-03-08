package org.gnuhpc.bigdata.misc;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class MaxPlusMinus {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        String str = scanner.next();
        System.out.println(calcMin(str));
    }

    //要使结果最小，则需要尽可能的将可以加在一起的先加在一起，然后一起被减去
    private static int calcMin(String str) {
        String[] splitRes = str.split("-");

        //cache the temp subres
        List<Integer> temp = new ArrayList<>();

        for (String s : splitRes) {
            if (s.isEmpty()) temp.add(0); //handle issues like -35
            else {
                if (!s.contains("+")) { // only a number
                    temp.add(Integer.parseInt(s));
                }
                else { // like a+b+c+d, calc the subadd result and push to the temp list
                    String[] addSplits = s.split("\\+");
                    int addRes = 0;
                    for (String num : addSplits) {
                        addRes += Integer.parseInt(num);
                    }

                    temp.add(addRes);
                }
            }
        }

        if (temp.isEmpty()) {
            throw new IllegalArgumentException();
        }

        // even for number like -35, the temp will be 0,35.
        // make 0 as a head is just fine
        int res = temp.get(0);

        for (int i = 1; i < temp.size(); i++) {
            res -= temp.get(i);
        }

        return res;
    }
}
