package entity.player;

import entity.core.Entity;
import util.direction.Direction;
import util.size.Size;

import java.awt.image.BufferedImage;
import java.util.HashMap;

public class Player extends Entity<PlayerAnimationType> {
    private final float moveSpeed = 1.5f * Size.TILES_DEFAULT_SCALE;
    private final HashMap<Direction, Boolean> currentDirectionMap = new HashMap<>() {{
        put(Direction.RIGHT, false);
        put(Direction.LEFT, false);
    }};

    public Player(
            float x,
            float y,
            int width,
            int height,
            HashMap<PlayerAnimationType, BufferedImage[]> animationMap
    ) {
        super(
                x,
                y,
                5 * Size.TILES_DEFAULT_SCALE,
                4 * Size.TILES_DEFAULT_SCALE,
                width,
                height,
                animationMap
        );

        initHitBox((int) (20 * Size.TILES_DEFAULT_SCALE), (int) (28 * Size.TILES_DEFAULT_SCALE));
        updateCurrentAnimationType(PlayerAnimationType.IDLE);
    }

    public void resetDirections() {
        setDirection(Direction.RIGHT, false);
        setDirection(Direction.LEFT, false);
    }

    public void setDirection(Direction direction, boolean active) {
        currentDirectionMap.put(direction, active);
    }

    @Override
    public void update() {
        super.update();
        updatePosition();
        updateAnimationType();
    }

    private void updatePosition() {
        int xSpeed = 0;

        if (currentDirectionMap.get(Direction.RIGHT)) {
            xSpeed += moveSpeed;
        }

        if (currentDirectionMap.get(Direction.LEFT)) {
            xSpeed -= moveSpeed;
        }

        updateX(xSpeed);
    }

    private void updateAnimationType() {
        if (isMoving()) {
            updateCurrentAnimationType(PlayerAnimationType.RUN);
        } else {
            updateCurrentAnimationType(PlayerAnimationType.IDLE);
        }
    }

    private void updateX(int xSpeed) {
        updateHorizontalPosition(getX() + xSpeed);
    }

    private boolean isMoving() {
        return isCurrentDirectionActive(Direction.RIGHT)
                || isCurrentDirectionActive(Direction.LEFT);
    }

    private boolean isCurrentDirectionActive(Direction direction) {
        return currentDirectionMap.get(direction);
    }
}
