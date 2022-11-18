package util.collision;

import util.size.Size;

import java.awt.geom.Rectangle2D;

/**
 * Zestaw funkcji utilowych pozwalających wyznaczać czy obiekt koliduje
 * się z innymi w zależności od podanych danych
 */
public class Collision {
    public static boolean canMove(float x, float y, float width, float height, int[][] levelData) {
        return !isSolid(x, y, levelData)
                && !isSolid(x + width, y, levelData)
                && !isSolid(x + width, y + height, levelData)
                && !isSolid(x, y + height, levelData);
    }

    public static float getEntityXPositionNextToWall(Rectangle2D.Float hitBox, float xSpeed) {
        int currentTile = (int) (hitBox.getX() / Size.TILES_SIZE);

        if (xSpeed > 0) {
            int tileXPosition = currentTile * Size.TILES_SIZE;
            int xOffset = (int) (Size.TILES_SIZE - hitBox.getWidth());

            return tileXPosition + xOffset - 1;
        }

        return currentTile * Size.TILES_SIZE;
    }

    public static float getEntityYPositionUnderRoofOrAboveFloor(Rectangle2D.Float hitBox, float airSpeed) {
        int currentTile = (int) (hitBox.getY() / Size.TILES_SIZE);

        if (airSpeed > 0) {
            float tileYPosition = currentTile * Size.TILES_SIZE;
            float yOffset = (float) (Size.TILES_SIZE - hitBox.getHeight());

            return tileYPosition + yOffset - 1;
        }

        return currentTile * Size.TILES_SIZE;
    }

    public static boolean isEntityOnFloor(Rectangle2D.Float hitBox, int[][] levelData) {
        return isSolid((float) hitBox.getX(), (float) (hitBox.getY() + hitBox.getHeight() + 1), levelData)
                && isSolid((float) (hitBox.getX() + hitBox.getWidth()), (float) (hitBox.getY() + hitBox.getHeight() + 1), levelData);
    }

    public static boolean isSolid(float x, float y, int[][] levelData) {
        int maxWidth = levelData[0].length * Size.TILES_SIZE;
        int maxHeight = levelData.length * Size.TILES_SIZE;

        if ((x <= 0 || x >= maxWidth) || (y <= 0 || y >= maxHeight)) {
            return true;
        }

        int xIndex = (int) x / Size.TILES_SIZE;
        int yIndex = (int) y / Size.TILES_SIZE;
        int value = levelData[yIndex][xIndex];

        return value == 3 || value == 7;
    }
}
