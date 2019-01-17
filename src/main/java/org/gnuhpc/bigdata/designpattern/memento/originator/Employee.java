package org.gnuhpc.bigdata.designpattern.memento.originator;

import lombok.Data;
import org.gnuhpc.bigdata.designpattern.memento.memento.EmployeeMemento;

//originator
@Data
public class Employee {
	private String name;
	private String address;
	private String phone;

	public EmployeeMemento save() {
		return new EmployeeMemento(name, phone);
	}
	
	public void revert(EmployeeMemento emp) {
		this.name = emp.getName();
		this.phone = emp.getPhone();
	}
}