package org.gnuhpc.interview.designpattern.abstractfactory.concretefactory;

import org.gnuhpc.interview.designpattern.abstractfactory.*;
import org.gnuhpc.interview.designpattern.abstractfactory.abstractfactory.CreditCardFactory;
import org.gnuhpc.interview.designpattern.abstractfactory.abstractproduct.CreditCard;
import org.gnuhpc.interview.designpattern.abstractfactory.product.VisaBlackCreditCard;
import org.gnuhpc.interview.designpattern.abstractfactory.product.VisaGoldCreditCard;
import org.gnuhpc.interview.designpattern.abstractfactory.type.CardType;

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
