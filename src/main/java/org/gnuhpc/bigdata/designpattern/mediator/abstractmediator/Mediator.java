package org.gnuhpc.bigdata.designpattern.mediator.abstractmediator;

import org.gnuhpc.bigdata.designpattern.mediator.abstractcolleague.Colleague;

public interface Mediator {
    public void saleOffer(String stock, int shares, int collCode);
    public void buyOffer(String stock, int shares, int collCode);
    public void addColleague(Colleague colleague);
    public void getStockOfferings();
}
