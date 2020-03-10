package org.gnuhpc.interview.designpattern.state.context;

import lombok.Data;
import org.gnuhpc.interview.designpattern.state.state.ATMState;
import org.gnuhpc.interview.designpattern.state.concretestate.HasCard;
import org.gnuhpc.interview.designpattern.state.concretestate.HasPin;
import org.gnuhpc.interview.designpattern.state.concretestate.NoCard;
import org.gnuhpc.interview.designpattern.state.concretestate.NoCash;

@Data
public class ATMMachine implements ATMState {

    private ATMState hasCard;
    private ATMState noCard;
    private ATMState hasCorrectPin;
    private ATMState atmOutOfMoney;
    private ATMState atmState;

    private boolean correctPinEntered = false;

    private int cashInMachine = 2000;

    public ATMMachine() {
        hasCard = new HasCard(this);
        noCard = new NoCard(this);
        hasCorrectPin = new HasPin(this);
        atmOutOfMoney = new NoCash(this);

        atmState = noCard;

        if (getCashInMachine() < 0) {
            atmState = atmOutOfMoney;
        }
    }

    public void insertCard() {
        atmState.insertCard();
    }

    public void ejectCard() {
        atmState.ejectCard();
    }

    public void requestCash(int cashToWithdraw) {
        atmState.requestCash(cashToWithdraw);
    }

    public void insertPin(int pinEntered) {
        atmState.insertPin(pinEntered);
    }
}