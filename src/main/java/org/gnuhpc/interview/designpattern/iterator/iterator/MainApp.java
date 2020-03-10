package org.gnuhpc.interview.designpattern.iterator.iterator;

import org.gnuhpc.interview.designpattern.iterator.iterator.concreateaggregate.DinerMenu;
import org.gnuhpc.interview.designpattern.iterator.iterator.concreateaggregate.PancakeHouseMenu;
import org.gnuhpc.interview.designpattern.iterator.iterator.iterator.Iterator;

import java.util.*;

/*有一个Iterator接口，其中有一个方法，获取一个java.util.Iterator。
所有的实现类都实现这个接口，不管这个实现类内部使用什么集合类
对外遍历的接口都是java.util.Iterator。
对于要遍历这个实现类中的数据大家就有了统一的接口

然后有个聚合抽象类，有一个创建iterator的方法 (实际上就是一个简单工厂方法)
随后派生几个具体的聚合类，作为数据的承载方
*/
public class MainApp {

    public static void main(String args[]) {
        PancakeHouseMenu pancakeHouseMenu = new PancakeHouseMenu();
        DinerMenu dinerMenu = new DinerMenu();

        // with no iterators
        System.out.println("\nMENU\n----\nBREAKFAST");
        ArrayList<String> breakfastItems = pancakeHouseMenu.getMenuItems();
        for (int i = 0; i < breakfastItems.size(); i++) {
            String menuItem = breakfastItems.get(i);
            System.out.println(menuItem);
        }

        System.out.println("\nLUNCH");
        String[] lunchItems = dinerMenu.getMenuItems();

        for (int i = 0; i < lunchItems.length; i++) {
            String menuItem = lunchItems[i];
            System.out.println(menuItem);
        }

        // with iterators
        Iterator pancakeIterator = pancakeHouseMenu.createIterator();
        Iterator dinerIterator = dinerMenu.createIterator();

        System.out.println("\nMENU (with iterators)\n----\nBREAKFAST");
        printMenu(pancakeIterator);
        System.out.println("\nLUNCH");
        printMenu(dinerIterator);
    }

    private static void printMenu(Iterator iterator) {
        while (iterator.hasNext()) {
            String menuItem = (String) iterator.next();
            System.out.println(menuItem);

        }
    }
}
