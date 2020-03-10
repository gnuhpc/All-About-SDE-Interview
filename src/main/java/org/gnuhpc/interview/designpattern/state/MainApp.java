package org.gnuhpc.interview.designpattern.state;

import org.gnuhpc.interview.designpattern.state.context.ATMMachine;

/*这个模式处理多状态之间的转换。可以用来封装多个if..else
首先创建一个状态接口，包含所有可能产生状态改变的动作。
然后创建实体类（context, 可以实现状态接口），里面含有所有状态的成员变量，并且有一个当前状态的成员变量，作为状态大集合和现有状态的记录
随后，实现所有具体状态类，实现状态接口。通过传入的context句柄进行状态转变。
初始化的时候初始化所有状态成员变量，传入this作为一个句柄。 每个动作都执行目前状态的该动作。 */
public class MainApp {

    public static void main(String[] args) {

        ATMMachine atmMachine = new ATMMachine();

        atmMachine.insertCard();

        atmMachine.ejectCard();

        atmMachine.insertCard();

        atmMachine.insertPin(1234);

        atmMachine.requestCash(2000);

        atmMachine.insertCard();

        atmMachine.insertPin(1234);

    }
}