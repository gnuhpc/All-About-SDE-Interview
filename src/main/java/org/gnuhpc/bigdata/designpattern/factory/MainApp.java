package org.gnuhpc.bigdata.designpattern.factory;

import org.gnuhpc.bigdata.designpattern.factory.factory.EnemyShipFactory;
import org.gnuhpc.bigdata.designpattern.factory.product.EnemyShip;
import org.gnuhpc.bigdata.designpattern.factory.type.ShipType;

import java.util.Scanner;

public class MainApp {

    public static void main(String[] args){

        // Create the factory object
        EnemyShipFactory shipFactory = new EnemyShipFactory();

        // Enemy ship object

        EnemyShip theEnemy = null;


        Scanner userInput = new Scanner(System.in);

        System.out.print("What type of ship? (U / R / B)");

        if (userInput.hasNextLine()){

            String typeOfShip = userInput.nextLine();
            ShipType st = null;
            if (typeOfShip.equals("U")){
                st = ShipType.U;
            } else if (typeOfShip.equals("B")){
                st = ShipType.B;
            }

            theEnemy = shipFactory.makeEnemyShip(st);

            if(theEnemy != null){

                doStuffEnemy(theEnemy);

            } else System.out.print("Please enter U, R, or B next time");

        }

		/*
		EnemyShip theEnemy = null;

		// Old way of creating objects
		// When we use new we are not being dynamic

		EnemyShip ufoShip = new UFOEnemyShip();

		doStuffEnemy(ufoShip);

		System.out.print("\n");

		// -----------------------------------------

		// This allows me to make the program more dynamic
		// It doesn't close the code from being modified
		// and that is bad!

		// Defines an input stream to watch: keyboard
		Scanner userInput = new Scanner(System.in);

		String enemyShipOption = "";

		System.out.print("What type of ship? (U or R)");

		if (userInput.hasNextLine()){

			enemyShipOption = userInput.nextLine();

		}

		if (enemyShipOption == "U"){

			theEnemy = new UFOEnemyShip();


		} else

		if (enemyShipOption == "R"){

			theEnemy = new RocketEnemyShip();

		} else {

			theEnemy = new BigUFOEnemyShip();

		}

		doStuffEnemy(theEnemy);

		// --------------------------------------------
		*/

    }

    // Executes methods of the super class

    public static void doStuffEnemy(EnemyShip anEnemyShip){

        anEnemyShip.displayEnemyShip();

        anEnemyShip.followHeroShip();

        anEnemyShip.enemyShipShoots();

    }

}