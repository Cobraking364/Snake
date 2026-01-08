package src;

import java.util.*;
import java.io.*;
import java.nio.file.Files;
import java.nio.file.StandardOpenOption;

//Create new hashmap
//Reads file to hashmap with bufferedreader
//When score is achieved compare to key in hashmap
//If score > value, wipe .txt and write hashmap to file

public class HighscoreHandler {
    public void highscoreHandler(Board boardSize, int score) {
        String board = boardSize + "";
        writeFileToMap(board, score);

    }

    public void writeFileToMap(String board, int score) {
        Map<String, String> highscoreMap = new HashMap<>();
        try (InputStream highscoreFile = HighscoreHandler.class.getResourceAsStream("/highscore.txt");
                BufferedReader writeToMap = new BufferedReader(new InputStreamReader(highscoreFile))) {
            String line;
            while ((line = writeToMap.readLine()) != null) {
                String[] parts = line.split(" ");
                String key = parts[0];
                String value = parts[1];
                highscoreMap.put(key, value);
            }
        } catch (IOException e) {
            System.out.println("couldn't read file");
        }
    }

    public void writeMapToFile(HashMap<String, String> newBoard) {
        BufferedWriter writeToFile = null;
       try (FileOutputStream name = new FileOutputStream("/highscore.txt", false)) {
       } catch (IOException e) {
            System.out.println("couldn't read file");
        }


        try { writeToFile = new BufferedWriter(new FileWriter("/highscore.txt"));
            for (Map.Entry<String, String> entry : newBoard.entrySet()) {
                writeToFile.write(entry.getKey() + " " + entry.getValue());
                writeToFile.newLine();
            }
        } catch (IOException e) {
            System.out.println("couldn't read file");
        }
    }
}
