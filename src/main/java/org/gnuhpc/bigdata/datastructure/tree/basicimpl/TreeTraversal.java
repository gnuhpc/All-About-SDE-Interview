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

    public static void preorderHelper(List<Integer> res, TreeNode root) {
        if (root == null) return;
        res.add(root.val);
        preorderHelper(res, root.left);
        preorderHelper(res, root.right);
    }

    public static List<Integer> preorderNonRecursive(TreeNode root) {
        List<Integer> result = new ArrayList<>();
        Deque<TreeNode> stack = new LinkedList<>();
        TreeNode p = root;
        while(!stack.isEmpty() || p != null) {
            if(p != null) {
                //先存着这个节点
                stack.push(p);
                result.add(p.val);  // Add before going to children
                p = p.left;
            } else {
                //p==null 意味着左子树已经遍历完毕了，该右子树了，因此pop
                p = stack.pop();
                p = p.right;
            }
        }
        return result;
    }

    /*
    Merhod 2： DFS模板方法
     */
    public static List<Integer> preorderNonRecursiveDFS(TreeNode root) {
        List<Integer> result = new ArrayList<>();

        Deque<TreeNode> stack = new LinkedList<>();
        stack.push(root);
        while(!stack.isEmpty()) {
            TreeNode n = stack.pop();
            if(n != null) {
                stack.push(n.right);
                stack.push(n.left);
                result.add(n.val);
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
    Create an empty stack s and Initialize current node as find
    Push the current node to s and set currentNode = currentNode.left until currentNode is NULL
    If currentNode is NULL and s is not empty then
    Pop the top node from stack and print it
     set currentNode = currentNode.right
    go to step 2
    If stack is empty and currentNode is also null then we are done with it
    */
    public static List<Integer> inorderNonRecursive(TreeNode root) {
        List<Integer> result = new ArrayList<>();
        Deque<TreeNode> stack = new LinkedList<>();
        TreeNode p = root;
        while(!stack.isEmpty() || p != null) {
            //左子树
            if(p != null) {
                stack.push(p);
                p = p.left;
            } else { // 为null时才pop，记忆方法：到叶子节点才进行值的访问
                p = stack.pop();
                result.add(p.val);  // Add after all left children , 这句话的位置注意和preorder进行对比
                p = p.right;
            }
        }
        return result;
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

    //后续遍历非递归，真实的另一种写法
    @AllArgsConstructor
    static class StackNode{
        TreeNode n;
        boolean isFirst;
    }

    public static List<Integer> postorderNonRecursive2(TreeNode root) {
        List<Integer> res = new ArrayList<>();
        Deque<StackNode> stack = new LinkedList<>();

        TreeNode cur = root;
        if (cur == null) return res;

        while (cur!=null || !stack.isEmpty()){
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
    public static List<Integer> postorderNonRecursive(TreeNode root) {
        LinkedList<Integer> result = new LinkedList<>();
        Deque<TreeNode> stack = new ArrayDeque<>();
        TreeNode p = root;
        while(!stack.isEmpty() || p != null) {
            //右子树
            if(p != null) {
                stack.push(p);
                //addFirst保证了结果集的倒序, DRL 访问，但是以LRD插入，返回则直接为LRD
                result.addFirst(p.val);  // Reverse the process of preorder
                p = p.right;             // Reverse the process of preorder
            } else {
                //左子树
                p = stack.pop().left; // Reverse the process of preorder
            }
        }
        return result;
    }

    public static List<List<Integer>> level(TreeNode root){
        List<List<Integer>> res = new ArrayList<>();
        if (root == null) return res;

        Queue<TreeNode> queue = new LinkedList<>();
        queue.offer(root);
        while (!queue.isEmpty()) {
            List<Integer> list = new ArrayList<>();
            //关键地方，queue的大小即为这一层需要多少个节点。
            int size = queue.size();
            //那么在轮询的时候即按照这个大小来轮询
            for (int i = 0; i < size; i++) {
                TreeNode cur = queue.poll();
                if (cur!=null){
                    list.add(cur.val);
                    queue.offer(cur.left);
                    queue.offer(cur.right);
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
}
