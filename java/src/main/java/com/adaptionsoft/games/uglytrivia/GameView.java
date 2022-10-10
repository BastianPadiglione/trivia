package com.adaptionsoft.games.uglytrivia;

public class GameView {

    public void printAddPlayer(String playerName, int playerNumber){
        System.out.println(playerName + " was added");
        System.out.println("They are player number " + playerNumber);
    }

    public void printPlayTurn(Player currentPlayer, int roll){
        System.out.println(currentPlayer + " is the current player");
        System.out.println("They have rolled a " + roll);
    }

    public void printOutOfPenaltyBox(Player currentPlayer){
        System.out.println(currentPlayer + " is getting out of the penalty box");
    }

    public void printNotOutOfPenaltyBox(Player currentPlayer){
        System.out.println(currentPlayer + " is not getting out of the penalty box");
    }

    public void printNewQuestion(Player currentPlayer, QuestionCategory currentCategory){
        System.out.println(currentPlayer
                + "'s new location is "
                + currentPlayer.getLocation());
        System.out.println("The category is " + currentCategory);
    }

    public void printRightAnswer(Player currentPlayer){
        System.out.println("Answer was correct!!!!");
        System.out.println(currentPlayer
                + " now has "
                + currentPlayer.getGold()
                + " Gold Coins.");
    }

    public void printWrongAnswer(Player currentPlayer){
        System.out.println("Question was incorrectly answered");
        System.out.println(currentPlayer + " was sent to the penalty box");
    }
}
