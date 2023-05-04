import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ChatListener implements ActionListener {
    JTextField input;
    JTextArea output;
    ChatListener(JTextField input, JTextArea output){
        this.input = input;
        this.output = output;
    }
    @Override
    public void actionPerformed(ActionEvent e) {
        output.setText(output.getText() + "\n>>" + input.getText());
        input.setText("");
    }
}