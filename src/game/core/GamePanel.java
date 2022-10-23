package game.core;

import util.size.Size;

import javax.swing.JPanel;
import java.awt.Dimension;
import java.awt.Graphics;

public class GamePanel extends JPanel {
    private GameRender gameRender;

    public GamePanel() {
        setPanelSize();
    }

    public void addRenderListener(GameRender gameRender) {
        this.gameRender = gameRender;
    }

    @Override
    protected void paintComponent(Graphics graphics) {
        super.paintComponent(graphics);

        if (gameRender == null) {
            return;
        }

        gameRender.render(graphics);
    }

    private void setPanelSize() {
        setPreferredSize(new Dimension(Size.GAME_WIDTH, Size.GAME_HEIGHT));
        System.out.println("Game panel size: " + Size.GAME_WIDTH + ":" + Size.GAME_HEIGHT);
    }
}
