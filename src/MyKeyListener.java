//import java.awt.event.KeyAdapter;
//import java.awt.event.KeyEvent;
//import java.awt.event.KeyListener;
//
//
//public class MyKeyListener implements KeyListener {
//
//    GameFields gameFields;
//    MyKeyListener(){
//        this.gameFields = new GameFields();
//    }
//
//    @Override
//    public void keyTyped(KeyEvent e) {
//
//    }
//
//    @Override
//    public void keyPressed(KeyEvent e) {
//        switch (e.getKeyCode()){
//            case KeyEvent.VK_LEFT:
//                if(gameFields.direction != 'R'){
//                    gameFields.direction = 'L';
//                }
//                break;
//            case KeyEvent.VK_RIGHT:
//                if(gameFields.direction != 'L'){
//                    gameFields.direction = 'R';
//                }
//                break;
//            case KeyEvent.VK_UP:
//                if (gameFields.direction != 'D'){
//                    gameFields.direction='U';
//                }
//                break;
//            case KeyEvent.VK_DOWN:
//                if (gameFields.direction != 'U'){
//                    gameFields.direction='D';
//                }
//                break;
//
//        }
//    }
//
//    @Override
//    public void keyReleased(KeyEvent e) {
//
//    }
//}
