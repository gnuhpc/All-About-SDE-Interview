package org.gnuhpc.bigdata.leetcode;

public class IsNumber65 {
    /**
     * 空格，符号，数字，小数点，自然底数和其他字符，我们需要五个标志变量，num, dot, exp, sign分别表示数字，小数点，自然底数和符号是否出现，numAfterE表示自然底数后面是否有数字，那么我们分别来看各种情况：
     *
     * - 空格： 我们需要排除的情况是，当前位置是空格而后面一位不为空格，但是之前有数字，小数点，自然底数或者符号出现时返回false。
     *
     * - 符号：符号前面如果有字符的话必须是空格或者是自然底数，标记sign为true。
     *
     * - 数字：标记num和numAfterE为true。
     *
     * - 小数点：如果之前出现过小数点或者自然底数，返回false，否则标记dot为true。
     *
     * - 自然底数：如果之前出现过自然底数或者之前从未出现过数字，返回false，否则标记exp为true，numAfterE为false。
     *
     * - 其他字符：返回false。
     *
     * 最后返回num && numAfterE即可。
     * @param s
     * @return
     */
    public boolean isNumber(String s) {
        boolean num = false, numAfterE = true, dot = false, exp = false, sign = false;
        int n = s.length();
        for (int i = 0; i < n; ++i) {
            if (s.charAt(i) == ' ') {
                if (i < n - 1 && s.charAt(i+1) != ' ' && (num || dot || exp || sign)) return false;
            } else if (s.charAt(i) == '+' || s.charAt(i) == '-') {
                if (i > 0 && s.charAt(i-1) != 'e' && s.charAt(i-1) != ' ') return false;
                sign = true;
            } else if (s.charAt(i) >= '0' && s.charAt(i) <= '9') {
                num = true;
                numAfterE = true;
            } else if (s.charAt(i) == '.') {
                if (dot || exp) return false;
                dot = true;
            } else if (s.charAt(i) == 'e') {
                if (exp || !num) return false;
                exp = true;
                numAfterE = false;
            } else return false;
        }
        return num && numAfterE;
    }

    //状态机写法，状态描述及转移方程定义参考
    //https://blog.csdn.net/u012601587/article/details/50560838
    // 二维数组初始化方法
    //https://blog.csdn.net/gideal_wang/article/details/3647837
    public boolean isNumber2(String s) {
        int[][] mp = {
            {-1,  0,  1,  2, -1,  3},
            {-1, -1, -1,  2, -1,  3},
            {-1, -1, -1, -1, -1,  4},
            {-1,  5, -1,  4,  6,  3},
            {-1,  5, -1, -1,  6,  4},
            {-1,  5, -1, -1, -1, -1},
            {-1, -1,  7, -1, -1,  8},
            {-1, -1, -1, -1, -1,  8},
            {-1,  5, -1, -1, -1,  8}
        };

        int now=0;
        for(int i=0;i<s.length();i++)
        {
            switch(s.charAt(i))
            {
                case '-': now=mp[now][2];break;
                case '+': now=mp[now][2];break;
                case ' ': now=mp[now][1];break;
                case '.': now=mp[now][3];break;
                case 'e': now=mp[now][4];break;
                case 'E': now=mp[now][4];break;
                default:
                {
                    if(s.charAt(i)>='0' && s.charAt(i)<='9')
                        now=mp[now][5];
                    else
                        now=mp[now][0];
                }
            }
            if(now==-1) return false;
        }
        return now==3 || now==4 || now==5 || now==8 ;
    }

}
