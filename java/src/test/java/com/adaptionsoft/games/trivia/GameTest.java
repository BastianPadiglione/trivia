package com.adaptionsoft.games.trivia;


import com.adaptionsoft.games.uglytrivia.Game;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
class GameTest {

    @Test
    void givenStringShouldAddPlayer(){
        Game game = new Game();
        game.addPlayer("Toto");
        game.addPlayer("Tata");
        game.addPlayer("Titi");
        game.addPlayer("Tutu");
        assertEquals(game.getPlayers(), List.of("Toto", "Tata", "Titi", "Tutu"));
    }

}
