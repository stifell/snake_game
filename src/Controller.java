import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;


public class Controller implements KeyListener {
    private Game game;
    private Main main;

    Controller(Game game, Main main){
        this.game = game;
        this.main = main;
    }

    @Override
    public void keyTyped(KeyEvent e) {
    }
    @Override
    public void keyPressed(KeyEvent e) {
        int key = e.getKeyCode();
        if (game.timer.isRunning()) { // чтобы при паузе нельзя было менять направление
            switch (key) {
                case KeyEvent.VK_W: // константы для клавиш
                    game.setDirection(Direction.UP);
                    break;
                case KeyEvent.VK_S:
                    game.setDirection(Direction.DOWN);
                    break;
                case KeyEvent.VK_A:
                    game.setDirection(Direction.LEFT);
                    break;
                case KeyEvent.VK_D:
                    game.setDirection(Direction.RIGHT);
                    break;
                case KeyEvent.VK_ESCAPE:
                    game.timer.stop();
                    main.pause_start();
                    break;
            }
        }
        else if (key == KeyEvent.VK_ESCAPE) {
            main.pause_exit();
            game.timer.start();
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {
    }
}