package game;

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
    private Main main;
    final int SNAKE_SIZE = 25;
    final int APPLE_SIZE = 25;
    public int score = 0;
    private boolean game;
    private BufferedImage game_panel;
    private Point apple = new Point();
    public Point apple_flow = new Point();
    private List<Point> snake = new ArrayList<Point>();
    private List<Point> change_point = new ArrayList<Point>();
    private List<Direction> directionList = new ArrayList<Direction>();
    private List<Direction> change_direction = new ArrayList<Direction>();
    private Random rng = new Random();
    private Direction direction = Direction.RIGHT; // начальное направление движения змейки вправо
    private Direction check_direction;
    public Timer timer;
    public Thread thread;
    Game(Main main){
        this.main = main;
        setBounds(68,57,Main.WIDTH-136,Main.HEIGTH-114);
        setLayout(null);
        addKeyListener(new Controller(this, main));
        setFocusable(true);
        start();
    }

    private void start(){
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
        thread = new Thread(new Flow(this));
        thread.start();
        timer = new Timer(90, this);
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

    private void snake_move() {
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
        if (apple.equals(newHead)){ // сравнение
            apple = newApple();
            directionList.add(0,direction);
            score++;
            main.update_score(score); // вывод счетчика
        } else if (apple_flow.equals(newHead)) {
            apple_flow = new Point(0,0);
            directionList.add(0,direction);
            score++;
            main.update_score(score);
        } else {
            snake.remove(snake.size() - 1);
        }
        if (direction != check_direction){
            change_point.add(snake.get(1));
            change_direction.add(direction);
            directionList.set(0,direction);
        }
    }

    public void setDirection(Direction direction) {
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
        int angle = 0;
        switch (direction){
            case RIGHT: angle = 0; break;
            case DOWN: angle = 90; break;
            case LEFT: angle = 180; break;
            case UP: angle = 270; break;
        }
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
        if (!apple_flow.equals(new Point(0,0)))
            g.drawImage(Resources.APPLE_IMAGE,apple_flow.x*APPLE_SIZE,apple_flow.y*APPLE_SIZE,APPLE_SIZE,APPLE_SIZE,null);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        repaint();
        snake_move();
    }
}