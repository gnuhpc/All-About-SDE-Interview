package org.gnuhpc.interview.leetcode.solutions;

import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

/**
 * IP地址由32位二进制数组成，为便于使用，
 * 常以XXX.XXX.XXX.XXX形式表现，每组XXX代表小于或等于255的10进制数。
 * 所以说IP地址总共有四段，每一段可能有一位，两位或者三位，范围是[0, 255]，
 * 题目明确指出输入字符串只含有数字，所以当某段是三位时，
 * 我们要判断其是否越界（>255)，还有一点很重要的是，
 * 当只有一位时，0可以成某一段，如果有两位或三位时，
 * 像 00， 01， 001， 011， 000等都是不合法的，
 * 所以我们还是需要有一个判定函数来判断某个字符串是否合法。
 * 这道题其实也可以看做是字符串的分段问题，
 * 在输入字符串中加入三个点，将字符串分为四段，
 * 每一段必须合法，求所有可能的情况。
 * 根据目前刷了这么多题，得出了两个经验，一
 * 是只要遇到字符串的子序列或配准问题首先考虑动态规划DP，
 * 二是只要遇到需要求出所有可能情况首先考虑用递归。
 * 这道题并非是求字符串的子序列或配准问题，
 * 更符合第二种情况，所以我们要用递归来解。
 * 我们用k来表示当前还需要分的段数，
 * 如果k = 0，则表示三个点已经加入完成，四段已经形成，
 * 若这时字符串刚好为空，则将当前分好的结果保存。
 * 若k != 0, 则对于每一段，我们分别用一位，两位，三位来尝试，
 * 分别判断其合不合法，如果合法，则调用递归继续分剩下的字符串，
 * 最终和求出所有合法组合，代码如下
 */
public class RestoreIpAddresses93 {
    public ArrayList<String> restoreIpAddresses(String s) {
        ArrayList<String> result = new ArrayList<>();

        if (s.length() < 4 || s.length() > 12)
            return result;

        helper(result, new ArrayList<>(), s, 0);
        return result;
    }

    public void helper(ArrayList<String> result, ArrayList<String> temp, String s, int start) {
        if (temp.size() == 4) {
            if (start != s.length())
                return;

            result.add(String.join(".", temp));
            return;
        }

        for (int i = start; i < s.length(); i++) {
            String tmp = s.substring(start, i + 1);
            if (isValid(tmp)) {
                temp.add(tmp);
                helper(result, temp, s, i + 1);
                temp.remove(temp.size() - 1);
            }
        }
    }

    private boolean isValid(String s) {
        if (s.length() > 3) return false;
        if (s.charAt(0) == '0')
            return s.equals("0");        // to eliminate cases like "00", "10"
        int digit = Integer.parseInt(s);
        return digit >= 0 && digit <= 255;
    }

    @Test
    public void test() {
        System.out.println(restoreIpAddresses("25525511135"));
    }


}
