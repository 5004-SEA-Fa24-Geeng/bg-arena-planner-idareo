package student;


import java.util.Comparator;
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
            column = GameData.fromString(parts[0].toUpperCase());
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
//        List<BoardGame> filteredGameList = filteredGames.filter(game ->
//                Filters.filter(game, column, operator, value)).toList(); //filters using filter created in filters class, that is why filter is returning a boolean value

        return filteredGames.filter(game -> Filters.filter(game, column, operator, value));
}

    @Override
    public Stream<BoardGame> filter(String filter, GameData sortOn) {
        Operations operator = Operations.getOperatorFromStr(filter);
        if (operator == null) {
            return  games.stream();
        }

        filter = filter.replaceAll(" ","");
        String[] split = filter.split(operator.getOperator());

        if(split.length != 2){
            return games.stream();
        }
        GameData column;
        try{
            column = GameData.fromString(split[0]);
        }catch(IllegalArgumentException e){
            return games.stream();
        }

        String value;
        try{
            value = split[1].trim();
        }catch(IllegalArgumentException e){
            return games.stream();
        }

        Stream<BoardGame> filteredList = games.stream().filter(game -> Filters.filter(game, column, operator, value));

        Comparator<BoardGame> compare = getComparator(sortOn);
        if (compare != null) {
            filteredList = filteredList.sorted(compare);
        }
        return filteredList;
    }

    @Override
    public Stream<BoardGame> filter(String filter, GameData sortOn, boolean ascending) {
        Operations operator = Operations.getOperatorFromStr(filter);
        if (operator == null) {
            return  games.stream();
        }

        filter = filter.replaceAll(" ","");
        String[] split = filter.split(operator.getOperator());

        if(split.length != 2){
            return games.stream();
        }
        GameData column;
        try{
            column = GameData.fromString(split[0]);
        }catch(IllegalArgumentException e){
            return games.stream();
        }

        String value;
        try{
            value = split[1].trim();
        }catch(IllegalArgumentException e){
            return games.stream();
        }

        Stream<BoardGame> filteredList = games.stream().filter(game -> Filters.filter(game, column, operator, value));

        Comparator<BoardGame> compare = getComparator(sortOn);
        if (compare != null) {
            filteredList = ascending? filteredList.sorted(compare) : filteredList.sorted(compare.reversed());
        }
        return filteredList;
    }

    private Comparator<BoardGame> getComparator(GameData sortOn) {
        switch (sortOn) {
            case NAME:
                return new gameDataSorts.nameSort();
            case ID:
                return new gameDataSorts.idSort();
            case RATING:
                return new gameDataSorts.ratingSort();
            case DIFFICULTY:
                return new gameDataSorts.difficultySort();
            case RANK:
                return new gameDataSorts.rankSort();
            case MAX_PLAYERS:
                return new gameDataSorts.maxPlayersSort();
            case MIN_PLAYERS:
                return new gameDataSorts.minPlayersSort();
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
