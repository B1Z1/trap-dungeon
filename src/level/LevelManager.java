package level;

import util.listener.RenderListener;
import util.listener.UpdateListener;
import util.loader.image.LoaderImage;
import util.size.Size;
import util.sprite.SpriteLevelData;

import java.awt.Graphics;
import java.awt.image.BufferedImage;

public class LevelManager implements RenderListener, UpdateListener {
    private static int TILE_SIZE = 16;
    private static int TILES_IN_WIDTH = 23;
    private static int TILES_IN_HEIGHT = 14;

    private BufferedImage[] levelSprite;

    private Level[] levels;

    private int currentLevelIndex = 0;

    public LevelManager() {
        initLevels();
        loadLevelAssets();
    }

    @Override
    public void render(Graphics graphics) {
        for (int j = 0; j < Size.TILES_IN_HEIGHT; j++) {
            for (int i = 0; i < Size.TILES_IN_WIDTH; i++) {
                int index = levels[currentLevelIndex].getSpriteIndex(i, j);
                int x = Size.TILES_SIZE * i;
                int y = Size.TILES_SIZE * j;

                graphics.drawImage(levelSprite[index], x, y, Size.TILES_SIZE, Size.TILES_SIZE, null);
            }
        }
    }

    @Override
    public void update() {

    }

    private void initLevels() {
        levels = new Level[]{
                new Level(
                        SpriteLevelData.getLevelDataFromImage(
                                "map/first-level-data.png",
                                Size.TILES_IN_WIDTH,
                                Size.TILES_IN_HEIGHT
                        )
                )
        };
    }

    private void loadLevelAssets() {
        BufferedImage image = LoaderImage.loadImage("map/assets.png");

        levelSprite = new BufferedImage[322];

        for (int y = 0; y < LevelManager.TILES_IN_HEIGHT; y++) {
            for (int x = 0; x < LevelManager.TILES_IN_WIDTH; x++) {
                int index = y * LevelManager.TILES_IN_WIDTH + x;

                levelSprite[index] = image.getSubimage(
                        x * LevelManager.TILE_SIZE,
                        y * LevelManager.TILE_SIZE,
                        LevelManager.TILE_SIZE,
                        LevelManager.TILE_SIZE
                );
            }
        }
    }
}
