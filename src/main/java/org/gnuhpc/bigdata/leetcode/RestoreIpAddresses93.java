package org.gnuhpc.bigdata.leetcode;

import java.util.ArrayList;
import java.util.List;

/**
 *
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
    public List<String> restoreIpAddresses(String s) {
        List<String> res = new ArrayList<String>();
        helper(s, 0, "", res);
        return res;
    }
    public void helper(String s, int n, String out, List<String> res) {
        if (n == 4) {
            if (s.isEmpty()) res.add(out);
            return;
        }
        for (int k = 1; k < 4; ++k) {
            if (s.length() < k) break;
            int val = Integer.parseInt(s.substring(0, k));
            if (val > 255 || k != String.valueOf(val).length()) continue;
            helper(s.substring(k), n + 1, out + s.substring(0, k) + (n == 3 ? "" : "."), res);
        }
    }


    //有限层循环
    public List<String> restoreIpAddresses2(String s) {
        List<String> res = new ArrayList<String>();
        for (int a = 1; a < 4; ++a)
            for (int b = 1; b < 4; ++b)
                for (int c = 1; c < 4; ++c)
                    for (int d = 1; d < 4; ++d)
                        if (a + b + c + d == s.length()) {
                            int A = Integer.parseInt(s.substring(0, a));
                            int B = Integer.parseInt(s.substring(a, a + b));
                            int C = Integer.parseInt(s.substring(a + b, a + b + c));
                            int D = Integer.parseInt(s.substring(a + b + c));
                            if (A <= 255 && B <= 255 && C <= 255 && D <= 255) {
                                String t = String.valueOf(A) + "." + String.valueOf(B) + "." + String.valueOf(C) + "." + String.valueOf(D);
                                // 注意最后需要再判断一下长度，防止1970032这种，19.7.00.32这是非法ip
                                // System.out.println(Integer.parseInt("003".substring(0, 2)));
                                // 输出为0
                                if (t.length() == s.length() + 3) res.add(t);
                            }
                        }
        return res;
    }


}
