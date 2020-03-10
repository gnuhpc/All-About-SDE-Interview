package org.gnuhpc.interview.datastructure.graph;

import org.junit.Test;

import java.util.ArrayList;
import java.util.List;


//有向图判断有环
/*
1. 定义顶点，注意增加一个beingVisited状态
2. 构造图
3. 判断有环： 对每个没有visited的顶点进行DFS
4. DFS具体实现：
	首先将此节点设置为正在访问
	然后对这个节点的邻接节点进行遍历
		如果已经是beingVisited说明有环
		如果不是visited，则进一步DFS深入
	最后别忘了对这一层的节点状态置为visited，并取消beingVisited状态
 */

/*
判断是否为DAG, 有Spark面试会已这个问题切入
 */
public class DirectedGraphCycleDetection {

    class MyVertex extends NLNode {
        public boolean beingVisited; // 中间状态

        public MyVertex(int data) {
            super(data);
        }
    }

    public void detectCycle(List<MyVertex> graph) {
        for (MyVertex vertex : graph) {
            if (!vertex.visited) {
                dfs(vertex);
            }
        }
    }

    private void dfs(MyVertex vertex) {
        System.out.println("DFS on vertex " + vertex);
        vertex.beingVisited = true;

        for (NLNode v : vertex.neighbours) {

            MyVertex node = (MyVertex) v;

            System.out.println("Visiting the neighbours of vertex " + node);
            if (node.beingVisited) { //如果得到的是中间状态，说明由绕回来了，就是有环
                System.out.println("Backward edge ... so there is a cycle");
                return;
            }

            // 这部分是标准的DFS
            if (!node.visited) { //如果不是最终状态，则进入递归
                System.out.println("visiting vertex " + v + " recursively...");
                dfs(node);
            }
        }

        System.out.println("Set vertex " + vertex + " setBeingVisited(false) and visited(true)");
        vertex.beingVisited = false;
        vertex.visited = true;
    }

    @Test
    public void test() {
        MyVertex vertex1 = new MyVertex(1);
        MyVertex vertex2 = new MyVertex(2);
        MyVertex vertex3 = new MyVertex(3);
        MyVertex vertex4 = new MyVertex(4);
        MyVertex vertex5 = new MyVertex(5);
        MyVertex vertex6 = new MyVertex(6);

        vertex1.addNeighbour(vertex2);
        vertex2.addNeighbour(vertex3);
        vertex1.addNeighbour(vertex3);
        vertex4.addNeighbour(vertex1);
        vertex4.addNeighbour(vertex5);
        vertex5.addNeighbour(vertex6); //Add this line to add a cycle
        vertex6.addNeighbour(vertex5);

        List<MyVertex> graph = new ArrayList<>();
        graph.add(vertex1);
        graph.add(vertex2);
        graph.add(vertex3);
        graph.add(vertex4);
        graph.add(vertex5);
        graph.add(vertex6);


        DirectedGraphCycleDetection cycleDetection = new DirectedGraphCycleDetection();
        cycleDetection.detectCycle(graph);

    }
}
