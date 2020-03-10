package org.gnuhpc.interview.leetcode.solutions;

import java.util.ArrayList;
import java.util.List;

//DONE
public class GeneratePossibleNextMoves293 {
    public List<String> generatePossibleNextMoves(String s) {
        List<String> lst = new ArrayList<>();
        if (s == null || s.length() == 0 || s.length() == 1) return lst;
        for (int i = 1; i < s.length(); i++) {
            if (s.charAt(i) == '+' && s.charAt(i - 1) == '+')
                lst.add(s.substring(0, i - 1) + "--" + s.substring(i + 1));
        }
        return lst;
    }
}
