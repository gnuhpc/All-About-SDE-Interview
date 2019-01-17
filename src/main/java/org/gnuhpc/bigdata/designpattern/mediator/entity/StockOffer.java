package org.gnuhpc.bigdata.designpattern.mediator.entity;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class StockOffer {
    private int stockShares = 0;
    private String stockSymbol = "";
    private int colleagueCode = 0;
}
