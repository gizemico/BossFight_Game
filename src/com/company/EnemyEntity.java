package com.company;

public class EnemyEntity extends Thread{

    private int entityID;
    private int healthPoints;
    private int baseDamage;

    public EnemyEntity(){
        setHealthPoints(100);
        setBaseDamage(10);
    }

    //creating getters
    public int getEntityID() {
        return entityID;
    }

    public int getHealthPoints() {
        return healthPoints;
    }

    public int getBaseDamage() {
        return baseDamage;
    }

    //creating setters
    public void setEntityID(int entityID) {
        this.entityID = entityID;
    }

    public void setHealthPoints(int healthPoints) {
        this.healthPoints = healthPoints;
    }

    public void setBaseDamage(int baseDamage) {
        this.baseDamage = baseDamage;
    }

    public int dealDamage() {
        return getBaseDamage();
    }

    public void takeDamage(int damages) {
        setHealthPoints(getHealthPoints() - damages);
    }

    /***
     * EnemyEntity will try to perform an attack on the tank every 1 second. However,
     * every 4th attack will be a group wide attack.
     */
    @Override
    public void run() {

        EncounterManager EM = EncounterManager.getInstance();

        while ((EM.enemyIsAlive() != 0) && (EM.playersAreAlive() != 0)) {

            try {

                int i = 0;
                while (i < 5){

                    if (i == 4){
                        EM.groupWideAttack();
                        i = -1;
                    }

                    else{
                        EM.enemyAttack();
                        Thread.sleep(1000);
                    }
                    i++;
                }

                EM.table();

            } catch (InterruptedException interruptedException) {
                interruptedException.printStackTrace();
            }

        }

    }

}
