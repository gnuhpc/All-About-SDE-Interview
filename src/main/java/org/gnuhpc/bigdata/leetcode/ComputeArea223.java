package org.gnuhpc.bigdata.leetcode;

/**
 * Copyright gnuhpc 2019/10/4
 */
public class ComputeArea223 {
    public int computeArea(int A, int B, int C, int D, int E, int F, int G, int H) {

        int l1 = C - A;
        int w1 = D - B;

        int l2 = G - E;
        int w2 = H - F;

        //如果不重叠
        if (A >= G || E >= C || B >= H || F >= D) {
            return l1 * w1 + l2 * w2;
        }
        //重叠部分四个顶点
        int left = E > A ? E : A;
        int right = C < G ? C : G;
        int top = D < H ? D : H;
        int down = F < B ? B : F;

        return l1 * w1 + l2 * w2 - (right - left) * (top - down);
    }
}
