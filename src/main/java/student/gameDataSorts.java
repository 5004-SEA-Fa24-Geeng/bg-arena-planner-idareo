package student;

import java.util.Comparator;

/**
 * class containing classes for GamData column sorts.
 */
public class gameDataSorts {

    public static class nameSort implements Comparator<BoardGame> {
        @Override
        public int compare(BoardGame o1, BoardGame o2){
            int compare = o1.getName().compareToIgnoreCase(o2.getName());
            if (compare == 0){
                return o1.getName().toLowerCase().compareTo(o2.getName().toLowerCase());
            }
            return compare;
        }
    }


    public static class ratingSort implements Comparator<BoardGame> {
        @Override
        public int compare(BoardGame o1, BoardGame o2){
            return Double.compare(o1.getRating(), o2.getRating());
        }
    }

    public static class difficultySort implements Comparator<BoardGame> {
        @Override
        public int compare(BoardGame o1, BoardGame o2){
            return Double.compare(o1.getDifficulty(), o2.getDifficulty());
        }
    }

    public static class rankSort implements Comparator<BoardGame>{
        @Override
        public int compare(BoardGame o1, BoardGame o2){
            return Double.compare(o1.getRank(), o2.getRank());
        }
    }

    public static class minPlayersSort implements Comparator<BoardGame>{
        @Override
        public int compare(BoardGame o1, BoardGame o2){
            return Integer.compare(o1.getMinPlayers(), o2.getMinPlayers());
        }
    }

    public static class maxPlayersSort implements Comparator<BoardGame>{
        @Override
        public int compare(BoardGame o1, BoardGame o2){
            return Integer.compare(o1.getMaxPlayers(), o2.getMaxPlayers());
        }
    }

    public static class minPlayTimeSort implements Comparator<BoardGame>{
        @Override
        public int compare(BoardGame o1, BoardGame o2){
            return Integer.compare(o1.getMinPlayTime(), o2.getMinPlayTime());
        }
    }

    public static class maxPlayTimeSort implements Comparator<BoardGame>{
        @Override
        public int compare(BoardGame o1, BoardGame o2){
            return Integer.compare(o1.getMaxPlayTime(), o2.getMaxPlayTime());
        }
    }

    public static class yearSort implements Comparator<BoardGame>{
        @Override
        public int compare(BoardGame o1, BoardGame o2){
            return Integer.compare(o1.getYearPublished(), o2.getYearPublished());
        }
    }

}
