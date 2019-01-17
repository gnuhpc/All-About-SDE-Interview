package org.gnuhpc.bigdata.designpattern.iterator.iterator_builtin.concreteiterator;

import java.util.ArrayList;
import java.util.Iterator;

public class PancakeHouseMenuIterator implements Iterator {
	ArrayList<String> items;
	int position = 0;
 
	public PancakeHouseMenuIterator(ArrayList<String> items) {
		this.items = items;
	}
 
	public String next() {
		String menuItem = items.get(position++);
		return menuItem;
	}
 
	public boolean hasNext() {
		if (position >= items.size()) {
			return false;
		} else {
			return true;
		}
	}
}
