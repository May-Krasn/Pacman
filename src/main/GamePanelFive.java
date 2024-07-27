package main;

import javax.swing.*;
import java.awt.Rectangle;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.util.Vector;

public class GamePanelFive extends GamePanel {
    public static final short ScreenWidth = 338, ScreenHeight = 241;
    KeyHandler keyH; Player player;
    EnemySmallMapOne enemyOne; EnemySmallMapTwo enemyTwo;

    public GamePanelFive(KeyHandler keyH) {

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
        time.setBounds(110, 205, 150, 30);
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
        Rectangle rec = new Rectangle(332 , 145, 5, 59);
        Walls.add(rec);
        rec = new Rectangle(297,137,60,7);
        Walls.add(rec);
        rec = new Rectangle(274,170,32,7);
        Walls.add(rec);
        rec = new Rectangle(274,177,7,28);
        Walls.add(rec);
        rec = new Rectangle(238,137,7,41);
        Walls.add(rec);
        rec = new Rectangle(245,137,26,7);
        Walls.add(rec);
        rec = new Rectangle(238,105,68,7);
        Walls.add(rec);
        rec = new Rectangle(238,60,7,45);
        Walls.add(rec);
        rec = new Rectangle(273,60,34,20);
        Walls.add(rec);
        rec = new Rectangle(332,36,25,80);
        Walls.add(rec);
        rec = new Rectangle(203,66,7,47);
        Walls.add(rec);
        rec = new Rectangle(129,66,7,47);
        Walls.add(rec);
        rec = new Rectangle(129,60,81,7);
        Walls.add(rec);
        rec = new Rectangle(128,137,82,7);
        Walls.add(rec);
        rec = new Rectangle(165,93,7,44);
        Walls.add(rec);
        rec = new Rectangle(207,171,2,34);
        Walls.add(rec);
        rec = new Rectangle(130,171,2,34);
        Walls.add(rec);
        rec = new Rectangle(183,171,24,2);
        Walls.add(rec);
        rec = new Rectangle(132,171,24,2);
        Walls.add(rec);
        rec = new Rectangle(59,170,7,34);
        Walls.add(rec);
        rec = new Rectangle(32,170,27,7);
        Walls.add(rec);
        rec = new Rectangle(93,136,7,43);
        Walls.add(rec);
        rec = new Rectangle(5,137,36,7);
        Walls.add(rec);
        rec = new Rectangle(-20,137,25,68);
        Walls.add(rec);
        rec = new Rectangle(-20,35,25,82);
        Walls.add(rec);
        rec = new Rectangle(32,60,33,20);
        Walls.add(rec);
        rec = new Rectangle(32,104,68,7);
        Walls.add(rec);
        rec = new Rectangle(93,60,7,44);
        Walls.add(rec);
        rec = new Rectangle(68,136,25,7);
        Walls.add(rec);

    }

    @Override
    public void drawWalls() {
        ImageIcon imageIcon = new ImageIcon("src/sprites/Maze338_241.png");
        Maze_Back = new JLabel(imageIcon);
        Maze_Back.setBounds(0,0,338,241);
    }

    public void closeSpawnPoint() {
        Walls.add(new Rectangle(157, 170, 25, 2));
    }
    public void OpenSpawnGhost() {
        Walls.remove(Walls.size()-1);
    }

    public void restartGame(boolean FullRestart) {

        player.setDefault();
        enemyOne.setDefault();
        enemyTwo.setDefault();

        if (Player.PointsX.isEmpty()) {
            player.setPointsPanelFive();
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
