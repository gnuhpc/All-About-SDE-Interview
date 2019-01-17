package org.gnuhpc.bigdata.designpattern.command.concretereceiver;

import org.gnuhpc.bigdata.designpattern.command.receiver.ElectronicDevice;

public class Radio implements ElectronicDevice {
    //保存的状态
    private int volume = 0;
    private boolean isOn = false;

    @Override
    public boolean isOn() {
        return isOn;
    }

    @Override
    public void setOn(boolean flag) {
        isOn = flag;
    }

    public void on() {
        setOn(true);
        System.out.println("Radio is on");
    }

    public void off() {
        setOn(false);
        System.out.println("Radio is off");
    }

    public void volumeUp() {
        volume++;
        System.out.println("Radio Volume is at: " + volume);
    }

    public void volumeDown() {
        volume--;
        System.out.println("Radio Volume is at: " + volume);
    }
}