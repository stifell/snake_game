import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

public class Game_over_panel extends JPanel {
    private Main main;
    private Game game;
    private JButton restart;
    private JButton exit;

    Game_over_panel(Game game, Main main){
        this.main = main;
        this.game = game;
        setBounds(0,0,Main.WIDTH,Main.HEIGTH);
        setLayout(null);
        setFocusable(true);

        Border no_border = BorderFactory.createEmptyBorder();
        restart = new JButton();
        restart.setIcon(Resources.ICON_RESTART);
        restart.setBounds((getWidth()-390)/2,(getHeight())/3,390,120);
        restart.setRolloverEnabled(false);
        restart.setBorder(no_border);

        exit = new JButton();
        exit.setBounds((getWidth()-390)/2,(getHeight())/2,390,120 );
        exit.setRolloverEnabled(false); // отключение эффетка подсветки при наведении
        exit.setBorder(no_border);
        exit.setIcon(Resources.ICON_EXIT);

        add(exit);
        add(restart);
        restart.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                main.startGame();
                main.game_over_exit();
            }
        });
        restart.addMouseListener(new MouseListener() {
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
                restart.setIcon(Resources.ICON_RESTART_DIRECT);
            }
            @Override
            public void mouseExited(MouseEvent e) {
                restart.setIcon(Resources.ICON_RESTART);
            }
        });
        exit.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.exit(0);
            }
        });
        exit.addMouseListener(new MouseListener() {
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
                exit.setIcon(Resources.ICON_EXIT_DIRECT);
            }
            @Override
            public void mouseExited(MouseEvent e) {
                exit.setIcon(Resources.ICON_EXIT);
            }
        });
    }
    protected void paintComponent(Graphics g){
        super.paintComponent(g);
        g.drawImage(Resources.BACKROUND,0,0,Main.WIDTH,Main.HEIGTH,null);
        String message = "Game over! Score: " + game.score;
        g.setColor(Color.white);
        g.setFont(Resources.FONT_SECOND); // шрифт
        int message_wight = g.getFontMetrics().stringWidth(message); // ширина текста
        g.drawString(message,(getWidth()-message_wight)/2,getHeight()/4);
    }
}