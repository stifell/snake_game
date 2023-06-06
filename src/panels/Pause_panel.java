package panels;

import extra.Listeners;
import game.*;
import extra.Resources;
import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;

public class Pause_panel extends JPanel {
    private Main main;
    private Game game;
    private JButton countinue;
    private JButton restart;
    private JButton exit;
    public Pause_panel(Main main, Game game){
        this.main = main;
        this.game = game;
        setBounds(0,0,Main.WIDTH,Main.HEIGTH);
        setLayout(null);
        setFocusable(true);

        countinue = new JButton();
        countinue.setIcon(Resources.ICON_CONTINUE);
        countinue.setBounds((getWidth()-390)/2,getHeight()/4,390,120);
        countinue.setRolloverEnabled(false);
        Border no_border = BorderFactory.createEmptyBorder();
        countinue.setBorder(no_border);
        countinue.setRolloverIcon(Resources.ICON_CONTINUE_DIRECT);

        restart = new JButton();
        restart.setIcon(Resources.ICON_RESTART);
        restart.setBounds((getWidth()-390)/2,(getHeight()+240)/3,390,120);
        restart.setRolloverEnabled(false);
        restart.setBorder(no_border);
        restart.setRolloverIcon(Resources.ICON_RESTART_DIRECT);

        exit = new JButton();
        exit.setBounds((getWidth()-390)/2,(getHeight()+180)/2,390,120 );
        exit.setRolloverEnabled(false); // отключение эффетка подсветки при наведении
        exit.setBorder(no_border);
        exit.setIcon(Resources.ICON_EXIT);
        exit.setRolloverIcon(Resources.ICON_EXIT_DIRECT);

        add(exit);
        add(restart);
        add(countinue);
        countinue.addActionListener(Listeners.ContinueButtonListener(main,game));
        restart.addActionListener(Listeners.RestartButtonListener(main));
        exit.addActionListener(Listeners.ExitButtonListener());
    }
    protected void paintComponent(Graphics g){
        super.paintComponent(g);
        g.drawImage(Resources.BACKROUND,0,0,getWidth(),getHeight(),null);
        String message = "Snake menu";
        g.setColor(Color.white);
        g.setFont(Resources.FONT_SECOND); // шрифт
        int message_wight = g.getFontMetrics().stringWidth(message); // ширина текста
        g.drawString(message,(getWidth()-message_wight)/2,getHeight()/6); // по середине
    }
}