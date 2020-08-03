package org.gnuhpc.interview.leetcode.solutions;

import java.util.*;

/**
 * Copyright gnuhpc 2020/8/1
 */
//滑动窗口
    /*

首先将kk组数据升序合并成一组，并记录每个数字所属的组，例如：

[[4,10,15,24,26], [0,9,12,20], [5,18,22,30]][[4,10,15,24,26],[0,9,12,20],[5,18,22,30]]

合并升序后得到：
[(0, 1), (4, 0), (5, 2), (9, 1), (10, 0), (12, 1), (15, 0), (18, 2), (20, 1), (22, 2), (24, 0), (26, 0), (30, 2)][(0,1),(4,0),(5,2),(9,1),(10,0),(12,1),(15,0),(18,2),(20,1),(22,2),(24,0),(26,0),(30,2)]

然后只看所属组的话，那么
[1, 0, 2, 1, 0, 1, 0, 2, 1, 2, 0, 0, 2][1,0,2,1,0,1,0,2,1,2,0,0,2]

按组进行滑窗，保证一个窗口的组满足kk组后在记录窗口的最小区间值。


[1 0 2] 2 1 0 1 0 2 1 2 0 0 2    [0, 5]
1 [0 2 1] 1 0 1 0 2 1 2 0 0 2    [0, 5]
1 0 [2 1 0] 0 1 0 2 1 2 0 0 2    [0, 5]
1 0 [2 1 0 1] 1 0 2 1 2 0 0 2    [0, 5]
1 0 [2 1 0 1 0] 0 2 1 2 0 0 2    [0, 5]
1 0 2 1 0 [1 0 2] 2 1 2 0 0 2    [0, 5]
1 0 2 1 0 1 [0 2 1] 1 2 0 0 2    [0, 5]
1 0 2 1 0 1 [0 2 1 2] 2 0 0 2    [0, 5]
1 0 2 1 0 1 0 2 [1 2 0] 0 0 2    [20, 24]
1 0 2 1 0 1 0 2 [1 2 0 0] 0 2    [20, 24]
1 0 2 1 0 1 0 2 [1 2 0 0  2 ]    [20, 24]

作者：netcan
链接：https://leetcode-cn.com/problems/smallest-range-covering-elements-from-k-lists/solution/pai-xu-hua-chuang-by-netcan/
来源：力扣（LeetCode）
著作权归作者所有。商业转载请联系作者获得授权，非商业转载请注明出处。
     */
public class SmallestRange632 {
    public int[] smallestRange(List<List<Integer>> nums) {
        int N = 0;
        for (List<Integer> num : nums) N += num.size();
        int[][] ordered = new int[N][2];
        int i = 0;
        int j = 0;
        for (List<Integer> group : nums) {
            for (Integer num : group) {
                ordered[i][0] = num;
                ordered[i][1] = j;
                i++;
            }
            j++;
        }
        Arrays.sort(ordered, Comparator.comparingInt(o -> o[0]));
        int[] ans = new int[2];
        int[] count = new int[nums.size()];
        int k = 0;
        i = 0;
        for (j = 0; j < N; j++) {
            if (0 == count[ordered[j][1]]++) k++;
            if (k == nums.size()) {
                while (count[ordered[i][1]] > 1) count[ordered[i++][1]]--;
                if ((ans[0] == 0 && ans[1] == 0) || ans[1] - ans[0] > ordered[j][0] - ordered[i][0]) {
                    ans = new int[]{ordered[i][0], ordered[j][0]};
                }
            }
        }
        return ans;
    }

}
