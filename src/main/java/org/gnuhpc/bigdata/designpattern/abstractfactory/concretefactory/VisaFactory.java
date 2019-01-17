package org.gnuhpc.bigdata.designpattern.abstractfactory.concretefactory;

import org.gnuhpc.bigdata.designpattern.abstractfactory.*;
import org.gnuhpc.bigdata.designpattern.abstractfactory.abstractfactory.CreditCardFactory;
import org.gnuhpc.bigdata.designpattern.abstractfactory.abstractproduct.CreditCard;
import org.gnuhpc.bigdata.designpattern.abstractfactory.product.VisaBlackCreditCard;
import org.gnuhpc.bigdata.designpattern.abstractfactory.product.VisaGoldCreditCard;
import org.gnuhpc.bigdata.designpattern.abstractfactory.type.CardType;

public class VisaFactory extends CreditCardFactory {
	@Override
	public CreditCard getCreditCard(CardType cardType) {
		switch (cardType) {
			case GOLD:
				return new VisaGoldCreditCard();
	
			case PLATINUM:
				return new VisaBlackCreditCard();
		}
		
		return null;
	}
}
