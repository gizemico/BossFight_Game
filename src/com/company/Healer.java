package com.company;

public class Healer extends Player implements PotencyCalculator {

    private int mind;

    /**
     * set the initial values for healer
     */
    public Healer(){

        setHealthPoints(100);
        setBaseDamage(10);
        setRole("Healer");
        setMind(8);
    }

    public Healer(int HealthPoints, int BaseDamage, String Role, int Mind){

        this.setHealthPoints(HealthPoints);
        this.setBaseDamage(BaseDamage);
        this.setRole(Role);
        this.setMind(Mind);
    }

    //creating getters
    public int getMind() {
        return mind;
    }

    //creating setters
    public void setMind(int mind) {
        this.mind = mind;
    }

    public int heal() {
        return mind + 10;
    }

    @Override
    public int dealDamage() {
        return getBaseDamage();
    }

    @Override
    public void takeDamage(int damages) {
        setHealthPoints(getHealthPoints() - damages);
    }

    @Override
    public void run() {

        EncounterManager EM = EncounterManager.getInstance();

        while ((EM.enemyIsAlive() != 0) && (EM.playersAreAlive() != 0)) {

            try {
                EM.healPlayer();
                System.out.println(this.getRole() + " is healed" + " (" + this.heal() + " HP)");
                Thread.sleep(1000);
                EM.table();

            } catch (InterruptedException interruptedException) {
                interruptedException.printStackTrace();
            }

        }

    }
}
