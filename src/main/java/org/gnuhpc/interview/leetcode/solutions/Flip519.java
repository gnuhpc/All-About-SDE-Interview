package org.gnuhpc.interview.leetcode.solutions;

import java.util.HashSet;
import java.util.Random;
import java.util.Set;

/**
 * Copyright gnuhpc 2021/11/27
 */
public class Flip519 {
    int m, n;
    Set<Integer> set = new HashSet<>();
    Random random = new Random(300);

    public Flip519(int _m, int _n) {
        m = _m;
        n = _n;
    }

    public int[] flip() {
        int a = random.nextInt(m * n), b = a;
        while (a >= 0 && set.contains(a)) a--;
        while (b < m * n && set.contains(b)) b++;
        int c = a >= 0 && !set.contains(a) ? a : b;
        set.add(c);
        return new int[]{c / n, c % n};
    }

    public void reset() {
        set.clear();
    }
/*
作者：AC_OIer
链接：https://leetcode-cn.com/problems/random-flip-matrix/solution/gong-shui-san-xie-note-bie-pian-yi-ti-sh-e6gi/
来源：力扣（LeetCode）
著作权归作者所有。商业转载请联系作者获得授权，非商业转载请注明出处。*/
}
