package student;


import java.util.List;
import java.util.Set;
import java.util.stream.Stream;

import static java.util.Arrays.stream;


public class Planner implements IPlanner {

    Set<BoardGame> games;

    public Planner(Set<BoardGame> games) {
        this.games = games;
    }

    @Override
    public Stream<BoardGame> filter(String filter) {
        //needs to implement multiple filters but think of case where filter only has one filter
        // return Stream<BoardGame>
        Stream<BoardGame>filteredStream = filterSingle(filter, games.stream());
        return filteredStream;
    }

    //helper method
    private Stream<BoardGame> filterSingle(String filter, Stream<BoardGame> filteredGames) {
        Operations operator = Operations.getOperatorFromStr(filter);
        if (operator == null) {
            return filteredGames;
        }
        // remove spaces
        filter = filter.replaceAll(" ", "");

        String[] parts = filter.split(operator.getOperator());
        if (parts.length != 2) {
            return filteredGames;
        }
        GameData column;
        try {
            column = GameData.fromString(parts[0]);
        } catch (IllegalArgumentException e) {
            return filteredGames;
        }

        String value;
        try{
            value = parts[1].trim();
        }catch(IllegalArgumentException e){
            return filteredGames;
        }
        System.out.print("Operator is:"+ operator);
        System.out.print("GameData is:"+ column);
        System.out.print("Value is:"+ value);
        //Filters.filter(boardGame, game Date, operator, String value)
        //Stream<BoardGame> filteredGames - you need to filter until you get "name == go" for example using a stream (don't have to use stream)
        List<BoardGame> filteredGameList = filteredGames.filter(game ->
                Filters.filter(game, column, operator, value)).toList(); //filters using filter created in filters class, that is why filter is returning a boolean value

        return filteredGameList.stream();
        // more work here to filter the games
        // we found creating a String filter and a Number filter to be useful.
        // both of the them take in both the GameData enum, Operator Enum, and the value to parse and filter on.

}

    @Override
    public Stream<BoardGame> filter(String filter, GameData sortOn) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'filter'");
    }

    @Override
    public Stream<BoardGame> filter(String filter, GameData sortOn, boolean ascending) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'filter'");
    }

    @Override
    public void reset() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'reset'");
    }


}
