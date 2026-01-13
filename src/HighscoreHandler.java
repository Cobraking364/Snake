package src;

import java.io.*;
import java.util.*;

//Create new hashmap
//Reads file to hashmap with bufferedreader
//When score is achieved compare to key in hashmap
//If score > value, wipe .txt and write hashmap to file
public class HighscoreHandler {

    public static void main(String[] args) {

        checkHighScore(10, 10, 40);
    }

    static void checkHighScore(int x, int y, int score) {

        String boardSize = x + ":" + y;
        Map<String, String> map = loadMapFromFile();

        String highScore = map.getOrDefault(boardSize, "0");

        if (Integer.parseInt(highScore) > score) {
            map.put(boardSize, score + "");
        }

        writeMapToFile(map);

    }

    // InputStream highscoreFile =
    // HighscoreHandler.class.getResourceAsStream("/highscore.txt");
    public static Map<String, String> loadMapFromFile() {
        Map<String, String> highscoreMap = new HashMap<>();
        File highscoreFile = new File (getFileName());
        if (!highscoreFile.exists()){
            System.out.println("can't find highscore.txt");
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
            System.out.println("couldn't read file1");
        }
        return highscoreMap;
    }

    public static Map<String, String> updateScoreMap(Map<String, String> map, String boardSize, int score) {

        if (!map.containsKey(boardSize)) {
            map.put(boardSize, "0");
        }
        if (Integer.parseInt(map.get(boardSize)) < score) {
            map.put(boardSize, score + "");
        }
        return map;
    }

    public static void writeMapToFile(Map<String, String> map) {

        try (PrintWriter writer = new PrintWriter(getFileName())) {
            String str = "";
            for (Map.Entry<String, String> entry : map.entrySet()) {
                str += (entry.getKey() + " " + entry.getValue() + "\n");
            }
            writer.write(str);
            System.out.println("the string is: " + str);
        } catch (IOException e) {

            System.out.println(e.getMessage() + "couldn't read file3");
        }
    }

    public static String getFileName() {
        return "/highscore.txt";
    }

}
