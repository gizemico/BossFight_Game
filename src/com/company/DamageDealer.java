package com.company;


public class DamageDealer extends Player implements PotencyCalculator {

    private int intelligence;

    public DamageDealer(){

        setHealthPoints(100);
        setBaseDamage(10);
        setRole("DamageDealer");
        setIntelligence(7);
    }

    public DamageDealer(int HealthPoints, int BaseDamage, String Role, int Intelligence){

        this.setHealthPoints(HealthPoints);
        this.setBaseDamage(BaseDamage);
        this.setRole(Role);
        this.setIntelligence(Intelligence);

    }

    //creating getters
    public int getIntelligence() {
        return intelligence;
    }

    //creating setters
    public void setIntelligence(int intelligence) {
        this.intelligence = intelligence;
    }

    @Override
    public int dealDamage() {
        int a;
        a = getBaseDamage() + getIntelligence();
        return a;
    }

    public void takeDamage(int damages) {
        setHealthPoints(getHealthPoints() - damages);
    }

    @Override
    public void run() {

        EncounterManager EM = EncounterManager.getInstance();

        while ((EM.enemyIsAlive() != 0) && (EM.playersAreAlive() != 0)) {

            try {
                EM.playerAttack(this);
                System.out.println(this.getRole() + " attacked the enemy" + " (" + this.dealDamage() + "damage attack)");
                Thread.sleep(500);
                EM.table();

            } catch (InterruptedException interruptedException) {
                interruptedException.printStackTrace();
            }

        }

    }
 }

