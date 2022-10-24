package game.state;

import assets.AssetsManager;
import entity.player.Player;
import entity.player.PlayerAnimationType;
import level.LevelManager;
import util.direction.Direction;
import util.loader.image.LoaderImage;
import util.size.Size;
import util.sprite.SpriteAnimation;
import util.sprite.SpriteLevelData;
import util.state.State;

import java.awt.Graphics;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;
import java.awt.event.WindowEvent;
import java.awt.image.BufferedImage;
import java.util.HashMap;

public class GamePlayingState extends State {
    private final int leftBorder = (int) (0.2 * Size.GAME_WIDTH);
    private final int rightBorder = (int) (0.8 * Size.GAME_WIDTH);
    private final int[][] backgroundData;
    private AssetsManager assetsManager;
    private Player player;
    private LevelManager levelManager;
    private int xLevelOffset;
    private int levelTilesWide;
    private int maxTilesOffset;
    private int maxLevelOffsetX;

    public GamePlayingState() {
        initClasses();

        backgroundData = SpriteLevelData.getLevelDataFromImage("map/background.png");

        levelTilesWide = levelManager.getCurrentLevelData()[0].length;
        maxTilesOffset = levelTilesWide - Size.VISIBLE_TILES_IN_WIDTH;
        maxLevelOffsetX = maxTilesOffset * Size.TILES_SIZE;
    }

    @Override
    public void update() {
        player.update();
        levelManager.update();

        updateXOffset();
        player.setXOffset(xLevelOffset);
        levelManager.setXOffset(xLevelOffset);
    }

    @Override
    public void render(Graphics graphics) {
        renderBackground(graphics);
        levelManager.render(graphics);
        player.render(graphics);
    }

    @Override
    public void keyTyped(KeyEvent e) {

    }

    @Override
    public void keyPressed(KeyEvent e) {
        switch (e.getKeyCode()) {
            case KeyEvent.VK_D -> player.setDirection(Direction.RIGHT, true);
            case KeyEvent.VK_A -> player.setDirection(Direction.LEFT, true);
            case KeyEvent.VK_W -> player.setJump(true);
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {
        switch (e.getKeyCode()) {
            case KeyEvent.VK_D -> player.setDirection(Direction.RIGHT, false);
            case KeyEvent.VK_A -> player.setDirection(Direction.LEFT, false);
            case KeyEvent.VK_W -> player.setJump(false);
        }
    }

    @Override
    public void mouseClicked(MouseEvent e) {

    }

    @Override
    public void mousePressed(MouseEvent e) {

    }

    @Override
    public void mouseReleased(MouseEvent e) {

    }

    @Override
    public void mouseEntered(MouseEvent e) {

    }

    @Override
    public void mouseExited(MouseEvent e) {

    }

    @Override
    public void mouseDragged(MouseEvent e) {

    }

    @Override
    public void mouseMoved(MouseEvent e) {

    }

    @Override
    public void windowGainedFocus(WindowEvent e) {

    }

    @Override
    public void windowLostFocus(WindowEvent e) {
        player.resetDirections();
    }

    private void initClasses() {
        initAssetsManager();
        initLevelManager();
        initPlayer();
    }

    private void initPlayer() {
        BufferedImage idleAnimation = LoaderImage.loadImage("player/Dude_Monster_Idle_4.png");
        BufferedImage runAnimation = LoaderImage.loadImage("player/Dude_Monster_Run_6.png");
        BufferedImage jumpAnimation = LoaderImage.loadImage("player/Dude_Monster_Jump_8.png");
        HashMap<PlayerAnimationType, BufferedImage[]> animationTypeBufferedImageHashMap = new HashMap<>() {{
            put(PlayerAnimationType.IDLE, SpriteAnimation.cutSprite(idleAnimation, Size.TILES_DEFAULT_SIZE, 4, 4));
            put(PlayerAnimationType.RUN, SpriteAnimation.cutSprite(runAnimation, Size.TILES_DEFAULT_SIZE, 6, 6));
            put(PlayerAnimationType.JUMP, SpriteAnimation.cutSprite(jumpAnimation, Size.TILES_DEFAULT_SIZE, 5, 5));
            put(PlayerAnimationType.FALL, SpriteAnimation.cutSprite(jumpAnimation, Size.TILES_DEFAULT_SIZE, 1, 6, 5));
        }};

        player = new Player(
                200,
                200,
                (int) (32 * Size.TILES_DEFAULT_SCALE),
                (int) (32 * Size.TILES_DEFAULT_SCALE),
                animationTypeBufferedImageHashMap,
                levelManager
        );
    }

    private void initAssetsManager() {
        assetsManager = new AssetsManager();
    }

    private void initLevelManager() {
        levelManager = new LevelManager(assetsManager);
    }

    private void renderBackground(Graphics graphics) {
        for (int j = 0; j < backgroundData.length; j++) {
            for (int i = 0; i < backgroundData[0].length; i++) {
                int backgroundSpriteIndex = backgroundData[j][i];
                BufferedImage sprite = assetsManager.getSpriteByIndex(backgroundSpriteIndex);
                int x = i * Size.TILES_SIZE - xLevelOffset;
                int y = j * Size.TILES_SIZE;

                graphics.drawImage(sprite, x, y, Size.TILES_SIZE, Size.TILES_SIZE, null);
            }
        }
    }

    private void updateXOffset() {
        int playerX = (int) player.getX();
        int diff = playerX - xLevelOffset;

        if (diff > rightBorder) {
            xLevelOffset += diff - rightBorder;
        } else if (diff < leftBorder) {
            xLevelOffset += diff - leftBorder;
        }

        if (xLevelOffset > maxLevelOffsetX) {
            xLevelOffset = maxLevelOffsetX;
        } else if (xLevelOffset < 0) {
            xLevelOffset = 0;
        }
    }
}
