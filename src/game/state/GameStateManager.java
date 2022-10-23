package game.state;

import util.state.State;

import java.util.HashMap;

public class GameStateManager {
    private final HashMap<GameStateType, State> statesMap = new HashMap<GameStateType, State>();

    private GameStateType currentStateType = GameStateType.MENU;

    public GameStateManager() {
        initStates();
    }

    public State getCurrentState() {
        return this.statesMap.get(currentStateType);
    }

    public GameStateType getCurrentStateType() {
        return currentStateType;
    }

    public void setCurrentStateType(GameStateType gameStateType) {
        currentStateType = gameStateType;
    }

    private void initStates() {
        statesMap.put(GameStateType.PLAYING, new GamePlayingState());
        statesMap.put(GameStateType.MENU, new GameMenuState());
    }
}
