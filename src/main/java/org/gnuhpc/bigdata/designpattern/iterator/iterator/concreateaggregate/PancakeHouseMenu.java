package org.gnuhpc.bigdata.designpattern.iterator.iterator.concreateaggregate;

import org.gnuhpc.bigdata.designpattern.iterator.iterator.aggregate.Menu;
import org.gnuhpc.bigdata.designpattern.iterator.iterator.concreteiterator.PancakeHouseMenuIterator;
import org.gnuhpc.bigdata.designpattern.iterator.iterator.iterator.Iterator;

import java.util.ArrayList;

public class PancakeHouseMenu implements Menu {
	ArrayList<String> menuItems;
 
	public PancakeHouseMenu() {
		menuItems = new ArrayList<String>();
    
		addItem("K&B's Pancake Breakfast");
		addItem("Regular Pancake Breakfast");
		addItem("Blueberry Pancakes");
		addItem("Waffles");
	}

	public void addItem(String name)
	{
		menuItems.add(name);
	}
 
	public ArrayList<String> getMenuItems() {
		return menuItems;
	}
  
	public Iterator createIterator() {
		return new PancakeHouseMenuIterator(menuItems);
	}
  
	public String toString() {
		return "Pancake House Menu";
	}

	// other menu methods here
}
