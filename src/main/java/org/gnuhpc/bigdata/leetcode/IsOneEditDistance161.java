package org.gnuhpc.bigdata.leetcode;

public class IsOneEditDistance161 {
    // add by tina
    /**
     * 1. 两个字符串的长度之差大于1，直接返回False。
     *
     * 2. 两个字符串的长度之差等于1，长的那个字符串去掉一个字符，剩下的应该和短的字符串相同。
     *
     * 3. 两个字符串的长度之差等于0，两个字符串对应位置的字符只能有一处不同。
     * @param s
     * @param t
     * @return
     */
    public boolean isOneEditDistance(String s, String t) {
        int ns = s.length();
        int nt = t.length();

        // Ensure that s is shorter than t.
        if (ns > nt)
            return isOneEditDistance(t, s);

        // The strings are NOT one edit away distance
        // if the length diff is more than 1.
        if (nt - ns > 1)
            return false;

        for (int i = 0; i < ns; i++){
            if (s.charAt(i) != t.charAt(i)){
                // if strings have the same length
                if (ns == nt)
                    return s.substring(i + 1).equals(t.substring(i + 1));
                    // if strings have different lengths
                else
                    return s.substring(i).equals(t.substring(i + 1));
            }
        }

        // If there is no diffs on ns distance
        // the strings are one edit away only if
        // t has one more character.
        return (ns + 1 == nt);
    }


}
