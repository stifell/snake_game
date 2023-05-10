import javax.swing.*;

public class Main extends JFrame {
    Main(){
        super("Snake");
        add(new GameFields());
        setDefaultCloseOperation(3);
        setResizable(false);
        pack();
        setVisible(true);
        setLocationRelativeTo(null);
    }
    public static void main(String[] args){
        new Main();
    }
}
