import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;


public class Controller implements KeyListener {
    private Game game;



    Controller(Game game){
        this.game = game;
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
                    break;
            }
        }
        else if (key == KeyEvent.VK_ESCAPE)
            game.timer.start();
    }




    @Override
    public void keyReleased(KeyEvent e) {
    }
}