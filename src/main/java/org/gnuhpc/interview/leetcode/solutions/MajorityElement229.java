package org.gnuhpc.interview.leetcode.solutions;

import java.util.ArrayList;
import java.util.List;

public class MajorityElement229 {
    public List<Integer> majorityElement(int[] nums) {
        List<Integer> lists = new ArrayList<>();
        if (nums.length < 2) { // 只有 1 个数则必为众数
            for (int i : nums) {
                lists.add(i);
            }
            return lists;
        }
        int num1 = nums[0], num2 = nums[1]; // 选出两数
        int len = nums.length / 3, count1 = 0, count2 = 0;
        for (int i = 0; i < nums.length; i++) {
            if (num1 == nums[i]) { //  若出现 num1、num2 中任意一数，计数+1
                count1++;
                continue;
            }else if (num2 == nums[i]) {
                count2++;
                continue;
            }else { // 都不等，则计数-1
                count1--;
                count2--;
            }
            if (count1 < 0) { // 更换新的众数
                num1 = nums[i];
                count1 = 1;
                count2++; // 确保每次只更换 1 个
            }
            if (count2 < 0) {
                num2 = nums[i];
                count2 = 1;
                count1++;
            }
        }
        count1 = 0; // 归零，重新计数
        count2 = 0;
        for (int num : nums) { // 验证次数，符合则加入集合
            if (num == num1) {
                count1++;
            } else if (num == num2) {
                count2++;
            }
        }
        if (count1 > len) {
            lists.add(num1);
        }
        if (count2 > len) {
            lists.add(num2);
        }
        return lists;
    }

    /*
    作者：code_farmering_hu-5HyzMqWsQ9
    链接：https://leetcode-cn.com/problems/majority-element-ii/solution/xuan-ze-liang-ge-shu-jia-ding-wei-yao-xun-zhao-zho/
    来源：力扣（LeetCode）
    著作权归作者所有。商业转载请联系作者获得授权，非商业转载请注明出处。
     */
}
