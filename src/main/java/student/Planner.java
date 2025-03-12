package student;


import java.util.Comparator;
import java.util.List;
import java.util.Set;
import java.util.stream.Stream;


public class Planner implements IPlanner {

    Set<BoardGame> games;

    public Planner(Set<BoardGame> games) {
        this.games = games;
    }

    //sort
    @Override
    public Stream<BoardGame> filter(String filter) {
        Stream<BoardGame> filteredStream = filterSingle(filter, games.stream());
        filteredStream = filteredStream.sorted(getComparator(GameData.NAME));
        return filteredStream;
    }

    //helper method
    private Stream<BoardGame> filterSingle(String filter, Stream<BoardGame> filteredGames) {
        Operations operator = Operations.getOperatorFromStr(filter);
        if (operator == null) {
            return filteredGames;
        }
        // remove spaces
        filter = filter.replaceAll(" ", "").trim();

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
        try {
            value = parts[1].trim();
        } catch (IllegalArgumentException e) {
            return filteredGames;
        }

        //Filters.filter(boardGame, game Date, operator, String value)
        //Stream<BoardGame> filteredGames - you need to filter until you get "name == go" for example using a stream (don't have to use stream)
        //List<BoardGame> filteredGameList = filteredGames.filter(game ->
                //Filters.filter(game, column, operator, value)).toList(); //filters using filter created in filters class, that is why filter is returning a boolean value


        //return filteredGameList.stream();
        return filteredGames.filter(game ->
                Filters.filter(game, column, operator, value));
    }

    @Override
    public Stream<BoardGame> filter(String filter, GameData sortOn) {

        if (filter == null || filter.isEmpty()) {
            return games.stream().sorted(getComparator(sortOn));
        }

        if (!filter.contains(",")) {
            return filterSingle(filter, games.stream()).sorted(getComparator(sortOn));
        }
        //split up input if multiple operations
        String[] multipleParts = filter.split(",");
        Stream<BoardGame> filteredGames = games.stream();

        //Stream<BoardGame>filteredGames;

//        //get filtered list of objects and column comparators
//        for (String part : multipleParts) {
//            filteredGames = filterSingle(part.trim(), games.stream());
//        }
        int index = 0;
        while (index < multipleParts.length) {
            String part = multipleParts[index].trim();  // Get the current filter part
            filteredGames = filterSingle(part, filteredGames);  // Apply the filter
            index++;  // Move to the next part
        }

        Comparator<BoardGame> column = getComparator(sortOn);
        if (column != null) {
            filteredGames = filteredGames.sorted(column);
        }

        return filteredGames;
    }


    @Override
    public Stream<BoardGame> filter(String filter, GameData sortOn, boolean ascending) {

        if (filter == null || filter.isEmpty()) {
            Stream<BoardGame> filteredGames = games.stream().sorted(getComparator(sortOn));
            return ascending ? filteredGames : filteredGames.sorted(getComparator(sortOn).reversed());
        }

        String[] multipleParts = filter.split(",");
        Stream<BoardGame> filteredGames = games.stream();

        for (String part : multipleParts) {
            filteredGames = filterSingle(part.trim(), filteredGames);
        }

        Comparator<BoardGame> column = getComparator(sortOn);
        if (column != null) {
            filteredGames = ascending
                    ? filteredGames.sorted(column)
                    : filteredGames.sorted(column.reversed());
        }
        return filteredGames;
    }

    private Comparator<BoardGame> getComparator(GameData sortOn) {
        if (sortOn == null) {
            return null;
        }
        GameData column;
        try {
            column = GameData.fromString(sortOn.toString());
        } catch (IllegalArgumentException e) {
            throw new IllegalArgumentException("Invalid column");
        }
        switch (column) {
            case NAME:
                //return Comparator.comparing(BoardGame::getName);
                return new GameDataSorts.NameSort();
            case RATING:
                return new GameDataSorts.RatingSort();
            case DIFFICULTY:
                return new GameDataSorts.DifficultySort();
            case RANK:
                return new GameDataSorts.RankSort();
            case MIN_PLAYERS:
                return new GameDataSorts.MinPlayersSort();
            case MAX_PLAYERS:
                return new GameDataSorts.MaxPlayersSort();
            case MIN_TIME:
                return new GameDataSorts.MinPlayTimeSort();
            case MAX_TIME:
                return new GameDataSorts.MaxPlayTimeSort();
            case YEAR:
                return new GameDataSorts.YearSort();
            default:
                return null;
        }
    }

    @Override
    public void reset() {
        games.stream();
    }

}
