package org.gnuhpc.interview.designpattern.factory.product;

import lombok.Data;

@Data
public abstract class EnemyShip {

    private String name;
    private double speed;
    private double directionX;
    private double directionY;
    private double damage;


    public void followHeroShip() {

        System.out.println(getName() + " is following the hero");

    }

    public void displayEnemyShip() {

        System.out.println(getName() + " is on the screen");

    }

    public void enemyShipShoots() {

        System.out.println(getName() + " attacks and does " + getDamage() + " damage to hero");

    }

}