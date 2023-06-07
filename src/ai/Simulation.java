package ai;

import extra.Direction;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Simulation{
    final int SNAKE_SIZE = 25;
    final int APPLE_SIZE = 25;
    public int score = 0;
    private boolean game;

    private Point apple = new Point();
    private java.util.List<Point> snake = new ArrayList<Point>();
    private Random rng = new Random();
    private Direction direction = Direction.RIGHT; // начальное направление движения змейки вправо

    public float run(AI ai){
        int steps = 0;
        start();
        do{
            snake_move(ai);
            steps++;
        }while (game && steps < 5000);
        return steps;
    }

    private void start(){
        game = true;
        // длинна змеи
        snake.add(new Point(2,0));
        snake.add(new Point(1,0));
        snake.add(new Point(0,0));
        apple = newApple();
    }

    public Point newApple(){
        Point newapple;
        while (true){
            newapple = new Point(rng.nextInt(1400/APPLE_SIZE),rng.nextInt(750/APPLE_SIZE));
            if (!snake.contains(newapple) && !apple.equals(newapple)){ // если яблока с такими координатами не на змее
                return newapple;
            }
        }
    }

    float obstacle_top(Direction direction){
        float size =0;
        switch (direction){
            case RIGHT: size = 56 - snake.get(0).x + 1;break;
            case LEFT: size = 56 - (56 - snake.get(0).x) + 1;break;
            case UP: size = 30 - (30 - snake.get(0).y) + 1;break;
            case DOWN: size = 30 - snake.get(0).y + 1;break;

        }
        return size;
    }
    float obstacle_left(Direction direction){
        float size =0;
        switch (direction){
            case DOWN: size = 56 - snake.get(0).x + 1;break;
            case UP: size = 56 - (56 - snake.get(0).x) + 1;break;
            case RIGHT: size = 30 - (30 - snake.get(0).y) + 1;break;
            case LEFT: size = 30 - snake.get(0).y + 1;break;

        }
        return size;
    }
    float obstacle_right(Direction direction){
        float size =0;
        switch (direction){
            case UP: size = 56 - snake.get(0).x + 1;break;
            case DOWN: size = 56 - (56 - snake.get(0).x) + 1;break;
            case LEFT: size = 30 - (30 - snake.get(0).y) + 1;break;
            case RIGHT: size = 30 - snake.get(0).y + 1;break;

        }
        return size;
    }

    private void snake_move(AI ai) {
        Point head = snake.get(0);
        int dx = head.x, dy = head.y;
        float x = snake.get(0).x;
        float y = snake.get(0).y;
        float x_a = apple.x;
        float y_a = apple.y;
        float o_t = obstacle_top(direction);
        float o_r = obstacle_right(direction);
        float o_l = obstacle_left(direction);
        int dir = ai.snake_go(o_t,o_r,o_l,x,y,x_a,y_a);
        switch (direction) {
            case UP:
                switch (dir) {
                    case -1:
                        direction = Direction.LEFT;
                        break;
                    case 1:
                        direction = Direction.RIGHT;
                        break ;
                }
                break;
            case DOWN:
                switch (dir){
                    case -1:
                        direction = Direction.RIGHT;
                        break;
                    case 1:
                        direction = Direction.LEFT;
                        break ;
                }
            case LEFT:
                switch (dir){
                    case -1:
                        direction = Direction.DOWN;
                        break;
                    case 1:
                        direction = Direction.UP;
                        break ;
                }
            case RIGHT:
                switch (dir){
                    case -1:
                        direction = Direction.UP;
                        break;
                    case 1:
                        direction = Direction.DOWN;
                        break ;
                }
        }
        switch (direction){
            case UP: dy -= 1; break;
            case DOWN: dy += 1; break;
            case RIGHT: dx += 1; break;
            case LEFT: dx -= 1; break;
        }
        Point newHead = new Point(dx,dy);
        snake.add(0,newHead);
        // если вышел за границу
        if (newHead.x < 0 || newHead.y < 0 || newHead.x >= 1400/SNAKE_SIZE || newHead.y >= 750/SNAKE_SIZE){
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
            score++;
        } else {
            snake.remove(snake.size() - 1);
        }
    }
}
