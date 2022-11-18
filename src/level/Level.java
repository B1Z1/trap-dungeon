package level;

public class Level {
    private final int[][] data;

    public Level(
            int[][] data
    ) {
        this.data = data;
    }

    public int getSpriteIndex(int x, int y) {
        return data[y][x];
    }

    public int[][] getData() {
        return data;
    }
}
