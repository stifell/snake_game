import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class Resources {
    public static final Font FONT_FIRST;
    public static final Font FONT_SECOND;
    public static BufferedImage APPLE_IMAGE;
    public static BufferedImage BACKROUND;
    public static ImageIcon ICON_PLAY;
    public static ImageIcon ICON_PLAY_DIRECT;
    public static ImageIcon ICON_EXIT;
    public static ImageIcon ICON_EXIT_DIRECT;

    public  static ImageIcon ICON_CONTINUE;
    public  static ImageIcon ICON_CONTINUE_DIRECT;
    public  static ImageIcon ICON_RESTART;
    public  static ImageIcon ICON_RESTART_DIRECT;
    static {
        try {
            FONT_FIRST = Font.createFont(Font.TRUETYPE_FONT, new File("resources/fonts/digital-7.ttf")).deriveFont(36f);
            FONT_SECOND = Font.createFont(Font.TRUETYPE_FONT, new File("resources/fonts/orbitron-light.otf")).deriveFont(72f);
            APPLE_IMAGE = ImageIO.read(new File("resources/image/apple.png"));
            BACKROUND = ImageIO.read(new File("resources/image/backround.jpg"));
            ICON_PLAY = new ImageIcon("resources/image/play.png");
            ICON_PLAY_DIRECT = new ImageIcon("resources/image/play_direct.png");
            ICON_EXIT = new ImageIcon("resources/image/exit.png");
            ICON_EXIT_DIRECT = new ImageIcon("resources/image/exit_direct.png");
            ICON_CONTINUE = new ImageIcon("resources/image/continue.png");
            ICON_CONTINUE_DIRECT = new ImageIcon("resources/image/continue_direct.png");
            ICON_RESTART = new ImageIcon("resources/image/restart.png");
            ICON_RESTART_DIRECT = new ImageIcon("resources/image/restart_direct.png");
        } catch (FontFormatException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}