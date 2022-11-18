package util.sprite;

import util.loader.image.LoaderImage;

import java.awt.Color;
import java.awt.image.BufferedImage;

/**
 * Mechanizm pozwalający wyciągnąć dane z obrazku mapy na podstawie kolorów w obrazku
 */
public class SpriteLevelData {
    public static int[][] getLevelDataFromImage(String path) {
        BufferedImage image = LoaderImage.loadImage(path);
        int[][] levelData = new int[image.getHeight()][image.getWidth()];

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
