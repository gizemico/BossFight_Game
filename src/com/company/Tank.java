package com.company;

public class Tank extends Player implements PotencyCalculator {

    private int defense;

    public Tank() {

        setHealthPoints(100);
        setBaseDamage(10);
        setRole("Tank");
        setDefense(6);
        setEntityID(23);
    }

    public Tank(int HealthPoints, int BaseDamage, String Role, int Defense, int EntityID) {

        this.setHealthPoints(HealthPoints);
        this.setBaseDamage(BaseDamage);
        this.setRole(Role);
        this.setDefense(Defense);
        this.setEntityID(EntityID);

    }

    //creating getters
    public int getDefense() {
        return defense;
    }

    //creating setters
    public void setDefense(int defense) {
        this.defense = defense;
    }

    @Override
    public int dealDamage() {
        return getBaseDamage();
    }

    @Override
    public void takeDamage(int damages) {
        setHealthPoints(getHealthPoints() - (damages - defense));
    }

    @Override
    public void run() {

        EncounterManager EM = EncounterManager.getInstance();

        while ((EM.enemyIsAlive() != 0) && (EM.playersAreAlive() != 0)) {

            try {
                EM.playerAttack(this);
                System.out.println(this.getRole() + " attacked the enemy" + " (" + this.dealDamage() + " damage attack)");
                Thread.sleep(1000);
                EM.table();

            } catch (InterruptedException interruptedException) {
                interruptedException.printStackTrace();
            }

        }

    }
}
