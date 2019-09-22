DFS magic spell: 1]push to stack, 2] pop top , 3] retrieve unvisited neighbours of top, push them to stack 4] repeat 1,2,3 while stack not empty. Now form a rap !


https://medium.com/leetcode-patterns/leetcode-pattern-1-bfs-dfs-25-of-the-problems-part-1-519450a84353


当遇到一个可以用到回溯法的时候需要按照如下步骤进行：
1. 确定问题的一个解空间树， 这个解空间树至少包含一个你需要的那个解， 否则这个树就完全没有意义了
2. 组织好这棵树， 弄明白这棵树的每一个节点代表什么， 每一个分支代表什么
3. 从这棵树的根节点不断的向下深搜， 当遇到不合适的节点的时候直接跳过以这个节点为根的子树
4. 当搜索到了叶子节点的时候就回溯
5. 不断的重复这个3， 4步骤

DFS回溯问题框架：
void dfs(int 当前状态)
	{
	      if(当前状态为边界状态)
	      {
	        记录或输出
	        return;
	      }
	      for(i=0;i<n;i++)		//横向遍历解答树所有子节点
	     {
	           //扩展出一个子状态。
	           修改了全局变量
	           if(子状态满足约束条件)
	            {
	              dfs(子状态)
	           }
	            恢复全局变量//回溯部分
	        }
	}


