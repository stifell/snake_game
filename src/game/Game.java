package game;

import ai.AI;
import extra.*;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.geom.AffineTransform;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Game extends JPanel implements ActionListener {
    private final Main main;
    final int SNAKE_SIZE = 25;
    final int APPLE_SIZE = 25;
    public int score = 0;
    private boolean game;
    private BufferedImage game_panel;
    private Point apple = new Point();
    //public Point apple_flow = new Point();
    private final List<Point> snake = new ArrayList<>();
    private final List<Point> change_point = new ArrayList<>();
    private final List<Direction> directionList = new ArrayList<>();
    private final List<Direction> change_direction = new ArrayList<>();
    private final Random rng = new Random();
    private static Direction direction = Direction.RIGHT; // начальное направление движения змейки вправо
    private Direction check_direction;
    public Timer timer;
    Direction dir;
    //public Thread thread;
    AI ai;
    Game(Main main){
        this.main = main;
        setBounds(68,57,Main.WIDTH-136,Main.HEIGTH-114);
        setLayout(null);
        addKeyListener(new Controller(this, main));
        setFocusable(true);
        start();
    }

    private void start(){
        //0.5922389f, 2.5494354f, 1.9609364f, 4.116872f, 1.9056655f, 1.9131424f
        ai = new AI(new float[]{0.5922389f, 2.5494354f, 1.9609364f, 4.116872f, 1.9056655f, 1.9131424f});
        score = 0;
        game = true;
        Game_panel();
        // длинна змеи
        snake.add(new Point(2,0));
        snake.add(new Point(1,0));
        snake.add(new Point(0,0));
        apple = newApple();
        for (int i = 0; i < snake.size(); i++)
            directionList.add(direction);
        change_point.add(snake.get(0));
        change_direction.add(directionList.get(0));
//        thread = new Thread(new Flow(this));
//        thread.start();
        timer = new Timer(10, this);
        timer.start();
    }

    public Point newApple(){
        Point newapple;
        while (true){
            newapple = new Point(rng.nextInt(getWidth()/APPLE_SIZE),rng.nextInt(getHeight()/APPLE_SIZE));
            if (!snake.contains(newapple) && !apple.equals(newapple)){ // если яблока с такими координатами не на змее
                return newapple;
            }
        }
    }

    private float obstacle_top(Direction direction){
        float size = 0;
        switch (direction){
            case RIGHT:
                for (int i = snake.get(0).x + 1; i < 56; i++) {
                    if (snake.contains(new Point(i, snake.get(0).y)))
                        return size;
                    size++;
                }
                break;
            case LEFT:
                for (int i = snake.get(0).x - 1; i >= 0; i--) {
                    if (snake.contains(new Point(i, snake.get(0).y)))
                        return size;
                    size++;
                }
                break;
            case UP:
                for (int i = snake.get(0).y - 1; i >= 0; i--) {
                    if (snake.contains(new Point(snake.get(0).x, i)))
                        return size;
                    size++;
                }
                break;
            case DOWN:
                for (int i = snake.get(0).y + 1; i < 30; i++) {
                    if (snake.contains(new Point(snake.get(0).x, i)))
                        return size;
                    size++;
                }
                break;
        }
        return size;
    }
    private float obstacle_left(Direction direction){
        float size = 0;
        switch (direction){
            case DOWN:
                for (int i = snake.get(0).x + 1; i < 56; i++) {
                    if (snake.contains(new Point(i, snake.get(0).y)))
                        return size;
                    size++;
                }
                break;
            case UP:
                for (int i = snake.get(0).x - 1; i >= 0; i--) {
                    if (snake.contains(new Point(i, snake.get(0).y)))
                        return size;
                    size++;
                }
                break;
            case RIGHT:
                for (int i = snake.get(0).y - 1; i >= 0; i--) {
                    if (snake.contains(new Point(snake.get(0).x, i)))
                        return size;
                    size++;
                }
                break;
            case LEFT:
                for (int i = snake.get(0).y + 1; i < 30; i++) {
                    if (snake.contains(new Point(snake.get(0).x, i)))
                        return size;
                    size++;
                }
                break;
        }
        return size;
    }
    private float obstacle_right(Direction direction){
        float size = 0;
        switch (direction){
            case UP:
                for (int i = snake.get(0).x + 1; i < 56; i++) {
                    if (snake.contains(new Point(i, snake.get(0).y)))
                        return size;
                    size++;
                }
                break;
            case DOWN:
                for (int i = snake.get(0).x - 1; i >= 0; i--) {
                    if (snake.contains(new Point(i, snake.get(0).y)))
                        return size;
                    size++;
                }
                break;
            case LEFT:
                for (int i = snake.get(0).y - 1; i >= 0; i--) {
                    if (snake.contains(new Point(snake.get(0).x, i)))
                        return size;
                    size++;
                }
                break;
            case RIGHT:
                for (int i = snake.get(0).y + 1; i < 30; i++) {
                    if (snake.contains(new Point(snake.get(0).x, i)))
                        return size;
                    size++;
                }
                break;
        }
        return size;
    }

    private Direction vary(Direction direction, Direction dir){
        switch (direction) {
            case UP:
                switch (dir) {
                    case LEFT:
                        return Direction.LEFT;
                    case RIGHT:
                        return Direction.RIGHT;
                }
                break;
            case DOWN:
                switch (dir){
                    case LEFT:
                        return Direction.RIGHT;
                    case RIGHT:
                        return Direction.LEFT;
                }
                break ;
            case LEFT:
                switch (dir){
                    case LEFT:
                        return Direction.DOWN;
                    case RIGHT:
                        return Direction.UP;
                }
                break ;
            case RIGHT:
                switch (dir){
                    case LEFT:
                        return Direction.UP;
                    case RIGHT:
                        return Direction.DOWN;
                }
                break ;
        }
        return direction;
    }

    private float EuclideanDistance(float x_h, float y_h, float x_a, float y_a){
        return (float) Math.sqrt(Math.pow(x_h - x_a,2) + Math.pow(y_h - y_a,2));
    }

    private float Euclidean_top(Direction direction, float x_h, float y_h, float x_a, float y_a){
        switch (direction){
            case RIGHT: return EuclideanDistance(x_h+1,y_h,x_a,y_a);
            case LEFT: return EuclideanDistance(x_h-1,y_h,x_a,y_a);
            case UP: return EuclideanDistance(x_h,y_h-1,x_a,y_a);
            case DOWN: return EuclideanDistance(x_h,y_h+1,x_a,y_a);
        }
        return -1;
    }

    private float Euclidean_right(Direction direction, float x_h, float y_h, float x_a, float y_a){
        switch (direction){
            case RIGHT: return EuclideanDistance(x_h,y_h+1,x_a,y_a);
            case LEFT: return EuclideanDistance(x_h,y_h-1,x_a,y_a);
            case UP: return EuclideanDistance(x_h+1,y_h,x_a,y_a);
            case DOWN: return EuclideanDistance(x_h-1,y_h,x_a,y_a);
        }
        return -1;
    }

    private float Euclidean_left(Direction direction, float x_h, float y_h, float x_a, float y_a){
        switch (direction){
            case RIGHT: return EuclideanDistance(x_h,y_h-1,x_a,y_a);
            case LEFT: return EuclideanDistance(x_h,y_h+1,x_a,y_a);
            case UP: return EuclideanDistance(x_h-1,y_h,x_a,y_a);
            case DOWN: return EuclideanDistance(x_h+1,y_h,x_a,y_a);
        }
        return -1;
    }

    private void snake_move(AI ai) {
        Point head = snake.get(0);
        int dx = head.x, dy = head.y;
        float x_h = snake.get(0).x;
        float y_h = snake.get(0).y;
        float x_a = apple.x;
        float y_a = apple.y;
        float e_t = Euclidean_top(direction,x_h,y_h,x_a,y_a);
        float e_r = Euclidean_right(direction,x_h,y_h,x_a,y_a);
        float e_l = Euclidean_left(direction,x_h,y_h,x_a,y_a);
        float o_t = 1/obstacle_top(direction);
        float o_r = 1/obstacle_right(direction);
        float o_l = 1/obstacle_left(direction);
        float top = ai.snake_go_top(o_t,e_t);
        float right = ai.snake_go_right(o_r,e_r);
        float left = ai.snake_go_left(o_l,e_l);
        float minWeight = Math.min(Math.min(top, right), left);
        if (minWeight == top) {
            dir = direction;
        } else if (minWeight == right) {
            dir = Direction.RIGHT;
        } else {
            dir = Direction.LEFT;
        }
        direction = vary(direction,dir);
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
        if (apple.equals(newHead)){ // сравнение
            apple = newApple();
            directionList.add(0,direction);
            score++;
            main.update_score(); // вывод счетчика
        }
        else {
            snake.remove(snake.size() - 1);
        }
        if (direction != check_direction){
            change_point.add(snake.get(1));
            change_direction.add(direction);
            directionList.set(0,direction);
        }
    }

    public void set_direction(Direction direction) {
        // запрещает изменение направления, если игра закончена
        if (!game) {
            return;
        }
        // запрещает изменение направления на противоположное
        if (this.direction == Direction.UP && direction == Direction.DOWN ||
                this.direction == Direction.DOWN && direction == Direction.UP ||
                this.direction == Direction.LEFT && direction == Direction.RIGHT ||
                this.direction == Direction.RIGHT && direction == Direction.LEFT ||
                this.direction == direction) {
            return;
        }
        check_direction = this.direction;
        this.direction = direction;
    }

    private int pictureDirection(Direction direction){
        int angle = switch (direction) {
            case RIGHT -> 0;
            case DOWN -> 90;
            case LEFT -> 180;
            case UP -> 270;
        };
        return angle;
    }

    private void Game_panel(){
        game_panel = new BufferedImage(getWidth(),getHeight(),BufferedImage.TYPE_INT_ARGB);
        Graphics2D g2 = game_panel.createGraphics();
        for (int i = 0; i < getHeight()/SNAKE_SIZE; i++){
            for (int j = 0; j < getWidth()/SNAKE_SIZE; j++){
                if (i % 2 == 0) {
                    if (j % 2 == 0)
                        g2.setColor(new Color(170, 215, 81));
                    else
                        g2.setColor(new Color(162, 209, 73));
                }
                else {
                    if (j % 2 == 0)
                        g2.setColor(new Color(162, 209, 73));
                    else
                        g2.setColor(new Color(170, 215, 81));
                }
                g2.fillRect(j*SNAKE_SIZE,i*SNAKE_SIZE,SNAKE_SIZE,SNAKE_SIZE);
            }
        }
        g2.dispose();
    }

    @Override
    protected void paintComponent(Graphics g){ // рисования графики внутри компонента
        super.paintComponent(g); // настройка графического контекста
        g.drawImage(game_panel,0,0,null);
        Graphics2D g2 = (Graphics2D) g;
        AffineTransform at = g2.getTransform();
        if (!game){
            main.game_over_start();
            return;
        }
        int pov = 0;
        BufferedImage image = null;
        for (int i = 0; i < snake.size(); i++) {
            for (int j = 0; j < change_point.size(); j++) {
                if (i == 0) {
                    image = Resources.SNAKE_HEAD;
                    pov = pictureDirection(directionList.get(i));
                } else if (i == snake.size() - 1) {
                    if (snake.get(i).equals(change_point.get(j))) {
                        directionList.set(i, change_direction.get(j));
                        change_point.remove(j);
                        change_direction.remove(j);
                    }
                    image = Resources.SNAKE_TAIL;
                    pov = pictureDirection(directionList.get(i));
                } else {
                    if (snake.get(i).equals(change_point.get(j)))
                        directionList.set(i,change_direction.get(j));
                    image = Resources.SNAKE_BODY;
                    pov = pictureDirection(directionList.get(i));
                }
            }
            at.rotate(Math.toRadians(pov), snake.get(i).x * SNAKE_SIZE + 12.5, snake.get(i).y * SNAKE_SIZE + 12.5);
            g2.setTransform(at);
            g.drawImage(image, snake.get(i).x * SNAKE_SIZE, snake.get(i).y * SNAKE_SIZE, 25, 25, null);
            at.rotate(-Math.toRadians(pov), snake.get(i).x * SNAKE_SIZE + 12.5, snake.get(i).y * SNAKE_SIZE + 12.5);
            g2.setTransform(at);
        }
        g.drawImage(Resources.APPLE_IMAGE,apple.x*APPLE_SIZE,apple.y*APPLE_SIZE,APPLE_SIZE,APPLE_SIZE,null);
//        if (!apple_flow.equals(new Point(0,0)))
//            g.drawImage(Resources.APPLE_YELLOW_IMAGE,apple_flow.x*APPLE_SIZE,apple_flow.y*APPLE_SIZE,APPLE_SIZE,APPLE_SIZE,null);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        repaint();
        snake_move(ai);
    }
}