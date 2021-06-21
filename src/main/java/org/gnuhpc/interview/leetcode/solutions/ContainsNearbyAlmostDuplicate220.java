package org.gnuhpc.interview.leetcode.solutions;

import org.junit.Test;

import java.util.*;

public class ContainsNearbyAlmostDuplicate220 {
    //利用了Treeset这个数据结构
    /*
     * 用于对元素排序的有序集合类，集合中的元素是自然排序的，也不能有重复的元素
     * subSet(E fromElement,true, E toElement, true) 方法用于返回这个集合，
     * 其元素范围从fromElement(包括)到toElement(包括)的部分视图。
     * long是为了防止整型加法溢出
     * */
    public boolean containsNearbyAlmostDuplicate(int[] nums, int k, int t) {
        if (nums == null || nums.length == 0 || t < 0)
            return false;
        TreeSet<Long> ts = new TreeSet<>();
        for (int i = 0; i < nums.length; ++i) {
            if (i > k)
                ts.remove((long) nums[i - k - 1]);
            TreeSet<Long> temp = (TreeSet<Long>) ts.subSet((long) nums[i] - t, true, (long) nums[i] + t, true);
            if (!temp.isEmpty())
                return true;
            ts.add((long) nums[i]);
        }
        return false;
    }

    //Method2: Bucket + Sliding window
    /*
    思路

受 桶排序 的启发，我们可以把 桶 当做窗口来实现一个线性复杂度的解法。

算法

桶排序是一种把元素分散到不同桶中的排序算法。接着把每个桶再独立地用不同的排序算法进行排序。桶排序的概览如下所示：

在上面的例子中，我们有 8 个未排序的整数。我们首先来创建五个桶，这五个桶分别包含 [0,9], [10,19], [20, 29], [30, 39], [40, 49][0,9],[10,19],[20,29],[30,39],[40,49] 这几个区间。这 8 个元素中的任何一个元素都在一个桶里面。对于值为 xx 的元素来说，它所属桶的标签为 x/wx/w，在这里我们让 w = 10w=10。对于每个桶我们单独用其他排序算法进行排序，最后按照桶的顺序收集所有的元素就可以得到一个有序的数组了。

回到这个问题，我们尝试去解决的最大的问题在于：

对于给定的元素 xx, 在窗口中是否有存在区间 [x-t, x+t][x−t,x+t] 内的元素？
我们能在常量时间内完成以上判断嘛？
我们不妨把把每个元素当做一个人的生日来考虑一下吧。假设你是班上新来的一位学生，你的生日在 三月 的某一天，你想知道班上是否有人生日跟你生日在 t=30t=30 天以内。在这里我们先假设每个月都是3030天，很明显，我们只需要检查所有生日在 二月，三月，四月 的同学就可以了。

之所以能这么做的原因在于，我们知道每个人的生日都属于一个桶，我们把这个桶称作月份！每个桶所包含的区间范围都是 tt，这能极大的简化我们的问题。很显然，任何不在同一个桶或相邻桶的两个元素之间的距离一定是大于 tt 的。

我们把上面提到的桶的思想应用到这个问题里面来，我们设计一些桶，让他们分别包含区间 ..., [0,t], [t+1, 2t+1], ......,[0,t],[t+1,2t+1],...。我们把桶来当做窗口，于是每次我们只需要检查 xx 所属的那个桶和相邻桶中的元素就可以了。终于，我们可以在常量时间解决在窗口中搜索的问题了。

还有一件值得注意的事，这个问题和桶排序的不同之处在于每次我们的桶里只需要包含最多一个元素就可以了，因为如果任意一个桶中包含了两个元素，那么这也就是意味着这两个元素是 足够接近的 了，这时候我们就直接得到答案了。因此，我们只需使用一个标签为桶序号的散列表就可以了。
     */

    // Get the ID of the bucket from element value x and bucket width w
    // In Java, `-3 / 5 = 0` and but we need `-3 / 5 = -1`.
    private long getID(long x, long w) {
        return x < 0 ? (x + 1) / w - 1 : x / w;
    }

    public boolean containsNearbyAlmostDuplicate2(int[] nums, int k, int t) {
        if (t < 0) return false;
        Map<Long, Long> d = new HashMap<>();
        long w = (long) t + 1;
        for (int i = 0; i < nums.length; ++i) {
            long m = getID(nums[i], w);
            // check if bucket m is empty, each bucket may contain at most one element
            if (d.containsKey(m))
                return true;
            // check the nei***or buckets for almost duplicate
            if (d.containsKey(m - 1) && Math.abs(nums[i] - d.get(m - 1)) < w)
                return true;
            if (d.containsKey(m + 1) && Math.abs(nums[i] - d.get(m + 1)) < w)
                return true;
            // now bucket m is empty and no almost duplicate in nei***or buckets
            d.put(m, (long) nums[i]);
            if (i >= k) d.remove(getID(nums[i - k], w));
        }
        return false;
    }

    public boolean containsNearbyAlmostDuplicate3(int[] nums, int k, int t) {
        if (nums == null || nums.length < 2 || k < 1)
            return false;

        final int len = nums.length;
        int[][] m = new int[len][2];
        for (int i = 0; i < len; i++) {
            m[i][0] = nums[i];
            m[i][1] = i;
        }
        Arrays.sort(m, (a, b) -> Integer.compare(a[0], b[0])); // n log n
        for (int i = 0; i < len - 1; i++) { // n times
            for (int j = i + 1; j < len; j++) { // n times (in practice, less than that, unless t is large)
                long tmp = (long) m[i][0] - (long) m[j][0];
                if (Math.abs(tmp) > t)//实际是一种剪枝
                    break;
                if (Math.abs(m[i][1] - m[j][1]) <= k)
                    return true;
            }
        }
        return false;
    }

    @Test
    public void test() {
        int[] array = new int[]{1, 2, 3, 1};
        int k = 3, t = 0;

        System.out.println(containsNearbyAlmostDuplicate(array, k, t));

    }
}
