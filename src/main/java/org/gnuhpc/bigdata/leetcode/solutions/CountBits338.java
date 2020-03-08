package org.gnuhpc.bigdata.leetcode.solutions;

public class CountBits338 {
    /*
        作者：duadua
        链接：https://leetcode-cn.com/problems/counting-bits/solution/hen-qing-xi-de-si-lu-by-duadua/
        来源：力扣（LeetCode）
        著作权归作者所有。商业转载请联系作者获得授权，非商业转载请注明出处。
     */
    public int[] countBits(int num) {
        int[] result = new int[num + 1];
        result[0] = 0;
        for (int i = 1; i <= num; i++) {
            if (i % 2 == 1) {
                result[i] = result[i - 1] + 1;
            }
            else {
                result[i] = result[i / 2];
            }
        }

        return result;
    }
}
