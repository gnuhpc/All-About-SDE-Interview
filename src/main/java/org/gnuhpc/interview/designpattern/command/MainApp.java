package org.gnuhpc.interview.designpattern.command;

import org.gnuhpc.interview.designpattern.command.command.Command;
import org.gnuhpc.interview.designpattern.command.concretecommand.*;
import org.gnuhpc.interview.designpattern.command.concretereceiver.Radio;
import org.gnuhpc.interview.designpattern.command.concretereceiver.Television;
import org.gnuhpc.interview.designpattern.command.invoker.DeviceButton;
import org.gnuhpc.interview.designpattern.command.receiver.ElectronicDevice;

import java.util.ArrayList;
import java.util.List;

/*
命令模式可以对请求进行很好的封装，针对面向对象的回调。做到发送者和处理者的解耦，并且可以用于设计undo功能。

JDK中的Runnable接口就是一个command pattern的例子

分为三大部分，一是定义命令。
二是定义发出命令的实体，三是定义接收命令的实体

第一部分：
首先，定义Command接口，主要是要实现的动作抽象。比如execute和undo方法
然后，实现一些具体的命令，每个命令都有一个第三部分的接口成员变量

第二部分：
定义一个类，传入Command接口实例，然后提供方法封装Command中有的方法。

第三部分：
定义一个接口，代表一种被命令执行实体接到命令的动作集合。
然后定义具体的被命令实体。

使用时，通过第一部分，封装第三部分，然后通过第二部分封装第一部分，最后对发出命令的实体进行调用。
TurnTVOn onCommand = new TurnTVOn(newDevice);
DeviceButton onPressed = new DeviceButton(onCommand);
onPressed.press();

 */
public class MainApp {

    public static void main(String[] args) {

        // Gets the ElectronicDevice to use

        //Init a receiver
        ElectronicDevice newDevice = new Television();

        // TurnTVOn contains the command to turn on the tv
        // When execute() is called on this command object
        // it will execute the method on() in Television

        //Init a command using a receiver
        TurnTVOn onCommand = new TurnTVOn(newDevice);

        // Calling the execute() causes on() to execute in Television

        //Init a invoker using the command
        DeviceButton onPressed = new DeviceButton(onCommand);

        // When press() is called theCommand.execute(); executes

        //Do the action
        onPressed.press();

        Command toggleCommand = new ToggleTV(newDevice);
        onPressed = new DeviceButton(toggleCommand);
        onPressed.press();
        onPressed.press();

        //----------------------------------------------------------
        // Now when execute() is called off() of Television executes
        TurnTVOff offCommand = new TurnTVOff(newDevice);

        // Calling the execute() causes off() to execute in Television
        onPressed = new DeviceButton(offCommand);

        // When press() is called theCommand.execute(); executes
        onPressed.press();

        //----------------------------------------------------------

        // Now when execute() is called volumeUp() of Television executes
        TurnVolumeUp volUpCommand = new TurnVolumeUp(newDevice);
        // Calling the execute() causes volumeUp() to execute in Television
        onPressed = new DeviceButton(volUpCommand);
        // When press() is called theCommand.execute(); executes
        onPressed.press();
        onPressed.press();
        onPressed.press();

        //----------------Macro------------------------------------------

        // Add the Electronic Devices to a List
        List<ElectronicDevice> allDevices = new ArrayList<>();
        // Creating a TV and Radio to turn off with 1 press
        Television theTV = new Television();
        Radio theRadio = new Radio();
        allDevices.add(theTV);
        allDevices.add(theRadio);

        // Send the List of Electronic Devices to TurnItAllOff
        // where a call to run execute() on this function will
        // call off() for each device in the list
        TurnItAllOff turnOffDevices = new TurnItAllOff(allDevices);
        // This calls for execute() to run which calls for off() to
        // run for every ElectronicDevice
        DeviceButton turnThemOff = new DeviceButton(turnOffDevices);
        turnThemOff.press();

        //----------------------------------------------------------

        /*
         * It is common to be able to undo a command in a command pattern
         * To do so, DeviceButton will have a method called undo
         * Undo() will perform the opposite action that the normal
         * Command performs. undo() needs to be added to every class
         * with an execute()
         */

        turnThemOff.pressUndo();

        // To undo more than one command add them to a LinkedList
        // using addFirst(). Then execute undo on each item until
        // there are none left. (This is your Homework)

    }

}