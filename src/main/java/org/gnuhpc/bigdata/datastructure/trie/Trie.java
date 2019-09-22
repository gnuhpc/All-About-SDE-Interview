package org.gnuhpc.bigdata.datastructure.trie;

import lombok.Getter;

import java.util.ArrayList;
import java.util.List;

//还可以做hash table的一种实现，在每个TrieNode中保存一个value，在insert的时候创建node添加
@Getter
public class Trie {

	public TrieNode root;
	private int indexOfSingleChild;

	public Trie() {
		this.root = new TrieNode("");
	}

	public Trie(TrieNode root){
		this.root = root;
	}

	/**
	 * Inserts a word into the trie.
	 */
	public void insert(String word) {
		TrieNode node = root;

		for (int i = 0; i < word.length(); ++i) {
			int index = word.charAt(i) - 'a';

			if (node.children[index] == null) {
				node.children[index] = new TrieNode(String.valueOf(word.charAt(i)));
			}
            node = node.children[index];
		}

		node.isLeaf = true;
	}

	/**
	 * Returns if the word is in the trie.
	 */
	public boolean search(String word) {
		TrieNode p = searchNode(word);
		if (p != null && p.isLeaf) {
			return true;
		} else {
			return false;
		}
	}

	/**
	 * Returns if there is any word in the trie that starts with the given prefix.
	 */
	public boolean startsWith(String prefix) {
		if (searchNode(prefix) == null) {
			return false;
		} else {
			return true;
		}
	}

	private TrieNode searchNode(String s) {
		TrieNode p = root;
		for (int i = 0; i < s.length(); i++) {
			int index = s.charAt(i) - 'a';
			if (p.children[index] != null) {
				p = p.children[index];
			} else {
				return null;
			}
		}

		return p;
	}

	//Return the search result of the prefix
	public List<String> allWordsWithPrefix(String prefix) {
		
		List<String> res = new ArrayList<>();
		//首先找到前缀最深的那个TrieNode
		TrieNode node = searchNode(prefix);
		// 然后向下收集
		collect(node, prefix, res);
		return res;
	}
	
	public String longestCommonPrefix() {
		
		TrieNode node = root;
		StringBuilder lcp = new StringBuilder();
		
		while( countNumOfChildren(node) == 1 && !node.isLeaf) {
			node = node.children[indexOfSingleChild];
			lcp.append((char) (indexOfSingleChild + 'a'));
		}
		
		return lcp.toString();
	}

	private int countNumOfChildren(TrieNode node) {
		
		int numOfChildren = 0;
		
		for(int i = 0; i< node.children.length; ++i) {
			if( node.children[i] != null ) {
				numOfChildren++;
				indexOfSingleChild = i;
			}
		}
		
		return numOfChildren;
	}

	//recursive
	private void collect(TrieNode node, String prefix, List<String> res) {
		
		if( node == null ) return;
		
		if( node.isLeaf){
			res.add(prefix);
		}
		
		for(TrieNode n : node.children) {
			if( n == null ) continue;
			String childCharacter = n.character;
			collect(n, prefix+childCharacter, res);
		}
	}

	public static void main(String[] args) {
		Trie trie = new Trie();

		trie.insert("shacb");
		trie.insert("she");
		trie.insert("shab");
		trie.insert("shell");
		trie.insert("sheshore");

		List<String> list = trie.allWordsWithPrefix("she");

		for(String s : list)
			System.out.println(s);

		System.out.println("Sorted List:");

		List<String> sortedList = trie.allWordsWithPrefix("");

		for(String s : sortedList)
			System.out.println(s);

		System.out.println("Longest common prefix:");
		System.out.println(trie.longestCommonPrefix());

	}
}















