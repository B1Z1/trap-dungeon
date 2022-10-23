package game.listeners;

import game.state.GameStateManager;

import java.awt.event.WindowEvent;
import java.awt.event.WindowFocusListener;

public class GameWindowFocusListener implements WindowFocusListener {
    private final GameStateManager gameStateManager;

    public GameWindowFocusListener(GameStateManager gameStateManager) {
        this.gameStateManager = gameStateManager;
    }


    @Override
    public void windowGainedFocus(WindowEvent e) {
        gameStateManager.getCurrentState().windowGainedFocus(e);
    }

    @Override
    public void windowLostFocus(WindowEvent e) {
        gameStateManager.getCurrentState().windowLostFocus(e);
    }
}
