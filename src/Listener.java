import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Listener implements ActionListener{ // Listener выполняет слушателя действий
    int i = 0;
    JButton button;
    Listener(JButton button){
        this.button = button;
    }
    @Override
    public void actionPerformed(ActionEvent e) { // отвечает за действия пользователя
        i++;
        button.setText("clicked " + i);
    }
}