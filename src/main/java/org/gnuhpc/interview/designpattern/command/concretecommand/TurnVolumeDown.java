package org.gnuhpc.interview.designpattern.command.concretecommand;

import lombok.AllArgsConstructor;
import org.gnuhpc.interview.designpattern.command.command.Command;
import org.gnuhpc.interview.designpattern.command.receiver.ElectronicDevice;

@AllArgsConstructor
public class TurnVolumeDown implements Command {
    ElectronicDevice theDevice;

    public void execute() {
        theDevice.volumeDown();
    }

    public void undo() {
        theDevice.volumeUp();
    }

}