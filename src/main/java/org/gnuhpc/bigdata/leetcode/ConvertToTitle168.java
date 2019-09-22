package org.gnuhpc.bigdata.leetcode;

import org.junit.Test;

public class ConvertToTitle168 {
    public String convertToTitle(int n) {
        StringBuffer sb = new StringBuffer();

        while(n>0){
            int k=n%26;
            if(k==0){
                n-=26;
                k=26;
            }

            char c = (char)((k-1) +(int)'A');
            sb.insert(0,c);
            n = n/26;
        }
        return sb.toString();
    }

    @Test
    public void test(){
        System.out.println(convertToTitle(28));
    }
}
