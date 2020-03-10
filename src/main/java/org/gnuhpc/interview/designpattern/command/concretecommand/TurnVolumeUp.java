package org.gnuhpc.interview.designpattern.command.concretecommand;

import lombok.AllArgsConstructor;
import org.gnuhpc.interview.designpattern.command.receiver.ElectronicDevice;
import org.gnuhpc.interview.designpattern.command.command.Command;

@AllArgsConstructor
public class TurnVolumeUp implements Command {
    ElectronicDevice theDevice;

    public void execute() {
        theDevice.volumeUp();
    }

    public void undo() {
        theDevice.volumeDown();
    }

}