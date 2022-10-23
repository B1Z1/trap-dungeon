package util.sprite;

import java.awt.image.BufferedImage;

public class SpriteAnimation {
    public static BufferedImage[] cutSprite(BufferedImage sprite, int spriteSize, int spritesCount) {
        BufferedImage[] sprites = new BufferedImage[spritesCount];

        for (int i = 0; i < spritesCount; i++) {
            sprites[i] = sprite.getSubimage(i * spriteSize, 0, spriteSize, spriteSize);
        }

        return sprites;
    }
}
