package org.gnuhpc.interview.leetcode.solutions;

import org.junit.Test;

import java.util.*;

public class FourSum18 {

    @Test
    public void test() {

        int[] nums = new int[]{-3, -1, 0, 2, 4, 5};
        int[] nums2 = new int[]{0, 0, 0, 0};
        int[] nums3 = new int[]{1, 2, 3, 4, 5};
        int target = 9;

        fourSum2(nums, target);
    }

    //双指针
    public List<List<Integer>> fourSum(int[] nums, int target) {
        List<List<Integer>> list = new ArrayList<>();
        if (nums.length < 4) {
            return list;
        }
        Arrays.sort(nums);
        //固定i，开始里面的循环
        for (int i = 0; i < nums.length - 3; i++) {
            //开始前先排除一些明显的情况
            if (nums[i] + nums[i + 1] + nums[i + 2] + nums[i + 3] > target)
                break; // first candidate too large, search finished
            if (nums[i] + nums[nums.length - 3] + nums[nums.length - 2] + nums[nums.length - 1] < target)
                continue; // first candidate too small

            //去重
            if (i > 0 && nums[i] == nums[i - 1])
                continue; // prevents duplicate result in ans list

            for (int j = i + 1; j < nums.length - 2; j++) {
                if (nums[i] + nums[j] + nums[j + 1] + nums[j + 2] > target)
                    break; // second candidate too large
                if (nums[i] + nums[j] + nums[nums.length - 1] + nums[nums.length - 2] < target)
                    continue; // second candidate too small
                if (j > i + 1 && nums[j] == nums[j - 1])
                    continue; // prevents duplicate results in ans list
                int l = j + 1;
                int h = nums.length - 1;
                while (l < h) {
                    int sum = nums[i] + nums[j] + nums[l] + nums[h];
                    if (sum == target) {
                        list.add(Arrays.asList(nums[i], nums[j], nums[l], nums[h]));
                        while (l < h && nums[l] == nums[l + 1]) {
                            l++;
                        }
                        while (l < h && nums[h] == nums[h - 1]) {
                            h--;
                        }
                        l++;
                        h--;
                    } else if (sum < target) {
                        l++;
                    } else {
                        h--;
                    }
                }
            }
        }
        return list;
    }

    //Method2 :Hashmap  九章算法讲的这个解法，其实效率不高，从leedcode跑的结果看来
    public List<List<Integer>> fourSum2(int[] num, int target) {
        //Create the dictionary. sum -- all the combinations(idxs) which can be added to sum.
        Map<Integer, ArrayList<ArrayList<Integer>>> dict = new HashMap<>();
        for (int i = 0; i < num.length - 1; i++) {
            for (int j = i + 1; j < num.length; j++) {
                int sum = num[i] + num[j];
                ArrayList<Integer> pairIdx = new ArrayList<>(2);
                pairIdx.add(i);
                pairIdx.add(j);
                if (!dict.containsKey(sum)) {
                    ArrayList<ArrayList<Integer>> value = new ArrayList<>();
                    value.add(pairIdx);
                    dict.put(sum, value);
                } else {
                    ArrayList<ArrayList<Integer>> value = dict.get(sum);
                    value.add(pairIdx);
                }
            }
        }

        //Use HashSet to prevent duplicate result.
        HashSet<ArrayList<Integer>> set = new HashSet<>();
        for (Integer sum : dict.keySet()) {
            ArrayList<ArrayList<Integer>> sumPairList = dict.get(sum);
            if (dict.containsKey(target - sum)) {
                //首先排除自己：正好sum是target的一半并且sumPairList里面的pair个数小于2
                //因为如果是sum是target的一半也有可能里面有正确答案，也就是从sumPairList中取两个即是
                if (target - sum == sum && sumPairList.size() < 2)
                    continue;
                ArrayList<ArrayList<Integer>> pairs = dict.get(target - sum);

                //配对
                for (ArrayList<Integer> pair1 : sumPairList) {
                    for (ArrayList<Integer> pair2 : pairs) {
                        //Make sure it is not the same pair.
                        if (pair1 == pair2)
                            continue;
                        //Make sure there is no same element in two pairs.
                        if (pair1.contains(pair2.get(0)) || pair1.contains(pair2.get(1)))
                            continue;
                        ArrayList<Integer> tmpResult = new ArrayList<>(4);
                        tmpResult.add(num[pair1.get(0)]);
                        tmpResult.add(num[pair1.get(1)]);
                        tmpResult.add(num[pair2.get(0)]);
                        tmpResult.add(num[pair2.get(1)]);
                        //Sort the list and add it into the set.
                        Collections.sort(tmpResult);
                        set.add(tmpResult);
                    }
                }
            }
        }
        return new ArrayList<>(set);
    }
}
