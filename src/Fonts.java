import java.awt.*;
import java.io.File;
import java.io.IOException;

public class Fonts {
    public static final Font FONT_FIRST;
    public static final Font FONT_SECOND;
    static {
        try {
            FONT_FIRST = Font.createFont(Font.TRUETYPE_FONT, new File("resources/fonts/digital-7.ttf")).deriveFont(24f);
            FONT_SECOND = Font.createFont(Font.TRUETYPE_FONT, new File("resources/fonts/orbitron-light.otf")).deriveFont(72f);
        } catch (FontFormatException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
