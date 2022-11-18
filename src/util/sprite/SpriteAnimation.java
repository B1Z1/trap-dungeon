package util.sprite;

import java.awt.image.BufferedImage;

/**
 * Zestaw funkcji pozwalające pociąć sprite na kilka
 */
public class SpriteAnimation {
    public static BufferedImage[] cutSprite(
            BufferedImage sprite,
            int spriteSize,
            int spriteCount,
            int lastSpriteIndex
    ) {
        return cutSprite(sprite, spriteSize, spriteCount, lastSpriteIndex, 0);
    }

    public static BufferedImage[] cutSprite(
            BufferedImage sprite,
            int spriteSize,
            int spriteCount,
            int lastSpriteIndex,
            int fromSprite
    ) {
        BufferedImage[] sprites = new BufferedImage[spriteCount];
        int index = 0;

        for (int i = fromSprite; i < lastSpriteIndex; i++) {
            sprites[index] = sprite.getSubimage(i * spriteSize, 0, spriteSize, spriteSize);

            index++;
        }

        return sprites;
    }
}
