package game.state;

import util.state.State;

import java.util.HashMap;

/**
 * Klasa odpowiedzialna za stan gry. Tutaj znajduje się
 * logika przełączaniem się pomiędzy rzeczywistą grą a innymi oknami
 */
public class GameStateManager {
    private final HashMap<GameStateType, State> statesMap = new HashMap<GameStateType, State>();

    private GameStateType currentStateType = GameStateType.PLAYING;

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
