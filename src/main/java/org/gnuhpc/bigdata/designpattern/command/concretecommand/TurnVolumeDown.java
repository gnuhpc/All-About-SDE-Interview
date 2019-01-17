package org.gnuhpc.bigdata.designpattern.command.concretecommand;

import lombok.AllArgsConstructor;
import org.gnuhpc.bigdata.designpattern.command.command.Command;
import org.gnuhpc.bigdata.designpattern.command.receiver.ElectronicDevice;

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