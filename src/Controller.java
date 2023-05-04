import javax.swing.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class Controller implements KeyListener {
    JLabel label;
    Controller(JLabel label){
        this.label = label;
    }
    @Override
    public void keyTyped(KeyEvent e) { // когда кнопка быстрый клик

    }

    @Override
    public void keyPressed(KeyEvent e) { // когда кнопка нажата
        switch (e.getKeyCode()){
            case KeyEvent.VK_W : // константы для клавиш
                label.setLocation(label.getX(),label.getY()-10);
                break;
            case KeyEvent.VK_S : // константы для клавиш
                label.setLocation(label.getX(),label.getY()+10);
                break;
            case KeyEvent.VK_A : // константы для клавиш
                label.setLocation(label.getX()-10,label.getY());
                break;
            case KeyEvent.VK_D : // константы для клавиш
                label.setLocation(label.getX()+10,label.getY());
                break;
        }
    }

    @Override
    public void keyReleased(KeyEvent e) { // когда кнопка отпущена

    }
}