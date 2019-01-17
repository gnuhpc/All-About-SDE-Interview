package org.gnuhpc.bigdata.lang.generics.generic_methods;

import org.gnuhpc.bigdata.lang.generics.wildcards.Person;

import java.io.*;

public class InterSection {
	
	
	public static void main(String[] args) throws FileNotFoundException {
		InterSection reader = new InterSection();
		
		DataInputStream stream = new DataInputStream(new FileInputStream("src/main/resources/person"));
		Person person = reader.read(stream);
		System.out.println(person);
		
		RandomAccessFile randomAccess = new RandomAccessFile("src/main/resources/person", "rw");
		person = reader.read(randomAccess);
		System.out.println(person);
	}
	
	public Person read(final DataInputStream source) {
		try(DataInputStream input = source) {
			return new Person(input.readUTF(), input.readInt());
		} catch (IOException e) {
			throw new RuntimeException(e);
		}
	}
	
	// T extends DataInput and Closeable
	public <T extends DataInput & Closeable> Person read(final T source) {
		try(T input = source) {
			return new Person(input.readUTF(), input.readInt());
		} catch(IOException e) {
			throw new RuntimeException(e);
		}
	}
}
