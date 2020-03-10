package org.gnuhpc.interview.designpattern.command.concretecommand;

import lombok.AllArgsConstructor;
import org.gnuhpc.interview.designpattern.command.command.Command;
import org.gnuhpc.interview.designpattern.command.receiver.ElectronicDevice;

@AllArgsConstructor
public class ToggleTV implements Command {
    private ElectronicDevice electronicDevice;

    @Override
    public void execute() {
        electronicDevice.toggleOnOff();
    }

    @Override
    public void undo() {
        electronicDevice.toggleOnOff();
    }
}
