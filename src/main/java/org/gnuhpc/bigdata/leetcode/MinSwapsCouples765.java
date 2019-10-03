package org.gnuhpc.bigdata.leetcode;

import org.gnuhpc.bigdata.datastructure.unionfind.QuickUnion;
import org.junit.Test;

import java.util.Arrays;

public class MinSwapsCouples765 {
    //Method 1: 贪心
    public int minSwapsCouples(int[] row) {
        int res = 0;

        int pairs = row.length/2 ;
        int[] ids = new int[row.length];
        Arrays.fill(ids,-1);

        //固定小的为idx，大的为value
        for (int i = 0; i < pairs; i++) {
            int a = row[2*i];
            int b = row[2*i+1];
            if (Math.min(a,b)%2 ==1|| Math.abs(a-b) != 1){
                ids[Math.min(a,b)] = Math.max(a,b);
            }
        }

        //如果都是-1说明都已经牵手，直接返回
        int j = 0;
        for (; j < ids.length; j++) {
            if (ids[j]!=-1){
                break;
            }
        }
        if (j == ids.length) return 0;

        //逐对交换
        for (int i = 0; i < ids.length-1; i++) {
            //-1的说明已经牵手或者没有出现，ids[i] = i+1 说明已经调整到位， 都是直接跳过
            if (ids[i] == -1 || ids[i] == i+1) continue;

            if (ids[i] != i+1){ //没有牵手的请调整
                makeRight(i,ids);
                res++;
            }

        }

        return res;
    }


    /*  实现如下逻辑, 还是idx小于value的规则， 确实有点绕，小心写就是...
          0  1  3   -->    0   1  2  3
          4  2  5          1  -1  4  5
     */
    private void makeRight(int i, int[] ids1) {
        int newIdx = ids1[i];
        int oldValue = ids1[i+1];
        int oldIdx = i+1;

        ids1[i] = i+1;
        if (ids1[oldIdx]< newIdx){
            ids1[oldValue] = newIdx;
        } else {
            ids1[newIdx] = oldValue;
        }
        ids1[oldIdx] = -1;
    }

    //Method 2 : Union - Find

    /*
    下面我们来看一种使用联合查找Union Find的解法。该解法对于处理群组问题时非常有效，比如岛屿数量有关的题就经常使用UF解法。
    如果总共有n个数字，则共有 n/2 对儿，所以我们初始化 n/2 个群组，每次处理两个数字。
    关键来了：怎么对应上并查集这个数据结构？ 通过定义一个起群组号。每个数字除以2就是其群组号，那么属于同一组的两个数的群组号是相同的，比如2和3，其分别除以2均得到1，所以其组号均为1。
    由于我们每次取的是两个数，且计算其群组号，并调用find函数，那么如果这两个数的群组号相同，那么find函数必然会返回同样的值，
    我们不用做什么额外动作，因为本身就是一对儿。
    如果两个数不是一对儿，那么其群组号必然不同，在二者没有归为一组之前，调用find函数返回的值就不同，此时我们将二者归为一组，
    并且cnt自减1，忘说了，cnt初始化为总群组数，即 n/2。
    那么最终cnt减少的个数就是交换的步数，还是用上面讲解中的例子来说明吧：
[3   1   4   0   2   5]
最开始的群组关系是：
群组0：0，1
群组1：2，3
群组2：4，5
取出前两个数字3和1，其群组号分别为1和0，带入find函数返回不同值，则此时将群组0和群组1链接起来，变成一个群组，则此时只有两个群组了，cnt自减1，变为了2。
群组0 & 1：0，1，2，3
群组2：4，5
此时取出4和0，其群组号分别为2和0，带入find函数返回不同值，则此时将群组0&1和群组2链接起来，变成一个超大群组，cnt自减1，变为了1。
群组0 & 1 & 2：0，1，2，3，4，5
此时取出最后两个数2和5，其群组号分别为1和2，因为此时都是一个大组内的了，带入find函数返回相同的值，不做任何处理。最终交换的步数就是cnt减少值，为2

     */
    public int minSwapsCouples2(int[] row) {
        int N = row.length/ 2;
        int count = N;
        QuickUnion uf = new QuickUnion(N);
        for (int i = 0; i < N; i++) {
            int a = row[2*i];
            int b = row[2*i + 1];
            if (!uf.connected(a/2,b/2)){
                count--;
            }
            uf.union(a/2, b/2);
        }
        return N - count;
    }

    @Test
    public void test(){
        System.out.println(minSwapsCouples2(new int[]{1,4,0,5,8,7,6,3,2,9}));
    }
}
