package util.sprite;

import util.loader.image.LoaderImage;

import java.awt.Color;
import java.awt.image.BufferedImage;

public class SpriteLevelData {
    public static int[][] getLevelDataFromImage(String path, int levelWidth, int levelHeight) {
        int[][] levelData = new int[levelHeight][levelWidth];
        BufferedImage image = LoaderImage.loadImage(path);

        for (int y = 0; y < image.getHeight(); y++) {
            for (int x = 0; x < image.getWidth(); x++) {
                Color color = new Color(image.getRGB(x, y));
                int value = color.getRed();

                levelData[y][x] = value;
            }
        }

        return levelData;
    }
}
