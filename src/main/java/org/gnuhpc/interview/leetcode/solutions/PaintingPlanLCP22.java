package org.gnuhpc.interview.leetcode.solutions;

/**
 * Copyright gnuhpc 2020/10/5
 */
public class PaintingPlanLCP22 {
    public int paintingPlan(int n, int k) {

        if (k == n * n) return 1;

        int ans = 0;
        for (int a = 0; a <= n; a++) {
            for (int b = 0; b <= n; b++) {
                int sum = a * n + b * n - a * b;
                if (sum == k) {
                    int x = combination(n, a);
                    int y = combination(n, b);
                    ans += x * y;
                }
            }
        }

        return ans;
    }

    int combination(int n, int m) {
        int ans = 1;
        for (int i = n; i > m; i--) ans *= i;
        for (int i = n - m; i > 0; i--) ans /= i;
        return ans;
    }
/*
作者：qi-shi-wo-shi-yi-ge-yan-yuan
链接：https://leetcode-cn.com/problems/ccw6C7/solution/lcp-22-hei-bai-fang-ge-hua-by-qi-shi-wo-shi-yi-ge-/
来源：力扣（LeetCode）
著作权归作者所有。商业转载请联系作者获得授权，非商业转载请注明出处。*/
}
