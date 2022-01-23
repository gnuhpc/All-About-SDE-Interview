package org.gnuhpc.interview.leetcode.solutions;

import org.gnuhpc.interview.leetcode.utils.Utils;
import org.junit.Test;

/**
 * Copyright gnuhpc 2020/4/28
 */
public class SingleNumbers56_260 {

    /*
    异或就是存放两数互异的点位
    假设两个只出现一次的数为a、b，那么将数组中所有数异或一次，最后结果就是c = a^b
因为a与b不一样，所以c肯定至少有1位是不为0的，我们找到最右边开始第一个1 记为h
再次遍历数组，用h和数组元素“与”操作为0还是为1区分开这两个数；
不过不用担心其他出现两次的数被分开，影响结果，他们&h结果是一样的，会被分到一起 x^x = 0会消掉。 最后得到的就是 a、b
     */
    /*
[1,2,1,3,2,5]

1 = 001
2 = 010
1 = 001
3 = 011
2 = 010
5 = 101

把上边所有的数字异或，最后得到的结果就是 3 ^ 5 = 6 (110)

然后对 110 调用 Integer.highestOneBit 方法就得到 100, 我们通过倒数第三位将原数组分类

倒数第三位为 0 的组
1 = 001
2 = 010
1 = 001
3 = 011
2 = 010

倒数第三位为 1 的组
5 = 101

最后组内数字依次异或即可。

作者：windliang
链接：https://leetcode-cn.com/problems/single-number-iii/solution/xiang-xi-tong-su-de-si-lu-fen-xi-duo-jie-fa-by-5-8/
来源：力扣（LeetCode）
著作权归作者所有。商业转载请联系作者获得授权，非商业转载请注明出处。
     */

    public int[] singleNumber(int[] nums) {
        int diff = 0;
        for (int n : nums) {
            diff ^= n;
        }
        diff = Integer.highestOneBit(diff);
        int[] result = {0, 0};
        for (int n : nums) {
            //当前位是 0 的组, 然后组内异或
            if ((diff & n) == 0) {
                result[0] ^= n;
                //当前位是 1 的组
            } else {
                result[1] ^= n;
            }
        }
        return result;
    }



    @Test
    public void test() {
        Utils.printArray(singleNumber(new int[]{4, 1, 4, 6}));
    }
}
