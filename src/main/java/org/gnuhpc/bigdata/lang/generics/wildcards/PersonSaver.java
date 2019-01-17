package org.gnuhpc.bigdata.lang.generics.wildcards;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.util.List;

public class PersonSaver {
	
	private final RandomAccessFile file;
	
	public PersonSaver(final File file) throws FileNotFoundException {
		this.file = new RandomAccessFile(file, "rw");
	}
	
	public void save(final Person person) throws IOException {
		file.writeUTF(person.getClass().getName());
		file.writeUTF(person.getName());
		file.writeInt(person.getAge());
	}

	//与下边的等价
//	public <T extends Person> void saveAll(final List<T> persons) throws IOException {
//		for(Person person : persons) {
//			save(person);
//		}
//	}

	// Same with upper commented generic method, syntactic sugar !
	public void saveAll(final List<? extends Person> persons) throws IOException {
	for(Person person : persons) {
		save(person);
	}
}

}
