public class Flow implements Runnable{
    Game game;
    Flow(Game game){
        this.game = game;

    }

    @Override
    public void run() {
        while (true) {
            game.newApple();//обращение к методу newApple из класса Game
            try {
                Thread.sleep(3000);//спаун яблок раз в 3 сек

            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
