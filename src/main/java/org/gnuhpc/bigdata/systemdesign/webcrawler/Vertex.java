package org.gnuhpc.bigdata.systemdesign.webcrawler;

import java.util.ArrayList;
import java.util.List;

public class Vertex {

	private String name;
	private boolean visited;
	private Vertex previousVertex;
	private List<Vertex> adjacenciesList;
	
	public Vertex(String name){
		this.name = name;
		this.adjacenciesList = new ArrayList<>();
	}
	
	public boolean isVisited() {
		return visited;
	}

	public void setVisited(boolean visited) {
		this.visited = visited;
	}

	public Vertex getPreviousVertex() {
		return previousVertex;
	}

	public void setPreviousVertex(Vertex previousVertex) {
		this.previousVertex = previousVertex;
	}

	public List<Vertex> getAdjacenciesList() {
		return adjacenciesList;
	}

	public void addNeighbour(Vertex vertex) {
		this.adjacenciesList.add(vertex);
	}

	@Override
	public String toString() {
		return this.name;
	}
}
