import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Main extends JFrame {
    static final int WIDTH = Toolkit.getDefaultToolkit().getScreenSize().width;
    static final int HEIGTH = Toolkit.getDefaultToolkit().getScreenSize().height;
    // static int score = 0;
    static JLabel label; // счетчик
    private Home_panel home; // панелька
    private Game game;
    private Pause_panel pause;
//    BufferedImage play;

    Main(){
        super("Snake Game");
        setDefaultCloseOperation(3); // закрытие завершает код
        setSize(WIDTH,HEIGTH); // размер окна
        setExtendedState(JFrame.MAXIMIZED_BOTH); // размер на весь экран
        setLayout(null); // отключаем менеджер компоновки
        setUndecorated(true); // полноэкранный режим
        getContentPane().setBackground(new Color(32,142,81));

        home = new Home_panel();
        add(home);

        home.getPlayButton().addActionListener(new ActionListener() { // считывание нажатия на кнопку play
            @Override
            public void actionPerformed(ActionEvent e) {
                startGame();
            }
        });

        label = new JLabel(); // счетик
        label.setForeground(Color.WHITE);
        label.setFont(Resources.FONT_FIRST);
        label.setBounds(68,15,150,50);
        label.setText("Score: " + 0);
        add(label);

        setVisible(true); // видимость окна
    }
    static public void update_score(int score){
        Main.label.setText("Score: " + score);
    } // меняем при каждом добавлении score
    public void startGame(){
        game = new Game(this);
        this.remove(home);
        this.add(game); // добавляем игру
        game.requestFocus(); // фокусируем игру
        revalidate(); // обновления компоновки (пересчитает размер и расположение компонентов)
        repaint(); // обновления отрисовки компонентов
    }
    public void pause_start(){
        pause = new Pause_panel(game,this); // панелька паузы
        game.setVisible(false); // убираем видимость
        label.setVisible(false);
        this.add(pause); // добавляем в окно
        revalidate();
        repaint();
    }
    public void pause_exit(){
        this.remove(pause); // убираем паузу
        game.setVisible(true); // добавляем видимость
        label.setVisible(true);
        game.requestFocus(); // фокусируем игру
        revalidate();
        repaint();
    }
    public static void main(String[] args) {
        new Main();
    }
}