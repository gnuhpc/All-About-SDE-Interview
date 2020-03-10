package org.gnuhpc.interview.designpattern.command.concretecommand;

import lombok.AllArgsConstructor;
import org.gnuhpc.interview.designpattern.command.receiver.ElectronicDevice;
import org.gnuhpc.interview.designpattern.command.command.Command;

@AllArgsConstructor
public class TurnTVOn implements Command {
    ElectronicDevice theDevice;

    public void execute() {
        theDevice.on();
    }

    public void undo() {
        theDevice.off();
    }

}