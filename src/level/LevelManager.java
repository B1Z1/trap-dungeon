package level;

import assets.AssetsManager;
import util.listener.RenderListener;
import util.listener.UpdateListener;
import util.size.Size;
import util.sprite.SpriteLevelData;

import java.awt.Graphics;

public class LevelManager implements RenderListener, UpdateListener {
    private final AssetsManager assetsManager;
    private Level[] levels;

    private int currentLevelIndex = 0;

    private int xOffset = 0;

    public LevelManager(
            AssetsManager assetsManager
    ) {
        this.assetsManager = assetsManager;

        initLevels();
    }

    @Override
    public void render(Graphics graphics) {
        Level currentLevel = levels[currentLevelIndex];
        int currentLevelWidth = currentLevel.getData()[0].length;

        for (int j = 0; j < Size.VISIBLE_TILES_IN_HEIGHT; j++) {
            for (int i = 0; i < currentLevelWidth; i++) {
                int index = currentLevel.getSpriteIndex(i, j);

                if (index == 0) {
                    continue;
                }

                int x = Size.TILES_SIZE * i - xOffset;
                int y = Size.TILES_SIZE * j;

                graphics.drawImage(
                        assetsManager.getSpriteByIndex(index),
                        x,
                        y,
                        Size.TILES_SIZE,
                        Size.TILES_SIZE,
                        null
                );
            }
        }
    }

    @Override
    public void update() {

    }

    public int[][] getCurrentLevelData() {
        return levels[currentLevelIndex].getData();
    }

    public void setXOffset(int xOffset) {
        this.xOffset = xOffset;
    }

    private void initLevels() {
        levels = new Level[]{
                new Level(
                        SpriteLevelData.getLevelDataFromImage("map/first-level-data.png")
                )
        };
    }
}
