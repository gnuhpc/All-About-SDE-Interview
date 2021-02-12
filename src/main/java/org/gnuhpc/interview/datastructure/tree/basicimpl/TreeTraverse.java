package org.gnuhpc.interview.datastructure.tree.basicimpl;

import org.junit.Test;

import java.util.*;
import java.util.stream.Collectors;

/*
stack for dfs, imagine a vertical flow
queue for bfs, horizontal flow

碰到二叉树的问题，就想想整棵树在该问题上的结果
和左右儿子在该问题上的结果之间的联系是什么

非递归模板的核心是在访问前放入一个null作为是否已经访问过的标识

 */
public class TreeTraverse {

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
    Merhod 2： Non recursive
     */
    /*
    DFS模板方法
    */
    public static List<Integer> preorderNonRecursive(TreeNode root) {
        if (root == null) return Collections.emptyList();
        List<Integer> res = new ArrayList<>();  //保存结果
        Deque<TreeNode> stack = new LinkedList<>();   //调用栈
        stack.push(root);    //先将根节点入栈
        while (!stack.isEmpty()) {
            TreeNode t = stack.pop();   //弹出节点并判断是否访问过
            //非空说明没访问过，然后右节点入栈，左节点入栈，最后根节点入栈，并入栈一个空节点
            if (t != null) {
                if (t.right != null) stack.push(t.right);
                if (t.left != null) stack.push(t.left);
                stack.push(t);
                stack.push(null);
                //如果弹出节点为空节点，表明当前栈顶节点已访问过
            } else {
                res.add(stack.pop().val);
            }
        }
        return res;
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
        Non-recursive
    */
    public static List<Integer> inorderNonRecursive(TreeNode root) {
        if (root == null) return Collections.emptyList();
        List<Integer> res = new ArrayList<>();  //保存结果
        Deque<TreeNode> stack = new LinkedList<>();   //调用栈
        stack.push(root);    //先将根节点入栈
        while (!stack.isEmpty()) {
            TreeNode t = stack.pop();   //弹出节点并判断是否访问过
            if (t != null) {
                if (t.right != null) stack.push(t.right);
                stack.push(t); //把t塞回去
                stack.push(null);
                if (t.left != null) stack.push(t.left);
                //表明当前节点以访问过
            } else {
                res.add(stack.pop().val);    //如果弹出节点为空节点，表明当前栈顶节点已访问过
            }
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

    //后续遍历非递归
    public static List<Integer> postorderNonRecursive(TreeNode root) {
        if (root == null) return Collections.emptyList();
        List<Integer> res = new ArrayList<>();  //保存结果
        Deque<TreeNode> stack = new LinkedList<>();   //调用栈
        stack.push(root);    //先将根节点入栈
        while (!stack.isEmpty()) {
            TreeNode t = stack.pop();   //弹出节点并判断是否访问过
            if (t != null) {
                stack.push(t);
                stack.push(null);
                if (t.right != null) stack.push(t.right);
                if (t.left != null) stack.push(t.left);
                //表明当前节点以访问过
            } else {
                res.add(stack.pop().val);    //如果弹出节点为空节点，表明当前栈顶节点已访问过
            }
        }
        return res;
    }

    public static List<List<Integer>> level(TreeNode root) {
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
                if (cur != null) {
                    list.add(cur.val);
                    if (cur.left != null) queue.offer(cur.left);
                    if (cur.right != null) queue.offer(cur.right);
                }
            }
            res.add(list);
        }

        return res;
    }

    public static List<List<Integer>> reverseLevel(TreeNode root) {
        List<List<Integer>> res = new ArrayList<>();
        Deque<int[]> resStack = new LinkedList<>();

        if (root == null) return res;
        Deque<Integer> stack = new LinkedList<>();
        Queue<TreeNode> q = new LinkedList<>();

        q.offer(root);

        while (!q.isEmpty()) {
            int size = q.size();
            int subResSize = 0;
            for (int i = 0; i < size; i++) {
                TreeNode n = q.poll();
                stack.push(n.val);
                subResSize++;

                if (n.right != null) q.offer(n.right);
                if (n.left != null) q.offer(n.left);
            }

            resStack.push(new int[subResSize]);
        }

        while (!resStack.isEmpty()) {
            int[] array = resStack.pop();
            for (int i = 0; i < array.length; i++) {
                array[i] = stack.pop();
            }

            res.add(Arrays.stream(array).boxed().collect(Collectors.toList()));
        }

        return res;
    }

    @Test
    public void testTraverse() {
        TreeNode root = TreeCreator.createTreeByLevel(new Integer[]{1, 2, 3, 4, 5, 6, 7});
        root.print();
        System.out.println("Preorder");
        TreeTraverse.preorder(root).forEach(i -> System.out.printf("%s,", i));
        System.out.println();
        TreeTraverse.preorderNonRecursive(root).forEach(i -> System.out.printf("%s,", i));
        System.out.println();

        System.out.println("Inorder");
        TreeTraverse.inorder(root).forEach(i -> System.out.printf("%s,", i));
        System.out.println();
        TreeTraverse.inorderNonRecursive(root).forEach(i -> System.out.printf("%s,", i));
        System.out.println();

        System.out.println("Postorder");
        TreeTraverse.postorder(root).forEach(i -> System.out.printf("%s,", i));
        System.out.println();
        TreeTraverse.postorderNonRecursive(root).forEach(i -> System.out.printf("%s,", i));
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
