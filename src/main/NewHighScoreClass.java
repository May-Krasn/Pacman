package main;

import javax.swing.*;
import java.awt.*;

public class NewHighScoreClass {
    static JFrame f;
    static JTextField newname;
    static JButton ButSubmit;
    static JLabel label;

    public NewHighScoreClass(JPanel CurrentPanel) {

        JPanel panel = new JPanel();
        JFrame f = new JFrame();

        panel.setSize(600, 200);
        panel.setFocusable(true);
        panel.setBackground(Color.BLACK);
        panel.setLayout(null);

        f.setTitle("New High Score Time");
        f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        f.setSize(600, 200);
        f.setResizable(false);
        f.setLocationRelativeTo(null);
        f.setVisible(true);
        f.add(panel);

        JLabel label = new JLabel("Enter Your Name");
        label.setFont(new Font("Arial", Font.BOLD, 25));
        label.setForeground(Color.WHITE);
        label.setBounds(170, 10, 250, 30);

        JTextField newname = new JTextField(16);
        newname.setBounds(10,75,490,50);

        JButton ButSubmit = new JButton("submit");
        ButSubmit.setBounds(510, 75, 75, 50);

        panel.add(label);
        panel.add(newname);
        panel.add(ButSubmit);

        f.repaint();
        f.revalidate();

        String s = "";


    }


}
