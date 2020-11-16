package org.gnuhpc.interview.leetcode.solutions;

import org.junit.Test;

import java.util.TreeMap;

//和315这两道题要一起看，一起回顾
public class CountRangeSum327 {
    //Method1 : Naive 方法 , LTE for sure
    public int countRangeSum0(int[] nums, int lower, int upper) {
        int n = nums.length;
        //构造前缀和数组,the prefix sum array has size n + 1, where prefix[i] = prefix[i – 1] + nums[i – 1].
        //preSum(i)代表有前i个数字的和
        //这个做法为了让所有区间都能表示成一个区间减另一个区间，size额外增加了1
        // 这样连只包含第一个元素的区间的和都可以表示为sums[1]-sums[0],这样写不用再分类讨论，挺好的
        long[] preSums = new long[n + 1];
        preSums[0] = 0;
        for (int i = 0; i < n; ++i)
            preSums[i + 1] = preSums[i] + nums[i];

        int ans = 0;
        for (int i = 0; i < n; ++i)
            for (int j = i + 1; j <= n; ++j)
                if (preSums[j] - preSums[i] >= lower && preSums[j] - preSums[i] <= upper)
                    ans++;

        return ans;
    }

    //Method2 : Merge Sort counting
    //讲解：https://medium.com/@bill800227/leetcode-327-count-of-range-sum-e4e8724f1ff4
    public int countRangeSum2(int[] nums, int lower, int upper) {
        int n = nums.length;
        // Build prefix array
        // Note that prefix array always size n + 1
        // preSums[i] - preSums[0] == sum of first i numbers
        // preSums[i] - preSums[j] == sum of (jth .. ith] numbers
        long[] preSums = new long[n + 1];
        preSums[0] = 0;
        for (int i = 0; i < n; i++) {
            preSums[i + 1] = preSums[i] + nums[i];
        }

        return countWhileMergeSort(preSums, 0, n + 1, lower, upper);
    }

    // start and end are index to the preSum, NOT index to the original array
    private int countWhileMergeSort(long[] sums, int start, int end, int lower, int upper) {
        if (end - start <= 1) {
            return 0;
        }

        int mid = (start + end) / 2;
        // Recurse on left and right halves and count segments within each half that fall into the range
        int count =
                countWhileMergeSort(sums, start, mid, lower, upper) + countWhileMergeSort(sums, mid, end, lower, upper);

        int i = start; // Left side index
        int a = mid;   // Right side lower bound index
        int b = mid;   // Right side upper bound index
        int j = mid;   // Right side index

        long[] cache = new long[end - start];
        int cacheIdx = 0;

        // Walk through left side, and count number of elements from
        // the right side with which can form a range that fall into
        // the given target range.
        for (; i < mid; i++, cacheIdx++) {
            while (b < end && sums[b] - sums[i] < lower) b++;
            while (a < end && sums[a] - sums[i] <= upper) a++;
            count += a - b;

            // Try to insert all numbers from right side that are smaller than
            // prefix[i1] into merged array before insert prefix[i1].
            for (; j < end && sums[j] < sums[i]; j++, cacheIdx++) {
                cache[cacheIdx] = sums[j];
            }

            cache[cacheIdx] = sums[i];
        }

        System.arraycopy(cache, 0, sums, start, j - start);

        return count;
    }

    //Method3: 二分
    public int countRangeSum3(int[] nums, int lower, int upper) {
        int len = nums.length;
        if (lower > upper || len <= 0) {
            return 0;
        }
        long[] preSums = new long[len + 1];
        preSums[0] = 0;
        for (int i = 0; i < len; i++) {
            preSums[i + 1] = preSums[i] + nums[i];
        }
        return getCount(nums, 0, len - 1, preSums, lower, upper);
    }

    private int getCount(int[] nums, int left, int right, long[] preSums, int lower, int upper) {
        if (left > right) {
            return 0;
        }
        if (left == right) {
            if (nums[left] <= upper && nums[left] >= lower) {
                return 1;
            } else {
                return 0;
            }
        }
        int mid = (left + right) / 2;
        int count = 0;
        for (int i = left; i <= mid; i++) {
            for (int j = mid + 1; j <= right; j++) { //暴力
                //preSums[i+1] = First i+1 nums' sum
                //preSums[j+1] = First j+1 nums' sum
                //preSums[j+1] - preSums[i+1] = [i+2,j+1] nums' sum
                //so we need to add nums[i] back -> [j+1,i+1] sum
                long tmp = preSums[j + 1] - preSums[i + 1] + nums[i];
                if (tmp <= upper && tmp >= lower) {
                    count++;
                }
            }
        }
        return count + getCount(nums, left, mid, preSums, lower, upper) +
                getCount(nums, mid + 1, right, preSums, lower, upper);
    }

    //Method4: BST
    private class TreeNode {
        long val = 0;
        int count = 1; //重复的个数
        int leftSize = 0;
        int rightSize = 0;
        TreeNode left = null;
        TreeNode right = null;

        public TreeNode(long v) {
            this.val = v;
            this.leftSize = 0;
            this.rightSize = 0;
        }
    }

    /* 递归用到炉火纯青
   建立BST，把prefix sum作为TreeNode.val存进去，
   为了避免重复的TreeNode.val处理麻烦，设置一个count记录多少个重复TreeNode.val,
   维护leftSize, 记录比该节点value小的节点个数，rightSize同理
     */
    public int countRangeSum4(int[] nums, int lower, int upper) {
        if (nums.length == 0) {
            return 0;
        }
        long[] preSums = new long[nums.length + 1];
        for (int i = 0; i < nums.length; i++) {
            preSums[i + 1] = preSums[i] + nums[i];
        }
        TreeNode root = new TreeNode(preSums[0]);
        int output = 0;
        for (int k = 1; k < preSums.length; k++) {
            /*
            由于RangeSum S(i,j)在[lower,upper]之间的条件是lower<=sums[j+1]-sums[i]<=upper,
            所以我们每次insert一个新的PrefixSum sums[k]进这个BST之前，
            先寻找一下（rangeSize）该BST内已经有多少个PrefixSum（叫它sums[t]吧）
            满足lower<=sums[k]-sums[t]<=upper, 即寻找有多少个sums[t]满足：
            sums[k]-upper<=sums[t]<=sums[k]-lower
             */
            output += rangeSize(root, preSums[k] - upper, preSums[k] - lower);
            insert(root, preSums[k]);
        }
        return output;
    }

    private TreeNode insert(TreeNode root, long val) {
        if (root == null) {
            return new TreeNode(val);
        } else if (root.val == val) {
            root.count++;
        } else if (val < root.val) {
            root.leftSize++;
            root.left = insert(root.left, val);
        } else {
            root.rightSize++;
            root.right = insert(root.right, val);
        }
        return root;
    }

    private int countSmaller(TreeNode root, long val) {
        if (root == null) {
            return 0;
        } else if (root.val == val) {
            return root.leftSize;
        } else if (root.val > val) {
            return countSmaller(root.left, val);
        } else {
            return root.leftSize + root.count + countSmaller(root.right, val);
        }
    }

    private int countLarger(TreeNode root, long val) {
        if (root == null) {
            return 0;
        } else if (root.val == val) {
            return root.rightSize;
        } else if (root.val < val) {
            return countLarger(root.right, val);
        } else {
            return countLarger(root.left, val) + root.count + root.rightSize;
        }
    }

    private int rangeSize(TreeNode root, long lower, long upper) {
        int total = root.count + root.leftSize + root.rightSize;
        int smaller = countSmaller(root, lower);    // Exclude everything smaller than lower
        int larger = countLarger(root, upper);      // Exclude everything larger than upper
        return total - smaller - larger;
    }

    public int countRangeSum(int[] nums, int lower, int upper) {
        if (nums == null || nums.length == 0) {
            return 0;
        }
        //键值为区间和和这个区间和出现的次数
        TreeMap<Long, Integer> tree = new TreeMap<>();
        tree.put(0L, 1);

        int count = 0;
        long sum = 0L;
        for (int num : nums) {
            sum += num;
            //subMap()返回一个值在sum - upper 和sum - lower 之间的子集合，values()方法获得这个映射的值得视图
            for (int cnt : tree.subMap(sum - upper, true, sum - lower, true).values()) {
                count += cnt; //统计满足条件的区间和个数
            }
            tree.put(sum, tree.getOrDefault(sum, 0) + 1);
        }
        return count;
    }

    /*
    作者：wdw87
    链接：https://leetcode-cn.com/problems/count-of-range-sum/solution/treemap-onlognfang-fa-by-wdw87/
    来源：力扣（LeetCode）
    著作权归作者所有。商业转载请联系作者获得授权，非商业转载请注明出处。
     */


    @Test
    public void test() {
        int[] arr = {-2, 5, -1};
        int L = -2;
        int R = 2;

        System.out.println(countRangeSum4(arr, L, R));
    }
}
