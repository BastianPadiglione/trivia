package com.adaptionsoft.games.trivia;

import com.adaptionsoft.games.uglytrivia.Player;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

class PlayerTest {
    @Test
    void when_name_is_blank_return_exception(){
        Exception exception = assertThrows(IllegalArgumentException.class, () -> new Player(""));
        assertEquals("Name shouldn't be blank", exception.getMessage());
    }

    @Test
    void when_add_gold_to_new_player_it_has_1_gold(){
        Player player = new Player("Toto");
        player.addGold();
        Assertions.assertEquals(1, player.getGold());
    }

    @Test
    void when_add_gold_to_player_that_has_1_gold_it_has_2_gold(){
        Player player = new Player("Toto");
        player.addGold();
        player.addGold();
        Assertions.assertEquals(2, player.getGold());
    }


    @ParameterizedTest
    @CsvSource(value = {"1:0:1", "3:7:10", "1:11:0", "4:10:2"}, delimiter = ':')
    void when_move_X_step_from_case_Y_go_to_case_Z(int x, int y, int z){
        Player player = new Player("Toto");
        player.move(y);
        boolean isMoved = player.move(x);
        Assertions.assertEquals(z, player.getLocation());
        Assertions.assertTrue(isMoved);
    }

    @Test
    void when_move_but_in_penalty_box_return_false_and_not_move(){
        Player player = new Player("Toto");
        player.goToPenaltyBox();
        Assertions.assertFalse(player.move(1));
    }
}
