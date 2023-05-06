import javax.swing.*;
import java.awt.*;

public class Main extends JFrame {
    Main(){
        super("Application"); // окно
        setSize(700,500); // размер
        setDefaultCloseOperation(3); // закрытие завершает код
        setLayout(null); // не меняем размер

        JButton button = new JButton("click"); // кнопка
        button.setBounds(20,20,180,60); // размеры
        add(button); // добавление кнопки в окно
        Listener firstListener = new Listener(button);
        button.addActionListener(firstListener); // на кнопку добавляем слушателя от нового слушателя к кнопке

        JTextArea textArea = new JTextArea(); // текст вывода
        textArea.setBounds(20,100,250,200); // размер
        add(textArea); // добавление
        textArea.setText("Hello word!"); // добавление текста
        textArea.setFont(new Font("Times New Roman",Font.PLAIN,20)); // шрифт,стиль,размер
        textArea.setEditable(false);

        JTextField textField = new JTextField(); // текст ввода
        textField.setBounds(20,310,100,30);
        add(textField);
        textField.addActionListener(new ChatListener(textField,textArea));

        JLabel label = new JLabel(new ImageIcon("picture.jpg"));
        label.setBounds(320,20,320,320);
        add(label);
        Controller controller = new Controller(label); // двигаем иконку
        // Дима красавчик
        button.addKeyListener(controller); // кнопка имеет приортет
        setVisible(true); // видимость окна
    }
    public static void main(String[] args) {
        new Main();
    }
}