import java.awt.*;
import java.util.ArrayList;
import java.util.List;

public class Flow implements Runnable{
    Game game;
    private volatile  boolean running = true;
    Flow(Game game){
        this.game = game;

    }

    @Override
    public void run() {
        while (running) {
            game.newApple();//обращение к методу newApple из класса Game
            try {
                Thread.sleep(3000);//спаун яблок раз в 3 сек

            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

    }
    public void stop(){
        running = false;
    }
}
