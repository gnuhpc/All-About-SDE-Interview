package org.gnuhpc.interview.designpattern.abstractfactory.concretefactory;

import org.gnuhpc.interview.designpattern.abstractfactory.abstractfactory.CreditCardFactory;
import org.gnuhpc.interview.designpattern.abstractfactory.abstractproduct.CreditCard;
import org.gnuhpc.interview.designpattern.abstractfactory.product.AmexGoldCreditCard;
import org.gnuhpc.interview.designpattern.abstractfactory.product.AmexPlatinumCreditCard;
import org.gnuhpc.interview.designpattern.abstractfactory.type.CardType;

public class AmexFactory extends CreditCardFactory {
    @Override
    public CreditCard getCreditCard(CardType cardType) {
        switch (cardType) {
            case GOLD:
                return new AmexGoldCreditCard();

            case PLATINUM:
                return new AmexPlatinumCreditCard();

            default:
                break;
        }

        return null;
    }
}
