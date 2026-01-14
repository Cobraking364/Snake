package src;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;

public class SettingsHandler {
    private int sizeX;
    private int sizeY;
    private int soundVolume;
    private int snakeSpeed;
    private int fruitCount;
    private int playerCount;

    public void loadSettings(Settings settings) {
        File settingsFile = new File(getFileName());
        if (!settingsFile.exists()) {
            System.out.println("can't find " + getFileName());
        }
        try (BufferedReader reader = new BufferedReader(new FileReader(getFileName()))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] parts = line.split("=");
                String specificSetting = parts[0];
                String value = parts[1];
                if (parts.length != 2) {
                    return;
                }
                switch (specificSetting) {
                    case "sizeX":
                        sizeX = Integer.parseInt(value);
                    case "sizeY":
                        sizeY = Integer.parseInt(value);
                    case "soundVolume":
                        soundVolume = Integer.parseInt(value);
                    case "snakeSpeed":
                        snakeSpeed = Integer.parseInt(value);
                    case "fruitCount":
                        fruitCount = Integer.parseInt(value);
                    case "playerCount":
                        playerCount = Integer.parseInt(value);
                    default:
                        break;
                }
            }
        } catch (IOException e) {
            System.out.println("can't find " + getFileName());
        }
    }

    public void saveSettings(Settings settings) {
        try (PrintWriter writer = new PrintWriter(getFileName())) {
            writer.write(
                    "sizeX= " + sizeX + "\n" +
                    "sizeY= " + sizeY + "\n" +
                    "soundVolume= " + soundVolume + "\n" +
                    "snakeSpeed= " + snakeSpeed + "\n" +
                    "fruitCount= " + fruitCount + "\n" +
                    "playerCount= " + playerCount + "\n");

        } catch (IOException e) {
            System.out.println("can't find " + getFileName());
        }
    }

    private String getFileName() {
        return "settings.txt"; // not called "/settings.txt"
    }
}
