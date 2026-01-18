package src.controllers;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.Map;

//duno if works in jar
public class HighscoreHandler {

    private Map<String, String> map;

    void checkHighscore(int x, int y, int score) {

        String boardSize = x + ":" + y;
        map = loadMapFromFile();

        int oldHighscore = getHighscore(x, y);

        if (oldHighscore < score) {
            map.put(boardSize, score + "");
            writeMapToFile(map);
        }
    }

    public Map<String, String> loadMapFromFile() {
        Map<String, String> highscoreMap = new HashMap<>();
        File highscoreFile = new File(getFileName());
        if (!highscoreFile.exists()) {
            System.out.println("can't find " + getFileName());
        }
        try (BufferedReader reader = new BufferedReader(new FileReader(getFileName()))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] parts = line.split(" ");
                String key = parts[0];
                String value = parts[1];
                highscoreMap.put(key, value);
            }
        } catch (IOException e) {
            System.out.println("can't find " + getFileName());
        }
        return highscoreMap;
    }

    public void writeMapToFile(Map<String, String> map) {
        try (PrintWriter writer = new PrintWriter(getFileName())) {
            String mapData = "";
            for (Map.Entry<String, String> entry : map.entrySet()) {
                mapData += (entry.getKey() + " " + entry.getValue() + "\n");
            }
            writer.write(mapData);
        } catch (IOException e) {
            System.out.println(e.getMessage() + "can't find " + getFileName());
        }
    }

    public static String getFileName() {
        return "highscore.txt"; // Not "/highscore.txt"
    }

    public int getHighscore(int x, int y) {
        String boardSize = x + ":" + y;
        return Integer.parseInt(map.getOrDefault(boardSize, "0"));
    }
}
