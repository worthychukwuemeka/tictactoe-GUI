package UI;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class TicTacToeGUI extends JFrame implements ActionListener {

    private final JButton[][] buttons;
    private boolean playerX = true; // Player X starts
    private int moves;

    public TicTacToeGUI() {
        setTitle("Tic-Tac-Toe");
        setSize(300, 300);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setLayout(new GridLayout(3, 3));

        buttons = new JButton[3][3];
        moves = 0;

        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                buttons[i][j] = new JButton("");
                buttons[i][j].setFont(new Font("Arial", Font.PLAIN, 48));
                buttons[i][j].addActionListener(this);
                add(buttons[i][j]);
            }
        }
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        JButton buttonClicked = (JButton) e.getSource();

        if (buttonClicked.getText().equals("") && playerX) {
            buttonClicked.setText("X");
            buttonClicked.setForeground(Color.BLUE);
            playerX = false;
            moves++;
        } else if (buttonClicked.getText().equals("") && !playerX) {
            buttonClicked.setText("O");
            buttonClicked.setForeground(Color.RED);
            playerX = true;
            moves++;
        }

        if (checkForWin()) {
            if (playerX)
                JOptionPane.showMessageDialog(this, "Player O wins!");
            else
                JOptionPane.showMessageDialog(this, "Player X wins!");
            resetBoard();
        } else if (moves == 9) {
            JOptionPane.showMessageDialog(this, "It's a draw!");
            resetBoard();
        }
    }

    private boolean checkForWin() {
        for (int i = 0; i < 3; i++) {
            if (buttons[i][0].getText().equals(buttons[i][1].getText()) &&
                    buttons[i][0].getText().equals(buttons[i][2].getText()) &&
                    !buttons[i][0].getText().equals("")) {
                return true;
            }
            if (buttons[0][i].getText().equals(buttons[1][i].getText()) &&
                    buttons[0][i].getText().equals(buttons[2][i].getText()) &&
                    !buttons[0][i].getText().equals("")) {
                return true;
            }
        }
        if (buttons[0][0].getText().equals(buttons[1][1].getText()) &&
                buttons[0][0].getText().equals(buttons[2][2].getText()) &&
                !buttons[0][0].getText().equals("")) {
            return true;
        }
        if (buttons[0][2].getText().equals(buttons[1][1].getText()) &&
                buttons[0][2].getText().equals(buttons[2][0].getText()) &&
                !buttons[0][2].getText().equals("")) {
            return true;
        }
        return false;
    }

    private void resetBoard() {
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                buttons[i][j].setText("");
            }
        }
        playerX = true;
        moves = 0;
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            TicTacToeGUI game = new TicTacToeGUI();
            game.setVisible(true);
        });
    }
}
