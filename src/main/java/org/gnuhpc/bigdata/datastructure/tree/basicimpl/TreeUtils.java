package org.gnuhpc.bigdata.datastructure.tree.basicimpl;

import org.gnuhpc.bigdata.leetcode.utils.TreeNode;
import org.junit.Test;

import java.util.*;

public class TreeUtils {

    /*
    返回从root到值为x的路径
     */
    public static Stack<TreeNode> pathToX(TreeNode root, int x) {
        if (root == null) {
            return null;
        }

        if (root.val == x) {
            Stack<TreeNode> path = new Stack<>();
            path.push(root);
            return path;
        }

        Stack<TreeNode> leftPath = pathToX(root.left, x);
        if (leftPath != null) {
            leftPath.push(root);
            return leftPath;
        }

        Stack<TreeNode> rightPath = pathToX(root.right, x);
        if (rightPath != null) {
            rightPath.push(root);
            return rightPath;
        }

        return null;
    }

    public static TreeNode findNode(TreeNode root, int value) {
        TreeNode left=null, right=null;
        if (root == null) {
            return null;
        }

        if(root.val == value) {
            return root;
        }

        left = findNode(root.left, value);
        if (left!=null){
            return left;
        }

        right= findNode(root.right, value);
        if (right!=null){
            return right;
        }

        return null;
    }

    public TreeNode findNodeNonRecusive(TreeNode root, int value){
        if (root==null) return null;

        Queue<TreeNode> q = new LinkedList<>();
        q.offer(root);

        while (!q.isEmpty()){
            TreeNode n = q.poll();
            if (n.val == value){
                return n;
            }

            if (n.left!=null) q.offer(n.left);
            if (n.right!=null) q.offer(n.right);
        }

        return null;
    }

    public static int findMax(TreeNode root){
        int max,leftMax,rightMax;

        if(root==null){ //到了叶子节点的null左右节点，
            // 则直接返回最小值,作为基准
            return Integer.MIN_VALUE;
        }

        leftMax = findMax(root.left);
        rightMax = findMax(root.right);

        int rootVal = root.val;

        if (leftMax>rightMax){
            max = leftMax;
        } else {
            max = rightMax;
        }

        if (rootVal > max){
            max = rootVal;
        }

        return max;
    }

    public static int findMaxNonRecusive(TreeNode root){
        int max = Integer.MIN_VALUE;
        Queue<TreeNode> q = new LinkedList<>();

        if (root == null) {
            return Integer.MIN_VALUE;
        }

        q.offer(root);

        while (!q.isEmpty()){
            TreeNode node = q.poll();

            if (node.val>max){
                max = node.val;
            }

            if (node.right!=null){
                q.offer(node.right);
            }

            if (node.left != null){
                q.offer(node.left);
            }
        }

        return max;
    }


    //非递归从level遍历
    public static int sizeofTree(TreeNode root){
        if (root==null){
            return 0;
        }

        return sizeofTree(root.right) + sizeofTree(root.left) +1;
    }

    public void deleteTree(TreeNode root){
        if (root==null){
            return;
        }

        deleteTree(root.left);
        deleteTree(root.right);

        root = null;
    }

    public static int heightOfTree(TreeNode root){
        int leftHeight, rightHeight;
        if (root==null){
            return 0;
        }

        leftHeight = heightOfTree(root.left);
        rightHeight = heightOfTree(root.right);

        return Math.max(leftHeight,rightHeight)+1;
    }

    public static int heightOfTreeNonRecursive(TreeNode root){
        int height = 0;
        if (root == null){
            return 0;
        }

        Queue<TreeNode> q = new LinkedList<>();
        q.offer(root);

        while (!q.isEmpty()){
            int size = q.size();
            for (int i = 0; i < size; i++) {
                TreeNode n = q.poll();
                if (n.left!=null) q.offer(n.left);
                if (n.right!=null) q.offer(n.right);
            }

            height++;
        }

        return height;
    }

    public static TreeNode findDeepestNode(TreeNode root){
        if (root == null) return null;
        if (root.left == null && root.right == null) return root;

        Queue<TreeNode> queue = new LinkedList<>();

        queue.offer(root);

        TreeNode lastNode = null;
        while (!queue.isEmpty()){
            lastNode = queue.poll();
            if (lastNode.left != null)
                queue.offer(lastNode.left);
            if (lastNode.right != null)
                queue.offer(lastNode.right);

        }
        return lastNode;
    }

    public static int sizeOfLeaves(TreeNode root){
        if (root==null) return -1;

        int res = 0;

        Queue<TreeNode> q = new LinkedList<>();
        q.offer(root);

        while (!q.isEmpty()){
            TreeNode n = q.poll();
            if (n.left!=null) q.offer(n.left);
            if (n.right!=null) q.offer(n.right);

            if (n.left == null && n.right == null) res++;
        }

        return res;
    }

    static int size = 0;
    public static int sizeOfLeavesRecursive(TreeNode root) {
        if (root == null) return 0;
        if (root.right == null && root.left == null) return 1;
        return sizeOfLeavesRecursive(root.left) + sizeOfLeavesRecursive(root.right);
    }

    public static int sizeOfFullNodes(TreeNode root){
        if (root==null) return -1;

        int res = 0;

        Queue<TreeNode> q = new LinkedList<>();
        q.offer(root);

        while (!q.isEmpty()){
            TreeNode n = q.poll();
            if (n.left!=null) q.offer(n.left);
            if (n.right!=null) q.offer(n.right);

            if (n.left != null && n.right != null) res++;
        }

        return res;
    }

    public static int sizeOfHalfNodes(TreeNode root){
        if (root==null) return -1;

        int res = 0;

        Queue<TreeNode> q = new LinkedList<>();
        q.offer(root);

        while (!q.isEmpty()){
            TreeNode n = q.poll();
            if (n.left!=null) q.offer(n.left);
            if (n.right!=null) q.offer(n.right);

            if (n.left == null && n.right != null) res++;
            if (n.left != null && n.right == null) res++;
        }

        return res;
    }

    public static List<TreeNode> getAllAncestors(TreeNode root, int val) {
        if (root == null){
            return null;
        }

        if ((root.left!=null && root.left.val == val) ||
                (root.right!=null && root.right.val == val)){
            List<TreeNode> treeNodes = new ArrayList<>();
            treeNodes.add(root);
            return treeNodes;
        }

        List<TreeNode> leftList = getAllAncestors(root.right, val);
        //有结果就追加
        if (leftList!=null){
            leftList.add(root);
            return leftList;
        }

        List<TreeNode> rightList = getAllAncestors(root.left, val);
        //有结果就追加
        if (rightList!=null){
            rightList.add(root);
            return rightList;
        }

        return null;
    }

    //当出现修改左右子树的情况时的递归程序模板
    public static TreeNode removeHalfNodes(TreeNode root){
        if (root == null) return null;
        root.left = removeHalfNodes(root.left);
        root.right = removeHalfNodes(root.right);
        if (root.left != null && root.right !=null) return root;
        if (root.left!=null && root.right ==null) {
            return root.left;
        }

        if (root.left==null && root.right != null){
            return root.right;
        }

        return root;
    }

    public static TreeNode removeLeafNodes(TreeNode root){
        if (root == null) return null;

        if (root.left==null && root.right==null) {
            return null;
        }

        root.left = removeLeafNodes(root.left);
        root.right = removeLeafNodes(root.right);

        return root;
    }

    public TreeNode removeLeafNodeByValue(TreeNode root, int val){
        if (root == null)
            return null;

        if (root.val == val && root.left == null && root.right == null) {
            return null;
        }
        root.left = removeLeafNodeByValue(root.left, val);
        root.right = removeLeafNodeByValue(root.right, val);

        return root;
    }



    /**
     * 二叉树的宽度：各层节点数的最大值
     * @param root 二叉树的根节点
     * @return 二叉树的宽度
     */
    public static int widthOfTree(TreeNode root) {
        if (root == null) {
            return 0;
        }
        //当前二叉树最大宽度=根节点
        int maxWith = 1;
        int currentWidth = 0;
        //队列：先进先出，每次循环后保留一层树的某一层的所有节点
        Queue<TreeNode> queue = new LinkedList<>();
        queue.add(root);
        while (queue.size() > 0) {
            currentWidth = queue.size();
            //遍历当前层的所有节点，并将所有节点的子节点加入到队列中
            for (int i = 0; i < currentWidth; i++) {
                TreeNode node = queue.peek();
                queue.poll();
                if (node.left!= null) {
                    queue.add(node.left);
                }
                if (node.right!= null) {
                    queue.add(node.right);
                }
            }
            maxWith = Math.max(maxWith, queue.size());
        }
        return maxWith;
    }

    @Test
    public void test(){
        TreeNode root = TreeCreator.createTreeByLevel(new Integer[]{1,5,3,null,5,4,null});

        System.out.println(sizeOfLeavesRecursive(root));

        removeLeafNodeByValue(root,5);

        removeLeafNodes(root);

        System.out.println(findMax(root));
        System.out.println(findMaxNonRecusive(root));
        System.out.println(findNode(root,7).val);
        System.out.println(findNodeNonRecusive(root,7).val);
        System.out.println(sizeofTree(root));
        System.out.println(heightOfTree(root));
        System.out.println(heightOfTreeNonRecursive(root));
        System.out.println(findDeepestNode(root).val);
        System.out.println("Size of Leaves:");
        System.out.println(sizeOfLeaves(root));
        System.out.println(sizeOfLeavesRecursive(root));
        System.out.println("Size of Fulle nodes:");
        System.out.println(sizeOfFullNodes(root));
        System.out.println(sizeOfHalfNodes(root));

        System.out.println("Get path to 8:");
        Stack<TreeNode> res = pathToX(root, 8);

        while (!res.isEmpty()){
            System.out.print(res.pop().val + " ");
        }
        System.out.println("\n====END====");

        System.out.println("Get all ancestors of 8:");
        List<TreeNode> res2 = getAllAncestors(root, 8);
        for(TreeNode n: res2){
            System.out.print(n.val + " ");
        }
        System.out.println("\n====END====");

//        TreeNode removeRoot = removeHalfNodes(find);
//        System.out.println();

        TreeNode removeLeafRoot = removeLeafNodes(root);
        System.out.println();
    }
}
