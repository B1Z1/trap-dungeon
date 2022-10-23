package game.listeners;

import game.state.GameStateManager;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

public class GameMouseListener implements MouseListener {
    private final GameStateManager gameStateManager;

    public GameMouseListener(GameStateManager gameStateManager) {
        this.gameStateManager = gameStateManager;
    }

    @Override
    public void mouseClicked(MouseEvent e) {
        gameStateManager.getCurrentState().mouseClicked(e);
    }

    @Override
    public void mousePressed(MouseEvent e) {
        gameStateManager.getCurrentState().mousePressed(e);
    }

    @Override
    public void mouseReleased(MouseEvent e) {
        gameStateManager.getCurrentState().mouseReleased(e);
    }

    @Override
    public void mouseEntered(MouseEvent e) {
        gameStateManager.getCurrentState().mouseEntered(e);
    }

    @Override
    public void mouseExited(MouseEvent e) {
        gameStateManager.getCurrentState().mouseExited(e);
    }
}
