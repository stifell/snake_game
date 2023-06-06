package panels;

import game.*;
import extra.*;
import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;

public class Home_panel extends JPanel{
    private Main main;
    private JButton play;
    private JButton exit;
    public Home_panel(Main main){
        this.main = main;
        setBounds(0,0,Main.WIDTH,Main.HEIGTH);
        setLayout(null); // отключаем менеджер компоновки
        setFocusable(true);

        Border no_border = BorderFactory.createEmptyBorder(); // убираем синие границы картинки
        play = new JButton();
        play.setIcon(Resources.ICON_PLAY);
        play.setBounds((getWidth()-390)/2,getHeight()/3,390,120);
        play.setRolloverEnabled(false); // отключение эффетка подсветки при наведении
        play.setBorder(no_border);
        play.setRolloverIcon(Resources.ICON_PLAY_DIRECT);
        add(play);

        exit = new JButton();
        exit.setBounds((getWidth()-390)/2,getHeight()/2,390,120 );
        exit.setRolloverEnabled(false);
        exit.setBorder(no_border);
        exit.setIcon(Resources.ICON_EXIT);
        exit.setRolloverIcon(Resources.ICON_EXIT_DIRECT);
        add(exit);

        play.addActionListener(Listeners.PlayButtonListener(main));
        exit.addActionListener(Listeners.ExitButtonListener());
    }

    protected void paintComponent(Graphics g){
        super.paintComponent(g);
        g.drawImage(Resources.BACKROUND,0,0,getWidth(),getHeight(),null);
        String message = "Welcome to the snake!";
        g.setColor(Color.white);
        g.setFont(Resources.FONT_SECOND); // шрифт
        int message_wight = g.getFontMetrics().stringWidth(message); // ширина текста
        g.drawString(message,(getWidth()-message_wight)/2,getHeight()/4); // по середине
    }
}