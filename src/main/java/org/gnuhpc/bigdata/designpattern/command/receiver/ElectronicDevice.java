package org.gnuhpc.bigdata.designpattern.command.receiver;

public interface ElectronicDevice {
    boolean isOn();
    void setOn(boolean flag);
    void on();
    void off();
    void volumeUp();
    void volumeDown();
    default void toggleOnOff(){
         if (isOn()){
            off();
        } else {
            on();
        }
    };
}