
/* Gizem Seval - 2243624

    CNG443 Assignment-2
 */

package com.company;

import java.util.Scanner;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;


public class EncounterManager{

    static EncounterManager EM = new EncounterManager();
    /***
     * called encounter manager class
     * @param args
     */
    public static void main(String[] args) {

        EM.menu();


    }


    public static EncounterManager getInstance(){ return EM;}

    Scanner sc = new Scanner(System.in);

    Tank tank = new Tank();
    DamageDealer damage = new DamageDealer();
    Healer healer = new Healer();
    EnemyEntity enemy = new EnemyEntity();


    /**
     * control whether the enemy has dead or not.
     * @return integer value
     */

    synchronized public int enemyIsAlive(){

        if (enemy.getHealthPoints() > 0){
            return 1;
        }

        else {
            System.out.println("The enemy is dead.");
            return 0;
        }
    }

    /**
     * control whether the players have dead or not
     * @return integer value
     */
    synchronized public int playersAreAlive(){

        if (tank.getHealthPoints() > 0 && damage.getHealthPoints() > 0 && healer.getHealthPoints() > 0){
            return 1;
        }

        else{
            System.out.println("Players have dead.");
            return 0;
        }
    }

    /**
     * player damages to the enemy
     * @param p
     */
    synchronized public void playerAttack(Player p){

        if (enemyIsAlive() != 0)
            enemy.takeDamage(p.dealDamage());
        else {
            System.out.println("Enemy has dead. The encounter has ended.");
            System.exit(0);
        }

    }


    /***
     * check all players' health and heal them depends on their healths
     */
    synchronized public void healPlayer(){

        if ( (tank.getHealthPoints() < damage.getHealthPoints()) && (tank.getHealthPoints() < healer.getHealthPoints()) ){

            if ( (tank.getHealthPoints()<= 90) && (tank.getHealthPoints() > 0) )
                tank.setHealthPoints(tank.getHealthPoints()+healer.heal());
            else if (tank.getHealthPoints() > 90)
                tank.setHealthPoints(100);
            else if (tank.getHealthPoints() <= 0)
                tank.setHealthPoints(0);
        }

        else if ( (damage.getHealthPoints() < tank.getHealthPoints()) && (damage.getHealthPoints() < healer.getHealthPoints()) ){

            if ( (damage.getHealthPoints() <= 90) && (damage.getHealthPoints() > 0) )
                damage.setHealthPoints(damage.getHealthPoints()+healer.heal());
            else if (damage.getHealthPoints() > 90)
                damage.setHealthPoints(100);
            else if (damage.getHealthPoints() <= 0)
                damage.setHealthPoints(0);
        }

        else if ( (healer.getHealthPoints() < tank.getHealthPoints()) && (healer.getHealthPoints() < damage.getHealthPoints()) ){

            if ( (healer.getHealthPoints() <= 90) && (healer.getHealthPoints() > 0) )
                healer.setHealthPoints(healer.getHealthPoints()+healer.heal());
            else if (healer.getHealthPoints() > 90)
                healer.setHealthPoints(100);
            else if (healer.getHealthPoints() <= 0)
                healer.setHealthPoints(0);

        }
    }

    /**
     * enemy attacks player if their healths more than 0
     */
    synchronized public void enemyAttack(){

        if (tank.getHealthPoints() > 0){
            tank.takeDamage(enemy.dealDamage());
        }

        else if (damage.getHealthPoints() > 0){
            damage.takeDamage(enemy.dealDamage());
        }

        else if (healer.getHealthPoints() > 0){
            healer.takeDamage(enemy.dealDamage());
        }

    }

    /**
     * enemy attacks all players
     */
    synchronized public void groupWideAttack(){

        tank.takeDamage(enemy.dealDamage());
        damage.takeDamage(enemy.dealDamage());
        healer.takeDamage(enemy.dealDamage());

    }

    /***
     *  shows the health points of enemy and players
     */
    synchronized public void table(){

        System.out.println("======================\n");

        System.out.println("Entities' HP\n");
        System.out.println("Tank: " + EM.tank.getHealthPoints());
        System.out.println("Damage Dealer: " + EM.damage.getHealthPoints());
        System.out.println("Healer: " + EM.healer.getHealthPoints());
        System.out.println("Enemy: " + EM.enemy.getHealthPoints());

        System.out.println("======================\n");
    }

    public void menu() {

        System.out.println("The encounter has started!\n");

        tank.start();
        damage.start();
        healer.start();
        enemy.start();

        ExecutorService executor = Executors.newFixedThreadPool(2);
        executor.execute(tank);
        executor.execute(damage);
        executor.execute(healer);
        executor.execute(enemy);
        executor.shutdown();

    }

}


