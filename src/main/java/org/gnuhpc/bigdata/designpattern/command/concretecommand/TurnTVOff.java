package org.gnuhpc.bigdata.designpattern.command.concretecommand;

import lombok.AllArgsConstructor;
import org.gnuhpc.bigdata.designpattern.command.receiver.ElectronicDevice;
import org.gnuhpc.bigdata.designpattern.command.command.Command;

@AllArgsConstructor
public class TurnTVOff implements Command {

    //用这个成员变量进行回调
    ElectronicDevice theDevice;

    public void execute() {
        theDevice.off();
    }

    // Used if you want to allow for undo
    // Do the opposite of execute()
    public void undo() {
        theDevice.on();
    }

}