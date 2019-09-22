package org.gnuhpc.bigdata.leetcode;

import org.gnuhpc.bigdata.leetcode.utils.TreeNode;
import org.gnuhpc.bigdata.datastructure.tree.basicimpl.BinarySearchTree;
import org.junit.Test;

/*
In the in-order traversal for a given node 'n', we visit n.left, then 'n', then n.right.
Therefore, if we want to find the in-order successor of node 'n', we do the following:

1. First notice that because of the order in which we visit nodes,
if 'n' has a right child, then the successor must be on the right side of 'n.
Specifically, immediately after visit of node 'n',
the left-most child in the right sub-tree of node 'n' will be visited.

2. If node 'n' does not have right child then -
        a. If 'n' is a left child of its cor(cor.left == 'n'),
        then cor is the in-order successor of 'n';
        b. If 'n' is a right child of its cor(cor.right == 'n'),
        Travel down the tree, if a node’s data is greater than find’s data then go right side,
        otherwise go to left side.

*/

public class InOrderSuccessorBST {
    public TreeNode inorderSuccessor(TreeNode root, TreeNode node) {
        int val = node.val;
        TreeNode curr = root;
        while (curr.val!=val){
            if (curr.val > val) curr = curr.left;
            else if (curr.val < val) curr = curr.right;
            else break;
        }

        // 如果目标节点有右节点，则找到其右节点的最左边的节点，就是下一个数
        if(curr.right != null){
            curr = curr.right;
            while(curr.left != null){
                curr = curr.left;
            }
            return curr;
        } else {
            TreeNode succ = null;

            // Start from find and search for successor down the tree
            while (root != null)
            {
                if (val < root.val)
                {
                    succ = root;
                    root = root.left;
                }
                else if (val > root.val)
                    root = root.right;
                else
                    break;
            }

            return succ;
        }
    }

    @Test
    public void test(){
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


        System.out.println(inorderSuccessor(b.getRoot(),new TreeNode(6)).val);


    }
}
