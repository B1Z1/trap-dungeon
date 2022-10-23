package entity.player;

import entity.core.Entity;
import level.LevelManager;
import util.collision.Collision;
import util.direction.Direction;
import util.size.Size;

import java.awt.geom.Rectangle2D;
import java.awt.image.BufferedImage;
import java.util.HashMap;

public class Player extends Entity<PlayerAnimationType> {
    private final float moveSpeed = 1.5f * Size.TILES_DEFAULT_SCALE;
    private final HashMap<Direction, Boolean> currentDirectionMap = new HashMap<>() {{
        put(Direction.RIGHT, false);
        put(Direction.LEFT, false);
    }};

    private final float fallSpeedAfterCollision = 0.5f * Size.TILES_DEFAULT_SCALE;
    private final float jumpSpeed = -2.25f * Size.TILES_DEFAULT_SCALE;
    private final float gravity = 0.04f * Size.TILES_DEFAULT_SCALE;

    private final LevelManager levelManager;

    private boolean jump = false;
    private boolean inAir = false;
    private float airSpeed = 0f;

    public Player(
            float x,
            float y,
            int width,
            int height,
            HashMap<PlayerAnimationType, BufferedImage[]> animationMap,
            LevelManager levelManager
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

        this.levelManager = levelManager;

        initHitBox((int) (20 * Size.TILES_DEFAULT_SCALE), (int) (28 * Size.TILES_DEFAULT_SCALE));
        updateCurrentAnimationType(PlayerAnimationType.IDLE);

        if (!Collision.isEntityOnFloor(getHitBox(), levelManager.getCurrentLevelData())) {
            inAir = true;
        }
    }

    @Override
    public void update() {
        super.update();
        updatePosition();
        updateAnimationType();
    }

    public void resetDirections() {
        setDirection(Direction.RIGHT, false);
        setDirection(Direction.LEFT, false);
    }

    public void setDirection(Direction direction, boolean active) {
        currentDirectionMap.put(direction, active);
    }

    public void setJump(boolean jump) {
        this.jump = jump;
    }

    private void updatePosition() {
        if (jump) {
            resolveJump();
        }

        if (!isMoving() && !inAir) {
            return;
        }

        int xSpeed = 0;

        if (currentDirectionMap.get(Direction.RIGHT)) {
            xSpeed += moveSpeed;
        }

        if (currentDirectionMap.get(Direction.LEFT)) {
            xSpeed -= moveSpeed;
        }

        Rectangle2D.Float hitBox = getHitBox();
        int[][] levelData = levelManager.getCurrentLevelData();

        boolean canMoveHorizontally = Collision.canMove(
                (float) hitBox.getX() + xSpeed,
                (float) hitBox.getY(),
                (float) hitBox.getWidth(),
                (float) hitBox.getHeight(),
                levelData
        );
        float newX = canMoveHorizontally
                ? getX() + xSpeed
                : Collision.getEntityXPositionNextToWall(hitBox, xSpeed);

        updateHorizontalPosition(newX);

        if (!inAir && !Collision.isEntityOnFloor(hitBox, levelData)) {
            inAir = true;
        }

        if (!inAir) {
            return;
        }

        boolean canMoveVertically = Collision.canMove(
                (float) hitBox.getX(),
                (float) hitBox.getY() + airSpeed,
                (float) hitBox.getWidth(),
                (float) hitBox.getHeight(),
                levelData
        );

        if (canMoveVertically) {
            updateVerticalPosition(getY() + airSpeed);
            airSpeed += gravity;
        } else {
            updateVerticalPosition(Collision.getEntityYPositionUnderRoofOrAboveFloor(hitBox, airSpeed));

            if (airSpeed > 0) {
                resetInAir();
            } else {
                airSpeed = fallSpeedAfterCollision;
            }
        }
    }

    private void resolveJump() {
        if (inAir) {
            return;
        }

        inAir = true;
        airSpeed = jumpSpeed;
    }

    private void updateAnimationType() {
        if (inAir) {
            PlayerAnimationType inAirAnimationType = airSpeed < 0
                    ? PlayerAnimationType.JUMP
                    : PlayerAnimationType.FALL;

            updateCurrentAnimationType(inAirAnimationType);
        } else if (isMoving()) {
            updateCurrentAnimationType(PlayerAnimationType.RUN);
        } else {
            updateCurrentAnimationType(PlayerAnimationType.IDLE);
        }
    }

    private void resetInAir() {
        inAir = false;
        airSpeed = 0;
    }

    private boolean isMoving() {
        return isCurrentDirectionActive(Direction.RIGHT)
                || isCurrentDirectionActive(Direction.LEFT);
    }

    private boolean isCurrentDirectionActive(Direction direction) {
        return currentDirectionMap.get(direction);
    }
}
