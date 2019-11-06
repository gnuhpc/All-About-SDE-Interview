package org.gnuhpc.bigdata.leetcode;

import org.gnuhpc.bigdata.leetcode.utils.TreeNode;

public class Codec449 {
    public String serialize(TreeNode root) {
        if(root==null) return "";
        StringBuilder sb = new StringBuilder();
        serialize(root,sb);
        return sb.toString();
    }

    public void serialize(TreeNode root,StringBuilder sb){
        if(root ==null) return;
        sb.append(root.val).append("#");
        serialize(root.left,sb);
        serialize(root.right,sb);
    }

    // Decodes your encoded data to tree.
    TreeNode node = null;
    public TreeNode deserialize(String data) {
        if(data.equals("")){
            return null;
        }
        String[] nums = data.split("#");
        node = new TreeNode(Integer.parseInt(nums[0]));
        for(int i=1;i<nums.length;i++){
            int num = Integer.parseInt(nums[i]);
            insert(node,num);
        }
        return node;

    }
    public void insert(TreeNode node ,int num){
        if(node.val>=num){
            if(node.left==null){
                node.left = new TreeNode(num);
                return;
            }else
                insert(node.left,num);
        }else{
            if(node.right==null){
                node.right = new TreeNode(num);
                return;
            }else
                insert(node.right,num);
        }
    }
}
