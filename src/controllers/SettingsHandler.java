package src.controllers;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;
import src.models.Settings;

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
                if (parts.length != 2) {
                    return;
                }
                String specificSetting = parts[0];
                String value = parts[1];
                switch (specificSetting) {
                    case "sizeX":
                        settings.setSizeX(Integer.parseInt(value));
                        break;
                    case "sizeY":
                        settings.setSizeY(Integer.parseInt(value));
                        break;
                    case "soundVolume":
                        settings.setSoundVolume(Integer.parseInt(value));
                        break;
                    case "snakeSpeed":
                        settings.setSnakeSpeed(Integer.parseInt(value));
                        break;
                    case "fruitCount":
                        settings.setFruitCount(Integer.parseInt(value));
                        break;
                    case "playerCount":
                        settings.setPlayerCount(Integer.parseInt(value));
                        break;
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
                    "sizeX=" + sizeX + "\n" +
                    "sizeY=" + sizeY + "\n" +
                    "soundVolume=" + soundVolume + "\n" +
                    "snakeSpeed=" + snakeSpeed + "\n" +
                    "fruitCount=" + fruitCount + "\n" +
                    "playerCount=" + playerCount + "\n");

        } catch (IOException e) {
            System.out.println("can't find " + getFileName());
        }
    }

    private String getFileName() {
        return "settings.txt"; // not called "/settings.txt"
    }
}
