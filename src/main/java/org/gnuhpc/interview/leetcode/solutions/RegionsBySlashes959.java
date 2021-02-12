package org.gnuhpc.interview.leetcode.solutions;

import org.gnuhpc.interview.datastructure.unionfind.UnionFind;

public class RegionsBySlashes959 {
    //https://pic.leetcode-cn.com/1611545617-bTKCqE-image.png
    public int regionsBySlashes(String[] grid) {
        UnionFind uf = new UnionFind(grid.length * grid.length * 4);

        for (int i = 0; i < grid.length; i++) {
            for (int j = 0; j < grid.length; j++) {
                int index = 4 * (i * grid.length + j);
                switch (grid[i].charAt(j)) {
                    case ' ': {
                        uf.union(index, index + 1);
                        uf.union(index + 1, index + 2);
                        uf.union(index + 2, index + 3);
                        break;
                    }
                    case '/': {
                        uf.union(index, index + 3);
                        uf.union(index + 1, index + 2);
                        break;
                    }
                    case '\\': {
                        uf.union(index, index + 1);
                        uf.union(index + 2, index + 3);
                        break;
                    }
                }

                if (i > 0) {
                    uf.union(index, index - 4 * grid.length + 2);
                }

                if (j > 0) {
                    uf.union(index + 3, index - 3);
                }
            }
        }

        return uf.count();
    }
}
