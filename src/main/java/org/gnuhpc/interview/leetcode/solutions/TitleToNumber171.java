package org.gnuhpc.interview.leetcode.solutions;

import org.junit.Test;

public class TitleToNumber171 {

    /*
    func titleToNumber(columnTitle string) int {
	ans := 0
	for i := 0; i < len(columnTitle); i++ {
		ans = ans* 26 + (int((columnTitle[i])-'A') + 1)
	}
	return ans
}

     */

    @Test
    public void test() {
        System.out.println(titleToNumber("AAA"));
    }

    //add by tina
    public int titleToNumber(String s) {
        if (s == null || s.length() == 0) return 0;
        int res = 0;
        for (int i = 0; i < s.length(); i++) {
            int num = s.charAt(i) - 'A' + 1;
            res = res * 26 + num;
        }

        return res;
    }
}
