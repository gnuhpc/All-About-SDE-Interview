package org.gnuhpc.bigdata.designpattern.abstractfactory;

import org.gnuhpc.bigdata.designpattern.abstractfactory.abstractfactory.CreditCardFactory;
import org.gnuhpc.bigdata.designpattern.abstractfactory.abstractproduct.CreditCard;
import org.gnuhpc.bigdata.designpattern.abstractfactory.type.CardType;

public class MainApp{

	public static void main(String[] args) {
		
		CreditCardFactory abstractFactory = CreditCardFactory.getCreditCardFactory(775);
		
		CreditCard card = abstractFactory.getCreditCard(CardType.PLATINUM);
		
		System.out.println(card.getClass());
		
		abstractFactory = CreditCardFactory.getCreditCardFactory(600);
		
		CreditCard card2 = abstractFactory.getCreditCard(CardType.GOLD);
		
		System.out.println(card2.getClass());
	}

}
