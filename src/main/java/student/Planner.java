package student;


import java.util.Comparator;
import java.util.List;
import java.util.Set;
import java.util.stream.Stream;


public class Planner implements IPlanner {
    /**
     * Set of BoardGame objects.
     */
    private Set<BoardGame> games;

    public Set<BoardGame> getGames() {
        return games;
    }
    /**
     *
     * @param games .
     */
    public Planner(Set<BoardGame> games) {
        this.games = games;
    }

    /**
     *
     * @param filter The filter to apply to the board games.
     * @return stream of games.
     */
    @Override
    public Stream<BoardGame> filter(String filter) {

        if (filter == null || filter.isEmpty()) {
            return getGames().stream().sorted(getComparator(GameData.NAME));
        }
        if (!filter.contains(",")) {
            return filterSingle(filter, getGames().stream()).sorted(getComparator(GameData.NAME));
        }
        //split up input if multiple operations
        String[] multipleParts = filter.split(",");
        Stream<BoardGame> filteredGames = getGames().stream();

        int index = 0;
        while (index < multipleParts.length) {
            String part = multipleParts[index].trim();  // Get the current filter part
            filteredGames = filterSingle(part, filteredGames);  // Apply the filter
            index++;  // Move to the next part
        }
        filteredGames = filteredGames.sorted(getComparator(GameData.NAME));

        return filteredGames;
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
        List<BoardGame> filteredGameList = filteredGames.filter(game ->
                Filters.filter(game, column, operator, value)).toList();

        return filteredGameList.stream();
    }

    @Override
    public Stream<BoardGame> filter(String filter, GameData sortOn) {

        if (filter == null || filter.isEmpty()) {
            return getGames().stream().sorted(getComparator(sortOn));
        }

        if (!filter.contains(",")) {
            return filterSingle(filter, getGames().stream()).sorted(getComparator(sortOn));
        }
        //split up input if multiple operations
        String[] multipleParts = filter.split(",");
        Stream<BoardGame> filteredGames = getGames().stream();

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
            Stream<BoardGame> filteredGames = getGames().stream().sorted(getComparator(sortOn));
            return ascending ? filteredGames : filteredGames.sorted(getComparator(sortOn).reversed());
        }

        String[] multipleParts = filter.split(",");
        Stream<BoardGame> filteredGames = getGames().stream();

        int index = 0;
        while (index < multipleParts.length) {
            String part = multipleParts[index].trim();  // Get the current filter part
            filteredGames = filterSingle(part, filteredGames);  // Apply the filter
            index++;  // Move to the next part
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
        getGames().stream();
    }

}
