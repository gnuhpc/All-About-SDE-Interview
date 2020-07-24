package org.gnuhpc.interview.leetcode.solutions;

/**
 * Copyright gnuhpc 2020/7/3
 */
public class Compress443 {
    /*
    利用滑动窗口法，左右指针起点都为原数组的首位。
实现步骤如下：

不断右移右指针，使窗口不断增大；
当窗口内出现一个不同的元素时，就可以将元素及其数量（等于左右指针之差）更新在数组中，然后让左指针指向右指针；
遍历到最后一个字符时也需要结算，因此将右指针的终点设为数组之外一格。当右指针移到终点时也要更新数组。
具体代码

作者：hui-fei-de-ma
链接：https://leetcode-cn.com/problems/string-compression/solution/hua-dong-chuang-kou-fa-ya-suo-zi-fu-chuan-java-by-/
来源：力扣（LeetCode）
著作权归作者所有。商业转载请联系作者获得授权，非商业转载请注明出处。
     */
    public int compress(char[] chars) {     // 数组大小范围： 1 <= chars.length <= 1000
        int left = 0;
        int size = 0;

        // 由于最后一个字符也需要判断，所以将右指针终点放到数组之外一格
        for (int right = 0; right <= chars.length; right++) {
            // 当遍历完成，或右指针元素不等于左指针元素时，更新数组
            if (right == chars.length || chars[right] != chars[left]) {
                chars[size++] = chars[left];    // 更新字符
                if (right - left > 1) {         // 更新计数，当个数大于 1 时才更新
                    for (char c : String.valueOf(right - left).toCharArray()) {
                        chars[size++] = c;
                    }
                }
                left = right;
            }
        }

        return size;
    }
}
