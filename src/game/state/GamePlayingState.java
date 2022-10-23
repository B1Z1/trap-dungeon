package game.state;

import entity.player.Player;
import util.direction.Direction;
import util.size.Size;
import util.state.State;

import java.awt.Graphics;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;
import java.awt.event.WindowEvent;

public class GamePlayingState extends State {
    private Player player;

    public GamePlayingState() {
        initClasses();
    }

    @Override
    public void update() {
        player.update();
    }

    @Override
    public void render(Graphics graphics) {
        player.render(graphics);
    }

    @Override
    public void keyTyped(KeyEvent e) {

    }

    @Override
    public void keyPressed(KeyEvent e) {
        switch (e.getKeyCode()) {
            case KeyEvent.VK_D -> player.setDirection(Direction.RIGHT, true);
            case KeyEvent.VK_A -> player.setDirection(Direction.LEFT, true);
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {
        switch (e.getKeyCode()) {
            case KeyEvent.VK_D -> player.setDirection(Direction.RIGHT, false);
            case KeyEvent.VK_A -> player.setDirection(Direction.LEFT, false);
        }
    }

    @Override
    public void mouseClicked(MouseEvent e) {

    }

    @Override
    public void mousePressed(MouseEvent e) {

    }

    @Override
    public void mouseReleased(MouseEvent e) {

    }

    @Override
    public void mouseEntered(MouseEvent e) {

    }

    @Override
    public void mouseExited(MouseEvent e) {

    }

    @Override
    public void mouseDragged(MouseEvent e) {

    }

    @Override
    public void mouseMoved(MouseEvent e) {

    }

    @Override
    public void windowGainedFocus(WindowEvent e) {

    }

    @Override
    public void windowLostFocus(WindowEvent e) {
        player.resetDirections();
    }

    private void initClasses() {
        player = new Player(
                200,
                200,
                (int) (32 * Size.TILES_DEFAULT_SCALE),
                (int) (32 * Size.TILES_DEFAULT_SCALE)
        );
    }
}
