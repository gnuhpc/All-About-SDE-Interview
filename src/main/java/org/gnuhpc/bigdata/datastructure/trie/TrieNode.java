package org.gnuhpc.bigdata.datastructure.trie;

public class TrieNode {
	private static final int ALPHABETSIZE = 26;

	//实现hash结构时采用
	public String character;

	//到达此节点的字符串数量
	public int path;

	//记录以此节点结尾的字符串数量
	public int end;

	public TrieNode[] children;
	public boolean isLeaf;

	public TrieNode(String character) {
		this.character = character;
		children = new TrieNode[ALPHABETSIZE];
	}

	public TrieNode() {
		this.character = "";
		children = new TrieNode[ALPHABETSIZE];
	}

	@Override
	public String toString() {
		return this.character;
	}

}
