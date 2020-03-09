package org.gnuhpc.bigdata.designpattern.adapter;

import org.gnuhpc.bigdata.designpattern.adapter.adaptee.EmployeeCSV;
import org.gnuhpc.bigdata.designpattern.adapter.adaptee.EmployeeLdap;
import org.gnuhpc.bigdata.designpattern.adapter.adapter.EmployeeAdapterCSV;
import org.gnuhpc.bigdata.designpattern.adapter.adapter.EmployeeAdapterLdap;
import org.gnuhpc.bigdata.designpattern.adapter.newproduct.EmployeeDB;
import org.gnuhpc.bigdata.designpattern.adapter.targetbean.Employee;

import java.util.ArrayList;
import java.util.List;

public class MainApp{

	public static void main(String[] args) {
		List<Employee> employees = new ArrayList<>();

		Employee employeeFromDB = new EmployeeDB("1234", "John", "Wick", "john@wick.com");

		employees.add(employeeFromDB);

		//EmployeeLdap 没有实现Employee这个接口，由于是过去的代码，也不要再改变，这个时候适配器came into play
		//适配器都是这个新接口的实现类，其中都有一个它要适配的类的实例作为成员变量
		// 由于在接口层面进行了统一，因此大家能一起进行操作，比如放到同一个列表中
		//Will not work! This is where the adapter comes into play!
		//Employee employeeFromLdap = new EmployeeLdap("chewie", "Solo", "Han", "han@solo.com");

		EmployeeLdap employeeFromLdap = new EmployeeLdap("chewie", "Solo", "Han", "han@solo.com");
		employees.add(new EmployeeAdapterLdap(employeeFromLdap));

		EmployeeCSV employeeFromCSV = new EmployeeCSV("567,Sherlock,Holmes,sherlock@holmes.com");
		employees.add(new EmployeeAdapterCSV(employeeFromCSV));

		for (Employee employee : employees) {
			System.out.println(employee);
		}
	}
}
