package main;

import javax.swing.*;
import java.awt.*;

public class HelpPanel extends JPanel {

    static final int screenWidth = 540;
    static final int screenHeight = 630;

    public HelpPanel() {
        this.setPreferredSize(new Dimension(screenWidth, screenHeight));
        this.setAlignmentX(CENTER_ALIGNMENT);
        this.setAlignmentY(BOTTOM_ALIGNMENT);
        this.setFocusable(true);
        this.setBackground(Color.BLACK);
        setLayout(null);

        JLabel Bonus1Image = new JLabel(new ImageIcon("src/sprites/strawberry.png"));
        Bonus1Image.setBounds(30, 180, 20, 20);
        JLabel Bonus1Text = new JLabel("Strawberry: +speed for player");
        Bonus1Text.setFont(new Font("Arial", Font.PLAIN, 25));
        Bonus1Text.setForeground(Color.WHITE);
        Bonus1Text.setBounds(60, 180, 600, 25);

        JLabel Bonus2Image = new JLabel(new ImageIcon("src/sprites/orange.png"));
        Bonus2Image.setBounds(70, 240, 20, 20);
        JLabel Bonus2Text = new JLabel("Orange: -speed for enemies");
        Bonus2Text.setFont(new Font("Arial", Font.PLAIN, 25));
        Bonus2Text.setForeground(Color.WHITE);
        Bonus2Text.setBounds(100, 240, 600, 25);

        JLabel Bonus3Image = new JLabel(new ImageIcon("src/sprites/apple.png"));
        Bonus3Image.setBounds(110, 300, 20, 20);
        JLabel Bonus3Text = new JLabel("Apple: more score for every point eaten");
        Bonus3Text.setFont(new Font("Arial", Font.PLAIN, 25));
        Bonus3Text.setForeground(Color.WHITE);
        Bonus3Text.setBounds(140, 300, 600, 25);

        JLabel Bonus4Image = new JLabel(new ImageIcon("src/sprites/fruit.png"));
        Bonus4Image.setBounds(70, 360, 20, 20);
        JLabel Bonus4Text = new JLabel("Fruit: +life (three max)");
        Bonus4Text.setFont(new Font("Arial", Font.PLAIN, 25));
        Bonus4Text.setForeground(Color.WHITE);
        Bonus4Text.setBounds(100, 360, 600, 25);

        JLabel Bonus5Image = new JLabel(new ImageIcon("src/sprites/cherry.png"));
        Bonus5Image.setBounds(30, 420, 20, 20);
        JLabel Bonus5Text = new JLabel("Cherry: freeze enemies for a few seconds");
        JLabel Bonus5TextAdd = new JLabel("(don't come to them pls)");
        Bonus5Text.setFont(new Font("Arial", Font.PLAIN, 25));
        Bonus5Text.setForeground(Color.WHITE);
        Bonus5Text.setBounds(60, 420, 600, 25);
        Bonus5TextAdd.setForeground(Color.WHITE);
        Bonus5TextAdd.setBounds(190, 435, 600, 25);


        JButton buttonBack = new JButton("Back");
        buttonBack.setBounds(240, 500, 100, 50);
        buttonBack.addActionListener(e -> MenuPanel.Help = false);

        JLabel name = new JLabel("PACMAN");
        name.setFont(new Font("Serif", Font.BOLD, 130));
        name.setForeground(Color.WHITE);
        name.setBounds(0,0, 700, 120);


        this.add(Bonus1Text);
        this.add(Bonus1Image);
        this.add(Bonus2Text);
        this.add(Bonus2Image);
        this.add(Bonus3Text);
        this.add(Bonus3Image);
        this.add(Bonus4Text);
        this.add(Bonus4Image);
        this.add(Bonus5Text);
        this.add(Bonus5TextAdd);
        this.add(Bonus5Image);


        this.add(name);
        this.add(buttonBack);

    }
}
