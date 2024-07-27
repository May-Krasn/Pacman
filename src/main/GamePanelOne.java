package main;

import javax.swing.*;
import java.awt.Rectangle;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.util.Vector;

public class GamePanelOne extends GamePanel {
    public static final short ScreenWidth = 809, ScreenHeight = 296;
    KeyHandler keyH; Player player;
    EnemySmallMapOne enemyOne; EnemySmallMapTwo enemyTwo;

    public GamePanelOne(KeyHandler keyH) {

        this.keyH = keyH;
        this.setLayout(null);

        this.player = new Player(this, keyH);
        this.enemyOne = new EnemySmallMapOne(this, player);
        this.enemyTwo = new EnemySmallMapTwo(this, player);
        this.setBackground(Color.BLACK);

        this.setPreferredSize(new Dimension(ScreenWidth, ScreenHeight));
        this.setAlignmentX(CENTER_ALIGNMENT);
        this.setAlignmentY(BOTTOM_ALIGNMENT);
        this.addKeyListener(keyH);

        score.setForeground(Color.WHITE);
        score.setFont(new Font("Serif", Font.BOLD, 22));
        score.setBounds(15, 0, 1000, 30);
        this.add(score);

        time.setForeground(Color.WHITE);
        time.setFont(new Font("Serif", Font.BOLD, 22));
        time.setBounds(345, 0, 150, 30);
        this.add(time);

        setWalls();
        drawWalls();

        for (int i = 0; i < Player.PointsX.size(); i++) {
            pointlabel.add(new JLabel(new ImageIcon("src/sprites/point.png")));
            pointlabel.get(pointlabel.size()-1)
                    .setBounds(Player.PointsX.get(i), Player.PointsY.get(i), 6, 6);
            this.add(pointlabel.get(pointlabel.size()-1));
        }

        this.add(Maze_Back);
    }
    public void updatePanel() {

        String CurrentTime = "";
        if (Seconds/60 < 10) CurrentTime += "0" + Seconds/60;
        else CurrentTime += Seconds/60;
        if (Seconds%60 < 10) CurrentTime += ":0" + Seconds%60;
        else CurrentTime += ":" + Seconds%60;
        time.setText("Timer: " + CurrentTime);
        score.setText("Score: " + PointsEarned);

        if (Player.PointsX.isEmpty()) RestartGame = true;

        if (BonusCounter != Game.Bonuses.size()) {
            for (int i = BonusCounter; i < Game.Bonuses.size(); i++) {
                Game.Bonuses.get(i).setBounds(Game.BonusesX.get(i),
                        Game.BonusesY.get(i), 20, 20);
                this.add(Game.Bonuses.get(i));
            }
            BonusCounter = Game.Bonuses.size();
            this.remove(Maze_Back);
            this.add(Maze_Back);
        }



    }
    public void setWalls() {
        Rectangle rec = new Rectangle(34 , 74, 28, 13);
        Walls.add(rec);
        rec = new Rectangle(341, 114, 84, 84);
        Walls.add(rec);
        rec = new Rectangle(229, 114, 84, 84);
        Walls.add(rec);
        rec = new Rectangle(90, 73, 112, 29);
        Walls.add(rec);
        rec = new Rectangle(90, 170, 112, 28);
        Walls.add(rec);
        rec = new Rectangle(172, 129, 30, 40);
        Walls.add(rec);
        rec = new Rectangle(147, 129, 24, 13);
        Walls.add(rec);
        rec = new Rectangle(90, 103, 31, 66);
        Walls.add(rec);
        rec = new Rectangle(0, 114, 63, 28);
        Walls.add(rec);
        rec = new Rectangle(0, 170, 63, 28);
        Walls.add(rec);
        rec = new Rectangle(35, 226, 40, 14);
        Walls.add(rec);
        rec = new Rectangle(105, 226, 124, 14);
        Walls.add(rec);
        rec = new Rectangle(257, 226, 15, 44);
        Walls.add(rec);
        rec = new Rectangle(299, 226, 125, 14);
        Walls.add(rec);
        rec = new Rectangle(452, 239, 13, 31);
        Walls.add(rec);
        rec = new Rectangle(535, 239, 14, 31);
        Walls.add(rec);
        rec = new Rectangle(577, 226, 111, 15);
        Walls.add(rec);
        rec = new Rectangle(716, 226, 56, 15);
        Walls.add(rec);
        rec = new Rectangle(452, 199, 97, 15);
        Walls.add(rec);
        rec = new Rectangle(493, 212, 15, 26);
        Walls.add(rec);
        rec = new Rectangle(740, 170, 68, 28);
        Walls.add(rec);
        rec = new Rectangle(740, 115, 68, 27);
        Walls.add(rec);
        rec = new Rectangle(633, 115, 83, 42);
        Walls.add(rec);
        rec = new Rectangle(633, 158, 29, 40);
        Walls.add(rec);
        rec = new Rectangle(663, 184, 53, 14);
        Walls.add(rec);
        rec = new Rectangle(577, 74, 28, 124);
        Walls.add(rec);
        rec = new Rectangle(631, 74, 57, 13);
        Walls.add(rec);
        rec = new Rectangle(716, 74, 57, 13);
        Walls.add(rec);
        rec = new Rectangle(450, 74, 101, 13);
        Walls.add(rec);
        rec = new Rectangle(227, 74, 198, 13);
        Walls.add(rec);

        rec = new Rectangle(452, 117, 32, 6);
        Walls.add(rec);
        rec = new Rectangle(452, 117, 7, 53);
        Walls.add(rec);
        rec = new Rectangle(452, 164, 97, 6);
        Walls.add(rec);
        rec = new Rectangle(542, 117, 7, 53);
        Walls.add(rec);
        rec = new Rectangle(517, 117, 31, 6);
        Walls.add(rec);

        rec = new Rectangle(0, 40, 12, 80);
        Walls.add(rec);
        rec = new Rectangle(0, 185, 12, 87);
        Walls.add(rec);
        rec = new Rectangle(794, 40, 12, 80);
        Walls.add(rec);
        rec = new Rectangle(794, 185, 12, 87);
        Walls.add(rec);
    }

    @Override
    public void drawWalls() {
        ImageIcon imageIcon = new ImageIcon("src/sprites/Maze809_296.png");
        Maze_Back = new JLabel(imageIcon);
        Maze_Back.setBounds(0,0,809,296);
    }

    public void closeSpawnPoint() {
        Walls.add(new Rectangle(482, 117, 37, 6));
    }
    public void OpenSpawnGhost() {
        Walls.remove(Walls.size()-1);
    }

    public void restartGame(boolean FullRestart) {

        player.setDefault();
        enemyOne.setDefault();
        enemyTwo.setDefault();

        if (Player.PointsX.isEmpty() && !FullRestart) {
            player.setPointsPanelOne();
            for (int i = 0; i < Player.PointsX.size(); i++) {
                pointlabel.add(new JLabel(new ImageIcon("src/sprites/point.png")));
                pointlabel.get(pointlabel.size()-1)
                        .setBounds(Player.PointsX.get(i), Player.PointsY.get(i), 6, 6);
                this.add(pointlabel.get(pointlabel.size()-1));
            }
            this.remove(Maze_Back);
            this.add(Maze_Back);
        }

        if (FullRestart){
            for (int i = 0; i < Player.PointsX.size(); i++) {
                this.remove(pointlabel.get(i));
            }
            Walls.clear();
            Player.PointsX.clear();
            Player.PointsY.clear();
            Player.Score.clear();
            pointlabel.clear();
            Game.Bonuses.clear();
            Game.BonusesY.clear();
            Game.BonusesX.clear();
            Game.BonusesScore.clear();
        }

        RestartGame = false;
    }


    public Player getPlayer() {
        return player;
    }
    public EnemySmallMapOne getEnemyOne() {
        return enemyOne;
    }
    public EnemySmallMapTwo getEnemyTwo() {
        return enemyTwo;
    }
    public static Vector<Rectangle> getWalls() {
        return Walls;
    }
    public int getPointsEarned() {
        return PointsEarned;
    }
}
