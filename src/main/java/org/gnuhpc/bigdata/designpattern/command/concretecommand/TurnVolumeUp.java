package org.gnuhpc.bigdata.designpattern.command.concretecommand;

import lombok.AllArgsConstructor;
import org.gnuhpc.bigdata.designpattern.command.receiver.ElectronicDevice;
import org.gnuhpc.bigdata.designpattern.command.command.Command;

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