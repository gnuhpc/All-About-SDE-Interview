package org.gnuhpc.bigdata.designpattern.state.concretestate;

import org.gnuhpc.bigdata.designpattern.state.context.ATMMachine;
import org.gnuhpc.bigdata.designpattern.state.state.ATMState;

public class HasPin implements ATMState {

    ATMMachine atmMachine;

    public HasPin(ATMMachine newATMMachine) {

        atmMachine = newATMMachine;

    }

    public void insertCard() {

        System.out.println("You already entered a card");

    }

    public void ejectCard() {

        System.out.println("Your card is ejected");
        atmMachine.setAtmState(atmMachine.getNoCard());

    }

    public void requestCash(int cashToWithdraw) {

        if (cashToWithdraw > atmMachine.getCashInMachine()) {
            System.out.println("You don't have that much cash available");
            System.out.println("Your card is ejected");
            atmMachine.setAtmState(atmMachine.getNoCard());

        } else {

            System.out.println(cashToWithdraw + " is provided by the machine");
            atmMachine.setCashInMachine(atmMachine.getCashInMachine()- cashToWithdraw);

            System.out.println("Your card is ejected");
            atmMachine.setAtmState(atmMachine.getNoCard());

            if (atmMachine.getCashInMachine() <= 0) {
                atmMachine.setAtmState(atmMachine.getAtmOutOfMoney());
            }
        }
    }

    public void insertPin(int pinEntered) {

        System.out.println("You already entered a PIN");

    }
}