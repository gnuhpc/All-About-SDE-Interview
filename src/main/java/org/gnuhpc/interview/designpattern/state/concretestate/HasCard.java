package org.gnuhpc.interview.designpattern.state.concretestate;

import lombok.AllArgsConstructor;
import org.gnuhpc.interview.designpattern.state.context.ATMMachine;
import org.gnuhpc.interview.designpattern.state.state.ATMState;

@AllArgsConstructor
public class HasCard implements ATMState {

    private ATMMachine atmMachine;

    public void insertCard() {
        System.out.println("You can only insert one card at a time");
    }

    public void ejectCard() {
        System.out.println("Your card is ejected");
        atmMachine.setAtmState(atmMachine.getNoCard());
    }

    public void requestCash(int cashToWithdraw) {
        System.out.println("You have not entered your PIN");
    }

    public void insertPin(int pinEntered) {
        if (pinEntered == 1234) {
            System.out.println("You entered the correct PIN");
            atmMachine.setCorrectPinEntered(true);
            atmMachine.setAtmState(atmMachine.getHasCorrectPin());

        } else {
            System.out.println("You entered the wrong PIN");
            atmMachine.setCorrectPinEntered(false);
            System.out.println("Your card is ejected");
            atmMachine.setAtmState(atmMachine.getNoCard());
        }
    }
}