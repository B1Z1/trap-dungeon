package game.core;

import javax.swing.JFrame;

public class GameWindow extends JFrame {
    public GameWindow(
            GamePanel gamePanel
    ) {
        super();

        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        add(gamePanel);
        setLocationRelativeTo(null);
        setResizable(false);
        pack();
        setVisible(true);
    }
}
