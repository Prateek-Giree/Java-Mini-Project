package Text_Editor;

import javax.swing.*;

public class MenuBar {
    static JMenuBar mb;
    JMenu menu = new JMenu();
    JMenuBar menuBar = new JMenuBar();
    JMenuItem menuItem = new JMenuItem();

    static JMenu file,edit,view,help; //creating objects
    static JMenuItem neww,open,save,saveAs,pageSetup,print,exit; //objects
    MenuBar() {
        mb = new JMenuBar();
        file = new JMenu("File");
        edit = new JMenu("Edit");
        view = new JMenu("View");
        help = new JMenu("Help");
        neww = new JMenuItem("New");
        open = new JMenuItem("Open");
        save = new JMenuItem("Save");
        saveAs = new JMenuItem("Save As");
        pageSetup = new JMenuItem("Page Setup");
        print = new JMenuItem("Print");
        exit = new JMenuItem("Exit");

        file.add(neww);
        file.add(open);
        file.add(save);
        file.add(saveAs);
        file.add(pageSetup);
        file.add(print);
        file.add(exit);
        mb.add(file);
        mb.add(edit);
        mb.add(view);
        mb.add(help);
    }
}
