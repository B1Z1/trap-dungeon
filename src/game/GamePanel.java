package game;

import javax.swing.JPanel;
import java.awt.Dimension;
import java.awt.Graphics;

public class GamePanel extends JPanel {
    private final Dimension size;
    private final GameRender gameRepaint;

    public GamePanel(
            GameRender gameRepaint,
            Dimension size
    ) {
        this.gameRepaint = gameRepaint;
        this.size = size;

        setPanelSize();
    }

    @Override
    protected void paintComponent(Graphics graphics) {
        super.paintComponent(graphics);
        gameRepaint.render(graphics);
    }

    private void setPanelSize() {
        setPreferredSize(size);
        System.out.println("Game panel size: " + size.getWidth() + ":" + size.getHeight());
    }
}
