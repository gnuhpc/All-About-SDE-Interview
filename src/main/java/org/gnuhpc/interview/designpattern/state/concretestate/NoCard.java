package org.gnuhpc.interview.designpattern.state.concretestate;

import org.gnuhpc.interview.designpattern.state.context.ATMMachine;
import org.gnuhpc.interview.designpattern.state.state.ATMState;

public class NoCard implements ATMState {

    ATMMachine atmMachine;

    public NoCard(ATMMachine newATMMachine) {

        atmMachine = newATMMachine;

    }

    public void insertCard() {

        System.out.println("Please enter your pin");
        atmMachine.setAtmState(atmMachine.getHasCard());

    }

    public void ejectCard() {

        System.out.println("You didn't enter a card");

    }

    public void requestCash(int cashToWithdraw) {

        System.out.println("You have not entered your card");

    }

    public void insertPin(int pinEntered) {

        System.out.println("You have not entered your card");

    }
}