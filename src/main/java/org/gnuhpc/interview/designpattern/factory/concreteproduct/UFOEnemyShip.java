package org.gnuhpc.interview.designpattern.factory.concreteproduct;

import org.gnuhpc.interview.designpattern.factory.product.EnemyShip;

public class UFOEnemyShip extends EnemyShip {

    public UFOEnemyShip() {

        setName("UFO Enemy Ship");

        setDamage(20.0);

    }

}