package main;

import javax.swing.*;
import java.awt.*;
import java.io.*;
import java.util.Scanner;
import java.util.Vector;

public class HighScorePanel extends JPanel {
    static final int screenWidth = 540, screenHeight = 630;

    private static Vector<Integer> Ranking = new Vector<>();
    static Vector<String> WholeRanking = new Vector<>();
    static JList<String> j; static JScrollPane scrollPane;

    public HighScorePanel() {
        this.setPreferredSize(new Dimension(screenWidth, screenHeight));
        this.setAlignmentX(CENTER_ALIGNMENT);
        this.setAlignmentY(BOTTOM_ALIGNMENT);
        this.setFocusable(true);
        this.setBackground(Color.BLACK);
        setLayout(null);


        Scanner input;
        File Scores = new File("src/sprites/HighScore.txt");
        try {
            input = new Scanner(Scores);
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }

        WholeRanking.clear();
        Ranking.clear();

        String s = "";

        while (input.hasNext()) {
            if (!s.isEmpty()) s += " " + input.next();
            else s = input.next();
            if (!input.hasNextInt()) continue;
            else {
                int x = input.nextInt();
                Ranking.add(x);
                WholeRanking.add(s+" "+x);
                s = "";
            }
        }
        input.close();

        j = new JList<>(WholeRanking.toArray(new String[0]));
        scrollPane = new JScrollPane(j);
        scrollPane.setBounds(190, 150, 200, 300);

        JButton buttonBack = new JButton("Back");
        buttonBack.setBounds(240, 500, 100, 50);
        buttonBack.addActionListener(e -> MenuPanel.High = false);


        JLabel name = new JLabel("PACMAN");
        name.setFont(new Font("Serif", Font.BOLD, 130));
        name.setForeground(Color.WHITE);
        name.setBounds(0,0, 700, 120);


        this.add(name);
        this.add(buttonBack);
        this.add(scrollPane, BorderLayout.CENTER);

    }


    public static Vector<Integer> getRanking() {
        return Ranking;
    }
    public static Vector<String> getWholeRanking() {
        return WholeRanking;
    }
    public static void ChangeRanking(Vector<String> NewRanking, Vector<Integer> NewRankingInts) {
        WholeRanking = NewRanking;
        Ranking =  NewRankingInts;

        File Scores = new File("src/sprites/HighScore.txt");
        FileWriter fileWriter;
        try {
            fileWriter = new FileWriter(Scores, false);
            for (String s: WholeRanking) {
                fileWriter.write(s+ " ");
            }
            fileWriter.close();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public  void loadHighScore() {
        Scanner input;
        File Scores = new File("src/sprites/HighScore.txt");
        try {
            input = new Scanner(Scores);
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }

        WholeRanking.clear();
        Ranking.clear();

        String s = "";

        while (input.hasNext()) {
            if (!s.isEmpty()) s += " " + input.next();
            else s = input.next();
            if (!input.hasNextInt()) continue;
            else {
                int x = input.nextInt();
                Ranking.add(x);
                WholeRanking.add(s+" "+x);
                s = "";
            }
        }
        input.close();

        updateScrollPane();
    }

    public void updateScrollPane() {
        this.remove(scrollPane);

        j = new JList<>(WholeRanking.toArray(new String[0]));
        scrollPane = new JScrollPane(j);
        scrollPane.setBounds(190, 150, 200, 300);

        this.add(scrollPane, BorderLayout.CENTER);

        this.repaint();
        this.revalidate();
    }


}
