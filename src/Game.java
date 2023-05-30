import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.concurrent.TimeUnit;

public class Game extends JPanel implements ActionListener {
    private Main main;
    final int SNAKE_SIZE = 25;
    final int APPLE_SIZE = 25;
    public int score;
    private boolean game;
    static List<Point> snake = new ArrayList<Point>();
    static List<Point> apples = new ArrayList<Point>();
    private Random rng = new Random();
    private Direction direction = Direction.RIGHT; // начальное направление движения змейки вправо
    public Timer timer;
    public Thread thread;
    public Flow flow;
    Game(Main main){
        this.main = main;
        setBounds(68,57,Main.WIDTH-136,Main.HEIGTH-114);
        setLayout(null);
        addKeyListener(new Controller(this, main));
        setFocusable(true);
        start();
    }

    void start(){
        game = true;
        score = 0;
        // длинна змеи
        snake.add(new Point(2,0));
        snake.add(new Point(1,0));
        snake.add(new Point(0,0));
//        newApple();
        flow();
        timer = new Timer(90, this);
        timer.start();
    }
    void flow(){
        flow = new Flow(this);
        thread = new Thread(flow);
        thread.start();
    }
    void newApple(){
        while (true){
            Point apple = new Point(rng.nextInt(getWidth()/APPLE_SIZE),rng.nextInt(getHeight()/APPLE_SIZE));
            if (!apples.contains(apple) && !snake.contains(apple)){ // если яблока с такими координатами нет и не на змее
                apples.add(apple);
                break;
            }
        }
    }

    void snake_move() {
        Point head = snake.get(0);
        int dx = head.x, dy = head.y;
        switch (direction){
            case UP: dy -= 1; break;
            case DOWN: dy += 1; break;
            case RIGHT: dx += 1; break;
            case LEFT: dx -= 1; break;

        }
        Point newHead = new Point(dx,dy);
        snake.add(0,newHead);
        // если вышел за границу
        if (newHead.x < 0 || newHead.y < 0 || newHead.x >= getWidth()/SNAKE_SIZE || newHead.y >= getHeight()/SNAKE_SIZE){
            game = false;
            return;
        }
        // если врезался в себя
        if (snake.subList(1,snake.size()).contains(newHead)){
            game = false;
            return;
        }
        // если яблоко увлечиваем счетчик
        if (apples.contains(newHead)){
            score++;
            apples.remove(newHead);
            main.update_score(score); // вывод счетчика
//            newApple();
        }
        else
            snake.remove(snake.size()-1);
    }

    void setDirection(Direction direction) {
        // запрещает изменение направления, если игра закончена
        if (!game) {
            return;
        }
        // запрещает изменение направления на противоположное
        if (this.direction == Direction.UP && direction == Direction.DOWN ||
                this.direction == Direction.DOWN && direction == Direction.UP ||
                this.direction == Direction.LEFT && direction == Direction.RIGHT ||
                this.direction == Direction.RIGHT && direction == Direction.LEFT) {
            return;
        }
        this.direction = direction;
    }

    @Override
    protected void paintComponent(Graphics g){ // рисования графики внутри компонента
        super.paintComponent(g); // настройка графического контекста
        for (int i = 0; i < getHeight()/SNAKE_SIZE; i++){
            for (int j = 0; j < getWidth()/SNAKE_SIZE; j++){
                if (i % 2 == 0) {
                    if (j % 2 == 0)
                        g.setColor(new Color(170, 215, 81));
                    else
                        g.setColor(new Color(162, 209, 73));
                }
                else {
                    if (j % 2 == 0)
                        g.setColor(new Color(162, 209, 73));
                    else
                        g.setColor(new Color(170, 215, 81));
                }
                g.fillRect(j*SNAKE_SIZE,i*SNAKE_SIZE,SNAKE_SIZE,SNAKE_SIZE);
            }
        }
        if (!game){
            main.game_over_start();
            return;
        }
        g.setColor(new Color(62,107,221)); // тело
        for (Point point: snake){
            g.fillRect(point.x*SNAKE_SIZE,point.y*SNAKE_SIZE,SNAKE_SIZE,SNAKE_SIZE); // отрисовка

        }
        g.setColor(Color.RED);
        for (Point point: apples){
            g.drawImage(Resources.APPLE_IMAGE,point.x*APPLE_SIZE,point.y*APPLE_SIZE,APPLE_SIZE,APPLE_SIZE,null);
        }
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        snake_move();
        repaint();
    }
}