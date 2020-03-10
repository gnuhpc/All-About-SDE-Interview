package org.gnuhpc.interview.designpattern.memento;

import org.gnuhpc.interview.designpattern.memento.caretaker.Caretaker;
import org.gnuhpc.interview.designpattern.memento.originator.Employee;

/*
这个模式用作记录一个类状态的时候需要

一个Memento类，明确需要记录的状态。

一个Originator为原始的entity类，并且添加实现设置状态功能：保存状态（设置状态生成Memento）和恢复状态（读取Memento设置状态）

最后有个Caretaker类进行Memento列表的管理。

 */
public class MainApp {

    public static void main(String args[]) {

        Caretaker caretaker = new Caretaker();

        Employee emp = new Employee();

        emp.setName("John Wick");
        emp.setAddress("111 org.gnuhpc.bigdata.concurrency.future.impl.Main Street");
        emp.setPhone("888-555-1212");

        System.out.println("Employee before save:                     " + emp);
        caretaker.save(emp);
        emp.setPhone("444-555-6666");
        caretaker.save(emp);
        System.out.println("Employee after changed phone number save: " + emp);
        emp.setPhone("333-999-6666"); // <--- we haven't called save!
        caretaker.revert(emp);
        System.out.println("Reverts to last save point:               " + emp);
        caretaker.revert(emp);
        System.out.println("Reverted to original:                     " + emp);

    }
}
