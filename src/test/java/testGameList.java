import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.io.TempDir;
import student.BoardGame;
import student.GameList;
import student.IGameList;

import java.nio.file.Path;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;

public class testGameList  {
    static Set<BoardGame> games;
    IGameList list;

    @TempDir
    static Path tempDir = Path.of("temp");

    @BeforeEach
    public  void setup(){
        games = new HashSet<>();
        games.add(new BoardGame("chess", 1, 3, 4, 60, 30, 2.5, 1, 4.5, 1995));
        games.add(new BoardGame("checkers", 3, 3, 4, 60, 30, 2.5, 1, 4.5, 1995));
        games.add(new BoardGame("Monopoly", 2, 6, 2, 120, 45, 3.0, 1, 4.0, 1935));
        games.add(new BoardGame("Catan", 3, 4, 3, 90, 45, 3.8, 1, 4.8, 1995));
        games.add(new BoardGame("Carcassonne", 2, 5, 3, 35, 20, 2.7, 1, 4.7, 2000));
        games.add(new BoardGame("Pandemic", 2, 4, 2, 45, 45, 3.9, 1, 4.6, 2008));
        games.add(new BoardGame("Ticket to Ride", 2, 5, 4, 60, 30, 3.5, 1, 4.4, 2004));
        games.add(new BoardGame("Scrabble", 2, 4, 2, 60, 30, 3.0, 1, 4.3, 1938));
        games.add(new BoardGame("Dominion", 2, 4, 4, 45, 30, 3.7, 1, 4.5, 2008));
        games.add(new BoardGame("7 Wonders", 3, 7, 3, 40, 30, 4.0, 1, 4.7, 2010));
        games.add(new BoardGame("Risk", 2, 6, 5, 120, 60, 3.2, 1, 4.1, 1957));
        games.add(new BoardGame("Azul", 2, 4, 3, 35, 20, 3.5, 1, 4.6, 2017));
        games.add(new BoardGame("Twilight Struggle", 2, 2, 5, 120, 60, 4.2, 1, 4.8, 2005));
        games.add(new BoardGame("Battleship", 2, 2, 4, 60, 30, 2.9, 1, 4.2, 1967));
        games.add(new BoardGame("Clue", 3, 6, 4, 60, 30, 3.1, 1, 4.4, 1949));
        games.add(new BoardGame("Exploding Kittens", 2, 5, 4, 15, 5, 2.3, 1, 4.1, 2015));
        games.add(new BoardGame("Betrayal at House on the Hill", 3, 6, 4, 60, 30, 3.6, 1, 4.3, 2004));
        games.add(new BoardGame("Code Names", 4, 8, 3, 30, 15, 3.8, 1, 4.5, 2015));
        games.add(new BoardGame("Splendor", 2, 4, 4, 30, 20, 3.9, 1, 4.6, 2014));
        games.add(new BoardGame("The Crew", 2, 4, 4, 45, 30, 3.4, 1, 4.5, 2019));

       list = new GameList();

    }

    @Test
    public void testSingleGameByIndex(){
        list.addToList("1",games.stream());
        assertEquals(1, list.count());
        System.out.println(list.getGameNames());
    }
    @Test
    public void testClearList(){
        //list.addToList
        list.addToList("1-5",games.stream());
        list.clear();
        assertEquals(0, list.count());
    }

    @Test
    public void testCountList(){
        list.addToList("1-5",games.stream());
        assertEquals(5, list.count());
    }

    @Test
    public void testAddRangeOfGames(){
        IGameList list = new GameList();
        list.addToList("1-3",games.stream()); //think of how to use enums(operations/gamedata to filter)

        //check games are added
        List<String> gameNames = list.getGameNames();
        assertEquals(3, gameNames.size());
    }

    @Test
    public void testAddAllGames(){
        IGameList list = new GameList();
        list.addToList("all",games.stream());
        assertEquals(games.size(), list.count());
    }

   @Test
    public void testAddByString(){
        list.addToList("chess",games.stream());
        assertTrue(list.getGameNames().contains("chess"));
   }

    @Test
    public void testRemoveAllGames(){
        list.addToList("chess",games.stream());
        list.addToList("checkers",games.stream());
        list.addToList("monopoly",games.stream());
        assertEquals(3, list.count()); //check that games were added first

        list.removeFromList("all");
        assertEquals(0, list.count()); //asserts games removed.
    }

    @Test
    public void testRemoveGameByString(){
        list.addToList("1-5",games.stream());
        System.out.println(list.getGameNames());
        list.removeFromList("Risk");
        list.removeFromList("Catan");
        assertEquals(3, list.count());
    }

    @Test
    public void testRemoveGameByIndex(){
        list.addToList("1-5",games.stream());
        list.removeFromList("1-3");
        assertEquals(2, list.count());
    }
}
