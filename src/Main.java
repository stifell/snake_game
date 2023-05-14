import javax.swing.*;
import java.awt.*;

public class Main extends JFrame {
    static final int WIDTH = Toolkit.getDefaultToolkit().getScreenSize().width;
    static final int HEIGTH = Toolkit.getDefaultToolkit().getScreenSize().height;
    // static int score = 0;
    static JLabel label;
    Main(){
        super("Snake Game");
        setDefaultCloseOperation(3); // закрытие завершает код
        setSize(WIDTH,HEIGTH); // размер окна
        setLayout(null);
        //setResizable(false); // не меняем размер
        setExtendedState(JFrame.MAXIMIZED_BOTH); // размер на весь экран
        setUndecorated(true); // полноэкранный режим
        getContentPane().setBackground(new Color(32,142,81)); // цвет фона

        Game game = new Game(); // поле
        add(game);

        label = new JLabel(); // счетик
        label.setForeground(Color.WHITE);
        label.setFont(Fonts.FONT_FIRST);
        label.setBounds(68,15,100,50);
        label.setText("Score: " + 0);
        add(label);

        setVisible(true); // видимость окна
    }
    public static void main(String[] args) {
        new Main();
    }

    static public void update_score(int score){
        label.setText("Score: " + score);
    } // меняем при каждом добавлении score
}