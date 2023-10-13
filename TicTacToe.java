package Projects;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Random;

public class TicTacToe implements ActionListener {
    JFrame frame = new JFrame("Tic-Tac-Toe");
    JPanel title_pnl = new JPanel();
    JPanel button_pnl = new JPanel(); //panel to hold array of buttons
    JButton[] btn = new JButton[9];
    JLabel lbl = new JLabel();
    Boolean player1_turn=false;
    Random random = new Random();
    TicTacToe(){
        frame.setSize(400, 400);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLayout(new BorderLayout());
//        frame.getContentPane().setBackground(new Color(80,250,250));
        frame.setVisible(true);

        lbl.setBackground(new Color(0,0,0));
        lbl.setForeground(new Color(16, 252, 8));
        lbl.setFont(new Font("Algerian", Font.ITALIC, 30));
        lbl.setHorizontalAlignment(JLabel.CENTER);
        lbl.setText("Tic-Tac-Toe");
        lbl.setOpaque(true);

        title_pnl.setLayout(new BorderLayout());
        title_pnl.setBounds(0, 0, 400, 20);

        //display button in 3x3 grid
        button_pnl.setLayout(new GridLayout(3, 3));
        button_pnl.setBackground(new Color(250, 250, 250));

        for (int i = 0; i < 9; i++) {
            btn[i] = new JButton();
            button_pnl.add(btn[i]);
            btn[i].setFont(new Font("Baskerville Old Face", Font.ITALIC, 60));
            btn[i].setFocusable(false);
            btn[i].addActionListener(this);
            btn[i].setBackground(new Color(0,0,0));
        }

        frame.add(button_pnl);
        title_pnl.add(lbl);
        frame.add(title_pnl, BorderLayout.NORTH);

        firstTurn();

    }

    @Override
    public void actionPerformed(ActionEvent e) {
        for (int i = 0; i < 9; i++) {

            // The source of the event is the button
            if (e.getSource() == btn[i]) {

                //checking if this is player1's turn
                if (player1_turn) {
                    //check if that specific button already has text
                    if (btn[i].getText() == "") {
                        btn[i].setForeground(new Color(169, 3, 252));
                        btn[i].setText("X");
                        player1_turn = false;
                        lbl.setText("Player O's turn");
                        checkWin();

                    }
                }
                else {
                    if (btn[i].getText() == "") {
                        btn[i].setForeground(new Color(0, 255, 255));
                        btn[i].setText("O");
                        player1_turn = true;
                        lbl.setText("Player X's turn");
                        checkWin();
                    }
                }

            }
        }
    }

    public void firstTurn(){

        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        int turn = random.nextInt(2);
        System.out.println(turn);
        if (turn == 0) {
            player1_turn = true;
            lbl.setText("Player X's turn");

        }
        else
            lbl.setText("Player O's turn");
    }

    public void checkWin(){
        //check player x win condition
        if (btn[0].getText() == "X" && btn[1].getText() == "X" && btn[2].getText() == "X")
            playerXWin(0, 1, 2);


        if (btn[0].getText() == "X" && btn[3].getText() == "X" && btn[6].getText() == "X")
            playerXWin(0,3,6);


        if (btn[0].getText() == "X" && btn[4].getText() == "X" && btn[8].getText() == "X")
            playerXWin(0,4,8);


        if (btn[3].getText() == "X" && btn[4].getText() == "X" && btn[5].getText() == "X")
            playerXWin(3,4,5);

        if (btn[6].getText() == "X" && btn[7].getText() == "X" && btn[8].getText() == "X")
            playerXWin(6, 7, 8);

        if (btn[1].getText() == "X" && btn[4].getText() == "X" && btn[7].getText() == "X")
            playerXWin(1, 4, 7);

        if (btn[2].getText() == "X" && btn[5].getText() == "X" && btn[8].getText() == "X")
            playerXWin(2, 5, 8);

        if (btn[2].getText() == "X" && btn[4].getText() == "X" && btn[6].getText() == "X")
            playerXWin(2, 4, 6);

        //check player o win condition
        if (btn[0].getText() == "O" && btn[1].getText() == "O" && btn[2].getText() == "O")
            playerOWin(0, 1, 2);

        if (btn[0].getText() == "O" && btn[3].getText() == "O" && btn[6].getText() == "O")
            playerOWin(0, 3, 6);

        if (btn[0].getText() == "O" && btn[4].getText() == "O" && btn[8].getText() == "O")
            playerOWin(0, 4, 8);

        if (btn[3].getText() == "O" && btn[4].getText() == "O" && btn[5].getText() == "O")
            playerOWin(3, 4, 5);

        if (btn[6].getText() == "O" && btn[7].getText() == "O" && btn[8].getText() == "O")
            playerOWin(6, 7, 8);

        if (btn[1].getText() == "O" && btn[4].getText() == "O" && btn[7].getText() == "O")
            playerOWin(1, 4, 7);

        if (btn[2].getText() == "O" && btn[5].getText() == "O" && btn[8].getText() == "O")
            playerOWin(2, 5, 8);

        if (btn[2].getText() == "O" && btn[4].getText() == "O" && btn[6].getText() == "O")
            playerOWin(2, 4, 6);

        else
            gameDraw();

    }

    public void playerXWin(int a, int b, int c) {
        btn[a].setBackground(Color.GREEN);
        btn[b].setBackground(Color.GREEN);
        btn[c].setBackground(Color.GREEN);

        //disabling buttons to prevent further interaction
        for (int i = 0; i < 9; i++) {
            btn[i].setEnabled(false);
        }
        lbl.setText("Player X has won");

        new Thread(() -> {
            try {
                Thread.sleep(2000); // Wait for 2 seconds
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }

            // Reset the game after 2 seconds
            newGame();
        }).start();
    }
    public void playerOWin(int a, int b, int c) {
        btn[a].setBackground(Color.GREEN);
        btn[b].setBackground(Color.GREEN);
        btn[c].setBackground(Color.GREEN);

        //disabling buttons to prevent further interaction
        for (int i = 0; i < 9; i++) {
            btn[i].setEnabled(false);
        }
        lbl.setText("Player O has won");


        new Thread(() -> {
            try {
                Thread.sleep(2000); // Wait for 2 seconds
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }

            // Reset the game after 2 seconds
            newGame();
        }).start();
    }

    public void newGame(){
        try {
            Thread.sleep(2000);
        } catch (Exception e) {
            e.printStackTrace();

        }
        for (int i = 0; i < 9; i++) {
            btn[i].setBackground(new Color(0, 0, 0));
            btn[i].setText(null);
            btn[i].setEnabled(true);
        }
        new TicTacToe();

    }

    public void gameDraw() {
        boolean isDraw = true;

        // Check if all buttons are filled
        for (int i = 0; i < 9; i++) {
            if (btn[i].getText().isEmpty()) {
                isDraw = false;
                break;
            }
        }

        if (isDraw) {
            lbl.setText("Draw!");
            for (int i = 0; i < 9; i++) {
                btn[i].setEnabled(false); // Disable buttons to prevent further interaction
            }
            new Thread(() -> {
                try {
                    Thread.sleep(2000); // Wait for 2 seconds
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
                newGame();
            }).start();
        }

    }

    public static void main(String[] args) {
        new TicTacToe();
    }
}
