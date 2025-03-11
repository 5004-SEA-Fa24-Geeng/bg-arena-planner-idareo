package student;


import java.util.Comparator;
import java.util.List;
import java.util.Set;
import java.util.stream.Stream;

import static java.util.Arrays.stream;
import static student.GameData.NAME;


public class Planner implements IPlanner {

    Set<BoardGame> games;

    public Planner(Set<BoardGame> games) {
        this.games = games;
    }

    @Override
    public Stream<BoardGame> filter(String filter) {
        Stream<BoardGame> filteredStream = filterSingle(filter, games.stream());
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
//        System.out.print("Operator is:"+ operator);
//        System.out.print("GameData is:"+ column);
//        System.out.print("Value is:"+ value);
        //Filters.filter(boardGame, game Date, operator, String value)
        //Stream<BoardGame> filteredGames - you need to filter until you get "name == go" for example using a stream (don't have to use stream)
        List<BoardGame> filteredGameList = filteredGames.filter(game ->
                Filters.filter(game, column, operator, value)).toList(); //filters using filter created in filters class, that is why filter is returning a boolean value


        return filteredGameList.stream();
}

    @Override
    public Stream<BoardGame> filter(String filter, GameData sortOn) {

        if(filter == null || filter.isEmpty()) {
            return games.stream().sorted(getComparator(sortOn));
        }
        //split up input if multiple operations
        String[] multipleParts = filter.split(",");
        Stream<BoardGame> filteredGames = games.stream();

        //get filtered list of objects and column comparators
        for(String parts : multipleParts){
            filteredGames = filterSingle(parts.trim(), filteredGames);
            }
        Comparator<BoardGame> column = getComparator(sortOn);
        if(column != null){
            filteredGames = filteredGames.sorted(column);
        }

        return filteredGames;
        }


    @Override
    public Stream<BoardGame> filter(String filter, GameData sortOn, boolean ascending) {

        if(filter == null || filter.isEmpty()){
            Stream<BoardGame> filteredGames = games.stream().sorted(getComparator(sortOn));
            return ascending? filteredGames : filteredGames.sorted(getComparator(sortOn).reversed());
        }

        String[] multipleParts = filter.split(",");
        Stream<BoardGame> filteredGames = games.stream();

        for(String parts : multipleParts){
            filteredGames = filterSingle(parts.trim(), filteredGames);
        }
        Comparator<BoardGame> column = getComparator(sortOn);
        if(column != null){
            filteredGames = ascending? filteredGames : filteredGames.sorted(column);
        }
        return filteredGames;
    }

    private Comparator<BoardGame> getComparator(GameData sortOn) {
        if (sortOn == null) {
            return null;
        }
        GameData column;
        try{
            column = GameData.fromString(sortOn.toString());
        }catch(IllegalArgumentException e){
            throw new IllegalArgumentException("Invalid column");
        }
        switch (column) {
            case NAME:
                return new gameDataSorts.nameSort();
            case RATING:
                return new gameDataSorts.ratingSort();
            case DIFFICULTY:
                return new gameDataSorts.difficultySort();
            case RANK:
                return new gameDataSorts.rankSort();
            case MIN_PLAYERS:
                return new gameDataSorts.minPlayersSort();
            case MAX_PLAYERS:
                return new gameDataSorts.maxPlayersSort();
            case MIN_TIME:
                return new gameDataSorts.minPlayTimeSort();
            case MAX_TIME:
                return new gameDataSorts.maxPlayTimeSort();
            case YEAR:
                return new gameDataSorts.yearSort();
            default:
                return null;
        }
    }
    @Override
    public void reset() {
        games.stream();
    }

}
