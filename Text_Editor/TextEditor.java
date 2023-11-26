package Text_Editor;

import javax.swing.*;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter; 

public class TextEditor implements ActionListener {
    JFrame frame = new JFrame("Text-Editor");
    JTextArea textArea;
    JScrollPane scrollPane;
    JSpinner fontSizePanel;
    JButton fontColorButton;
    JComboBox fontStyle;

    TextEditor(){

        //changing look and feel
        try {
            UIManager.setLookAndFeel("javax.swing.plaf.nimbus.NimbusLookAndFeel");
        } catch (Exception e) {
            e.printStackTrace();
        }

        new MenuBar();
        frame.setJMenuBar(MenuBar.mb);
        textArea = new JTextArea();
        textArea.setLineWrap(true); //word wrapping
        textArea.setWrapStyleWord(true); //text will wrap at word boundaries
        textArea.setFont(new Font("Arial", Font.PLAIN, 20));

        scrollPane = new JScrollPane(textArea);
        scrollPane.setPreferredSize(new Dimension(850, 630));
        scrollPane.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);

        // Create the font size spinner and add it to the top panel.
        SpinnerModel fontSizeModel = new SpinnerNumberModel(20, 11, 72, 1);
        fontSizePanel = new JSpinner(fontSizeModel);
        fontSizePanel.setPreferredSize(new Dimension(75, 30));
        fontSizePanel.addChangeListener(new ChangeListener() {
                @Override
                public void stateChanged(ChangeEvent e) {
                    textArea.setFont(new Font(textArea.getFont().getFamily(), Font.PLAIN, (int) fontSizePanel.getValue()));
                }
            });
        frame.add(new JLabel("Font Size:"));
        frame.add(fontSizePanel);

        fontColorButton = new JButton("Font-Color");
        fontColorButton.addActionListener(this);

        //Adding all the available fonts in the array of strings
        String[] fonts = GraphicsEnvironment.getLocalGraphicsEnvironment().getAvailableFontFamilyNames();
        fontStyle = new JComboBox(fonts);
        fontStyle.addActionListener(this);
        fontStyle.setSelectedItem("Arial");

        MenuBar.exit.addActionListener(this);
        MenuBar.save.addActionListener(this);

        frame.add(new JLabel("Fonts:"));
        frame.add(fontStyle);
        frame.add(fontColorButton);
        frame.add(scrollPane);
        frame.setSize(900, 700);
        frame.setResizable(false);
        frame.setLayout(new FlowLayout());
        frame.setLocationRelativeTo(null);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);
    }
    @Override
    public void actionPerformed(ActionEvent e) {

        //changes the font color
        if (e.getSource() == fontColorButton) {
            JColorChooser colorChooser = new JColorChooser();

            Color color = colorChooser.showDialog(null, "choose a color", Color.BLACK);
            textArea.setForeground(color);

        }

        //To change the font style but preserve the font size
        if (e.getSource() == fontStyle) {
            textArea.setFont(new Font((String) fontStyle.getSelectedItem(), Font.PLAIN, textArea.getFont().getSize()));

        }

        //exit frame when exit is clicked
        if (e.getSource()==MenuBar.exit){
            System.exit(0);
        }

        //save file when save is clicked
        if (e.getSource() == MenuBar.save) {
            JFileChooser fileChooser = new JFileChooser();
            //sets the default directory to the current working directory 
            fileChooser.setCurrentDirectory(new File("."));

            int response = fileChooser.showSaveDialog(null);

            if (response == JFileChooser.APPROVE_OPTION) {
                File file;
                PrintWriter fileOut = null;
                file = new File(fileChooser.getSelectedFile().getAbsolutePath());
                try {
                    fileOut = new PrintWriter(file);
                    fileOut.println(textArea.getText());
                } catch (FileNotFoundException ex) {
                    throw new RuntimeException(ex);
                }
                finally {
                    fileOut.close();

                }
            }
        }
    }
}
