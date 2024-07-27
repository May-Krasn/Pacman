package main;

import javax.swing.*;
import java.awt.Rectangle;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.util.Vector;

public class GamePanelThree extends GamePanel {
    public static final short ScreenWidth = 338, ScreenHeight = 422;
    KeyHandler keyH; Player player;
    EnemySmallMapOne enemyOne; EnemySmallMapTwo enemyTwo;

    public GamePanelThree(KeyHandler keyH) {

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
        time.setBounds(100, 390, 1050, 30);
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
        Rectangle rec = new Rectangle(127 , 173, 27, 4);
        Walls.add(rec);
        rec = new Rectangle(127, 177, 4, 33);
        Walls.add(rec);
        rec = new Rectangle(127, 210, 83, 4);
        Walls.add(rec);
        rec = new Rectangle(182, 173, 28, 4);
        Walls.add(rec);
        rec = new Rectangle(205, 177, 5, 33);
        Walls.add(rec);
        rec = new Rectangle(236, 173, 8, 40);
        Walls.add(rec);
        rec = new Rectangle(244, 205, 26, 8);
        Walls.add(rec);
        rec = new Rectangle(297, 204, 8, 42);
        Walls.add(rec);
        rec = new Rectangle(274, 238, 23, 8);
        Walls.add(rec);
        rec = new Rectangle(199, 238, 48, 8);
        Walls.add(rec);
        rec = new Rectangle(236, 246, 11, 33);
        Walls.add(rec);
        rec = new Rectangle(273, 271, 61, 7);
        Walls.add(rec);
        rec = new Rectangle(334, 271, 8, 101);
        Walls.add(rec);
        rec = new Rectangle(272, 304, 34, 7);
        Walls.add(rec);
        rec = new Rectangle(298, 311, 8, 35);
        Walls.add(rec);
        rec = new Rectangle(237, 338, 34, 7);
        Walls.add(rec);
        rec = new Rectangle(237, 304, 8, 34);
        Walls.add(rec);
        rec = new Rectangle(201, 337, 7, 33);
        Walls.add(rec);
        rec = new Rectangle(128, 337, 8, 33);
        Walls.add(rec);
        rec = new Rectangle(164, 311, 8, 35);
        Walls.add(rec);
        rec = new Rectangle(127, 304, 83, 7);
        Walls.add(rec);
        rec = new Rectangle(164, 237, 8, 34);
        Walls.add(rec);
        rec = new Rectangle(127, 271, 83, 7);
        Walls.add(rec);
        rec = new Rectangle(92, 238, 45, 8);
        Walls.add(rec);
        rec = new Rectangle(92, 246, 7, 33);
        Walls.add(rec);
        rec = new Rectangle(91, 303, 7, 42);
        Walls.add(rec);
        rec = new Rectangle(65, 338, 26, 7);
        Walls.add(rec);
        rec = new Rectangle(30, 304, 34, 7);
        Walls.add(rec);
        rec = new Rectangle(30, 311, 7, 35);
        Walls.add(rec);
        rec = new Rectangle(0, 278, 3, 92);
        Walls.add(rec);
        rec = new Rectangle(-20, 271, 84, 7);
        Walls.add(rec);
        rec = new Rectangle(31, 238, 33, 8);
        Walls.add(rec);
        rec = new Rectangle(31, 205, 7, 33);
        Walls.add(rec);
        rec = new Rectangle(67, 205, 31, 8);
        Walls.add(rec);
        rec = new Rectangle(91, 173, 7, 32);
        Walls.add(rec);
        rec = new Rectangle(30, 173, 32, 7);
        Walls.add(rec);
        rec = new Rectangle(54, 140, 8, 33);
        Walls.add(rec);
        rec = new Rectangle(-20, 138, 48, 8);
        Walls.add(rec);
        rec = new Rectangle(-20, 146, 23, 90);
        Walls.add(rec);
        rec = new Rectangle(-20, 94, 43, 18);
        Walls.add(rec);
        rec = new Rectangle(0, 36, 3, 58);
        Walls.add(rec);
        rec = new Rectangle(54, 70, 8, 44);
        Walls.add(rec);
        rec = new Rectangle(31, 62, 31, 8);
        Walls.add(rec);
        rec = new Rectangle(92, 70, 7, 77);
        Walls.add(rec);
        rec = new Rectangle(92, 62, 44, 8);
        Walls.add(rec);
        rec = new Rectangle(163, 61, 8, 34);
        Walls.add(rec);
        rec = new Rectangle(128, 95, 82, 8);
        Walls.add(rec);
        rec = new Rectangle(126, 127, 84, 20);
        Walls.add(rec);
        rec = new Rectangle(236, 70, 8, 77);
        Walls.add(rec);
        rec = new Rectangle(201, 62, 43, 8);
        Walls.add(rec);
        rec = new Rectangle(273, 70, 7, 44);
        Walls.add(rec);
        rec = new Rectangle(273, 62, 33, 8);
        Walls.add(rec);
        rec = new Rectangle(274, 140, 7, 33);
        Walls.add(rec);
        rec = new Rectangle(274, 173, 33, 7);
        Walls.add(rec);
        rec = new Rectangle(334, 37, 3, 58);
        Walls.add(rec);
        rec = new Rectangle(310, 95, 42, 17);
        Walls.add(rec);
        rec = new Rectangle(311, 138, 43, 10);
        Walls.add(rec);
        rec = new Rectangle(334, 148, 13, 99);
        Walls.add(rec);
    }
    @Override
    public void drawWalls() {
        ImageIcon imageIcon = new ImageIcon("src/sprites/Maze338_421.png");
        Maze_Back = new JLabel(imageIcon);
        Maze_Back.setBounds(0,0,338,421);
    }


    public void closeSpawnPoint() {
        Rectangle rec = new Rectangle(155, 173, 26, 4);
        Walls.add(rec);
    }

    public void OpenSpawnGhost() {
        Walls.remove(Walls.size()-1);
    }


    public void restartGame(boolean FullRestart) {

        player.setDefault();
        enemyOne.setDefault();
        enemyTwo.setDefault();

        if (Player.PointsX.isEmpty()) {
            player.setPointsPanelThree();
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
