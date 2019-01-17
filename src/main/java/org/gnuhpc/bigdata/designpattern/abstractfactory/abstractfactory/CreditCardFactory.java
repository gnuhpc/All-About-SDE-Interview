package org.gnuhpc.bigdata.designpattern.abstractfactory.abstractfactory;

import org.gnuhpc.bigdata.designpattern.abstractfactory.*;
import org.gnuhpc.bigdata.designpattern.abstractfactory.abstractproduct.CreditCard;
import org.gnuhpc.bigdata.designpattern.abstractfactory.concretefactory.AmexFactory;
import org.gnuhpc.bigdata.designpattern.abstractfactory.concretefactory.VisaFactory;
import org.gnuhpc.bigdata.designpattern.abstractfactory.type.CardType;

//AbstractFactory
public abstract class CreditCardFactory {

	public static CreditCardFactory getCreditCardFactory(int creditScore) {
	
		if(creditScore > 650) {
			return new AmexFactory();
		}
		else {
			return new VisaFactory();
		}
	}
	
	public abstract CreditCard getCreditCard(CardType cardType);

}
