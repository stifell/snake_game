import javax.imageio.ImageIO;
import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class Main extends JFrame {
    static final int WIDTH = Toolkit.getDefaultToolkit().getScreenSize().width;
    static final int HEIGTH = Toolkit.getDefaultToolkit().getScreenSize().height;
    // static int score = 0;
    static JLabel label;
//    BufferedImage play;

    Main(){
        super("Snake Game");
        setDefaultCloseOperation(3); // закрытие завершает код
        setSize(WIDTH,HEIGTH); // размер окна
        setLayout(null);
        //setResizable(false); // не меняем размер
        JPanel panel = new JPanel(){protected void paintComponent(Graphics g) {
            super.paintComponent(g);
            ImageIcon imageIcon = new ImageIcon("фон.jpg"); // путь к изображению
            Image image = imageIcon.getImage();
            g.drawImage(image, 0, 0, getWidth(), getHeight(), this);
            String message = "Welcome to the snake!";
            g.setColor(Color.white);
            g.setFont(Fonts.FONT_SECOND); // шрифт
            int message_wight = g.getFontMetrics().stringWidth(message); // ширина текста
            g.drawString(message,(getWidth()-message_wight)/2,getHeight()/4); // по середине
        }};
        panel.setBounds(0,0,WIDTH,HEIGTH);
        panel.setLayout(null);
        setExtendedState(JFrame.MAXIMIZED_BOTH); // размер на весь экран
        setUndecorated(true); // полноэкранный режим
        add(panel);
        ImageIcon icon = new ImageIcon("knopka.png");
        ImageIcon icon_navod = new ImageIcon("knopka-navod.png");
        JButton play = new JButton();
        play.setIcon(icon);
        play.setBounds((getWidth()-390)/2,getHeight()/3,390,120);
        play.setRolloverEnabled(false);
        Border emptyBorder = BorderFactory.createEmptyBorder();
        play.setBorder(emptyBorder);
        panel.add(play,BorderLayout.CENTER);
        play.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                label = new JLabel(); // счетик
                label.setForeground(Color.WHITE);
                label.setFont(Fonts.FONT_FIRST);
                label.setBounds(68,15,100,50);
                label.setText("Score: " + 0);
                getContentPane().setBackground(new Color(32,142,81));
                add(label);
                add(new Game());
                panel.setVisible(false);
            }
        });
        ImageIcon icon1 = new ImageIcon("vykhod.png");
        ImageIcon vykhod_navod = new ImageIcon("vykhod-navod.png");
        JButton exit = new JButton();
        exit.setBounds((getWidth()-390)/2,getHeight()/2,390,120 );
        exit.setRolloverEnabled(false);
        Border emptyBorder1 = BorderFactory.createEmptyBorder();
        exit.setBorder(emptyBorder1);
        exit.setIcon(icon1);
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
                play.setIcon(icon_navod);


            }

            @Override
            public void mouseExited(MouseEvent e) {
                play.setIcon(icon);

            }
        });
        panel.add(exit,BorderLayout.EAST);
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
                exit.setIcon(vykhod_navod);

            }

            @Override
            public void mouseExited(MouseEvent e) {
                exit.setIcon(icon1);

            }
        });
        


        setVisible(true); // видимость окна
    }
    public static void main(String[] args) {
        new Main();
    }

}