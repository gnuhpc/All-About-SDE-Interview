package org.gnuhpc.bigdata.designpattern.factory.factory;

// This is a factory thats only job is creating ships
// By encapsulating ship creation, we only have one
// place to make modifications

import org.gnuhpc.bigdata.designpattern.factory.concreteproduct.BigUFOEnemyShip;
import org.gnuhpc.bigdata.designpattern.factory.product.EnemyShip;
import org.gnuhpc.bigdata.designpattern.factory.concreteproduct.UFOEnemyShip;
import org.gnuhpc.bigdata.designpattern.factory.type.ShipType;

/*
* 当有一系列类都实现某个接口或者父类，而你要动态指定用哪个类的时候，
* 有一个工厂类来封装很多的if操作来产生这个类（通过接口或父类的引用）
* 也可用反射来进行，直接传入某个类的名字（或其中的一部分）
* */
public class EnemyShipFactory{

    // This could be used as a static method if we
    // are willing to give up subclassing it

    public EnemyShip makeEnemyShip(ShipType newShipType){

        switch (newShipType){
            case B:
                return new BigUFOEnemyShip();
            case U:
                return new UFOEnemyShip();
            default:
                return null;
        }
    }

}