package org.gnuhpc.interview.leetcode.solutions;

import org.gnuhpc.interview.datastructure.tree.basicimpl.TreeCreator;
import org.gnuhpc.interview.datastructure.tree.basicimpl.TreeNode;
import org.junit.Test;

import java.util.*;
import java.util.stream.Collectors;

/**
 * Copyright gnuhpc 19-8-26
 */
public class VerticalTraversal987 {
    class Cor {
        public final int x;
        public final int y;

        public Cor(int x, int y) {
            this.x = x;
            this.y = y;
        }
    }

    class Tuple implements Comparable {
        public final Cor cor;
        public final TreeNode node;

        public Tuple(Cor cor, TreeNode node) {
            this.cor = cor;
            this.node = node;
        }

        @Override
        public int compareTo(Object o) {
            Tuple tuple = (Tuple) o;
            if (this.cor.x == tuple.cor.x) {
                if (this.cor.y == tuple.cor.y) {
                    return this.node.val - tuple.node.val;
                } else {
                    return -(this.cor.y - tuple.cor.y);
                }
            } else { //x1!=x2
                return this.cor.y - tuple.cor.y;
            }
        }
    }

    /*
    BFS
     */
    public List<List<Integer>> verticalTraversal(TreeNode root) {
        List<List<Integer>> res = new ArrayList<>();
        List<Tuple> posList = new ArrayList<>();

        if (root == null) return res;

        Queue<Tuple> queue = new LinkedList<>();
        Tuple rootTuple = new Tuple(new Cor(0, 0), root);
        queue.offer(rootTuple);
        posList.add(rootTuple);

        while (!queue.isEmpty()) {
            int size = queue.size();
            for (int i = 0; i < size; i++) {
                Tuple tuple = queue.poll();
                if (tuple.node.left != null) {
                    Tuple left = new Tuple(new Cor(tuple.cor.x - 1, tuple.cor.y - 1), tuple.node.left);
                    queue.offer(left);
                    posList.add(left);
                }
                if (tuple.node.right != null) {
                    Tuple right = new Tuple(new Cor(tuple.cor.x + 1, tuple.cor.y - 1), tuple.node.right);
                    queue.offer(right);
                    posList.add(right);
                }
            }
        }

        Collections.sort(posList);

        Map<Integer, List<Tuple>> temp = posList.stream().collect(Collectors.groupingBy(tuple -> tuple.cor.x));

        TreeMap<Integer, List<Tuple>> treeMap = new TreeMap<>(temp);

        treeMap.forEach((k, v) -> {
            Collections.sort(v);
            List<Integer> l = v.stream().map(t -> t.node.val).collect(Collectors.toList());
            res.add(l);
        });

        return res;
    }

    /*
    DFS
     */

    private Map<Integer, List<List<Integer>>> map = new HashMap<>();
    private int depth;

    public List<List<Integer>> verticalTraversal2(TreeNode root) {
        depth = getDepth(root);
        dfs(root, 0, 0);
        List<List<Integer>> result = new ArrayList<>();
        int min = 0;
        for (Integer key : map.keySet()) {
            min = Math.min(min, key);
            result.add(new ArrayList<Integer>());
        }
        for (Integer key : map.keySet()) {
            for (int i = 0; i < depth; i++) {
                List<Integer> temp = map.get(key).get(i);
                if (temp.size() == 1)
                    result.get(key - min).add(temp.get(0));
                else if (temp.size() > 1) {  // 同层同列的元素，按照从小到大排序
                    Collections.sort(temp);
                    for (Integer t : temp)
                        result.get(key - min).add(t);
                }
            }
        }
        return result;
    }

    public int getDepth(TreeNode root) {
        if (root == null)
            return 0;
        return 1 + Math.max(getDepth(root.left), getDepth(root.right));
    }

    public void dfs(TreeNode root, int x, int y) {
        if (root == null)
            return;
        List<List<Integer>> temp;
        if (map.containsKey(x))
            temp = map.get(x);
        else {
            temp = new ArrayList<>();
            for (int i = 0; i < depth; i++)
                temp.add(new ArrayList<Integer>());
        }
        temp.get(y).add(root.val);
        map.put(x, temp);
        dfs(root.left, x - 1, y + 1);
        dfs(root.right, x + 1, y + 1);
    }

    @Test
    public void test() {
        TreeNode root = TreeCreator.createTreeByLevel(new Integer[]{
                0,
                1,
                2,
                3,
                16,
                4,
                5,
                7,
                8,
                17,
                56,
                6,
                null,
                18,
                11,
                9,
                36,
                14,
                10,
                20,
                54,
                null,
                null,
                13,
                21,
                null,
                29,
                12,
                null,
                26,
                25,
                null,
                null,
                null,
                39,
                27,
                32,
                47,
                31,
                76,
                null,
                19,
                null,
                null,
                33,
                null,
                34,
                86,
                15,
                48,
                38,
                53,
                52,
                50,
                41,
                91,
                null,
                null,
                81,
                null,
                null,
                87,
                35,
                null,
                102,
                null,
                22,
                44,
                49,
                46,
                59,
                null,
                100,
                24,
                37,
                null,
                55,
                75,
                78,
                66,
                62,
                null,
                null,
                null,
                null,
                null,
                null,
                null,
                null,
                null,
                null,
                null,
                89,
                105,
                73,
                null,
                null,
                65,
                23,
                97,
                63,
                57,
                61,
                null,
                84,
                64,
                101,
                null,
                null,
                45,
                28,
                40,
                51,
                null,
                88,
                107,
                null,
                null,
                null,
                85,
                null,
                83,
                null,
                null,
                99,
                null,
                null,
                null,
                null,
                null,
                90,
                67,
                42,
                null,
                null,
                79,
                77,
                70,
                null,
                null,
                null,
                98,
                null,
                108,
                null,
                null,
                null,
                null,
                null,
                43,
                30,
                null,
                null,
                null,
                60,
                null,
                null,
                null,
                null,
                null,
                null,
                null,
                null,
                null,
                null,
                null,
                null,
                null,
                74,
                69,
                null,
                96,
                null,
                null,
                null,
                null,
                null,
                null,
                104,
                null,
                null,
                80,
                null,
                null,
                58,
                71,
                null,
                82,
                null,
                null,
                null,
                null,
                106,
                null,
                null,
                103,
                92,
                68,
                72,
                null,
                95,
                93,
                94,
                null,
                null,
                null,
                null,
                null,
                null,
                null,
                null,
                109});

        verticalTraversal(root);
    }
}
