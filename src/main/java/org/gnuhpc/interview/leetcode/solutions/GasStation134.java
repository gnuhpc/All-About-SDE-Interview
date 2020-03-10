package org.gnuhpc.interview.leetcode.solutions;

public class GasStation134 {
    // add by Tina,暴力解法O(n^2)
    public int canCompleteCircuit(int[] gas, int[] cost) {
        int n = gas.length;
        for (int i = 0; i < n; i++) {
            int start = i;
            int dis = gas[i];
            for (int j = start; j < start + n; j++) {
                if (dis < cost[j % n]) {
                    dis -= cost[j % n];
                    break;
                } else dis += gas[(j + 1) % n] - cost[j % n];
            }

            if (dis >= 0) return start;
        }
        return -1;
    }

    public int canCompleteCircuit2(int[] gas, int[] cost) {
        int sumGas = 0;
        int sumCost = 0;
        int start = 0;
        int tank = 0; //表示从当前start开始，累计油量
        //这道转圈加油问题不算很难，只要想通其中的原理就很简单。
        // 我们首先要知道能走完整个环的前提是gas的总量要大于cost的总量，
        // 这样才会有起点的存在。假设开始设置起点start = 0,
        // 并从这里出发，如果当前的gas值大于cost值，就可以继续前进，
        // 此时到下一个站点，剩余的gas加上当前的gas再减去cost，
        // 看是否大于0，若大于0，则继续前进。当到达某一站点时，
        // 若这个值小于0了，则说明从起点到这个点中间的任何一个点都不能作为起点，
        // 则把起点设为下一个点，继续遍历。当遍历完整个环时，
        // 当前保存的起点即为所求
        for (int i = 0; i < gas.length; i++) {
            sumGas += gas[i];
            sumCost += cost[i];
            tank += gas[i] - cost[i];

            if (tank < 0) {
                start = i + 1;
                tank = 0;
            }
        }

        if (sumGas >= sumCost) {
            return start;
        } else {
            return -1;
        }
    }


}
