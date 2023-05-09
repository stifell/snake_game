import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.Random;

public class GameFields extends JPanel implements ActionListener {

    static int screen_width = 800;
    static int screen_height = 800;
    static int unit_size = 25;
    static int game_units = (screen_width*screen_height)/unit_size;
    final int[] x = new int[game_units];
    final int[] y = new int[game_units];
    int bodyParts = 3;
    int appleEaten;
    int appleX;
    int appleY;
    char direction = 'D';
    boolean running = false;
    Random random;
    Timer timer;
    GameFields(){
        random = new Random();
        this.setPreferredSize(new Dimension(screen_width,screen_height));
        this.setBackground(Color.black);
        this.setFocusable(true);
        this.addKeyListener(new MyKeyListener());
        start_game();
    }

    public  void start_game(){
        newApple();
        running = true;
        timer = new Timer(100,this);
        timer.start();
    }
    public void paintComponent (Graphics g) {
        super.paintComponent(g);
        draw(g);
    }
    public void draw(Graphics g){
        if(running){
            //рисуем клетки
            for(int i = 0;i<screen_height/unit_size;i++){
                g.drawLine(i*unit_size,0,i*unit_size,screen_height);
                g.drawLine(0,i*unit_size,screen_width,i*unit_size);
            }
            //отображение яблока
            g.setColor(Color.red);
            g.fillOval(appleX,appleY,unit_size,unit_size);
            //Цвет головы и туловища змеи
            for(int i =0;i<bodyParts;i++){
                if(i==0){
                    g.setColor(Color.red);
                }else {
                    g.setColor(Color.green);

                }
                g.fillRect(x[i],y[i],unit_size,unit_size);
                //счет
                g.setColor(Color.red);
                g.setFont(new Font("Ink Free",Font.BOLD,40));
                FontMetrics metrics = getFontMetrics(g.getFont());
                g.drawString("Score: "+appleEaten,(screen_width - metrics.stringWidth("Score: "+appleEaten))/2,g.getFont().getSize());
            }
        }else {
            gameOver(g);
        }

    }
    public void newApple(){
        appleX = random.nextInt( screen_width/unit_size)*unit_size;
        appleY = random.nextInt(screen_height/unit_size)*unit_size;
    }
    public void move(){
        for (int i = bodyParts; i > 0;i--){
            x[i] = x[i-1];
            y[i] = y[i-1];
        }
        switch (direction){
            case 'U':
                y[0] = y[0] - unit_size;
                break;
            case 'D':
                y[0] = y[0] + unit_size;
                break;
            case 'L':
                x[0] = x[0] - unit_size;
                break;
            case 'R':
                x[0] = x[0] + unit_size;
                break;
        }
    }
    public void checkApple(){
        if ((x[0] == appleX) && (y[0] == appleY)){
            bodyParts+=1;
            appleEaten+=1;
            newApple();
        }
    }
    public void  checkCollision(){
        for(int i =bodyParts;i>0;i--){
            if ((x[0] == x[i]) && (y[0] == y[i])){
                running = false;
            }
        }
        if (x[0] < 0 || x[0] > screen_width || y[0] > screen_height || y[0] < 0 ){
            running = false;
        }
        if (!running){
            timer.stop();
        }
    }
    public void gameOver(Graphics g){
        g.setColor(Color.red);
        g.setFont(new Font("ink free",Font.BOLD,40));
        FontMetrics metrics = getFontMetrics(g.getFont());
        g.drawString("Score: "+appleEaten, (screen_width - metrics.stringWidth("Score: "+appleEaten))/2, g.getFont().getSize());
        g.setColor(Color.red);
        g.setFont(new Font("ink free",Font.BOLD,75));
        FontMetrics metrics1 = getFontMetrics(g.getFont());
        g.drawString("Game Over",(screen_width - metrics.stringWidth("Game Over"))/2,screen_height/2);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (running){
            move();
            checkApple();
            checkCollision();
        }
        repaint();

    }
    public class MyKeyListener implements KeyListener{
        @Override
        public void keyTyped(KeyEvent e) {

        }

        @Override
        public void keyPressed(KeyEvent e){
            switch (e.getKeyCode()){
                case KeyEvent.VK_LEFT:
                    if(direction != 'R'){
                        direction = 'L';
                    }
                    break;
                case KeyEvent.VK_RIGHT:
                    if(direction != 'L'){
                        direction = 'R';
                    }
                    break;
                case KeyEvent.VK_UP:
                    if(direction != 'D'){
                        direction = 'U';
                    }
                    break;
                case KeyEvent.VK_DOWN:
                    if(direction != 'U'){
                        direction = 'D';
                    }
                    break;
            }
        }
        @Override
        public void keyReleased(KeyEvent e) {

        }
    }

}
