package org.gnuhpc.bigdata.designpattern.circularbuffer;

/*
http://www.mathcs.emory.edu/~cheung/Courses/171/Syllabus/8-List/array-queue2.html
 */
public class GenericCircularBuffer<T> {
	
	private final T[] buffer;
	private int readCursor = 0;
	private int writeCursor = 0;
	
	@SuppressWarnings("unchecked")
	public GenericCircularBuffer(int size) {
		buffer = (T[]) new Object[size];
	}
	
	public boolean offer(T value) {
		if(buffer[writeCursor] != null) {
			return false;
		}
		
		buffer[writeCursor] = value;
		writeCursor = next(writeCursor);
		
		return true;
	}
	
	public T poll() {
		T value = buffer[readCursor];
		
		if(value != null) {
			buffer[readCursor] = null;
			readCursor = next(readCursor);
		}
		
		return value;
	}

	public boolean isEmpty(){
		return writeCursor==readCursor;
	}
	
	private int next(int index) {
		return (index + 1) % buffer.length;
	}

}
