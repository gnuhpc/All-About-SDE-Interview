package org.gnuhpc.interview.designpattern.flyweight.facade;

import org.gnuhpc.interview.designpattern.flyweight.concreteflyweight.Item;
import org.gnuhpc.interview.designpattern.flyweight.flyweightfactory.Catalog;

import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

public class InventorySystem {

    private final Catalog catalog = new Catalog();
    private final List<Order> orders = new CopyOnWriteArrayList<>();

    public void takeOrder(String itemName, int orderNumber) {
        Item item = catalog.lookup(itemName);
        Order order = new Order(orderNumber, item);
        orders.add(order);
    }

    public void process() {
        for (Order order : orders) {
            order.processOrder();
            orders.remove(order);
        }
    }

    public String report() {
        return "\nTotal Item objects made: "
                + catalog.totalItemsMade();
    }

    //外部可变部分
    class Order {
        private final int orderNumber;
        //享元不可变部分
        private final Item item;

        public Order(int orderNumber, Item item) {
            this.orderNumber = orderNumber;
            this.item = item;
        }

        void processOrder() {
            System.out.println("Ordering " + item + " for order number " + orderNumber);
        }
    }
}