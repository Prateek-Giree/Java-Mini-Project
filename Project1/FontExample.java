package Project1;
import java.awt.GraphicsEnvironment;

public class FontExample {
    public static void main(String[] args) {
        GraphicsEnvironment ge = GraphicsEnvironment.getLocalGraphicsEnvironment();
        String[] fontNames = ge.getAvailableFontFamilyNames();

        System.out.println("Available Fonts:");
        for (String fontName : fontNames) {
            System.out.println(fontName);
        }
    }
}
