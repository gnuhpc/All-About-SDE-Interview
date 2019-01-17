package org.gnuhpc.bigdata.designpattern.circularbuffer;

public class MainTest {

	public static void main(String[] args) {
		GenericCircularBuffer<String> buffer = new GenericCircularBuffer<>(10);
		System.out.println(buffer.poll());
		
		buffer.offer("a");
		buffer.offer("bc");
//		buffer.offer(1);		// Unresolved compilation problem
								// Run-time Error converted to
								// a Compile-Time Error
		buffer.offer("d");
		
		String value = concatenate(buffer);
		System.out.println(value);
	}

	private static String concatenate(GenericCircularBuffer<String> buffer) {
		StringBuilder result = new StringBuilder();
		
		String value;
		while((value = buffer.poll()) != null) {
			result.append(value);
		}
		
		return result.toString();
	}

}
