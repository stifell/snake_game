package ai;

import extra.Direction;

import java.awt.*;
import java.util.ArrayList;
import java.util.Random;

public class Simulation{
    final int SNAKE_SIZE = 25;
    final int APPLE_SIZE = 25;
    public int score = 0;
    private boolean game;

    private Point apple = new Point();
    private final java.util.List<Point> snake = new ArrayList<>();
    private final Random rng = new Random();
    private static Direction direction = Direction.RIGHT; // начальное направление движения змейки вправо

    public float run(AI ai){
        int steps = 0;
        start();
        do{
            snake_move(ai);
            steps++;
        }while (game && score < 1000 && steps < 5000);
        return score;
    }

    private void start(){
        game = true;
        score = 0;
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
            if (!snake.contains(newapple)){ // если яблока с такими координатами не на змее
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
        Direction dir;
        if (minWeight == top) {
            dir = direction;
        } else if (minWeight == right) {
            dir = Direction.RIGHT;
        } else {
            dir = Direction.LEFT;
        }
        direction = vary(direction, dir);
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
