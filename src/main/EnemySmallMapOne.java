package main;

import javax.swing.*;
import java.awt.Rectangle;
import java.util.Vector;

public class EnemySmallMapOne {
    JPanel panel; Player player;
    static ImageIcon imageicon = null;
    static JLabel l = new JLabel();
    public Rectangle HitBox;
    char Direction = 'D';
    private static double x = 470, y = 130, velX = 0.006, velY = 0;

    public EnemySmallMapOne(JPanel panel, Player player) {
        this.panel = panel;
        this.player = player;


        imageicon = new ImageIcon("src/sprites/duch1.png");
        l.setIcon(imageicon);
        panel.add(l);

        setDefault();
    }

    public void setDefault() {
        switch (Game.Current_Maze) {
            case First -> {
                x = 470; y = 130;
            }
            case Second -> {
                x = 183; y = 78;
            }
            case Third -> {
                x = 135; y = 180;
            }
            case Fourth -> {
                x = 472; y = 180;
            }
            case Fifth -> {
                x = 135; y = 176;
            }
        }

        velX = 0.006; velY = 0;
        Direction = 'D';
        GettingOut = false;
        HitBox = new Rectangle((int)x, (int)y, 20, 20);
    }

    static boolean GettingOut = false;
    public void update() {

        if (Player.isFreezeBonus()) {
            imageicon = new ImageIcon("src/sprites/duch1Frozen.png");
            l.setIcon(imageicon);
        }
        else {
            imageicon = new ImageIcon("src/sprites/duch1.png");
            l.setIcon(imageicon);
        }

        if (!GettingOut) {
            switch (Game.Current_Maze) {
                case First -> {
                    if (x < 485) Direction = 'D';
                    else if (y > 90) Direction = 'W';
                    else GettingOut = true;
                }
                case Second -> {
                    if (y < 170) Direction = 'S';
                    else GettingOut = true;
                }
                case Third -> {
                    if (x < 159) Direction = 'D';
                    else if (y > 150) Direction = 'W';
                    else GettingOut = true;
                }
                case Fourth -> {
                    if (x < 493) Direction = 'D';
                    else if (y > 150) Direction = 'W';
                    else GettingOut = true;
                }
                case Fifth -> {
                    if (x < 158) Direction = 'D';
                    else if (y > 146) Direction = 'W';
                    else GettingOut = true;
                }
            }

        }

        switch (Direction) {
            case 'W':
                velY = -0.006;
                velX = 0;
                break;
            case 'A':
                velY = 0;
                velX = -0.006;
                break;
            case 'S':
                velY = 0.006;
                velX = 0;
                break;
            case 'D':
                velY = 0;
                velX = 0.006;
                break;
        }

        if (Player.isEnemyBonus()) {
            if (velX > 0) velX = 0.0035;
            if (velX < 0) velX = -0.0035;
            if (velY > 0) velY = 0.0035;
            if (velY < 0) velY = -0.0035;
        }
        if (Player.isFreezeBonus()) {
            velX = 0;
            velY = 0;
        }

        switch (Game.Current_Maze) {
            case First -> {
                if (((velY < 0 && y > 50) || velY > 0 && y < 245) && Moving(Direction)) y += velY;
                if (Moving(Direction)) x += velX;
                if (x < -20) x = 809;
                if (x > 809) x = -20;
            }
            case Second -> {
                if (((velY < 0 && y > 80) || velY > 0 && y < 575) && Moving(Direction)) y += velY;
                if (Moving(Direction)) x += velX;
                if (x < -20) x = 440;
                if (x > 440) x = -20;
            }
            case Third -> {
                if (((velY < 0 && y > 40) || velY > 0 && y < 346) && Moving(Direction)) y += velY;
                if (Moving(Direction)) x += velX;
                if (x < -20) x = 377;
                if (x > 377) x = -20;
            }
            case Fourth -> {
                if (((velY < 0 && y > 40) || velY > 0 && y < 183) && Moving(Direction)) y += velY;
                if (Moving(Direction)) x += velX;
                if (x < -20) x = 1030;
                if (x > 1030) x = -20;
            }
            case Fifth -> {
                if (((velY < 0 && y > 40) || velY > 0 && y < 183) && Moving(Direction)) y += velY;
                if (Moving(Direction)) x += velX;
                if (x < -20) x = 350;
                if (x > 350) x = -20;
            }
        }

        l.setBounds((int)x, (int)y, 20, 20);
        HitBox.setBounds((int)x, (int)y, 20, 20);

    }

    public boolean Moving(char Direction) {
        boolean move = true;
        switch (Direction) {
            case 'W':
                velY = -0.006;
                velX = 0;
                break;
            case 'A':
                velY = 0;
                velX = -0.006;
                break;
            case 'S':
                velY = 0.006;
                velX = 0;
                break;
            case 'D':
                velY = 0;
                velX = 0.006;
                break;
        }

        if (Player.isEnemyBonus()) {
            if (velX > 0) velX = 0.0035;
            if (velX < 0) velX = -0.0035;
            if (velY > 0) velY = 0.0035;
            if (velY < 0) velY = -0.0035;
        }
        if (Player.isFreezeBonus()) {
            velX = 0;
            velY = 0;
        }

        Rectangle Check = new Rectangle((int)(x+velX), (int)(y+velY), 20, 20);
        Vector<Rectangle> Walls = null;
        switch (Game.Current_Maze) {
            case First -> Walls = GamePanelOne.getWalls();
            case Second -> Walls = GamePanelTwo.getWalls();
            case Third -> Walls = GamePanelThree.getWalls();
            case Fourth -> Walls = GamePanelFour.getWalls();
            case Fifth -> Walls = GamePanelFive.getWalls();
        }


        for (Rectangle X : Walls) {
            if (Check.intersects(X)) {
                move = false;
                Check.setBounds((int) x, (int) y, 20, 20);
                break;
            }
        }

        return move;
    }

    public boolean isGettingOut() {
        return GettingOut;
    }

    public void ChangeDirection() {
            if (player.getY() - y > -10 && player.getY() - y < 10) {
                if (player.getX() > x && Moving('D')) Direction = 'D';
                else if (player.getX() < x && Moving('A')) Direction = 'A';
                else {
                    int i = 0;
                    while (!Moving(Direction)) {
                        if (i == 0) Direction = 'W';
                        if (i == 1) Direction = 'D';
                        if (i == 2) Direction = 'S';
                        i++;
                    }
                }

            } else if (player.getY() > y) {
                if (Moving('S')) Direction = 'S';
                else if ((player.getX() > x && Moving('D')) ||
                        (Moving('D') && Direction == 'D')) Direction = 'D';
                    else Direction = 'A';

            } else if (player.getY() < y) {
                if (Moving('W')) Direction = 'W';
                else if (player.getX() > x && Moving('D') ||
                        (Moving('D') && Direction == 'D')) Direction = 'D';
                        else Direction = 'A';
            }
    }


    public Rectangle getHitBox() {
        return HitBox;
    }

    public static int getX() {
        return (int)x;
    }
    public static int getY() {
        return (int)y;
    }

}
