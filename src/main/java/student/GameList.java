package student;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.HashSet;
import java.util.List;
import java.util.Scanner;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;


public class GameList implements IGameList {

    private Set<String> gameNames;

    /**
     * Constructor for the GameList.
     */
    public GameList() {
        gameNames = new HashSet<>();
    }

    @Override
    public List<String> getGameNames() {
        //convert set to list and sort in case-sensitive ascending
        return gameNames.stream()
                .sorted(String.CASE_INSENSITIVE_ORDER)
                .collect(Collectors.toList());
    }

    @Override
    public void clear() {
        gameNames.clear();
    }

    @Override
    public int count() {
        return gameNames.size();
    }

    @Override
    public void saveGame(String filename) {

        FileWriter fileWriter = null;
        BufferedWriter bufferedWriter = null;

        //Saves the list of games to a file
        try {
            List<String> games = getGameNames();
            fileWriter = new FileWriter(filename);
            bufferedWriter = new BufferedWriter(fileWriter);
            for (String game : games) {
                bufferedWriter.write(game);
                bufferedWriter.newLine();
            }
        } catch (IOException e) {
            System.out.println("Error Saving Game" + e.getMessage());
        } finally {
            // Close the BufferedWriter (and FileWriter) to ensure resources are released
            try {
                if (bufferedWriter != null) {
                    bufferedWriter.close();
                }
                if (fileWriter != null) {
                    fileWriter.close();
                }
            } catch (IOException e) {
                System.out.println("Error" + e.getMessage());
            }
        }
    }

    @Override
    public void addToList(String str, Stream<BoardGame> filtered) throws IllegalArgumentException {
        //convert to list from stream
        List<BoardGame> filteredlist = filtered.toList();

        if (str.equals(ADD_ALL)) {
            for (BoardGame game : filteredlist) {
                gameNames.add(game.getName());
            }
        } else if (str.matches("\\d+")) {
            int index = Integer.parseInt(str) - 1;
            if (index >= 0 && index < filteredlist.size()) {
                gameNames.add(filteredlist.get(index).getName());
            } else {
                throw new IllegalArgumentException("Index out of bounds");
            }
        } else if (str.contains("-")) {
            String[] split = str.split("-");
            try {
                int start = Integer.parseInt(split[0]) - 1;
                int end = Integer.parseInt(split[1]) - 1;

                if (start < 0 || start > end) {
                    throw new IllegalArgumentException("Index out of bounds");
                }
                if (end >= filteredlist.size()) {
                    end = filteredlist.size() - 1;
                }
                for (int i = start; i <= end; i++) {
                    gameNames.add(filteredlist.get(i).getName());
                }
            } catch (IllegalArgumentException e) {
                throw new IllegalArgumentException("Invalid range format");
            }

        } else {
            for (BoardGame game : filteredlist) {
                if (str.equalsIgnoreCase(game.getName())) {
                    gameNames.add(game.getName());
                }
            }
        }
    }

    @Override
    public void removeFromList(String str) throws IllegalArgumentException {
        if (str.equals(ADD_ALL)) {
            clear();
        } else if (str.matches("\\d+")) {
            try {
                int index = Integer.parseInt(str) - 1;
                if (index >= 0 && index < gameNames.size()) {
                    gameNames.remove(getGameNames().get(index));
                } else {
                    throw new IllegalArgumentException("Index out of bounds");
                }
            } catch (NumberFormatException e) {
                throw new IllegalArgumentException("Index out of bounds");
            }
        } else if (str.contains("-")) {

            String[] split = str.split("-");
            try {
                int start = Integer.parseInt(split[0]) - 1;
                int end = Integer.parseInt(split[1]) - 1;

                if (start < 0 || start > end) {
                    throw new IllegalArgumentException("Index out of bounds");
                }
                if (end >= gameNames.size()) {
                    end = gameNames.size() - 1;
                }
                for (int i = end; i >= start; i--) {
                    gameNames.remove(getGameNames().get(i));
                }
            } catch (NumberFormatException e) {
                throw new IllegalArgumentException("Invalid range format");
            }
        } else {
            gameNames.remove(str);
        }
    }
}
