package org.gnuhpc.bigdata.leetcode;

import java.util.ArrayList;
import java.util.List;

public class GeneratePossibleNextMoves293 {
    public List<String> generatePossibleNextMoves(String s) {
        List<String> lst = new ArrayList<>();
        if(s == null || s.length()==0 || s.length() == 1) return lst;
        for(int i = 0;i<s.length()-1;i++){
            if(s.charAt(i) == '+' && s.charAt(i+1) == '+')
                lst.add(s.substring(0,i)+"--"+s.substring(i+2,s.length()));
        }
        return lst;
    }
}
