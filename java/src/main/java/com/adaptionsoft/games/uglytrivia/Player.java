package com.adaptionsoft.games.uglytrivia;

import java.util.Random;
import java.util.random.RandomGenerator;

public class Player {
    private final String name;
    private int gold = 0;
    private int location = 0; //TODO init to 1 ?
    private boolean isInPenaltyBox = false;

    private static RandomGenerator randomGenerator = new Random();

    public Player(String name) {
        if (name.isBlank()){
            throw new IllegalArgumentException("Name shouldn't be blank");
        }
        this.name = name;
    }

    public int rollDice(){
        return randomGenerator.nextInt(1, 7);
    }

    public void addGold(){
        this.gold++;
    }

    public int getGold() {
        return gold;
    }

    public boolean move(int caseNumber){
        if (this.isInPenaltyBox){
            return false;
        }
        this.location = (this.location + caseNumber) % 12;
        return true;
    }

    public boolean tryToEscape(){
        int diceResult = this.rollDice();

        if(!isInPenaltyBox || diceResult % 2 != 0){
            return this.move(diceResult);
        }

        return false;
    }

    public int getLocation() {
        return this.location;
    }

    public void goToPenaltyBox() {
        isInPenaltyBox = true;
    }
}
