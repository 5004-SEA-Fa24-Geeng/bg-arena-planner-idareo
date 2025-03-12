package student;

import java.util.Comparator;

/**
 * class containing classes for GamData column sorts.
 */
public class GameDataSorts {

    public static class NameSort implements Comparator<BoardGame> {
        @Override
        public int compare(BoardGame o1, BoardGame o2){
            int compare = o1.getName().compareToIgnoreCase(o2.getName());
            if (compare == 0){
                return o1.getName().toLowerCase().compareTo(o2.getName().toLowerCase());
            }
            return compare;
        }
    }


    public static class RatingSort implements Comparator<BoardGame> {
        @Override
        public int compare(BoardGame o1, BoardGame o2){
            return Double.compare(o1.getRating(), o2.getRating());
        }
    }

    public static class DifficultySort implements Comparator<BoardGame> {
        @Override
        public int compare(BoardGame o1, BoardGame o2){
            return Double.compare(o1.getDifficulty(), o2.getDifficulty());
        }
    }

    public static class RankSort implements Comparator<BoardGame>{
        @Override
        public int compare(BoardGame o1, BoardGame o2){
            return Double.compare(o1.getRank(), o2.getRank());
        }
    }

    public static class MinPlayersSort implements Comparator<BoardGame>{
        @Override
        public int compare(BoardGame o1, BoardGame o2){
            return Integer.compare(o1.getMinPlayers(), o2.getMinPlayers());
        }
    }

    public static class MaxPlayersSort implements Comparator<BoardGame>{
        @Override
        public int compare(BoardGame o1, BoardGame o2){
            return Integer.compare(o1.getMaxPlayers(), o2.getMaxPlayers());
        }
    }

    public static class MinPlayTimeSort implements Comparator<BoardGame>{
        @Override
        public int compare(BoardGame o1, BoardGame o2){
            return Integer.compare(o1.getMinPlayTime(), o2.getMinPlayTime());
        }
    }

    public static class MaxPlayTimeSort implements Comparator<BoardGame>{
        @Override
        public int compare(BoardGame o1, BoardGame o2){
            return Integer.compare(o1.getMaxPlayTime(), o2.getMaxPlayTime());
        }
    }

    public static class YearSort implements Comparator<BoardGame>{
        @Override
        public int compare(BoardGame o1, BoardGame o2){
            return Integer.compare(o1.getYearPublished(), o2.getYearPublished());
        }
    }

}
