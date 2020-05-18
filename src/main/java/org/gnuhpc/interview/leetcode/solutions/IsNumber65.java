package org.gnuhpc.interview.leetcode.solutions;

public class IsNumber65 {

    /*
    这题看着很麻烦，实际上也有些麻烦，但只要把注意的点都搞清楚，其实代码结构是很简单的

先设定numSeen，dotSeen和eSeen三种boolean变量，分别代表是否出现数字、点和E
然后遍历目标字符串
1.判断是否属于数字的0~9区间
2.遇到点的时候，判断前面是否有点或者E，都需要return false
3.遇到E的时候，判断前面数字是否合理，是否有E，并把numSeen置为false，防止E后无数字
4.遇到-+的时候，判断是否是第一个，如果不是第一个判断是否在E后面，都不满足则return false
5.其他情况都为false
最后返回numSeen的结果即可

作者：CharlesGao
链接：https://leetcode-cn.com/problems/valid-number/solution/java-luo-ji-chao-qing-xi-jie-fa-by-charlesgao/
来源：力扣（LeetCode）
著作权归作者所有。商业转载请联系作者获得授权，非商业转载请注明出处。
     */
    public boolean isNumber(String s) {
        if (s == null || s.length() == 0) return false;
        boolean numSeen = false;
        boolean dotSeen = false;
        boolean eSeen = false;
        char[] arr = s.trim().toCharArray();
        for (int i = 0; i < arr.length; i++) {
            if (arr[i] >= '0' && arr[i] <= '9') {
                numSeen = true;
            } else if (arr[i] == '.') {
                if (dotSeen || eSeen) {
                    return false;
                }
                dotSeen = true;
            } else if (arr[i] == 'E' || arr[i] == 'e') {
                if (eSeen || !numSeen) {
                    return false;
                }
                eSeen = true;
                numSeen = false;
            } else if (arr[i] == '+' || arr[i] == '-') {
                if (i != 0 && arr[i - 1] != 'e' && arr[i - 1] != 'E') {
                    return false;
                }
            } else {
                return false;
            }
        }
        return numSeen;
    }

}
