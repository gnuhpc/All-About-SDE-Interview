package org.gnuhpc.bigdata.lang.generics.generic_methods;

import java.util.Comparator;

public class AgeComparator implements Comparator<Person> {

	@Override
	public int compare(final Person left, final Person right) {
		return Integer.compare(left.getAge(), right.getAge());
	}

}
