package main;

import javax.swing.*;
import java.awt.Rectangle;
import java.util.Vector;

public class EnemySmallMapTwo {
    JPanel panel; Player player;

    static ImageIcon imageicon = null;
    static JLabel l = new JLabel();
    public Rectangle HitBox;
    char Direction = 'D';
    private static double x = 510, y = 130, velX = 0.006, velY = 0;

    public EnemySmallMapTwo(JPanel panel, Player player) {
        this.panel = panel;
        this.player = player;

        imageicon = new ImageIcon("src/sprites/duch2.png");
        l.setIcon(imageicon);
        panel.add(l);

        setDefault();
    }

    public void setDefault() {

        switch (Game.Current_Maze) {
            case First -> {
                x = 510; y = 130;
            }
            case Second -> {
                x = 186; y = 574;
            }
            case Third -> {
                x = 175; y = 180;
            }
            case Fourth -> {
                x = 515; y = 184;
            }
            case Fifth -> {
                x = 180; y = 176;
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
            imageicon = new ImageIcon("src/sprites/duch2Frozen.png");
            l.setIcon(imageicon);
        }
        else {
            imageicon = new ImageIcon("src/sprites/duch2.png");
            l.setIcon(imageicon);
        }

        if (!GettingOut) {
            switch (Game.Current_Maze) {
                case First -> {
                    if (x > 490) Direction = 'A';
                    else if (y > 90) Direction = 'W';
                    else GettingOut = true;
                }
                case Second -> {
                    if (y > 480) Direction = 'W';
                    else GettingOut = true;
                }
                case Third -> {
                    if (x > 159) Direction = 'A';
                    else if (y > 150) Direction = 'W';
                    else GettingOut = true;
                }
                case Fourth -> {
                    if (x > 493) Direction = 'A';
                    else if (y > 150) Direction = 'W';
                    else GettingOut = true;
                }
                case Fifth -> {
                    if (x > 158) Direction = 'A';
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
                if (((velY < 0 && y > 40) || velY > 0 && y < 348) && Moving(Direction)) y += velY;
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

        HitBox.setBounds((int)x, (int)y, 20, 20);
        l.setBounds((int)x, (int)y, 20, 20);

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

//    public void draw(Graphics g) {
//        try {
//            if (Player.isFreezeBonus()) image = ImageIO.read(new File("src/sprites/duch2Frozen.png"));
//            else image = ImageIO.read(new File("src/sprites/duch2.png"));
//        } catch (IOException e) {
//            System.out.println("RuntimeException, can't read the image");
//            exit(2);
//        }
//        g.drawImage(image, (int)x, (int)y, null);
//
//    }

    public boolean isGettingOut() {
        return GettingOut;
    }

    public void ChangeDirection() {
        if (player.getX() - x > -10 && player.getX() - x < 10) {
            if (player.getY() > y && Moving('S')) Direction = 'S';
            else if (player.getY() < y && Moving('W')) Direction = 'W';
            else {
                int i = 0;
                while (!Moving(Direction)) {
                    if (i == 0) Direction = 'D';
                    if (i == 1) Direction = 'S';
                    if (i == 2) Direction = 'W';
                    i++;
                }
            }

        } else if (player.getX() > x) {
            if (Moving('D')) Direction = 'D';
            else if ((player.getY() > y && Moving('S')) ||
                    (Moving('S') && Direction == 'S')) Direction = 'S';
            else Direction = 'W';

        } else if (player.getX() < x) {
            if (Moving('A')) Direction = 'A';
            else if (player.getY() > y && Moving('S') ||
                    (Moving('S') && Direction == 'S')) Direction = 'S';
            else Direction = 'W';
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
