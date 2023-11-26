package Project1;

import javax.script.ScriptEngine;
import javax.script.ScriptEngineManager;
import javax.script.ScriptException;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.geom.Ellipse2D;

class OvalButton extends JButton {
    private boolean hover = false;

    OvalButton(String label) {
        super(label);
        setContentAreaFilled(false);
        setFocusPainted(false);
        setBorderPainted(false);
        setOpaque(false);

        addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Add action for button click here
            }
        });

        addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                hover = true;
                repaint();
            }

            public void mouseExited(java.awt.event.MouseEvent evt) {
                hover = false;
                repaint();
            }
        });
    }

    @Override
    public void paint(Graphics g) {
        Graphics2D g2d = (Graphics2D) g.create();
        g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

        int width = getWidth();
        int height = getHeight();

        if (hover) {
            g2d.setColor(getBackground().brighter());
        } else {
            g2d.setColor(getBackground());
        }

        Shape shape = new Ellipse2D.Float(0, 0, width, height);
        g2d.fill(shape);

        g2d.setColor(getForeground());
        g2d.draw(shape);

        g2d.dispose();

        super.paint(g);
    }

    @Override
    public boolean contains(int x, int y) {
        int width = getWidth();
        int height = getHeight();
        Shape shape = new Ellipse2D.Float(0, 0, width, height);
        return shape.contains(x, y);
    }
}

public class Calculator {
    private JTextField txtCalc;
    private StringBuilder currentInput;

    Calculator() {
        currentInput = new StringBuilder();

        JFrame f = new JFrame("Calculator");
        txtCalc = new JTextField();
        txtCalc.setBounds(10, 20, 320, 50);
        txtCalc.setEditable(false);
        f.add(txtCalc);

        // Create buttons for numbers, operators, and special functions
        createButtons(f);

        f.setSize(360, 600);
        f.setResizable(false);
        f.setLayout(null);
        f.getContentPane().setBackground(new Color(25, 35, 35));
        ImageIcon img = new ImageIcon("D:\\JavaProjects\\Project1\\calc.png");
        f.setIconImage(img.getImage());
        f.setVisible(true);
        f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }

    private void createButtons(JFrame frame) {
        // Create and add buttons for numbers
        int x = 10;
        int y = 100;
        for (int i = 7; i <= 9; i++) {
            OvalButton btn = new OvalButton(Integer.toString(i));
            btn.setBounds(x, y, 50, 40);
            btn.setBorder(null);
            frame.add(btn);
            addButtonListener(btn);
            x += 90;
        }

        x = 10;
        y = 180;
        for (int i = 4; i <= 6; i++) {
            OvalButton btn = new OvalButton(Integer.toString(i));
            btn.setBounds(x, y, 50, 40);
            btn.setBorder(null);
            frame.add(btn);
            addButtonListener(btn);
            x += 90;
        }

        x = 10;
        y = 260;
        for (int i = 1; i <= 3; i++) {
            OvalButton btn = new OvalButton(Integer.toString(i));
            btn.setBounds(x, y, 50, 40);
            btn.setBorder(null);
            frame.add(btn);
            addButtonListener(btn);
            x += 90;
        }

        // Create and add buttons for point, division, and equals
        OvalButton btn0 = new OvalButton("0");
        btn0.setBounds(10, 340, 50, 40);
        btn0.setBorder(null);
        frame.add(btn0);
        addButtonListener(btn0);

        OvalButton btnPoint = new OvalButton(".");
        btnPoint.setBounds(100, 340, 50, 40);
        btnPoint.setBackground(new Color(235, 173, 52));
        frame.add(btnPoint);
        addButtonListener(btnPoint);

        OvalButton btnDiv = new OvalButton("÷");
        btnDiv.setBackground(new Color(235, 173, 52));
        btnDiv.setBounds(190, 340, 50, 40);
        frame.add(btnDiv);
        addButtonListener(btnDiv);

        OvalButton btnEqual = new OvalButton("=");
        btnEqual.setBounds(280, 340, 50, 40);
        btnEqual.setBackground(new Color(235, 173, 52));
        frame.add(btnEqual);
        addButtonListener(btnEqual);

        OvalButton btnMul = new OvalButton("x");
        btnMul.setBounds(280, 100, 50, 40);
        btnMul.setBackground(new Color(235, 173, 52));
        btnMul.setBorder(null);
        frame.add(btnMul);
        addButtonListener(btnMul);

        OvalButton btnPlus = new OvalButton("+");
        btnPlus.setBounds(280, 180, 50, 40);
        btnPlus.setBackground(new Color(235, 173, 52));
        btnPlus.setBorder(null);
        frame.add(btnPlus);
        addButtonListener(btnPlus);

        OvalButton btnMinus = new OvalButton("-");
        btnMinus.setBounds(280, 260, 50, 40);
        btnMinus.setBackground(new Color(235, 173, 52));
        btnMinus.setBorder(null);
        frame.add(btnMinus);
        addButtonListener(btnMinus);

        JButton btnClear = new JButton("Clear");
        btnClear.setBackground(new Color(235, 173, 52));
        btnClear.setBorder(null);
        btnClear.setBounds(10, 400, 100, 50);
        frame.add(btnClear);
        btnClear.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                txtCalc.setText(null);
                addButtonListener(null);

            }
        });

        btnEqual.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                super.mouseClicked(e);
                calculateResult();
            }
        });
    }

    private void addButtonListener(OvalButton button) {
        button.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                super.mouseClicked(e);
                currentInput.append(button.getText());
                txtCalc.setText(currentInput.toString());
            }
        });
    }

    private void calculateResult() {
        try {
            String expression = currentInput.toString();
            double result = evaluateExpression(expression);
            txtCalc.setText(Double.toString(result));
            currentInput.setLength(0); // Clear the input
            currentInput.append(Double.toString(result)); // Use the result for further calculations
        } catch (Exception ex) {
            txtCalc.setText("Error");
        }
    }

    private double evaluateExpression(String expression) throws ScriptException {
        ScriptEngineManager manager = new ScriptEngineManager();
        ScriptEngine engine = manager.getEngineByName("JavaScript");
        return (double) engine.eval(expression);
    }

    public static void main(String[] args) {
        new Calculator();
    }
}
