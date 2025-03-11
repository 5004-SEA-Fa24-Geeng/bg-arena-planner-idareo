import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import student.BoardGame;
import student.Filters;
import student.GameData;
import student.Operations;

import static org.junit.jupiter.api.Assertions.assertTrue;

public class testFilters {

    private BoardGame game1;
    private BoardGame game2;
    private BoardGame game3;

    @BeforeEach
    public void setUp(){
        game1 = new BoardGame("chess", 1, 3, 4, 60, 30, 2.5, 1, 4.5, 1995);
        game2 = new BoardGame("checkers", 3, 3, 4, 60, 30, 2.5, 1, 4.5, 1995);
        game3 = new BoardGame("Monopoly", 2, 6, 2, 120, 45, 3.0, 1, 4.0, 1935);
    }

    @Test
    public void testFilterEqualsName(){
        assertTrue(Filters.filter(game1, GameData.NAME, Operations.EQUALS, "cHeSS"));
    }

    @Test
    public void testFilterContainsName(){
        assertTrue(Filters.filter(game3, GameData.NAME, Operations.CONTAINS, "MON"));
    }
    @Test
    public void testFilterLessThanDifficulty(){
        assertTrue(Filters.filter(game2, GameData.DIFFICULTY, Operations.LESS_THAN, "2.8"));
    }

    @Test
    public void testFilterLessThanEqualsDifficulty(){
        assertTrue(Filters.filter(game2, GameData.DIFFICULTY, Operations.LESS_THAN_EQUALS, "2.5"));
    }

    @Test
    public void testFilterNotEqualsMinPlayers(){
        assertTrue(Filters.filter(game3, GameData.MIN_PLAYERS, Operations.NOT_EQUALS, "4"));
    }
}
