import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class Controller implements KeyListener {
    Game game;
    Controller(Game game){
        this.game = game;
    }

    @Override
    public void keyTyped(KeyEvent e) {

    }

    @Override
    public void keyPressed(KeyEvent e) {
        switch (e.getKeyCode()){
            case KeyEvent.VK_W : // константы для клавиш
                game.setDirection(Direction.UP);
                break;
            case KeyEvent.VK_S :
                game.setDirection(Direction.DOWN);
                break;
            case KeyEvent.VK_A :
                game.setDirection(Direction.LEFT);
                break;
            case KeyEvent.VK_D :
                game.setDirection(Direction.RIGHT);
                break;
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {

    }
}