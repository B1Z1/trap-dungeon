package util.size;

/**
 * Zestaw parametrów globalnych
 */
public class Size {
    public final static int VISIBLE_TILES_IN_WIDTH = 26;
    public final static int VISIBLE_TILES_IN_HEIGHT = 14;
    public final static int TILES_DEFAULT_SIZE = 32;
    public final static float TILES_DEFAULT_SCALE = 2f;
    public final static int TILES_SIZE = (int) (TILES_DEFAULT_SIZE * TILES_DEFAULT_SCALE);

    public final static int GAME_WIDTH = VISIBLE_TILES_IN_WIDTH * TILES_SIZE;
    public final static int GAME_HEIGHT = VISIBLE_TILES_IN_HEIGHT * TILES_SIZE;
}
