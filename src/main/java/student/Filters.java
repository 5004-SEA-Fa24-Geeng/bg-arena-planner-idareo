package student;

import static student.Operations.CONTAINS;
import static student.Operations.EQUALS;

public class Filters {
    private Filters() {
    }

    public static boolean filter(BoardGame game, GameData column, Operations op, String value) {
        switch (column) {
            case NAME:
                //filter the name(string)
                return filterString(game.getName(), op, value);
            case ID:
                return filterNum(game.getId(), op, value);
            case RATING:
                return filterNum(game.getRating(), op, value);
            case DIFFICULTY:
                return filterNum(game.getDifficulty(), op, value);
            case RANK:
                return filterNum(game.getRank(), op, value);
            case MAX_PLAYERS:
                //filter based on max-players(numerical)
                return filterNum(game.getMaxPlayers(), op, value);
            case MIN_PLAYERS:
                return filterNum(game.getMinPlayers(), op, value);
            case MIN_TIME:
                return filterNum(game.getMinPlayTime(), op, value);
            case MAX_TIME:
                return filterNum(game.getMaxPlayTime(), op, value);
            case YEAR:
                return filterNum(game.getYearPublished(), op, value);
            default:
                return false;
        }
    }

    //method to handle filtering for gameData
    public static boolean filterString(String gameData, Operations op, String value) {

        gameData = gameData.replaceAll(" ", "").toLowerCase();  // Remove spaces and convert to lowercase
        value = value.replaceAll(" ", "").toLowerCase();

        switch (op) {
            case CONTAINS:
                return gameData.toLowerCase().contains(value.toLowerCase());
            case EQUALS:
                return gameData.equalsIgnoreCase(value);
            case LESS_THAN:
                return gameData.compareToIgnoreCase(value) < 0;
            case LESS_THAN_EQUALS:
                return gameData.compareToIgnoreCase(value) <= 0;
            case GREATER_THAN:
                return gameData.compareToIgnoreCase(value) > 0;
            case GREATER_THAN_EQUALS:
                return gameData.compareToIgnoreCase(value) >= 0;
            case NOT_EQUALS:
                return !gameData.equalsIgnoreCase(value);
            default:
                return false;
        }
    }

    public static boolean filterNum(int gameData, Operations op, String value) {
        int val;
        try {
            val = Integer.parseInt(value);
        } catch (NumberFormatException e) {
            return false;
        }
        switch (op) {
            case EQUALS:
                return gameData == val;
            case LESS_THAN:
                return gameData < val;
            case LESS_THAN_EQUALS:
                return gameData <= val;
            case GREATER_THAN:
                return gameData > val;
            case GREATER_THAN_EQUALS:
                return gameData >= val;
            case NOT_EQUALS:
                return gameData != val;
            default:
                return false;
        }
    }

    public static boolean filterNum(double gameData, Operations op, String value) {
        double val;
        try {
            val = Double.parseDouble(value);
        } catch (NumberFormatException e) {
            return false;
        }
        switch (op) {
            case EQUALS:
                return gameData == val;
            case LESS_THAN:
                return gameData < val;
            case LESS_THAN_EQUALS:
                return gameData <= val;
            case GREATER_THAN:
                return gameData > val;
            case GREATER_THAN_EQUALS:
                return gameData >= val;
            case NOT_EQUALS:
                return gameData != val;
            default:
                return false;
        }
    }
}
