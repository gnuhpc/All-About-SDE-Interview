package org.gnuhpc.bigdata.leetcode.solutions;

import org.gnuhpc.bigdata.datastructure.unionfind.UnionFind;

/**
 * Copyright gnuhpc 2020/1/12
 */
public class EquationsPossible990 {
    public boolean equationsPossible(String[] equations) {
        // 26 个英文字母
        UnionFind uf = new UnionFind(26);
        // 先让相等的字母形成连通分量
        for (String eq : equations) {
            if (eq.charAt(1) == '=') {
                char x = eq.charAt(0);
                char y = eq.charAt(3);
                uf.union(x - 'a', y - 'a');
            }
        }
        // 检查不等关系是否打破相等关系的连通性
        for (String eq : equations) {
            if (eq.charAt(1) == '!') {
                char x = eq.charAt(0);
                char y = eq.charAt(3);
                // 如果相等关系成立，就是逻辑冲突
                if (uf.isConnected(x - 'a', y - 'a'))
                    return false;
            }
        }
        return true;
    }
}
