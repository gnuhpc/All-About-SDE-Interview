package org.gnuhpc.bigdata.leetcode;

/**
 * Copyright gnuhpc 19-8-22
 */
public class IsAdditiveNumber306 {
    /*
    Method: DFS
    这是一个多阶段决策问题，且必须走到字符串最后一个字符才能得出结论，因此适合用深搜或DP。
    再仔细想一下状态转换图，每次索引变化一下，就跟之前的完全没有重复，
    因此状态转换图是一颗树，不是DAG，因此不存在重叠子问题，因此排除DP，本题应该用深搜。
     */
    public boolean isAdditiveNumber(String num) {
        if (num == null || num.length() <= 2)
            return false;

        //遍历构造三个数据
        for (int i = 1; i < num.length(); i++) {
            String num1 = num.substring(0, i);
            if (num1.charAt(0) == '0' && num1.length() >= 2)
                continue;
            for (int j = i + 1; j < num.length(); j++) {
                String num2 = num.substring(i, j);
                if (num2.charAt(0) == '0' && num2.length() >= 2)
                    continue;
                String num3 = num.substring(j);
                if (dfs(num1, num2, num3))
                    return true;
            }
        }
        return false;
    }

    private boolean dfs(String num1, String num2, String num3) {
        //第三个num3位空的时候直接返回true
        if (num3 == null || num3.length() <= 0)
            return true;
        else {
            //len为-1表示false，否者继续递归尝试
            int len = add(num1, num2, num3);
            if (len >= 1) {
                num1 = num2;
                num2 = num3.substring(0, len);
                num3 = num3.substring(len);
                return dfs(num1, num2, num3);
            }
            else
                return false;
        }
    }

    //处理两个数据相加的问题，这里使用字符串匹配来做
    private int add(String num1, String num2, String num3) {
        long res = Long.parseLong(num1) + Long.parseLong(num2);
        String one = res + "";

        if (one.length() > num3.length())
            return -1;
        else if (num3.startsWith(one))
            return one.length();
        else
            return -1;
    }

}
