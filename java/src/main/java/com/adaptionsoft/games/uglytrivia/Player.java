package com.adaptionsoft.games.uglytrivia;

public class Player {
    private final String name;
    private int gold = 0;
    private int location = 0;
    private boolean isInPenaltyBox = false;

    public Player(String name) {
        if (name.isBlank()){
            throw new IllegalArgumentException("Name shouldn't be blank");
        }
        this.name = name;
    }

    public void addGold(){
        this.gold++;
    }

    public int getGold() {
        return gold;
    }

    //TODO check caseNumber < 0
    public boolean move(int caseNumber){
        if (this.isInPenaltyBox){
            return false;
        }
        this.location = (this.location + caseNumber) % 12;
        return true;
    }

    //TODO check roll < 0
    public boolean tryToEscape(int roll){
        if(roll % 2 != 0){
            this.isInPenaltyBox = false;
            return true;
        }
        return false;
    }

    public int getLocation() {
        return this.location;
    }

    public void goToPenaltyBox() {
        isInPenaltyBox = true;
    }

    public boolean isInPenaltyBox() {
        return isInPenaltyBox;
    }

    @Override
    public String toString() {
        return name ;
    }
}
