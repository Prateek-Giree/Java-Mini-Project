package Project1;

import javax.swing.*;
import java.awt.*;
import java.util.Arrays;
import java.util.List;

    public class HelloWorldFonts {
        public static void main(String[] args) {
            List<String> fontNames = Arrays.asList(
                    "Agency FB", "Algerian", "Arial", "Arial Black", "Arial Narrow",
                    "Arial Rounded MT Bold", "Artifakt Element", "Artifakt Element Black",
                    "Artifakt Element Book", "Artifakt Element Hair", "Artifakt Element Heavy",
                    "Artifakt Element Light", "Artifakt Element Medium", "Artifakt Element Thin",
                     "Bahnschrift", "BankGothic Lt BT", "BankGothic Md BT",
                    "Baskerville Old Face"
            );

            JFrame frame = new JFrame("Hello, World! in Different Fonts");
            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            frame.setSize(600, 400);
            frame.setLayout(new GridLayout(fontNames.size(), 1));

            for (String fontName : fontNames) {
                JLabel label = new JLabel("Hello World");
                label.setFont(new Font(fontName, Font.PLAIN, 20));
                frame.add(label);
            }

            frame.setVisible(true);
        }
    }


