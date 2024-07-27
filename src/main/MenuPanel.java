package main;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.io.File;
import java.io.IOException;

import static java.lang.System.exit;

public class MenuPanel extends JPanel {

    static final int screenWidth = 540, screenHeight = 630;
    static boolean High = false, start = false, Help = false;
    private static Maze Current_Maze = Maze.Third;
    JLabel Rozmiar = new JLabel("Maze: 338 x 421");

    public MenuPanel() {

        this.setPreferredSize(new Dimension(screenWidth, screenHeight));
        this.setAlignmentX(CENTER_ALIGNMENT);
        this.setAlignmentY(BOTTOM_ALIGNMENT);
        this.setFocusable(true);
        this.setBackground(Color.BLACK);

        Rozmiar.setBounds(220, 245, 300, 50);
        Rozmiar.setForeground(Color.WHITE);
        Rozmiar.setFont(new Font("Arial", Font.PLAIN, 20));

        setLayout(null);

        ImageIcon ArrowR = new ImageIcon("src/sprites/arrowRight.png");
        ImageIcon ArrowL = new ImageIcon("src/sprites/arrowLeft.png");

        JButton buttonStart = new JButton("Start");
        JButton buttonChangeRight = new JButton(ArrowR);
        JButton buttonChangeLeft = new JButton(ArrowL);
        JButton buttonHelp = new JButton("Help");
        JButton buttonScore = new JButton("High Score");
        JButton buttonExit = new JButton("Exit");
        buttonStart.setBounds(240, 180, 100, 50);
        buttonChangeLeft.setBounds(150, 250, 45, 45);
        buttonChangeRight.setBounds(390, 250, 45, 45);
        buttonHelp.setBounds(240, 330, 100, 50);
        buttonScore.setBounds(240, 410, 100, 50);
        buttonExit.setBounds(240, 490, 100, 50);

        buttonStart.addActionListener(e -> start = true);

        buttonChangeLeft.addActionListener(e -> {

            switch (Current_Maze) {
                case First:
                    Current_Maze = Maze.Second;
                    Rozmiar.setText("Maze: 443 x 622");
                    break;
                case Second:
                    Current_Maze = Maze.Third;
                        Rozmiar.setText("Maze: 338 x 421");
                    break;
                case Third:
                    Current_Maze = Maze.Fifth;
                    Rozmiar.setText("Maze: 338 x 241");
                    break;
                case Fourth:
                    Current_Maze = Maze.First;
                    Rozmiar.setText("Maze: 809 x 296");
                    break;
                case Fifth:
                    Current_Maze = Maze.Fourth;
                    Rozmiar.setText("Maze: 1012 x 377");
                    break;
            }
        });
        buttonChangeRight.addActionListener(e -> {
            switch (Current_Maze) {
                case First:
                    Current_Maze = Maze.Fourth;
                    Rozmiar.setText("Maze: 1012 x 377");
                    break;
                case Second:
                    Current_Maze = Maze.First;
                    Rozmiar.setText("Maze: 809 x 296");
                    break;
                case Third:
                    Current_Maze = Maze.Second;
                    Rozmiar.setText("Maze: 443 x 622");
                    break;
                case Fourth:
                    Current_Maze = Maze.Fifth;
                    Rozmiar.setText("Maze: 338 x 241");
                    break;
                case Fifth:
                    Current_Maze = Maze.Third;
                    Rozmiar.setText("Maze: 338 x 421");
                    break;
            }
        });

        buttonHelp.addActionListener(e -> Help = true);
        buttonScore.addActionListener(e -> High = true);
        buttonExit.addActionListener(e -> exit(0));


        JLabel name = new JLabel("PACMAN");
        name.setFont(new Font("Serif", Font.BOLD, 130));
        name.setForeground(Color.WHITE);
        name.setBounds(0,0, 700, 120);

        this.add(name);
        this.add(Rozmiar);
        this.add(buttonStart);
        this.add(buttonChangeLeft);
        this.add(buttonChangeRight);
        this.add(buttonHelp);
        this.add(buttonScore);
        this.add(buttonExit);


    }

    public boolean getHigh() {
        return High;
    }

    public boolean isHelp() {
        return Help;
    }

    public boolean getStart() {
        return start;
    }
    public void ChangeStart() {
        start = false;
    }
    public Maze getCurrent_Maze() {
        return Current_Maze;
    }

}
