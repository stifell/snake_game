import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

public class Home_panel extends JPanel{
    private JButton play;
    private JButton exit;
    Home_panel(){
        setBounds(0,0,Main.WIDTH,Main.HEIGTH);
        setLayout(null); // отключаем менеджер компоновки
        setFocusable(true);

        play = new JButton();
        play.setIcon(Resources.ICON_PLAY);
        play.setBounds((getWidth()-390)/2,getHeight()/3,390,120);
        play.setRolloverEnabled(false); // отключение эффетка подсветки при наведении
        Border no_border = BorderFactory.createEmptyBorder(); // убираем синие границы картинки
        play.setBorder(no_border);
        add(play);

        exit = new JButton();
        exit.setBounds((getWidth()-390)/2,getHeight()/2,390,120 );
        exit.setRolloverEnabled(false);
        exit.setBorder(no_border);
        exit.setIcon(Resources.ICON_EXIT);
        add(exit);

        play.addMouseListener(new MouseListener() {
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
                play.setIcon(Resources.ICON_PLAY_DIRECT);
            }
            @Override
            public void mouseExited(MouseEvent e) {
                play.setIcon(Resources.ICON_PLAY);
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

    public JButton getPlayButton() {
        return play;
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