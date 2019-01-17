package org.gnuhpc.bigdata.designpattern.strategy.context;


import lombok.Data;
import org.gnuhpc.bigdata.designpattern.strategy.strategy.Flys;

/*
*
* 要赋予某种能力的时候采用Strategy模式，设定一个接口为这个能力
* 进一步用几个类实现这个接口为这个能力给出具体的实现
* 然后实体类中包含这个接口的成员变量，方法上调用这个成员变量的动作，就能够赋予这个能力了。
* 传入什么能力是什么能力。
* 实体类可以有一个总的抽象类，其余的继承类在继承的时候通过设置这个接口的成员变量就能拥有不同的能力
* 能力或者行为都可以
*
* 这个模式的实质是把变化的东西通过组合和接口独立出来，把不变的东西通过继承得到能力
* */

@Data
public class Animal {

    private String name;
    private double height;
    private int weight;
    private String favFood;
    private double speed;
    private String sound;

    // Instead of using an interface in a traditional way
    // we use an instance variable that is a subclass
    // of the Flys interface.

    // Animal doesn't care what flyingType does, it just
    // knows the behavior is available to its subclasses

    // This is known as Composition : Instead of inheriting
    // an ability through inheritance the class is composed
    // with Objects with the right ability

    // Composition allows you to change the capabilities of
    // objects at run time!

    public Flys flyingType;

    public void setWeight(int newWeight) {
        if (newWeight > 0) {
            weight = newWeight;
        } else {
            System.out.println("Weight must be bigger than 0");
        }
    }


	/* BAD
    * You don't want to add methods to the super class.
	* You need to separate what is different between subclasses
	* and the super class
	public void fly(){

		System.out.println("I'm flying");

	}
	*/

    // Animal pushes off the responsibility for flying to flyingType

    public String tryToFly() {
        return flyingType.fly();
    }

    // If you want to be able to change the flyingType dynamically
    // add the following method

    public void setFlyingAbility(Flys newFlyType) {
        flyingType = newFlyType;
    }

}