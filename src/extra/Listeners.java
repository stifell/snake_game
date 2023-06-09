package extra;

import game.*;
import java.awt.event.*;

public class Listeners {
    public static ActionListener PlayButtonListener(Main main) {
        return new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Логика обработки нажатия кнопки "play"
                main.start_game();
            }
        };
    }

    public static ActionListener ContinueButtonListener(Main main,Game game) {
        return new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Логика обработки нажатия кнопки "continue"
                main.pause_exit();
                game.timer.start();
            }
        };
    }

    public static ActionListener ExitButtonListener() {
        return new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Логика обработки нажатия кнопки "exit"
                System.exit(0);
            }
        };
    }

    public static ActionListener RestartButtonListener(Main main) {
        return new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Логика обработки нажатия кнопки "restart"
                main.game = null;
                main.restart_game();
                main.pause_exit();
            }
        };
    }
    public  static ActionListener RestartGameOverButtonListener(Main main){
        return new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                main.restart_game();
                main.game_over_exit();
            }
        };
    }
}
