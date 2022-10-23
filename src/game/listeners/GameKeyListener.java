package game.listeners;

import game.state.GameStateManager;
import game.state.GameStateType;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class GameKeyListener implements KeyListener {
    private final GameStateManager gameStateManager;

    public GameKeyListener(GameStateManager gameStateManager) {
        this.gameStateManager = gameStateManager;
    }

    @Override
    public void keyTyped(KeyEvent e) {
        gameStateManager.getCurrentState().keyTyped(e);
    }

    @Override
    public void keyPressed(KeyEvent e) {
        gameStateManager.getCurrentState().keyPressed(e);

        if (e.getKeyCode() == KeyEvent.VK_ESCAPE) {
            GameStateType gameStateType = gameStateManager.getCurrentStateType();

            switch (gameStateType) {
                case PLAYING -> gameStateManager.setCurrentStateType(GameStateType.MENU);
                case MENU -> gameStateManager.setCurrentStateType(GameStateType.PLAYING);
            }
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {
        gameStateManager.getCurrentState().keyReleased(e);
    }
}
