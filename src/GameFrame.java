import javax.swing.*;

public class GameFrame extends JFrame {
    GameFrame(){
        super("Snake");
        this.add(new GameFields());
        this.setDefaultCloseOperation(3);
        this.setResizable(false);
        this.pack();
        this.setVisible(true);
        this.setLocationRelativeTo(null);
    }
}
