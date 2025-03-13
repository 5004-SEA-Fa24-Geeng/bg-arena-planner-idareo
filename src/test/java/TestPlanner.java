import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import student.BoardGame;

import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Stream;

import student.Planner;
import student.IPlanner;
import student.GameData;

import static org.junit.jupiter.api.Assertions.*;
import static student.GameData.MIN_PLAYERS;
import static student.GameData.NAME;


/**
 * JUnit test for the Planner class.
 * 
 * Just a sample test to get you started, also using
 * setup to help out. 
 */
public class TestPlanner {
    static Set<BoardGame> games;

    @BeforeEach
    public void setup() {
        games = new HashSet<>();
        games.add(new BoardGame("17 days", 6, 1, 8, 70, 70, 9.0, 600, 9.0, 2005));
        games.add(new BoardGame("Chess", 7, 2, 2, 10, 20, 10.0, 700, 10.0, 2006));
        games.add(new BoardGame("Go", 1, 2, 5, 30, 30, 8.0, 100, 7.5, 2000));
        games.add(new BoardGame("Go Fish", 2, 2, 10, 20, 120, 3.0, 200, 6.5, 2001));
        games.add(new BoardGame("golang", 4, 2, 7, 50, 55, 7.0, 400, 9.5, 2003));
        games.add(new BoardGame("GoRami", 3, 6, 6, 40, 42, 5.0, 300, 8.5, 2002));
        games.add(new BoardGame("Monopoly", 8, 6, 10, 20, 1000, 1.0, 800, 5.0, 2007));
        games.add(new BoardGame("Tucano", 5, 10, 20, 60, 90, 6.0, 500, 8.0, 2004));
    }

     @Test
    public void testFilterName() {
        IPlanner planner = new Planner(games);
        List<BoardGame> filtered = planner.filter("name == Go").toList();
        assertEquals(1, filtered.size());
        assertEquals("Go", filtered.get(0).getName());
    }

    @Test
    public void testFilterNameContains(){
        IPlanner  planner = new Planner(games);
        List<BoardGame> filtered = planner.filter("name ~= go f").toList();
        assertEquals(1, filtered.size());
    }

    @Test
    public void testFilterMinPlayers(){
        IPlanner planner = new Planner(games);
        List<BoardGame> filtered = planner.filter("minPlayers>6").toList();
        assertEquals("Tucano", filtered.get(0).getName());
    }

    @Test
    public void testFilterMaxPlayers(){
        IPlanner planner = new Planner(games);
        List<BoardGame> filtered = planner.filter("maxPlayers<10").toList();
        assertEquals(5, filtered.size());

    }
    @Test
    public void testFilterDifficulty(){
        IPlanner planner = new Planner(games);
        List<BoardGame> filtered = planner.filter("difficulty>6").toList();
        assertEquals(4, filtered.size());
    }

    @Test
    public void testMultipleFiltersSameColumn(){
        IPlanner planner = new Planner(games);
        List<BoardGame> filtered = planner.filter("minPlayers>2, minPlayers<10", NAME).toList();

        for (BoardGame game : filtered) {
            assertTrue(game.getMinPlayers() > 2);
            assertTrue(game.getMinPlayers() < 10);
        }
    }

    @Test
    public void testMultipleFiltersDiffColumn(){
        IPlanner planner = new Planner(games);
        List<BoardGame> filtered = planner.filter("minPlayers>2, maxPlayers<10").toList();

        for (BoardGame game : filtered) {
            assertTrue(game.getMinPlayers() > 2);
            assertTrue(game.getMaxPlayers() < 10);
        }
    }

    @Test
    public void testMultipleFiltersString(){
        IPlanner planner = new Planner(games);
        List<BoardGame> filtered = planner.filter("name~=Go, name==GoRami").toList();
        System.out.println(filtered);
        assertEquals(1, filtered.size());
    }

    @Test
    public void testMultipleFiltersStringNum(){
        IPlanner planner = new Planner(games);
        List<BoardGame> filtered = planner.filter("minPlayers>2, name~=go",NAME).toList();
        System.out.println(filtered);
        assertEquals(1, filtered.size());
    }

    @Test
    public void testMultipleFiltersStringBoolean() {
        IPlanner planner = new Planner(games);
        List<BoardGame> filtered = planner.filter("minPlayers==2, name~=go", NAME, false).toList();
        System.out.println(filtered);
        if (filtered.size() > 1) {
            for (int i = 0; i < filtered.size() - 1; i++) {
                assertTrue(filtered.get(i).getName().compareToIgnoreCase(filtered.get(i + 1).getName()) >= 0);
            }
        }
    }
}