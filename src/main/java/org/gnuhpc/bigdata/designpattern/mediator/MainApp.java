package org.gnuhpc.bigdata.designpattern.mediator;

import org.gnuhpc.bigdata.designpattern.mediator.abstractcolleague.Colleague;
import org.gnuhpc.bigdata.designpattern.mediator.abstractmediator.Mediator;
import org.gnuhpc.bigdata.designpattern.mediator.colleague.GormanSlacks;
import org.gnuhpc.bigdata.designpattern.mediator.colleague.JTPoorman;
import org.gnuhpc.bigdata.designpattern.mediator.mediator.StockMediator;

/*
中介者模式有一个Mediator，里面包含要共同操作的东西。
在通信的双方都有一个Mediator类的成员变量。通过这个成员变量进行操作。
解耦两个通信

 */
public class MainApp {
    public static void main(String[] args){
        Mediator stockMediator = new StockMediator();
        Colleague broker = new GormanSlacks(stockMediator);
        Colleague broker2 = new JTPoorman(stockMediator);

        broker.saleOffer("MSFT", 100);
        broker.saleOffer("GOOG", 50);

        broker2.buyOffer("MSFT", 100);
        broker2.saleOffer("NRG", 10);

        broker.buyOffer("NRG", 10);

        stockMediator.getStockOfferings();
    }
}
