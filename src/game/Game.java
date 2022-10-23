package game;

import util.size.Size;

import java.awt.Dimension;
import java.awt.Graphics;

public class Game implements Runnable {
    public final static int TILES_IN_WIDTH = 26;
    public final static int TILES_IN_HEIGHT = 14;
    private final static int PANEL_WIDTH = TILES_IN_WIDTH * Size.TILES_SIZE;
    private final static int PANEL_HEIGHT = TILES_IN_HEIGHT * Size.TILES_SIZE;

    private final static int FPS_SET = 120;
    private final static int UPS_SET = 200;
    private final static double NANO_SECOND = 1000000000.0;

    private final GamePanel gamePanel;
    private final GameWindow gameWindow;

    public Game() {
        gamePanel = new GamePanel(this::render, new Dimension(PANEL_WIDTH, PANEL_HEIGHT));
        gameWindow = new GameWindow(gamePanel);
    }

    @Override
    public void run() {
        double nanoTimePerFrame = NANO_SECOND / FPS_SET;
        double nanoTimePerUpdate = NANO_SECOND / UPS_SET;
        double deltaFrame = 0;
        double deltaUpdate = 0;
        int frames = 0;
        int updates = 0;
        long previousTime = System.nanoTime();
        long lastCheck = System.currentTimeMillis();

        while (true) {
            long currentTime = System.nanoTime();
            double deltaTime = currentTime - previousTime;

            deltaFrame += deltaTime / nanoTimePerFrame;
            deltaUpdate += deltaTime / nanoTimePerUpdate;

            previousTime = currentTime;

            if (deltaUpdate >= 1) {
                update();

                updates++;
                deltaUpdate--;
            }

            if (deltaFrame >= 1) {
                gamePanel.repaint();

                frames++;
                deltaFrame--;
            }

            if (System.currentTimeMillis() - lastCheck >= 1000) {
                lastCheck = System.currentTimeMillis();

                System.out.println("FPS: " + frames + " | UPS: " + updates);

                frames = 0;
                updates = 0;
            }
        }
    }

    private void update() {

    }

    private void render(Graphics graphics) {

    }
}
