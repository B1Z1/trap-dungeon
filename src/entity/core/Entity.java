package entity.core;

import util.listener.RenderListener;
import util.listener.UpdateListener;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.geom.Rectangle2D;
import java.awt.image.BufferedImage;
import java.util.HashMap;

public abstract class Entity<T> implements RenderListener, UpdateListener {
    private final float xOffset;
    private final float yOffset;
    private final HashMap<T, BufferedImage[]> animationMap;

    private float x, y;
    private int width, height;

    private Rectangle2D.Float hitBox;

    private int animationIndex = 0;
    private int animationSpeed = 25;
    private int animationTick = 0;
    private T currentAnimationType;

    public Entity(
            float x,
            float y,
            float xOffset,
            float yOffset,
            int width,
            int height,
            HashMap<T, BufferedImage[]> animationMap
    ) {
        this.x = x;
        this.y = y;
        this.xOffset = xOffset;
        this.yOffset = yOffset;
        this.width = width;
        this.height = height;
        this.animationMap = animationMap;
    }

    @Override
    public void update() {
        updateAnimationTick();
    }

    @Override
    public void render(Graphics graphics) {
        renderImage(graphics);

        if (isHitBoxDefined()) {
            renderHitBox(graphics);
        }
    }

    public T getCurrentAnimationType() {
        return currentAnimationType;
    }

    public void updateCurrentAnimationType(T currentAnimationType) {
        if (this.currentAnimationType == currentAnimationType) {
            return;
        }

        this.currentAnimationType = currentAnimationType;
        resetAnimation();
    }

    public BufferedImage getCurrentAnimationImage() {
        return animationMap.get(currentAnimationType)[animationIndex];
    }

    public int getCurrentAnimationIndex() {
        return animationIndex;
    }

    public float getX() {
        return x;
    }

    public void updateHorizontalPosition(float x) {
        this.x = x;

        if (isHitBoxDefined()) {
            hitBox.x = x;
        }
    }

    public float getY() {
        return y;
    }

    public void updateVerticalPosition(float y) {
        this.y = y;

        if (isHitBoxDefined()) {
            hitBox.y = y;
        }
    }

    public int getWidth() {
        return width;
    }

    public int getHeight() {
        return height;
    }

    public Rectangle2D.Float getHitBox() {
        return hitBox;
    }

    protected void initHitBox(int width, int height) {
        hitBox = new Rectangle2D.Float(x, y, width, height);
    }

    private void renderImage(Graphics graphics) {
        BufferedImage image = getCurrentAnimationImage();
        int xWithOffset = (int) (x - xOffset);
        int yWithOffset = (int) (y - yOffset);

        graphics.drawImage(image, xWithOffset, yWithOffset, width, height, null);
    }

    private void renderHitBox(Graphics graphics) {
        graphics.setColor(Color.GREEN);
        graphics.drawRect(
                (int) hitBox.getX(),
                (int) hitBox.getY(),
                (int) hitBox.getWidth(),
                (int) hitBox.getHeight()
        );
    }

    private void updateAnimationTick() {
        animationTick++;

        if (animationTick >= animationSpeed) {
            BufferedImage[] animationSprites = animationMap.get(currentAnimationType);
            int animationSpritesMaxIndex = animationSprites.length - 1;

            animationTick = 0;
            animationIndex = animationIndex >= animationSpritesMaxIndex
                    ? 0
                    : animationIndex + 1;
        }
    }

    private void resetAnimation() {
        animationTick = 0;
        animationIndex = 0;
    }

    private boolean isHitBoxDefined() {
        return hitBox != null;
    }
}
