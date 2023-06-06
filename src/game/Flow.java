package game;

public class Flow implements Runnable{
    Game game;
    Flow(Game game){
        this.game = game;

    }

    @Override
    public void run() {
        while (true) {
            game.apple_flow = game.newApple();//обращение к методу newApple_Flow из класса Game
            game.repaint();
            try {
                Thread.sleep(3000);//спаун яблок раз в 3 сек
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
