package org.gnuhpc.interview.designpattern.abstractfactory.abstractfactory;

import org.gnuhpc.interview.designpattern.abstractfactory.*;
import org.gnuhpc.interview.designpattern.abstractfactory.abstractproduct.CreditCard;
import org.gnuhpc.interview.designpattern.abstractfactory.concretefactory.AmexFactory;
import org.gnuhpc.interview.designpattern.abstractfactory.concretefactory.VisaFactory;
import org.gnuhpc.interview.designpattern.abstractfactory.type.CardType;

//AbstractFactory
public abstract class CreditCardFactory {

    public static CreditCardFactory getCreditCardFactory(int creditScore) {

        if (creditScore > 650) {
            return new AmexFactory();
        } else {
            return new VisaFactory();
        }
    }

    public abstract CreditCard getCreditCard(CardType cardType);

}
