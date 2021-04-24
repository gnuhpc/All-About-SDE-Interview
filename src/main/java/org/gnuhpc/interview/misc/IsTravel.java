package org.gnuhpc.interview.misc;

import org.junit.Test;

import java.util.*;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

public class IsTravel {
    class TreeNode {
        public int val;
        public TreeNode left;
        public TreeNode right;

        public TreeNode(int val) {
            this.val = val;
        }
    }
    private Map<Integer,Integer> seqMap  = new HashMap<>();

    public boolean canTravel(TreeNode root, int[] seqs){
        if(root==null) return false;

        for(int i =0; i<seqs.length;i++) seqMap.put(seqs[i], i);

        List<Integer> res = helper(root);
        return res!=null;
    }

    private List<Integer> helper(TreeNode root){
        if(root == null) return new ArrayList<>();

        List<Integer> l1 = helper(root.left);
        List<Integer> l2 = helper(root.right);
        List<Integer> tmp;

        if(l1==null || l2 == null) return null;
        else {
            //Merge
            tmp = tryMerge(l1,l2);
        }

        //processing
        if(seqMap.containsKey(root.val)){
            tmp.add(seqMap.get(root.val));
        }
        return tmp;
    }

    private List<Integer> tryMerge(List<Integer> l1, List<Integer> l2) {
        if(l2.size() ==0 && l1.size()==0) return l1;
        if(l1.size() ==0 && l2.size()!=0) return l1;
        if(l2.size() ==0 && l1.size()!=0) return l2;
        if(l1.get(l1.size()-1)+1 == l2.get(0)){
            l1.addAll(l2);
            return l1;
        } else if(l2.get(l2.size()-1)+1 == l1.get(0)){
            l2.addAll(l1);
            return l2;
        } else {
            return null;
        }
    }

    private TreeNode createTreeByLevel(Integer[] initArray) {
        TreeNode head = new TreeNode(initArray[0]);
        Queue<TreeNode> q = new LinkedList<>();
        q.offer(head);

        for (int i = 0; !q.isEmpty() && i < initArray.length; i++) {
            TreeNode cur = q.poll();
            if (2 * i + 1 < initArray.length && initArray[2 * i + 1] != null) {
                cur.left = new TreeNode(initArray[2 * i + 1]);
                q.offer(cur.left);
            }

            if (2 * i + 2 < initArray.length && initArray[2 * i + 2] != null) {
                cur.right = new TreeNode(initArray[2 * i + 2]);
                q.offer(cur.right);
            }
        }

        return head;
    }

    @Test
    public void test1(){
        TreeNode root = createTreeByLevel(new Integer[]{
                5,10,13,null,null,12,11
        });

        assertTrue(canTravel(root, new int[]{10,12,11}));
    }

    @Test
    public void test2(){
        TreeNode root = createTreeByLevel(new Integer[]{
                5,10,13,null,null,12,11
        });

        assertFalse(canTravel(root, new int[]{12,10,11}));
    }

    @Test
    public void test3(){
        TreeNode root = createTreeByLevel(new Integer[]{
                5,10,13,null,null,12,11
        });

        assertTrue(canTravel(root, new int[]{12,11,10}));
    }

    @Test
    public void test4(){
        TreeNode root = createTreeByLevel(new Integer[]{
                5,10,13,null,null,12,11
        });

        assertFalse(canTravel(root, new int[]{11,10,12}));
    }
}
