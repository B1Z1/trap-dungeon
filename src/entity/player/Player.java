package entity.player;

import entity.core.Entity;
import util.direction.Direction;
import util.size.Size;

import java.awt.Color;
import java.awt.Graphics;
import java.util.HashMap;

public class Player extends Entity {
    private final float moveSpeed = 1.5f * Size.TILES_DEFAULT_SCALE;
    private final HashMap<Direction, Boolean> currentDirectionMap = new HashMap<Direction, Boolean>() {{
        put(Direction.RIGHT, false);
        put(Direction.LEFT, false);
    }};

    public Player(
            float x,
            float y,
            int width,
            int height
    ) {
        super(x, y, width, height);

        initHitBox(x, y, width, height);
    }

    public void resetDirections() {
        setDirection(Direction.RIGHT, false);
        setDirection(Direction.LEFT, false);
    }

    public void setDirection(Direction direction, boolean active) {
        currentDirectionMap.put(direction, active);
    }

    @Override
    public void render(Graphics graphics) {
        int x = (int) hitBox.x;
        int y = (int) hitBox.y;

        graphics.setColor(Color.BLACK);
        graphics.drawRect(x, y, width, height);
    }

    @Override
    public void update() {
        updatePosition();
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

    private void updateX(int xSpeed) {
        hitBox.x += xSpeed;
    }
}
