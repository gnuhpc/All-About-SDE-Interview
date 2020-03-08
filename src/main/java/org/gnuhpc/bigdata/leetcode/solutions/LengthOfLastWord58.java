package org.gnuhpc.bigdata.leetcode.solutions;

import java.nio.CharBuffer;
import java.util.Arrays;

public class LengthOfLastWord58 {
    public int lengthOfLastWord(String s) {
        if (s == null || s.length() == 0) return 0;
        if (s.length() ==
            s.chars().mapToObj(i -> (char) i).filter(c -> c == ' ').count()) {
            return 0;
        }

        String[] splitStrArray = s.split(" ");

        return splitStrArray[splitStrArray.length - 1].length();

        //其实就一句就可以了：
        // return s.trim().length()-s.trim().lastIndexOf(" ")-1;
    }
}
