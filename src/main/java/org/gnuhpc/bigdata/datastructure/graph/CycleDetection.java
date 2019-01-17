package org.gnuhpc.bigdata.datastructure.graph;

import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
class MyVertex {

	private String name;
	//注意添加的这两个访问的状态，也可以用一个变量enum类型进行区分
	private boolean visited; //最终状态
	private boolean beingVisited; // 中间状态
	private List<MyVertex> adjacenciesList;

	public MyVertex(String name) {
		this.name = name;
		this.adjacenciesList = new ArrayList<>();
	}

	public void addNeighbour(MyVertex vertex) {
		this.adjacenciesList.add(vertex);
	}

	@Override
	public String toString() {
		return this.name;
	}
}

public class CycleDetection {

	public void detectCycle(List<MyVertex> vertexList) {
		
		for(MyVertex vertex : vertexList) {
			
			if( !vertex.isVisited() ){
				dfs(vertex);
			}
		}
	}

	private void dfs(MyVertex vertex) {
		
		System.out.println("DFS on vertex " + vertex);
		vertex.setBeingVisited(true);
		
		for(MyVertex v : vertex.getAdjacenciesList()){

			//这部分不是标准的DFS
			System.out.println("Visiting the neighbours of vertex "+vertex);
			if( v.isBeingVisited() ) { //如果得到的是中间状态，说明由绕回来了，就是有环
				System.out.println("Backward edge ... so there is a cycle");
				return;
			}

			// 这部分是标准的DFS
			if( !v.isVisited() ){ //如果不是最终状态，则进入递归
				System.out.println("visiting vertex "+v+" recursively...");
				v.setVisited(true);
				dfs(v);
			}
		}
		
		System.out.println("Set vertex "+vertex+" setBeingVisited(false) and visited(true)");
		vertex.setBeingVisited(false);
		vertex.setVisited(true);
	}

	public static void main(String[] args) {

		MyVertex vertex1 = new MyVertex("1");
		MyVertex vertex2 = new MyVertex("2");
		MyVertex vertex3 = new MyVertex("3");
		MyVertex vertex4 = new MyVertex("4");
		MyVertex vertex5 = new MyVertex("5");
		MyVertex vertex6 = new MyVertex("6");

		vertex1.addNeighbour(vertex2);
		vertex2.addNeighbour(vertex3);
		vertex3.addNeighbour(vertex1);
		vertex4.addNeighbour(vertex1);
		vertex4.addNeighbour(vertex5);
		vertex5.addNeighbour(vertex6);
		vertex6.addNeighbour(vertex4);

		List<MyVertex> vertexList = new ArrayList<>();
		vertexList.add(vertex1);
		vertexList.add(vertex2);
		vertexList.add(vertex3);
		vertexList.add(vertex4);
		vertexList.add(vertex5);
		vertexList.add(vertex6);


		CycleDetection cycleDetection = new CycleDetection();
		cycleDetection.detectCycle(vertexList);

	}
}
