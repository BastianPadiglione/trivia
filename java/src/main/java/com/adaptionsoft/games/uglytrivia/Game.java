package com.adaptionsoft.games.uglytrivia;

import com.adaptionsoft.games.uglytrivia.exceptions.DuplicatePlayerException;
import com.adaptionsoft.games.uglytrivia.exceptions.GameBadInitializationException;

import java.util.*;
import java.util.random.RandomGenerator;

import static com.adaptionsoft.games.uglytrivia.QuestionCategory.*;

public class Game {
    private static final int QUESTION_NUMBER = 50;

    private static final RandomGenerator randomGenerator = new Random();

    private final List<Player> players = new ArrayList<>();

    private final Deque<String> popQuestions = new LinkedList<>();
    private final Deque<String> scienceQuestions = new LinkedList<>();
    private final Deque<String> sportsQuestions = new LinkedList<>();
    private final Deque<String> rockQuestions = new LinkedList<>();

    private Player currentPlayer;

    private final GameView gameView = new GameView();

    public Game(){
        for (int i = 0; i < QUESTION_NUMBER; i++) {
            popQuestions.addLast("Pop Question " + i);
            scienceQuestions.addLast("Science Question " + i);
            sportsQuestions.addLast("Sports Question " + i);
            rockQuestions.addLast("Rock Question " + i);
        }
    }


    public List<Player> getPlayers() {
        return players;
    }

    public boolean isPlayable() {
        return (playerNumber() >= 2);
    }

    public void addPlayer(String playerName) {
        Player player = new Player(playerName);
        if (players.contains(player)) {
            throw new DuplicatePlayerException("Two players can't have the same name : " + playerName);
        } else {
            players.add(new Player(playerName));
        }
        gameView.printAddPlayer(playerName, playerNumber());
    }

    public int playerNumber() {
        return players.size();
    }

    public void playTurn() {
        int roll = this.rollDice();
        gameView.printPlayTurn(currentPlayer,roll);

        if (this.currentPlayer.isInPenaltyBox()) {// in penalty box
            gameView.printOutOfPenaltyBox(this.currentPlayer);
            if (this.currentPlayer.tryToEscape(roll)) {
                this.currentPlayer.move(roll);
                this.introduceNewQuestion();
            } else {
                gameView.printNotOutOfPenaltyBox(this.currentPlayer);
            }
        } else {//not in penalty box
            this.currentPlayer.move(roll);
            this.introduceNewQuestion();
        }
    }

    public int rollDice(){
        return randomGenerator.nextInt(1, 7);
    }

    private void introduceNewQuestion() {
        gameView.printNewQuestion(this.currentPlayer, currentCategory());
        askQuestion();
    }

    private void askQuestion() {//TODO view ??
        switch (currentCategory()) {
            case POP -> System.out.println(popQuestions.removeFirst());
            case SCIENCE -> System.out.println(scienceQuestions.removeFirst());
            case SPORT -> System.out.println(sportsQuestions.removeFirst());
            case ROCK -> System.out.println(rockQuestions.removeFirst());
        }
    }

    private QuestionCategory currentCategory() {
        return switch (this.currentPlayer.getLocation() % 4) {
            case 0 -> POP;
            case 1 -> SCIENCE;
            case 2 -> SPORT;
            default -> ROCK;
        };
    }

    public void rightAnswer() {
        if (!currentPlayer.isInPenaltyBox()) {//not in penalty box
            this.currentPlayer.addGold();
            gameView.printRightAnswer(this.currentPlayer);
        }
    }

    private void moveToNextPlayer() {
        int playerIndex = players.indexOf(currentPlayer);
        playerIndex = (playerIndex + 1) % players.size();
        currentPlayer = players.get(playerIndex);
    }

    public void wrongAnswer() {
       gameView.printWrongAnswer(this.currentPlayer);
        this.currentPlayer.goToPenaltyBox();
    }


    private boolean didPlayerWin(Player player) {
        return player.getGold() == 6;
    }

    public void play() {
        if (!this.isPlayable()) {
            throw new GameBadInitializationException("Please add at least two players at the game");
        }
        this.currentPlayer = players.get(0);

        Player lastPlayer;
        do {
            this.playTurn();

            if (randomGenerator.nextInt(9) == 7) {
                this.wrongAnswer();
            } else {
                this.rightAnswer();
            }
            lastPlayer = this.currentPlayer;
            this.moveToNextPlayer();
        } while (!this.didPlayerWin(lastPlayer));
    }
}
