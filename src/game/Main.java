package game;

import panels.*;
import extra.*;
import javax.swing.*;
import java.awt.*;

public class Main extends JFrame {
    public static final int WIDTH = Toolkit.getDefaultToolkit().getScreenSize().width;
    public static final int HEIGTH = Toolkit.getDefaultToolkit().getScreenSize().height;
    private JLabel label; // счетчик
    private Home_panel home; // панелька
    private Game game;
    private Pause_panel pause;
    private Game_over_panel game_over;

    Main(){
        super("Snake Game");
        setDefaultCloseOperation(3); // закрытие завершает код
        setSize(WIDTH,HEIGTH); // размер окна
        setExtendedState(JFrame.MAXIMIZED_BOTH); // размер на весь экран
        setLayout(null); // отключаем менеджер компоновки
        setUndecorated(true); // полноэкранный режим
        getContentPane().setBackground(new Color(32,142,81));

        home = new Home_panel(this); // начальная панелька
        add(home);

        label = new JLabel(); // счетик
        label.setForeground(Color.WHITE);
        label.setFont(Resources.FONT_FIRST);
        label.setBounds(68,15,200,50);
        label.setText("Score: 0000");
        add(label);

        setVisible(true); // видимость окна
    }
    public void update_score(int score){
        String message_score = String.format("Score: %04d",score);
        label.setText(message_score);
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
        pause = new Pause_panel(this,game); // панелька паузы
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
        update_score(game.score); // прошлый счет при рестарте сохраняется, обновляем
        game.requestFocus(); // фокусируем игру
        revalidate();
        repaint();
    }
    public void game_over_start(){
        game_over = new Game_over_panel(this,game); // панелька game over
        game.setVisible(false); // убираем видимость
        label.setVisible(false);
        this.add(game_over); // добавляем в окно
        revalidate();
        repaint();
    }
    public void game_over_exit(){
        this.remove(game_over); // убираем панельку
        game.setVisible(true);
        label.setVisible(true);
        update_score(game.score); // прошлый счет при рестарте сохраняется, обновляем
        game.requestFocus(); // фокусируем игру
        revalidate();
        repaint();
    }
    public static void main(String[] args) {
        new Main();
    }
}