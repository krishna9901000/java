
import com.collisiongame.MyFrame;
import com.ponggame.GameFrame;
import com.tic_tac_toe.TicTacToe;

import javax.swing.*;

import java.awt.*;

import java.awt.event.*;



public class GameSelector extends JFrame {

    

    public GameSelector() {

        setTitle("Game Center");

        setSize(400, 200);

        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        setLayout(new GridLayout(4, 1));

        

        JButton collisionButton = new JButton("Collision Game");

        JButton pongButton = new JButton("Pong Game");

        JButton snakeButton = new JButton("Snake Game");

        JButton ticTacToeButton = new JButton("Tic-Tac-Toe");

        

        collisionButton.addActionListener(new ActionListener() {

            public void actionPerformed(ActionEvent e) {
                MyFrame myFrame = new MyFrame();
                runGame("CollisionGame");

            }

        });

        

        pongButton.addActionListener(new ActionListener() {

            public void actionPerformed(ActionEvent e) {

                runGame("PongGame");
                new com.ponggame.GameFrame();
            }

        });

        

        snakeButton.addActionListener(new ActionListener() {

            public void actionPerformed(ActionEvent e) {

                runGame("SnakeGame");
                new com.snakegame.GameFrame();
            }

        });

        

        ticTacToeButton.addActionListener(new ActionListener() {

            public void actionPerformed(ActionEvent e) {

                runGame("TicTacToe");
                new TicTacToe();
            }

        });

        

        add(collisionButton);

        add(pongButton);

        add(snakeButton);

        add(ticTacToeButton);

        

        setVisible(true);

    }

    

    private void runGame(String gameName) {

        try {

            Runtime.getRuntime().exec("java " + gameName);

        } catch (Exception ex) {

            ex.printStackTrace();

        }

    }

    

    public static void main(String[] args) {

        new GameSelector();

    }

}