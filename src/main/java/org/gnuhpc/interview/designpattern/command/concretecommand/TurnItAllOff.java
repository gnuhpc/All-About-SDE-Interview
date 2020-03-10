package org.gnuhpc.interview.designpattern.command.concretecommand;

import lombok.AllArgsConstructor;
import org.gnuhpc.interview.designpattern.command.receiver.ElectronicDevice;
import org.gnuhpc.interview.designpattern.command.command.Command;

import java.util.List;

//Macro Command
@AllArgsConstructor
public class TurnItAllOff implements Command {
    List<ElectronicDevice> theDevices;

    public void execute() {
        theDevices.forEach(device -> device.off());
    }

    public void undo() {
        theDevices.forEach(device -> device.on());
    }
}