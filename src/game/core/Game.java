package game.core;

import game.listeners.GameKeyListener;
import game.listeners.GameMouseListener;
import game.state.GameStateManager;

import java.awt.Graphics;

public class Game implements Runnable {


    private final static int FPS_SET = 120;
    private final static int UPS_SET = 200;
    private final static double NANO_SECOND = 1000000000.0;

    private final GamePanel gamePanel;
    private final GameWindow gameWindow;

    private final GameStateManager gameStateManager;

    private final GameKeyListener gameKeyListener;
    private final GameMouseListener gameMouseListener;

    public Game() {
        gameStateManager = new GameStateManager();

        gamePanel = new GamePanel();
        gameWindow = new GameWindow(gamePanel);

        gameKeyListener = new GameKeyListener(gameStateManager);
        gameMouseListener = new GameMouseListener(gameStateManager);

        gamePanel.addRenderListener(this::render);
        gamePanel.addKeyListener(gameKeyListener);
        gamePanel.addMouseListener(gameMouseListener);
        gamePanel.requestFocus();
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
        gameStateManager.getCurrentState().update();
    }

    private void render(Graphics graphics) {
        gameStateManager.getCurrentState().render(graphics);
    }
}
