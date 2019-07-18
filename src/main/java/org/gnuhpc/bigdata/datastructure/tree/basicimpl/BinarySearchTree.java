package org.gnuhpc.bigdata.datastructure.tree.basicimpl;

import lombok.Data;
import org.gnuhpc.bigdata.leetcode.utils.TreeNode;
import org.junit.Test;

import static java.lang.Math.max;
import static java.lang.Math.min;
import static org.junit.Assert.*;
/*
• 从定义出发：
    • 左子树都比根节点小
    • 右子树都不小于根节点
• 从效果出发：
    • 中序遍历 in-order traversal 是“ 不下降”序列• 从定义出发：
    • 如果一棵二叉树的中序遍历不是“不下降”序列，则一定不是BST
    • 如果一棵二叉树的中序遍历是不下降，也未必是BST

    O(h)的时间复杂度
 */
@Data
public class BinarySearchTree {
    private TreeNode root = null;

    public TreeNode create(int[] initArray){
        if (initArray.length == 0) return null;
        TreeNode head = new TreeNode(initArray[0]);
        TreeNode cur = head;

        for (int i = 1; i < initArray.length;) {
            if (initArray[i] >= head.val){
                if (cur.right==null){
                    cur.right = new TreeNode(initArray[i++]);
                } else{
                    cur = cur.right;
                }
            } else {
                if ( cur.left == null){
                    cur.left = new TreeNode(initArray[i++]);
                } else {
                    cur = cur.left;
                }
            }
        }

        return head;

    }

    private boolean find(int val) {
        TreeNode current = root;
        while (current != null) {
            if (current.val == val) {
                return true;
            } else if (current.val > val) {
                current = current.left;
            } else {
                current = current.right;
            }
        }
        return false;
    }

    private boolean findRecursive(int val){
        return findRecursiveHelper(root, val);
    }

    private boolean findRecursiveHelper(TreeNode root, int val) {
        if (root == null) return false;
        if (root.val == val) return true;
        else if (root.val > val) return findRecursiveHelper(root.left, val);
        else return findRecursiveHelper(root.right,val);
    }

    public void insert(int val) {
        TreeNode newNode = new TreeNode(val);
        if (root == null) {
            root = newNode;
            return;
        }
        TreeNode current = root;
        TreeNode parent = null;
        while (true) {
            parent = current;
            if (val < current.val) {
                current = current.left;
                if (current == null) {
                    parent.left = newNode;
                    return;
                }
            } else {
                current = current.right;
                if (current == null) {
                    parent.right = newNode;
                    return;
                }
            }
        }
    }

    private void insertRecursive(int val){
        root = insertRecursiveHelper(root, val);
    }

    private TreeNode insertRecursiveHelper(TreeNode root, int val) {
        if (root == null) return new TreeNode(val);
        if (root.val > val) {
            root.left = insertRecursiveHelper(root.left,val);
            return root;
        }
        if (root.val < val) {
            root.right = insertRecursiveHelper(root.right,val);
            return root;
        }

        return root;
    }

    public void deleteRecursive(int val){
        root = deleteRecursiveHelper(root,val);
    }

    private TreeNode deleteRecursiveHelper(TreeNode node, int val) {
        if (node == null) return null;
        if (val < node.val){
            node.left = deleteRecursiveHelper(node.left,val);
            return node;
        } else if (val > node.val){
            node.right = deleteRecursiveHelper(node.right,val);
            return node;
        } else {
            if (node.left == null) { //没有左子树 （包含没有左右子树的情况）
                return node.right;
            }

            if (node.right == null) { //没有右子树
                return node.left;
            }

            //左右节点都不为空
            TreeNode successor = findMinNode(node.right);
            successor.right = deleteMinHelper(node.right);
            successor.left = node.left;

            return successor;
        }
    }

    private TreeNode findMinNode(TreeNode node) {
        if (node.left == null) {
            return node;
        } else {
            return findMinNode(node.left);
        }
    }

    public void delete(int val){
        TreeNode current = root;
        TreeNode prev = root;
        boolean isLeftChild = false;

        while (current.val!= val){
            prev = current;
            if (current.val>val){
                isLeftChild = true;
                current = current.left;
            } else {
                isLeftChild = false;
                current = current.right;
            }

            if (current==null){
                return;
            }
        }


        //Case 1: delete isLeaf node
        if (current.left == null && current.right == null){
            if(current == root){
                root = null;
            }
            if (isLeftChild){
                prev.left = null;
            } else {
                prev.right = null;
            }
        }

        //Case 2: has left child
        else if (current.right == null){
            if (current == root) {
                root = current.left;
            }

            if (isLeftChild){
                prev.left = current.left;
            } else {
                prev.right = current.left;
            }

        //Case 3: has right child
        } else if( current.left == null){
            if (current == root) {
                root = current.right;
            }

            if (isLeftChild){
                prev.left = current.right;
            } else {
                prev.right = current.right;
            }

        } else { // Both right and left are not null.
            TreeNode successor = getSuccessor(current);
            if (current == root){
                root = successor;
            }

            if (isLeftChild){
                prev.left = successor;
            } else {
                prev.right = successor;
            }

            successor.left = current.left;
        }

    }

    //Get smallest node in the subtree， 后继节点
    //找到右子树中最左的节点
    private TreeNode getSuccessor(TreeNode delNode)
    {
        TreeNode successorParent = delNode;
        TreeNode successor = delNode;
        TreeNode current = delNode.right;	// go to right child
        while (current != null)
        {
            successorParent = successor;
            successor = current;
            current = current.left;	//go to left child
        }// end while


        if(successor != delNode.right)	//if successor is not right child
        {
            successorParent.left= successor.right;
            successor.right= delNode.right;
        } // end if

        return successor;
    }

    public static int findMax(TreeNode root){
        int max = Integer.MIN_VALUE;
        while (root!=null){
            max = max(max,root.val);
            root = root.right;
        }
        return max;
    }

    public static int findMin(TreeNode root){
        int min = Integer.MAX_VALUE;
        while (root!=null){
            min = min(min,root.val);
            root = root.left;
        }
        return min;
    }

    public void deleteMin(){
        if(root==null) return;
        root = deleteMinHelper(root);
    }

    private TreeNode deleteMinHelper(TreeNode root) {
        if (root.left==null){
            return root.right;
        } else {
            root.left = deleteMinHelper(root.left);
            return root;
        }
    }

    public void deleteMax(){
        if (root==null) return;
        root = deleteMaxHelper(root);
    }

    private TreeNode deleteMaxHelper(TreeNode root) {
        if (root.right==null) {
            return root.left;
        } else {
            root.right = deleteMaxHelper(root.right);
            return root;
        }
    }

    @Test
    public void testBST() {
        //given
        BinarySearchTree b = new BinarySearchTree();

        //when
        b.insert(3);
        b.insert(8);
        b.insert(1);
        b.insert(4);
        b.insert(6);
        b.insert(2);
        b.insert(10);
        b.insert(9);
        b.insert(20);
        b.insert(25);
        b.insert(15);
        b.insert(16);
        //then
        int[] results = TreeTraversal.inorder(b.root).stream().mapToInt(i -> i).toArray();
        int[] array =  new int[]{
                1, 2, 3, 4, 6, 8, 9, 10, 15, 16, 20, 25};
        assertArrayEquals(results, array);

        assertTrue(b.find(4));
        assertFalse(b.find(100));

        b.delete(20);

        TreeTraversal.inorder(b.root).forEach(System.out::println);
    }

    @Test
    public void testBSTDeletion(){
        BinarySearchTree b = new BinarySearchTree();

        b.insert(3);
        b.insert(8);
        b.insert(1);
        b.insert(6);

        b.delete(8);

        int[] ids = TreeTraversal.inorder(b.root).stream().mapToInt(i -> i).toArray();
        int[] array = new int[] {1,3,6};

        assertArrayEquals(ids, array);
    }


    @Test
    public void testBuildBST(){
        BinarySearchTree b = new BinarySearchTree();
        b.insertRecursive(8);
        b.insertRecursive(5);
        b.insertRecursive(1);
        b.insertRecursive(2);
        b.insertRecursive(3);
        b.insertRecursive(7);

        TreeTraversal.level(b.root).forEach(System.out::println);
        b.deleteRecursive(5);
        System.out.println();
        TreeTraversal.level(b.root).forEach(System.out::println);
//        System.out.println(b.findRecursive(5));
//        System.out.println(b.findRecursive(4));
//
//        b.deleteMin();
//        TreeTraversal.level(b.find).forEach(System.out::println);
//
//        System.out.println();
//        b.deleteMax();
//        TreeTraversal.level(b.find).forEach(System.out::println);

    }

    @Test
    public void testBuildBST3(){
        TreeNode root = new BinarySearchTree().create(new int[]{1,2,3,3,4});
        System.out.println(TreeTraversal.preorder(root));
    }

}
