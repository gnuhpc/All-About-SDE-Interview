package org.gnuhpc.bigdata.designpattern.adapter.adapter;

import org.gnuhpc.bigdata.designpattern.adapter.adaptee.EmployeeLdap;
import org.gnuhpc.bigdata.designpattern.adapter.targetbean.Employee;

public class EmployeeAdapterLdap implements Employee {

	private EmployeeLdap instance;
	
	public EmployeeAdapterLdap(EmployeeLdap instance) {
		this.instance = instance;
	}
	
	@Override
	public String getId() {
		return instance.getCn();
	}

	@Override
	public String getFirstName() {
		return instance.getGivenName();
	}

	@Override
	public String getLastName() {
		return instance.getSurname();
	}

	@Override
	public String getEmail() {
		return instance.getMail();
	}

	public String toString() {
		return "ID: " + instance.getCn();
	}
	
}
