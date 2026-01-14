package src;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;

public class SettingsHandler {
    
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
                        settings.setSizeX(Integer.parseInt(value));
                    case "sizeY":
                        settings.setSizeY(Integer.parseInt(value));
                    case "soundVolume":
                        settings.setSoundVolume(Integer.parseInt(value));
                    case "snakeSpeed":
                        settings.setSnakeSpeed(Integer.parseInt(value));
                    case "fruitCount":
                        settings.setFruitCount(Integer.parseInt(value));
                    case "playerCount":
                        settings.setPlayerCount(Integer.parseInt(value));
                    default:
                        break;
                }
            }
        } catch (IOException e) {
            System.out.println("can't find " + getFileName());
        }
    }

    public void saveSettings(Settings settings) {
        int sizeX = settings.getSizeX();
        int sizeY = settings.getSizeY();
        int soundVolume = settings.getSoundVolume();
        int snakeSpeed = settings.getSnakeSpeed();
        int fruitCount = settings.getFruitCount();
        int playerCount = settings.getPlayerCount();
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
