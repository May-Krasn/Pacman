package main;

import javax.swing.*;
import java.awt.Rectangle;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.util.Vector;

public class GamePanelTwo extends GamePanel {
    public static final short ScreenWidth = 443, ScreenHeight = 622;
    KeyHandler keyH; Player player;
    EnemySmallMapOne enemyOne; EnemySmallMapTwo enemyTwo;

    public GamePanelTwo(KeyHandler keyH) {

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
        score.setBounds(6, 0, 1000, 30);
        this.add(score);

        time.setForeground(Color.WHITE);
        time.setFont(new Font("Serif", Font.BOLD, 22));
        time.setBounds(140, 0, 150, 30);
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

    @Override
    public void setWalls() {
        Rectangle rec = new Rectangle(114 , 68, 69, 97);
        Walls.add(rec);
        rec = new Rectangle(209 , 64, 86, 101);
        Walls.add(rec);
        rec = new Rectangle(324 , 108, 31, 16);
        Walls.add(rec);
        rec = new Rectangle(388 , 108, 14, 16);
        Walls.add(rec);
        rec = new Rectangle(324 , 161, 79, 51);
        Walls.add(rec);
        rec = new Rectangle(80 , 196, 249, 16);
        Walls.add(rec);
        rec = new Rectangle(37 , 161, 47, 51);
        Walls.add(rec);
        rec = new Rectangle(116 , 211, 16, 55);
        Walls.add(rec);
        rec = new Rectangle(37 , 108, 47, 16);
        Walls.add(rec);
        rec = new Rectangle(0 , 68, 9, 178);
        Walls.add(rec);
        rec = new Rectangle(0 , 245, 88, 61);
        Walls.add(rec);
        rec = new Rectangle(0, 334, 88, 77);
        Walls.add(rec);
        rec = new Rectangle(0, 410, 9, 193);
        Walls.add(rec);
        rec = new Rectangle(37, 515, 47, 52);
        Walls.add(rec);
        rec = new Rectangle(113, 510, 70, 93);
        Walls.add(rec);
        rec = new Rectangle(208, 510, 71, 93);
        Walls.add(rec);
        rec = new Rectangle(356, 515, 46, 52);
        Walls.add(rec);
        rec = new Rectangle(308, 477, 15, 90);
        Walls.add(rec);
        rec = new Rectangle(134, 462, 189, 16);
        Walls.add(rec);
        rec = new Rectangle(37, 444, 96, 34);
        Walls.add(rec);
        rec = new Rectangle(116, 391, 16, 53);
        Walls.add(rec);
        rec = new Rectangle(165, 391, 46, 34);
        Walls.add(rec);
        rec = new Rectangle(116, 302, 16, 53);
        Walls.add(rec);
        rec = new Rectangle(165, 249, 31, 72);
        Walls.add(rec);
        rec = new Rectangle(245, 352, 29, 73);
        Walls.add(rec);
        rec = new Rectangle(165, 319, 109, 36);
        Walls.add(rec);
        rec = new Rectangle(228, 249, 46, 35);
        Walls.add(rec);
        rec = new Rectangle(308, 248, 15, 17);
        Walls.add(rec);
        rec = new Rectangle(308, 302, 15, 53);
        Walls.add(rec);
        rec = new Rectangle(308, 391, 15, 34);
        Walls.add(rec);
        rec = new Rectangle(356, 444, 47, 34);
        Walls.add(rec);
        rec = new Rectangle(432, 406, 8, 200);
        Walls.add(rec);
        rec = new Rectangle(352, 334, 100, 77);
        Walls.add(rec);
        rec = new Rectangle(352, 245, 100, 61);
        Walls.add(rec);
        rec = new Rectangle(432, 69, 8, 178);
        Walls.add(rec);
    }
    @Override
    public void drawWalls() {
        ImageIcon imageIcon = new ImageIcon("src/sprites/Maze640_720.png");
        Maze_Back = new JLabel(imageIcon);
        Maze_Back.setBounds(0,0,443,622);
    }
    public void closeSpawnPoint() {
        Rectangle rec = new Rectangle(182, 160, 28, 5);
        Walls.add(rec);
        rec = new Rectangle(182, 510, 27, 5);
        Walls.add(rec);
    }

    public void OpenSpawnGhost() {
        Walls.remove(Walls.size()-1);
        Walls.remove(Walls.size()-1);
    }


    public void restartGame(boolean FullRestart) {

        player.setDefault();
        enemyOne.setDefault();
        enemyTwo.setDefault();

        if (Player.PointsX.isEmpty()) {
            player.setPointsPanelTwo();
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
