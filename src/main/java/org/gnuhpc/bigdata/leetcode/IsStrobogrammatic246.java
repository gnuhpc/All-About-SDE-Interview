package org.gnuhpc.bigdata.leetcode;

public class IsStrobogrammatic246 {
    // add by tina
    public boolean isStrobogrammatic(String num) {
        if(num == null || num.length() == 0 ) return false;
        StringBuilder sb = new StringBuilder();
        for(int i = 0; i<num.length();i++){
            if(num.charAt(i) == '8') sb.insert(0 ,"8");
            else if(num.charAt(i) == '6') sb.insert(0,"9");
            else if(num.charAt(i) == '9') sb.insert(0,"6");
            else if(num.charAt(i) == '0') sb.insert(0,"0");
            else if(num.charAt(i) == '1') sb.insert(0,"1");//1比较有歧义
            else return false;
        }

        if(sb.toString().equals(num)) return true;
        return false;
    }

}
