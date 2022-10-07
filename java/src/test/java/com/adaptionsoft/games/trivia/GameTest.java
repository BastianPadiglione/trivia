package com.adaptionsoft.games.trivia;


import com.adaptionsoft.games.uglytrivia.Game;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class GameTest {

    @Test
    public void givenStringShouldAddPlayer(){
        Game game = new Game();
        game.addPlayer("Toto");
        game.addPlayer("Tata");
        game.addPlayer("Titi");
        game.addPlayer("Tutu");
        assertEquals(game.getPlayers(), List.of("Toto", "Tata", "Titi", "Tutu"));
    }

}
