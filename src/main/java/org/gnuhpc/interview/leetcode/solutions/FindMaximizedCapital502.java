package org.gnuhpc.interview.leetcode.solutions;

import org.gnuhpc.interview.leetcode.utils.Point;

import java.util.Arrays;
import java.util.Comparator;
import java.util.PriorityQueue;

/**
 * Copyright gnuhpc 2020/2/11
 */
public class FindMaximizedCapital502 {
    public int findMaximizedCapital(int k, int w, int[] profits, int[] capital) {
        int n = profits.length;
        int[][] projects = new int[n][2];
        for(int i=0;i<n;i++){
            projects[i][0] = capital[i];
            projects[i][1] = profits[i];
        }
        Arrays.sort(projects, (a, b)->a[0] - b[0]);
        PriorityQueue<Integer> cur = new PriorityQueue<>((a,b)->b-a);
        for(int i=0,idx=0;i < k; i++){
            while(idx < n && projects[idx][0] <= w)
                cur.add(projects[idx++][1]);
            if(cur.size() > 0)
                w += cur.poll();
            else
                break;
        }
        return w;
    }
}


/*
package greedy

import (
	"container/heap"
	"fmt"
)

func findMaximizedCapital(k int, W int, Profits []int, Capital []int) int {
	h1 := MaxHeap{}
	h2 := MinHeap{}
	res := W
	for i := range Capital {
		cur := pair{Profits[i], Capital[i]}
		heap.Push(&h2, cur)
	}
	for k > 0 {
		fmt.Println("res", res)
		for len(h2) != 0 && h2[0].c <= res {
			top := heap.Pop(&h2).(pair)
			fmt.Println("cap:", top.c)
			heap.Push(&h1, top)
		}
		if len(h1) == 0 {
			return res
		} else {
			mostPro := heap.Pop(&h1).(pair)
			res += mostPro.p
			k--
		}
	}
	return res
}

type pair struct {
	p int
	c int
}

//大根堆，按利润排序
type MaxHeap []pair

func (h MaxHeap) Len() int {
	return len(h)
}
func (h MaxHeap) Less(i, j int) bool {
	return h[i].p > h[j].p
}
func (h MaxHeap) Swap(i, j int) {
	h[i], h[j] = h[j], h[i]
}
func (h *MaxHeap) Pop() interface{} {
	res := (*h)[len(*h)-1]
	(*h) = (*h)[:len(*h)-1]
	return res
}
func (h *MaxHeap) Push(x interface{}) {
	*h = append((*h), x.(pair))
}

//按资本排序
type MinHeap []pair

func (h MinHeap) Len() int {
	return len(h)
}
func (h MinHeap) Less(i, j int) bool {
	return h[i].c < h[j].c
}
func (h MinHeap) Swap(i, j int) {
	h[i], h[j] = h[j], h[i]
}
func (h *MinHeap) Pop() interface{} {
	res := (*h)[len(*h)-1]
	(*h) = (*h)[:len(*h)-1]
	return res
}
func (h *MinHeap) Push(x interface{}) {
	*h = append((*h), x.(pair))
}


https://www.huaweicloud.com/articles/a879fb2512954074896cebc90c4ab06b.html

作者：rclove2k
链接：https://leetcode-cn.com/problems/ipo/solution/tan-xin-sou-suo-li-run-zui-da-de-ke-yong-56d7/
来源：力扣（LeetCode）
著作权归作者所有。商业转载请联系作者获得授权，非商业转载请注明出处。



 */