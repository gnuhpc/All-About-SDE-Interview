package org.gnuhpc.bigdata.datastructure.tree.basicimpl;

import lombok.AllArgsConstructor;
import org.gnuhpc.bigdata.leetcode.utils.TreeNode;
import org.junit.Test;

import java.util.*;
import java.util.stream.Collectors;

/*
stack for dfs, imagine a vertical flow
queue for bfs, horizontal flow

碰到二叉树的问题，就想想整棵树在该问题上的结果
和左右儿子在该问题上的结果之间的联系是什么

 */
public class TreeTraversal {

    public static List<Integer> preorder(TreeNode root) {
        List<Integer> res = new ArrayList<>();
        if (root == null) return res;
        preorderHelper(res, root);
        return res;
    }

    //典型的DFS

    public static void preorderHelper(List<Integer> res, TreeNode root) {
        if (root == null) return;
        res.add(root.val);
        if (root.left != null) preorderHelper(res, root.left);
        if (root.right != null) preorderHelper(res, root.right);
    }

    /*
    Merhod 2： Non recursive , 和下边方法的区别是这个方法一直在左边进行，不得以才进行右边的节点
     */
    /*
    DFS模板方法
    */
    public static List<Integer> preorderNonRecursive(TreeNode root) {
        List<Integer> res = new ArrayList<>();
        Deque<TreeNode> stack = new LinkedList<>();
        TreeNode p = root;

        while (p != null || !stack.isEmpty()) {
            while (p != null) {
                res.add(p.val);
                stack.push(p);
                p = p.left;
            }
            p = stack.pop();
            p = p.right;
        }
        return res;
    }

    public static List<Integer> preorderNonRecursiveDFS(TreeNode root) {
        List<Integer> result = new ArrayList<>();

        Deque<TreeNode> stack = new LinkedList<>();
        stack.push(root);
        while (!stack.isEmpty()) {
            TreeNode n = stack.pop();
            result.add(n.val);
            if (n.right != null) {
                stack.push(n.right);
            }
            if (n.left != null) {
                stack.push(n.left);
            }
        }
        return result;
    }

    /*
    Method3 : 分治法
     */
    public ArrayList<Integer> preorderTraversal(TreeNode root) {
        ArrayList<Integer> result = new ArrayList<>();
        // null or leaf
        if (root == null) {
            return result;
        }

        // Divide
        ArrayList<Integer> left = preorderTraversal(root.left);
        ArrayList<Integer> right = preorderTraversal(root.right);

        // Conquer
        result.add(root.val);
        result.addAll(left);
        result.addAll(right);
        return result;
    }


    public static List<Integer> inorder(TreeNode root) {
       List<Integer> res = new ArrayList<>();
       if (root == null) return res;
       inorderHelper(res, root);
       return res;
    }

    public static void inorderHelper(List<Integer> res, TreeNode root) {
        if (root == null) return;
        inorderHelper(res, root.left);
        res.add(root.val);
        inorderHelper(res, root.right);
    }

    /*
        Non-recursive ，注意res.add的位置
    */
    public static List<Integer> inorderNonRecursive(TreeNode root) {
        List<Integer> res = new ArrayList<>();
        Deque<TreeNode> stack = new LinkedList<>();
        TreeNode p = root;

        while (p != null || !stack.isEmpty()) {
            while (p != null) {
                stack.push(p);
                p = p.left;
            }
            p = stack.pop();
            res.add(p.val);
            p = p.right;
        }
        return res;

    }

    public static List<Integer> postorder(TreeNode root) {
        List<Integer> res = new ArrayList<>();
        if (root == null) return res;
        postorderHelper(res, root);
        return res;
    }

    public static void postorderHelper(List<Integer> res, TreeNode root) {
        if (root == null) return;
        postorderHelper(res, root.left);
        postorderHelper(res, root.right);
        res.add(root.val);
    }


    //后续遍历的真实写法， 并不推荐
    public static List<Integer> postorderNonRecursive1(TreeNode root) {
        Stack<TreeNode> stack=new Stack<>();
        List<Integer> res=new ArrayList<>();
        if(root==null) return res;
        stack.push(root);
        TreeNode pre=root;
        TreeNode cur=root;
        while(!stack.isEmpty())
        {
            pre=cur;
            cur=stack.peek();
            //不是叶子结点或者不是在向上回溯
            if((cur.left!=null||cur.right!=null)&&(pre!=cur.left&&pre!=cur.right)) {
                if(cur.right!=null) stack.push(cur.right);
                if(cur.left!=null)  stack.push(cur.left);
            }
            else {
                res.add(stack.pop().val);
            }
        }
        return res;
    }

    //后续遍历非递归，真实的另一种写法, 推荐
    @AllArgsConstructor
    static class StackNode{
        TreeNode n;
        boolean isFirst; //记录是不是第一次被遍历到
    }

    public static List<Integer> postorderNonRecursive2(TreeNode root) {
        List<Integer> res = new ArrayList<>();
        Deque<StackNode> stack = new LinkedList<>();

        TreeNode cur = root;
        if (cur == null) return res;

        while (cur!=null || !stack.isEmpty()){
            // 要点，一直向左找下去
            while (cur!=null){
                stack.push(new StackNode(cur,true));
                cur = cur.left;
            }

            if (!stack.isEmpty()){
                StackNode sn = stack.pop();

                if (sn.isFirst) {
                    sn.isFirst = false;
                    stack.push(sn);
                    cur = sn.n.right;
                } else{
                    res.add(sn.n.val);
                }
            }
        }

        return res;
    }

    //后续遍历非递归，但是这个并不是真实的，只是从结果集上是对的，访问的顺序不是
    //推荐写法
    //和preorder就是左右的前后顺序变了下，再reverse一下，非常好记!
    public static List<Integer> postorderNonRecursive(TreeNode root) {
        LinkedList<Integer> res = new LinkedList<>();
        Deque<TreeNode> stack = new LinkedList<>();

        TreeNode p = root;

        while (p != null || !stack.isEmpty()) {
            while (p != null) {
                res.addFirst(p.val);
                stack.push(p);
                p = p.right;
            }
            p = stack.pop();
            p = p.left;
        }
        return res;
    }

    public static List<List<Integer>> level(TreeNode root){
        int depth = 0; //使用BFS进行depth的算法
        List<List<Integer>> res = new ArrayList<>();
        if (root == null) return res;

        Queue<TreeNode> queue = new LinkedList<>();
        queue.offer(root);
        while (!queue.isEmpty()) {
            List<Integer> list = new ArrayList<>();
            depth++;
            //关键地方，queue的大小即为这一层需要多少个节点。
            int size = queue.size();
            //那么在轮询的时候即按照这个大小来轮询
            for (int i = 0; i < size; i++) {
                TreeNode cur = queue.poll();
                if (cur!=null){
                    list.add(cur.val);
                    if (cur.left != null) queue.offer(cur.left);
                    if (cur.right != null) queue.offer(cur.right);
                }
            }
            res.add(list);
        }

        return res;
    }

    public static List<List<Integer>> reverseLevel(TreeNode root){
        List<List<Integer>> res = new ArrayList<>();
        Deque<int[]> resStack = new LinkedList<>();

        if (root == null) return res;
        Deque<Integer> stack = new LinkedList<>();
        Queue<TreeNode> q = new LinkedList<>();

        q.offer(root);

        while (!q.isEmpty()){
            int size = q.size();
            int subResSize = 0;
            for (int i = 0; i < size; i++) {
                TreeNode n = q.poll();
                stack.push(n.val);
                subResSize++;

                if (n.right!=null) q.offer(n.right);
                if (n.left!=null) q.offer(n.left);
            }

            resStack.push(new int[subResSize]);
        }

        while (!resStack.isEmpty()){
            int[] array = resStack.pop();
            for (int i = 0; i < array.length; i++) {
                array[i] = stack.pop();
            }

            res.add(Arrays.stream(array).boxed().collect(Collectors.toList()));
        }

        return res;
    }

    @Test
    public void testTraverse(){
        TreeNode root = TreeCreator.createTreeByLevel(new Integer[]{1,2,3,4,5,6,7});
//        System.out.println("Preorder");
//        TreeTraversal.preorder(find).forEach(i-> System.out.printf("%s,",i));
//        System.out.println();
//        TreeTraversal.preorderNonRecursive(find).forEach(i-> System.out.printf("%s,",i));
//        System.out.println();
//
//        System.out.println("Inorder");
//        TreeTraversal.inorder(find).forEach(i-> System.out.printf("%s,",i));
//        System.out.println();
//        TreeTraversal.inorderNonRecursive(find).forEach(i-> System.out.printf("%s,",i));
//        System.out.println();

        System.out.println("Postorder");
        TreeTraversal.postorder(root).forEach(i-> System.out.printf("%s,",i));
        System.out.println();
        TreeTraversal.postorderNonRecursive(root).forEach(i-> System.out.printf("%s,",i));
        System.out.println();
        TreeTraversal.postorderNonRecursive2(root).forEach(i-> System.out.printf("%s,",i));
        System.out.println();

//        List<List<Integer>> levelNodes = TreeTraversal.level(find);
//
//        for(int i = 0; i<levelNodes.size();i++){
//            System.out.println("Level " + i);
//            for (int v : levelNodes.get(i)){
//                System.out.print(v + " ");
//            }
//            System.out.println();
//        }
//
//        System.out.println();
//
//        List<List<Integer>> reverseLevelNodes = TreeTraversal.reverseLevel(find);
//        for(int i = 0; i< reverseLevelNodes.size();i++){
//            for (int v : reverseLevelNodes.get(i)){
//                System.out.print(v + " ");
//            }
//            System.out.println();
//        }
    }

    @Test
    public void test2() {
        TreeNode root = TreeCreator.createTreeByLevel(new Integer[]{1, 2, 2, 3, 4, 5, 6});
        System.out.println(level(root));
    }
}
