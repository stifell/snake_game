import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

public class Pause_panel extends JPanel {
    private Game game;
    private Main main;
    private JButton countinue;
    private JButton restart;

    Pause_panel(Game game, Main main){
        this.game = game;
        this.main = main;
        setBounds(0,0,Main.WIDTH,Main.HEIGTH);
        setLayout(null);
        setFocusable(true);

        countinue = new JButton();
        countinue.setIcon(Resources.ICON_CONTINUE);
        countinue.setBounds((getWidth()-390)/2,getHeight()/3,390,120);
        countinue.setRolloverEnabled(false);
        Border no_border = BorderFactory.createEmptyBorder();
        countinue.setBorder(no_border);

        restart = new JButton();
        restart.setIcon(Resources.ICON_RESTART);
        restart.setBounds((getWidth()-390)/2,getHeight()/2,390,120);
        restart.setRolloverEnabled(false);
        restart.setBorder(no_border);

        add(restart);
        add(countinue);
        add(Home_panel.exit);
        countinue.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                main.pause_exit();
                game.timer.start();
            }
        });
        countinue.addMouseListener(new MouseListener() {
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
                countinue.setIcon(Resources.ICON_CONTINUE_DIRECT);
            }
            @Override
            public void mouseExited(MouseEvent e) {
                countinue.setIcon(Resources.ICON_CONTINUE);
            }
        });
        restart.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                main.startGame();
                main.pause_exit();
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
    }
    protected void paintComponent(Graphics g){
        super.paintComponent(g);
        g.drawImage(Resources.BACKROUND,0,0,getWidth(),getHeight(),null);
        String message = "Snake menu";
        g.setColor(Color.white);
        g.setFont(Resources.FONT_SECOND); // шрифт
        int message_wight = g.getFontMetrics().stringWidth(message); // ширина текста
        g.drawString(message,(getWidth()-message_wight)/2,getHeight()/4); // по середине
    }
}