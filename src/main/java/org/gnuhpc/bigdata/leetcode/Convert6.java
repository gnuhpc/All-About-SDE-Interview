package org.gnuhpc.bigdata.leetcode;

/**
 * Description:
 * User: gnuhpc
 * Date: 2018-12-30 6:51
 * Version: 0.1
 */
public class Convert6 {
    /*
    简单字符串
     */
    public String convert(String s, int numRows) {
        if (numRows<=1) return s;
        StringBuilder[] sb= new StringBuilder[numRows];
        for (int i=0; i<numRows; i++) sb[i]=new StringBuilder();
        int idx=0, k=1;
        for (char c: s.toCharArray()){
            sb[idx].append(c);
            if (idx==0) k=1;
            if (idx==numRows-1) k=-1;
            idx+=k;
        }
        return String.join("", sb);
    }
}
