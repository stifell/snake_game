package panels;

import game.*;
import extra.*;
import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;

public class Game_over_panel extends JPanel {
    private Main main;
    private Game game;
    private JButton restart;
    private JButton exit;

    public Game_over_panel(Main main,Game game){
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
        restart.setRolloverIcon(Resources.ICON_RESTART_DIRECT);

        exit = new JButton();
        exit.setBounds((getWidth()-390)/2,(getHeight())/2,390,120 );
        exit.setRolloverEnabled(false); // отключение эффетка подсветки при наведении
        exit.setBorder(no_border);
        exit.setIcon(Resources.ICON_EXIT);
        exit.setRolloverIcon(Resources.ICON_EXIT_DIRECT);

        add(exit);
        add(restart);
        restart.addActionListener(Listeners.RestartGameOverButtonListener(main));
        exit.addActionListener(Listeners.ExitButtonListener());
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