package main;

import javax.swing.*;
import java.awt.Rectangle;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.util.Vector;

public class GamePanelFour extends GamePanel {
    public static final short ScreenWidth = 1012, ScreenHeight = 377;
    KeyHandler keyH; Player player;
    EnemySmallMapOne enemyOne; EnemySmallMapTwo enemyTwo;

    public GamePanelFour(KeyHandler keyH) {

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
        rec = new Rectangle(1008, 35, 25, 213);
        Walls.add(rec);
        rec = new Rectangle(984, 95, 27, 53);
        Walls.add(rec);
        rec = new Rectangle(947, 271, 86, 7);
        Walls.add(rec);
        rec = new Rectangle(1008, 278, 8, 92);
        Walls.add(rec);
        rec = new Rectangle(875, 337, 7, 32);
        Walls.add(rec);
        rec = new Rectangle(802, 337, 8, 32);
        Walls.add(rec);
        rec = new Rectangle(972, 311, 8, 35);
        Walls.add(rec);
        rec = new Rectangle(946, 304, 34, 7);
        Walls.add(rec);
        rec = new Rectangle(919, 338, 26, 7);
        Walls.add(rec);
        rec = new Rectangle(911, 304, 8, 41);
        Walls.add(rec);
        rec = new Rectangle(838, 311, 8, 35);
        Walls.add(rec);
        rec = new Rectangle(801, 304, 83, 7);
        Walls.add(rec);
        rec = new Rectangle(801, 271, 83, 7);
        Walls.add(rec);
        rec = new Rectangle(838, 237, 8, 34);
        Walls.add(rec);
        rec = new Rectangle(910, 246, 11, 33);
        Walls.add(rec);
        rec = new Rectangle(874, 238, 47, 8);
        Walls.add(rec);
        rec = new Rectangle(948, 238, 31, 8);
        Walls.add(rec);
        rec = new Rectangle(971, 204, 8, 34);
        Walls.add(rec);
        rec = new Rectangle(918, 205, 26, 8);
        Walls.add(rec);
        rec = new Rectangle(909, 174, 8, 40);
        Walls.add(rec);
        rec = new Rectangle(955, 173, 26, 7);
        Walls.add(rec);
        rec = new Rectangle(948, 141, 7, 39);
        Walls.add(rec);
        rec = new Rectangle(801, 173, 83, 41);
        Walls.add(rec);
        rec = new Rectangle(800, 127, 84, 20);
        Walls.add(rec);
        rec = new Rectangle(910, 62, 8, 85);
        Walls.add(rec);
        rec = new Rectangle(875, 61, 35, 8);
        Walls.add(rec);
        rec = new Rectangle(947, 69, 7, 45);
        Walls.add(rec);
        rec = new Rectangle(947, 62, 33, 8);
        Walls.add(rec);
        rec = new Rectangle(802, 95, 82, 8);
        Walls.add(rec);
        rec = new Rectangle(837, 61, 8, 34);
        Walls.add(rec);
        rec = new Rectangle(766, 62, 44, 8);
        Walls.add(rec);
        rec = new Rectangle(766, 70, 7, 77);
        Walls.add(rec);
        rec = new Rectangle(765, 173, 7, 40);
        Walls.add(rec);
        rec = new Rectangle(741, 205, 24, 8);
        Walls.add(rec);
        rec = new Rectangle(773, 238, 38, 8);
        Walls.add(rec);
        rec = new Rectangle(766, 238, 7, 41);
        Walls.add(rec);
        rec = new Rectangle(765, 303, 7, 42);
        Walls.add(rec);
        rec = new Rectangle(740, 338, 26, 7);
        Walls.add(rec);
        rec = new Rectangle(704, 304, 7, 42);
        Walls.add(rec);
        rec = new Rectangle(711, 304, 27, 7);
        Walls.add(rec);
        rec = new Rectangle(672, 345, 5, 25);
        Walls.add(rec);
        rec = new Rectangle(672, 271, 5, 31);
        Walls.add(rec);
        rec = new Rectangle(677, 271, 61, 7);
        Walls.add(rec);
        rec = new Rectangle(712, 238, 26, 8);
        Walls.add(rec);
        rec = new Rectangle(705, 202, 7, 41);
        Walls.add(rec);
        rec = new Rectangle(645, 204, 32, 44);
        Walls.add(rec);
        rec = new Rectangle(702, 172, 35, 5);
        Walls.add(rec);
        rec = new Rectangle(730, 134, 7, 38);
        Walls.add(rec);
        rec = new Rectangle(726, 64, 8, 15);
        Walls.add(rec);
        rec = new Rectangle(698, 59, 36, 5);
        Walls.add(rec);
        rec = new Rectangle(613, 59, 36, 5);
        Walls.add(rec);
        rec = new Rectangle(613, 59, 8, 20);
        Walls.add(rec);
        rec = new Rectangle(614, 172, 35, 5);
        Walls.add(rec);
        rec = new Rectangle(614, 134, 7, 38);
        Walls.add(rec);
        rec = new Rectangle(654, 90, 41, 55);
        Walls.add(rec);

        rec = new Rectangle(634, 305, 9, 42);
        Walls.add(rec);
        rec = new Rectangle(610, 338, 24, 9);
        Walls.add(rec);
        rec = new Rectangle(620, 272, 23, 9);
        Walls.add(rec);
        rec = new Rectangle(610, 239, 10, 42);
        Walls.add(rec);
        rec = new Rectangle(547, 239, 36, 9);
        Walls.add(rec);
        rec = new Rectangle(538, 239, 9, 42);
        Walls.add(rec);
        rec = new Rectangle(574, 272, 9, 75);
        Walls.add(rec);
        rec = new Rectangle(538, 305, 69, 9);
        Walls.add(rec);
        rec = new Rectangle(466, 338, 81, 6);
        Walls.add(rec);
        rec = new Rectangle(502, 305, 10, 33);
        Walls.add(rec);
        rec = new Rectangle(501, 238, 11, 43);
        Walls.add(rec);
        rec = new Rectangle(466, 249, 10, 32);
        Walls.add(rec);
        rec = new Rectangle(430, 239, 46, 10);
        Walls.add(rec);
        rec = new Rectangle(430, 273, 11, 74);
        Walls.add(rec);
        rec = new Rectangle(407, 305, 69, 10);
        Walls.add(rec);
        rec = new Rectangle(378, 338, 26, 9);
        Walls.add(rec);
        rec = new Rectangle(371, 304, 7, 43);
        Walls.add(rec);
        rec = new Rectangle(334, 346, 8, 24);
        Walls.add(rec);
        rec = new Rectangle(370, 273, 35, 8);
        Walls.add(rec);
        rec = new Rectangle(393, 239, 12, 34);
        Walls.add(rec);
        rec = new Rectangle(334, 206, 34, 42);
        Walls.add(rec);
        rec = new Rectangle(394, 207, 46, 7);
        Walls.add(rec);
        rec = new Rectangle(429, 173, 11, 34);
        Walls.add(rec);
        rec = new Rectangle(574, 206, 45, 9);
        Walls.add(rec);
        rec = new Rectangle(574, 173, 10, 33);
        Walls.add(rec);
        rec = new Rectangle(463, 209, 83, 4);
        Walls.add(rec);
        rec = new Rectangle(541, 172, 5, 37);
        Walls.add(rec);
        rec = new Rectangle(463, 172, 4, 37);
        Walls.add(rec);
        rec = new Rectangle(518, 172, 23, 4);
        Walls.add(rec);
        rec = new Rectangle(467, 172, 23, 4);
        Walls.add(rec);
        rec = new Rectangle(465, 129, 82, 20);
        Walls.add(rec);
        rec = new Rectangle(465, 94, 82, 11);
        Walls.add(rec);
        rec = new Rectangle(503, 62, 7, 32);
        Walls.add(rec);
        rec = new Rectangle(574, 70, 9, 79);
        Walls.add(rec);
        rec = new Rectangle(537, 63, 46, 7);
        Walls.add(rec);
        rec = new Rectangle(429, 64, 48, 8);
        Walls.add(rec);
        rec = new Rectangle(429, 72, 12, 77);
        Walls.add(rec);
        rec = new Rectangle(391, 62, 8, 20);
        Walls.add(rec);
        rec = new Rectangle(363, 62, 28, 5);
        Walls.add(rec);
        rec = new Rectangle(286, 62, 28, 5);
        Walls.add(rec);
        rec = new Rectangle(278, 62, 8, 20);
        Walls.add(rec);
        rec = new Rectangle(395, 137, 7, 43);
        Walls.add(rec);
        rec = new Rectangle(279, 137, 7, 43);
        Walls.add(rec);
        rec = new Rectangle(367, 175, 28, 5);
        Walls.add(rec);
        rec = new Rectangle(286, 175, 28, 5);
        Walls.add(rec);
        rec = new Rectangle(319, 93, 41, 55);
        Walls.add(rec);
        rec = new Rectangle(297, 204, 8, 42);
        Walls.add(rec);
        rec = new Rectangle(274, 238, 23, 8);
        Walls.add(rec);
        rec = new Rectangle(334, 271, 8, 36);
        Walls.add(rec);
        rec = new Rectangle(273, 271, 61, 7);
        Walls.add(rec);
        rec = new Rectangle(298, 304, 8, 42);
        Walls.add(rec);
        rec = new Rectangle(272, 304, 26, 7);
        Walls.add(rec);
        rec = new Rectangle(245, 338, 26, 7);
        Walls.add(rec);
        rec = new Rectangle(237, 304, 8, 41);
        Walls.add(rec);
        rec = new Rectangle(201, 337, 7, 34);
        Walls.add(rec);
        rec = new Rectangle(128, 337, 8, 32);
        Walls.add(rec);
        rec = new Rectangle(164, 311, 8, 35);
        Walls.add(rec);
        rec = new Rectangle(127, 304, 83, 7);
        Walls.add(rec);
        rec = new Rectangle(127, 271, 83, 7);
        Walls.add(rec);
        rec = new Rectangle(164, 237, 8, 34);
        Walls.add(rec);
        rec = new Rectangle(236, 238, 11, 41);
        Walls.add(rec);
        rec = new Rectangle(199, 238, 37, 8);
        Walls.add(rec);
        rec = new Rectangle(99, 238, 38, 8);
        Walls.add(rec);
        rec = new Rectangle(92, 238, 7, 41);
        Walls.add(rec);
        rec = new Rectangle(91, 303, 7, 42);
        Walls.add(rec);
        rec = new Rectangle(65, 338, 26, 7);
        Walls.add(rec);
        rec = new Rectangle(30, 304, 7, 42);
        Walls.add(rec);
        rec = new Rectangle(37, 304, 27, 7);
        Walls.add(rec);
        rec = new Rectangle(-20, 271, 23, 99);
        Walls.add(rec);
        rec = new Rectangle(3, 271, 61, 7);
        Walls.add(rec);
        rec = new Rectangle(38, 238, 26, 8);
        Walls.add(rec);
        rec = new Rectangle(31, 205, 7, 41);
        Walls.add(rec);
        rec = new Rectangle(-20, 37, 23, 211);
        Walls.add(rec);
        rec = new Rectangle(3, 94, 24, 52);
        Walls.add(rec);
        rec = new Rectangle(67, 205, 31, 8);
        Walls.add(rec);
        rec = new Rectangle(91, 173, 7, 32);
        Walls.add(rec);
        rec = new Rectangle(30, 173, 32, 7);
        Walls.add(rec);
        rec = new Rectangle(54, 140, 8, 33);
        Walls.add(rec);
        rec = new Rectangle(54, 62, 8, 52);
        Walls.add(rec);
        rec = new Rectangle(30, 62, 24, 8);
        Walls.add(rec);
        rec = new Rectangle(92, 62, 44, 8);
        Walls.add(rec);
        rec = new Rectangle(92, 70, 7, 77);
        Walls.add(rec);
        rec = new Rectangle(236, 62, 8, 85);
        Walls.add(rec);
        rec = new Rectangle(200, 62, 36, 8);
        Walls.add(rec);
        rec = new Rectangle(128, 95, 82, 8);
        Walls.add(rec);
        rec = new Rectangle(163, 61, 8, 34);
        Walls.add(rec);
        rec = new Rectangle(126, 127, 84, 20);
        Walls.add(rec);
        rec = new Rectangle(127, 173, 83, 41);
        Walls.add(rec);
        rec = new Rectangle(236,205,34,8);
        Walls.add(rec);
        rec = new Rectangle(236,173,8,32);
        Walls.add(rec);

    }

    @Override
    public void drawWalls() {
        ImageIcon imageIcon = new ImageIcon("src/sprites/Maze1012_377.png");
        Maze_Back = new JLabel(imageIcon);
        Maze_Back.setBounds(0,0,1012,377);
    }

    public void closeSpawnPoint() {
        Walls.add(new Rectangle(491, 172, 26, 4));
    }
    public void OpenSpawnGhost() {
        Walls.remove(Walls.size()-1);
    }

    public void restartGame(boolean FullRestart) {

        player.setDefault();
        enemyOne.setDefault();
        enemyTwo.setDefault();

        if (Player.PointsX.isEmpty()) {
            player.setPointsPanelFour();
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

