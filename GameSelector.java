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

                runGame("CollisionGame");

            }

        });

        

        pongButton.addActionListener(new ActionListener() {

            public void actionPerformed(ActionEvent e) {

                runGame("PongGame");

            }

        });

        

        snakeButton.addActionListener(new ActionListener() {

            public void actionPerformed(ActionEvent e) {

                runGame("SnakeGame");

            }

        });

        

        ticTacToeButton.addActionListener(new ActionListener() {

            public void actionPerformed(ActionEvent e) {

                runGame("TicTacToe");

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