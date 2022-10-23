package level;

import util.listener.RenderListener;
import util.listener.UpdateListener;
import util.loader.image.LoaderImage;
import util.size.Size;

import java.awt.Graphics;
import java.awt.image.BufferedImage;

public class LevelManager implements RenderListener, UpdateListener {
    private BufferedImage[] levelSprite;

    private Level[] levels;

    private int currentLevelIndex = 0;

    public LevelManager(
            Level[] levels
    ) {
        this.levels = levels;

        loadSprites();
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

    private void loadSprites() {
        BufferedImage image = LoaderImage.loadImage("map/assets.png");

        levelSprite = new BufferedImage[322];

        for (int y = 0; y < 14; y++) {
            for (int x = 0; x < 23; x++) {
                int index = y * 23 + x;

                levelSprite[index] = image.getSubimage(x * 16, y * 16, 16, 16);
            }
        }
    }
}
