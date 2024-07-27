package main;

import javax.swing.*;
import java.awt.Rectangle;
import java.util.Vector;

public abstract class GamePanel extends JPanel {


    public static JLabel Maze_Back;

    public static Vector<Rectangle> Walls = new Vector<>();
    static boolean RestartGame = false, GameOver = false;
    public static int PointsEarned = 0;
    public static int Seconds = 0;
    public static int BonusCounter = 0;
    public static Vector<JLabel> pointlabel = new Vector<>();


    public static JLabel score = new JLabel();
    public static JLabel time = new JLabel();



    public abstract void setWalls();
    public abstract void drawWalls();
    public abstract void restartGame(boolean FullRestart);


    public abstract Player getPlayer();
    public abstract EnemySmallMapOne getEnemyOne();
    public abstract EnemySmallMapTwo getEnemyTwo();
    public abstract int getPointsEarned();
    public abstract void closeSpawnPoint();
    public abstract void OpenSpawnGhost();

    public abstract void updatePanel();
}
