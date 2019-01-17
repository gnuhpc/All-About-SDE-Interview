package org.gnuhpc.bigdata.algorithm.search;

import java.util.HashMap;
import java.util.Map;

public class BoyerMooreSearch {

	private Map<Character, Integer> mismatchShiftsTable;
	private String text;
	private String pattern;

	public BoyerMooreSearch(){}

	public BoyerMooreSearch(String text, String pattern) {
		this.pattern = pattern;
		this.text = text;
		this.mismatchShiftsTable = new HashMap<>();
		precomputeShifts();
	}

	private void precomputeShifts() {

		int lengthOfPattern = this.pattern.length();

		for (int index = 0; index < lengthOfPattern; index++) {
			char actualCharacter = this.pattern.charAt(index);
			int maxShift = Math.max(1, lengthOfPattern - index - 1);
			this.mismatchShiftsTable.put(actualCharacter, maxShift);
		}
	}

	public int search() {

		int lengthOfPattern = this.pattern.length();
		int lengthOfText = this.text.length();
		int numOfSkips;

		for (int i = 0; i <= lengthOfText - lengthOfPattern; i += numOfSkips) {

			numOfSkips = 0;

			for (int j = lengthOfPattern - 1; j >= 0; j--) {
				if (pattern.charAt(j) != text.charAt(i + j)) {

					if ( this.mismatchShiftsTable.get(text.charAt(i+j)) != null ) {
						numOfSkips = this.mismatchShiftsTable.get(text.charAt(i+j));
						break;
					} else {
						numOfSkips = lengthOfPattern;
						break;
					}
				}
			}

			if (numOfSkips == 0) return i;
		}

		return -1;
	}

	public static void main(String[] args) {

		String text = "This is a test";
		String pattern = "test";

		BoyerMooreSearch boyerMoore = new BoyerMooreSearch(text, pattern);
		System.out.println( boyerMoore.search() );
	}
}
