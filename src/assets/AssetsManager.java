package assets;

import util.loader.image.LoaderImage;

import java.awt.image.BufferedImage;

public class AssetsManager {
    private static int TILE_SIZE = 16;

    private BufferedImage[] levelSprite;

    public AssetsManager() {
        initLevelSprite();
    }

    public BufferedImage getSpriteByIndex(int index) {
        return levelSprite[index];
    }

    private void initLevelSprite() {
        BufferedImage image = LoaderImage.loadImage("map/assets.png");
        int tilesInWidth = image.getWidth() / AssetsManager.TILE_SIZE;
        int tilesInHeight = image.getHeight() / AssetsManager.TILE_SIZE;

        levelSprite = new BufferedImage[tilesInWidth * tilesInHeight];

        for (int y = 0; y < tilesInHeight; y++) {
            for (int x = 0; x < tilesInWidth; x++) {
                int index = y * tilesInWidth + x;

                levelSprite[index] = image.getSubimage(
                        x * AssetsManager.TILE_SIZE,
                        y * AssetsManager.TILE_SIZE,
                        AssetsManager.TILE_SIZE,
                        AssetsManager.TILE_SIZE
                );
            }
        }
    }
}
