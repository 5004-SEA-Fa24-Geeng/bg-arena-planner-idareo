package student;

public class Filters {
    private Filters() {}

    public static boolean filter(BoardGame game, GameData column, Operations op, String value){
        switch(column){
            case NAME:
                //filter the name(string)
                return filterString(game.getName(), op, value);
            case MAX_PLAYERS:
                //filter based on max-players(numerical)
                return false; //placeholder
            default:
                return false; //placeholder
        }
    }

    //method to handle filtering for gameData
    public static boolean filterString(String gameData, Operations op, String value){
        switch(op){
            case EQUALS:
                return gameData.equals(value); //checks if the game data equals the value passed in with filter
            case LESS_THAN:
                //do something
            default:
                return false;

        }
    }

//    public static boolean filterNum(int gameData, Operations op, String value){
//        int value = Integer.parseInt(value);
//        switch(op){
//            case EQUALS:
//                //double something
//            case CONTAINS:
//        }
//    }
}
