package main;

import javax.swing.*;

public class GameFrame extends JFrame {

    public GameFrame(JPanel GamePanel) {
        this.setTitle("We love, We stan Pacman <3");
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setSize(600, 700);     // 600 x 700
        this.setResizable(false);
        this.setLocationRelativeTo(null);

        ImageIcon logo = new ImageIcon("src/sprites/duch1.png");
        this.setIconImage(logo.getImage());

        this.add(GamePanel);



        this.setVisible(true);
    }

}
