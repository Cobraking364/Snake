package src;

import java.io.*;
import java.util.*;

//Create new hashmap
//Reads file to hashmap with bufferedreader
//When score is achieved compare to key in hashmap
//If score > value, wipe .txt and write hashmap to file

public class HighscoreHandler {
    public void highscoreHandler(Board boardSize, int score) {
         
        String board = boardSize + "";
        Map<String,String> map = writeFileToMap(board, score);
        compareBoardToKey(map, board, score);
        writeMapToFile(map);

    }

    public Map<String,String> writeFileToMap(String board, int score) {
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
        return highscoreMap;
    }
    public Map<String,String> compareBoardToKey(Map<String,String> map, String board, int score){
        boolean exists = false;
        for (Map.Entry<String, String> entry : map.entrySet()) {
            if (board.equals(entry.getKey())){
                exists = true;
            }
        }
        if (!exists) {
            map.put(board, "0");
        }
        if (Integer. parseInt(map.get(board)) < score) {
            map.put(board, score+"");
        }
        return map;
    }
    public void writeMapToFile(Map<String, String> map) {
        BufferedWriter writeToFile = null;
       try (FileOutputStream name = new FileOutputStream("/highscore.txt", false)) { //cleans file
       } catch (IOException e) {
            System.out.println("couldn't read file");
        }


        try { writeToFile = new BufferedWriter(new FileWriter("/highscore.txt"));
            for (Map.Entry<String, String> entry : map.entrySet()) {
                writeToFile.write(entry.getKey() + " " + entry.getValue());
                writeToFile.newLine();
            }
        } catch (IOException e) {
            System.out.println("couldn't read file");
        }
    }
}
