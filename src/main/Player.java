package main;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.Rectangle;
import java.awt.Color;
import java.awt.Font;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.Vector;

public class Player {
    private static JPanel panel; private static KeyHandler keyH; static JLabel playerLabel = new JLabel();
    private static final JLabel[] lifeImages = new JLabel[]{new JLabel(), new JLabel(), new JLabel()};
    public static int spriteCounter = 0;
    public static int TileX, TileY;
    private static byte Life;
    private static double x, y, velX = 0.006, velY = 0;
    private Rectangle HitBox; boolean Moving = true;
    static char Direction = 'D';
    public static boolean BonusSpeed = false, EnemyBonus = false,
            BonusScore = false, FreezeBonus = false;



    public static Vector<Integer> PointsX = new Vector<>(), PointsY = new Vector<>(), Score = new Vector<>();
    Vector<Rectangle> Walls = null;

    public Player(JPanel panel, KeyHandler keyH) {
        this.panel = panel;
        this.keyH = keyH;

        Life = 3;

        panel.add(playerLabel);

        int drawLifeX = 0;
        switch (Game.Current_Maze) {
            case First -> drawLifeX = 625;
            case Second -> drawLifeX = 305;
            case Third -> drawLifeX = 200;
            case Fourth -> drawLifeX = 830;
            case Fifth -> drawLifeX = 170;
        }
        JLabel LifeCounter = new JLabel("Lives: ");
        LifeCounter.setForeground(Color.WHITE);
        LifeCounter.setFont(new Font("Serif", Font.BOLD, 22));
        LifeCounter.setBounds(drawLifeX, 0, 120, 30);

        ImageIcon lifeimage = new ImageIcon("src/sprites/ducheatable.png");
        lifeImages[0].setIcon(lifeimage);
        lifeImages[0].setBounds(drawLifeX + 65, 5, 20, 20);
        lifeImages[1].setIcon(lifeimage);
        lifeImages[1].setBounds(drawLifeX + 90, 5, 20, 20);
        lifeImages[2].setIcon(lifeimage);
        lifeImages[2].setBounds(drawLifeX + 115, 5, 20, 20);



        panel.add(LifeCounter);
        panel.add(lifeImages[0]);
        panel.add(lifeImages[1]);
        panel.add(lifeImages[2]);

        setDefault();

        switch (Game.Current_Maze) {
            case First -> setPointsPanelOne();
            case Second -> setPointsPanelTwo();
            case Third -> setPointsPanelThree();
            case Fourth -> setPointsPanelFour();
            case Fifth -> setPointsPanelFive();
        }
        
        switch (Game.Current_Maze) {
            case First -> Walls = GamePanelOne.getWalls();
            case Second -> Walls = GamePanelTwo.getWalls();
            case Third -> Walls = GamePanelThree.getWalls();
            case Fourth -> Walls = GamePanelFour.getWalls();
            case Fifth -> Walls = GamePanelFive.getWalls();
        }


    }

    public void setDefault() {

        switch (Game.Current_Maze) {
            case First -> {
                x = 490; y = 242;
            }
            case Second -> {
                x = 210; y = 360;
            }
            case Third -> {
                x = 160; y = 280;
            }
            case Fourth -> {
                x = 495; y = 347;
            }
            case Fifth -> {
                x = 160; y = 38;
            }
        }

        TileX = ((int)x)/16;
        TileY = ((int)y)/16;

        velX = 0.006; velY = 0;
        Direction = 'D';

        spriteCounter = 0;
        
        HitBox = new Rectangle((int)x, (int)y, 20, 20);

        updateImage();
    }


    public void update() {

        panel.setFocusable(true);
        panel.requestFocus();
        panel.requestFocusInWindow();

        if (keyH.getDirection() != Direction) {
            switch (keyH.getDirection()) {
                case 'W':
                    velY = -0.006;
                    velX = 0;
                    Direction = 'W';
                    Moving = true;
                    break;
                case 'A':
                    velY = 0;
                    velX = -0.006;
                    Direction = 'A';
                    Moving = true;
                    break;
                case 'S':
                    velY = 0.006;
                    velX = 0;
                    Direction = 'S';
                    Moving = true;
                    break;
                case 'D':
                    velY = 0;
                    velX = 0.006;
                    Direction = 'D';
                    Moving = true;
                    break;
            }
            updateImage();
        }

        if (Moving){
            HitBox.setBounds((int) (x + velX), (int) (y + velY), 20, 20);
            for (Rectangle X : Walls) {
                if (HitBox.intersects(X)) {
                    Moving = false;
                    HitBox.setBounds((int) x, (int) y, 20, 20);

                    System.out.println("Wall: " + X.getX() + " x " + X.getY());

                    break;
                }
            }

        }

        if (BonusSpeed) {
            if (velX > 0) velX = 0.01;
            if (velX < 0) velX = -0.01;
            if (velY > 0) velY = 0.01;
            if (velY < 0) velY = -0.01;
        }

        switch (Game.Current_Maze) {
            case First -> {
                if (((velY < 0 && y > 50) || velY > 0 && y < 245) && Moving) y += velY;
                if (Moving) x += velX;
                if (x < -20) x = 809;
                if (x > 809) x = -20;
            }
            case Second -> {
                if (((velY < 0 && y > 75) || velY > 0 && y < 575) && Moving) y += velY;
                if (Moving) x += velX;
                if (x < -20) x = 440;
                if (x > 440) x = -20;
            }
            case Third -> {
                if (((velY < 0 && y > 40) || velY > 0 && y < 346) && Moving) y += velY;
                if (Moving) x += velX;
                if (x < -20) x = 378;
                if (x > 378) x = -20;
            }
            case Fourth -> {
                if (((velY < 0 && y > 40) || velY > 0 && y < 348) && Moving) y += velY;
                if (Moving) x += velX;
                if (x < -20) x = 1032;
                if (x > 1032) x = -20;
            }
            case Fifth -> {
                if (((velY < 0 && y > 40) || velY > 0 && y < 183) && Moving) y += velY;
                if (Moving) x += velX;
                if (x < -20) x = 350;
                if (x > 350) x = -20;
            }
        }

        playerLabel.setBounds((int) x, (int) y, 20, 20);
        HitBox.setBounds((int) x, (int) y, 20, 20);


        for (int i = 0; i < PointsX.size(); i++)  {

            if (HitBox.intersects(PointsX.get(i), PointsY.get(i), 6,6)) {
                PointsX.remove(i);
                PointsY.remove(i);
                GamePanel.PointsEarned += Score.get(i);
                if (BonusScore) GamePanel.PointsEarned += 25;
                Score.remove(i);
                panel.remove(GamePanel.pointlabel.get(i));
                panel.repaint();
                panel.revalidate();
                GamePanel.pointlabel.remove(i);

            }
        }

        for (int i = 0; i < Game.Bonuses.size(); i++) {

            if (HitBox.intersects(Game.BonusesX.get(i), Game.BonusesY.get(i), 20,20)) {
                Game.BonusesX.remove(i);
                Game.BonusesY.remove(i);
                panel.remove(Game.Bonuses.get(i));
                Game.Bonuses.remove(i);
                GamePanel.BonusCounter--;
                GamePanel.PointsEarned += Game.BonusesScore.get(i);
                // +SPEED BONUS
                if (Game.BonusesScore.get(i) == 30 && !BonusSpeed) BonusSpeed = true;
                if (Game.BonusesScore.get(i) == 30 && BonusSpeed) Game.BonusSpeedTimer = 2;
                // -SPEED BONUS
                if (Game.BonusesScore.get(i) == 20 && !EnemyBonus) EnemyBonus = true;
                if (Game.BonusesScore.get(i) == 20 && EnemyBonus) Game.EnemySpeedTimer = 2;
                // +SCORES
                if (Game.BonusesScore.get(i) == 10 && !BonusScore) BonusScore = true;
                if (Game.BonusesScore.get(i) == 10 && BonusScore) Game.BonusScoreTimer = 3;
                // +Life
                if (Game.BonusesScore.get(i) == 50 && Life != 3) {
                    panel.add(lifeImages[Life]);
                    panel.remove(GamePanel.Maze_Back);
                    panel.add(GamePanel.Maze_Back);
                    Life++;
                }
                // Freeze Enemies
                if (Game.BonusesScore.get(i) == 40 && !FreezeBonus) FreezeBonus = true;
                if (Game.BonusesScore.get(i) == 40 && FreezeBonus) Game.FreezeBonusTimer = 2;

                Game.BonusesScore.remove(i);
            }

        }


    }

    private static BufferedImage U, D, R, L, currentImage;
    public static BufferedImage getImage() {
        try {
            U = ImageIO.read(new File("src/sprites/pacmanU.png"));
            D = ImageIO.read(new File("src/sprites/pacmanD.png"));
            R = ImageIO.read(new File("src/sprites/pacmanR.png"));
            L = ImageIO.read(new File("src/sprites/pacmanL.png"));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        switch (Direction) {
            case 'W' -> currentImage = U;
            case 'A' -> currentImage = L;
            case 'S' -> currentImage = D;
            case 'D' -> currentImage = R;
        }
        return currentImage;

    }
    public static void updateImage(){
//        if (((int)x) / 16 != TileX || ((int)y) / 16 != TileY) {
//            TileX = ((int)x) / 16;
//            TileY = ((int)y) / 16;
//            spriteCounter++;
//            if (spriteCounter > 2) spriteCounter = 0;
//        }
        switch (spriteCounter) {
            case 0 -> {
                ImageIcon currentIcon = new ImageIcon(getImage().getSubimage(0,0,20,20));
                playerLabel.setIcon(currentIcon);
            }
            case 1 -> {
                ImageIcon currentIcon = new ImageIcon(getImage().getSubimage(21,0,20,20));
                playerLabel.setIcon(currentIcon);
            }
            case 2 -> {
                ImageIcon currentIcon = new ImageIcon(getImage().getSubimage(42,0,20,20));
                playerLabel.setIcon(currentIcon);
            }
        }
    }

    public static int getTileX() {
        return TileX;
    }

    public static int getTileY() {
        return TileY;
    }

    public double getX() {
        return x;
    }
    public double getY() {
        return y;
    }
    public Rectangle getHitBox() {
        return HitBox;
    }
    public static boolean getBonusSpeed() {
        return BonusSpeed;
    }
    public static boolean isEnemyBonus() {
        return EnemyBonus;
    }
    public static boolean isBonusScore() {
        return BonusScore;
    }
    public static boolean isFreezeBonus() {
        return FreezeBonus;
    }

    public static byte getLife() {
        return Life;
    }
    public static void Dying() {
        Life--;
        panel.remove(lifeImages[Life]);
    }
    public static void LifeRestart() {
        Life = 3;
    }










    public void setPointsPanelOne() {

        for (int i = 1; i < 40; i++) {
            if (i == 13 || (i > 22 && i < 28)) continue;
            PointsX.add(20 * i);
            PointsY.add(249);
            Score.add(25);
        }
        PointsX.add(241); PointsY.add(228); Score.add(25);
        PointsX.add(85); PointsY.add(228); Score.add(25);
        PointsX.add(20); PointsY.add(228); Score.add(25);
        PointsX.add(280); PointsY.add(228); Score.add(25);
        PointsX.add(440); PointsY.add(228); Score.add(25);
        PointsX.add(460); PointsY.add(228); Score.add(25);
        PointsX.add(480); PointsY.add(228); Score.add(25);
        PointsX.add(520); PointsY.add(228); Score.add(25);
        PointsX.add(540); PointsY.add(228); Score.add(25);
        PointsX.add(560); PointsY.add(228); Score.add(25);
        PointsX.add(700); PointsY.add(228); Score.add(25);
        PointsX.add(780); PointsY.add(228); Score.add(25);

        for (int i = 1; i < 40; i++) {
            if (i > 22 && i < 28) continue;
            PointsX.add(20 * i);
            PointsY.add(207);
            Score.add(25);
        }

        PointsX.add(75); PointsY.add(180); Score.add(25);
        PointsX.add(215); PointsY.add(180); Score.add(25);
        PointsX.add(320); PointsY.add(180); Score.add(25);
        for (int i = 22; i < 29; i++) {
            PointsX.add(20 * i);
            PointsY.add(180);
            Score.add(25);
        }
        PointsX.add(620); PointsY.add(180); Score.add(25);
        PointsX.add(725); PointsY.add(186); Score.add(25);

        PointsX.add(680); PointsY.add(165); Score.add(25);
        PointsX.add(700); PointsY.add(165); Score.add(25);
        PointsX.add(75); PointsY.add(155); Score.add(25);
        PointsX.add(135); PointsY.add(155); Score.add(25);
        PointsX.add(160); PointsY.add(155); Score.add(25);
        PointsX.add(215); PointsY.add(155); Score.add(25);
        PointsX.add(320); PointsY.add(155); Score.add(25);
        PointsX.add(440); PointsY.add(155); Score.add(25);
        PointsX.add(560); PointsY.add(155); Score.add(25);
        PointsX.add(620); PointsY.add(155); Score.add(25);
        PointsX.add(725); PointsY.add(165); Score.add(25);


        PointsX.add(75); PointsY.add(130); Score.add(25);
        PointsX.add(135); PointsY.add(130); Score.add(25);
        PointsX.add(215); PointsY.add(130); Score.add(25);
        PointsX.add(320); PointsY.add(130); Score.add(25);
        PointsX.add(440); PointsY.add(130); Score.add(25);
        PointsX.add(560); PointsY.add(130); Score.add(25);
        PointsX.add(620); PointsY.add(130); Score.add(25);
        PointsX.add(725); PointsY.add(130); Score.add(25);

        PointsX.add(135); PointsY.add(110); Score.add(25);
        PointsX.add(155); PointsY.add(110); Score.add(25);
        PointsX.add(175); PointsY.add(110); Score.add(25);
        PointsX.add(195); PointsY.add(110); Score.add(25);
        PointsX.add(215); PointsY.add(110); Score.add(25);

        PointsX.add(20); PointsY.add(100); Score.add(25);
        PointsX.add(48); PointsY.add(100); Score.add(25);
        PointsX.add(75); PointsY.add(100); Score.add(25);
        for (int i = 12; i < 40; i++) {
            if (i == 30 || i == 29) continue;
            PointsX.add(20 * i);
            PointsY.add(100);
            Score.add(25);
        }

        PointsX.add(20); PointsY.add(80); Score.add(25);
        PointsX.add(75); PointsY.add(80); Score.add(25);
        PointsX.add(210); PointsY.add(80); Score.add(25);
        PointsX.add(435); PointsY.add(80); Score.add(25);
        PointsX.add(560); PointsY.add(80); Score.add(25);
        PointsX.add(620); PointsY.add(80); Score.add(25);
        PointsX.add(700); PointsY.add(80); Score.add(25);
        PointsX.add(780); PointsY.add(80); Score.add(25);

        for (int i = 1; i < 40; i++) {
            PointsX.add(20 * i);
            PointsY.add(60);
            Score.add(25);
        }
    }
    public void setPointsPanelTwo() {
        for (int i = 0; i < 7; i++) {
            PointsX.add(25); PointsY.add(90+(23*i)); Score.add(25);
            PointsX.add(415); PointsY.add(90+(23*i)); Score.add(25);
            if (i < 5) {
                PointsX.add(310);
                PointsY.add(90 + (23 * i));
                Score.add(25);
            }
        }
        for (int i = 0; i < 8; i++) {
            PointsX.add(25); PointsY.add(420+(23*i)); Score.add(25);
            PointsX.add(415); PointsY.add(420+(23*i)); Score.add(25);
            if (i > 2) {
                PointsX.add(100);
                PointsY.add(420 + (23 * i));
                Score.add(25);
                PointsX.add(295);
                PointsY.add(420 + (23 * i));
                Score.add(25);
            }
        }
        for (int i = 0; i < 15; i++) {
            if (i == 5) i++;
            PointsX.add(100); PointsY.add(90+(24*i)); Score.add(25);
            if (i > 5) {
                PointsX.add(150);
                PointsY.add(85+(24*i));
                Score.add(25);
                PointsX.add(290);
                PointsY.add(85+(24*i));
                Score.add(25);
            }
            if (i == 14) {
                PointsX.add(150);
                PointsY.add(85+(24*15));
                Score.add(25);

                PointsX.add(290);
                PointsY.add(85+(24*15));
                Score.add(25);
            }
        }
        for (int i = 0; i < 16; i++) {
            PointsX.add(340); PointsY.add(230+(23*i)); Score.add(25);
        }

        PointsX.add(50); PointsY.add(90); Score.add(25);
        PointsX.add(75); PointsY.add(90); Score.add(25);
        PointsX.add(50); PointsY.add(140); Score.add(25);
        PointsX.add(75); PointsY.add(140); Score.add(25);
        PointsX.add(50); PointsY.add(230); Score.add(25);
        PointsX.add(75); PointsY.add(230); Score.add(25);
        PointsX.add(50); PointsY.add(425); Score.add(25);
        PointsX.add(75); PointsY.add(425); Score.add(25);
        PointsX.add(50); PointsY.add(495); Score.add(25);
        PointsX.add(75); PointsY.add(495); Score.add(25);
        PointsX.add(50); PointsY.add(580); Score.add(25);
        PointsX.add(75); PointsY.add(580); Score.add(25);

        for (int i = 0; i < 4; i++) {
            PointsX.add(330 + (20*i)); PointsY.add(90); Score.add(25);
            PointsX.add(330 + (20*i)); PointsY.add(137); Score.add(25);
            if (i == 2) {
                PointsX.add(330 + (20*i));
                PointsY.add(115);
                Score.add(25);
            }
        }

        for (int i = 0; i < 3; i++) {
            PointsX.add(360+(20*i)); PointsY.add(230); Score.add(25);
            PointsX.add(360+(20*i)); PointsY.add(423); Score.add(25);
            PointsX.add(360+(20*i)); PointsY.add(495); Score.add(25);
            PointsX.add(360+(20*i)); PointsY.add(580); Score.add(25);
        }

        for (int i = 0; i < 6; i++) {
            PointsX.add(170+(20*i)); PointsY.add(230); Score.add(25);
            PointsX.add(170+(20*i)); PointsY.add(445); Score.add(25);
        }
        PointsX.add(315); PointsY.add(230); Score.add(25);
        PointsX.add(315); PointsY.add(285); Score.add(25);
        PointsX.add(125); PointsY.add(285); Score.add(25);
        PointsX.add(315); PointsY.add(375); Score.add(25);
        PointsX.add(125); PointsY.add(375); Score.add(25);
        PointsX.add(315); PointsY.add(445); Score.add(25);
        PointsX.add(315); PointsY.add(582); Score.add(25);

        for (int i = 0; i < 9; i++) {
            PointsX.add(120+(20*i)); PointsY.add(490); Score.add(25);
            PointsX.add(120+(20*i)); PointsY.add(183); Score.add(25);
        }
        PointsX.add(213); PointsY.add(255); Score.add(25);
        PointsX.add(213); PointsY.add(278); Score.add(25);
        PointsX.add(213); PointsY.add(300); Score.add(25);
        PointsX.add(228); PointsY.add(300); Score.add(25);
        PointsX.add(250); PointsY.add(300); Score.add(25);
        PointsX.add(268); PointsY.add(300); Score.add(25);
    }
    public void setPointsPanelThree() {

        for (int i = 0; i < 16; i++) {
            if (i == 6) i = 10;
            PointsX.add(16 + (i*20));
            PointsY.add(355); Score.add(25);

            PointsX.add(16 + (i*20));
            PointsY.add(290); Score.add(25);

            if (i == 4 || i == 11) continue;
            PointsX.add(16 + (i*20));
            PointsY.add(190); Score.add(25);

            if (i > 4 && i < 11) continue;
            PointsX.add(16 + (i*20));
            PointsY.add(125); Score.add(25);
        }

        PointsX.add(145); PointsY.add(355); Score.add(25);
        PointsX.add(165); PointsY.add(355); Score.add(25);
        PointsX.add(185); PointsY.add(355); Score.add(25);

        for (int i = 0; i < 16; i++) {

            PointsX.add(16 + (i*20));
            PointsY.add(45); Score.add(25);

            if (i != 2 && i != 13) {
                PointsX.add(16 + (i*20));
                PointsY.add(160); Score.add(25);
            }

            if (i != 1 && i != 14) {
                PointsX.add(16 + (i*20));
                PointsY.add(225); Score.add(25);
            }

            if (i == 4 || i == 11) continue;
            PointsX.add(16 + (i*20));
            PointsY.add(255); Score.add(25);

            if (i == 1 || i == 14) continue;
            PointsX.add(16 + (i*20));
            PointsY.add(325); Score.add(25);
        }

        PointsX.add(110); PointsY.add(80); Score.add(25);
        PointsX.add(110); PointsY.add(110); Score.add(25);
        PointsX.add(110); PointsY.add(135); Score.add(25);

        PointsX.add(130); PointsY.add(110); Score.add(25);
        PointsX.add(148); PointsY.add(110); Score.add(25);
        PointsX.add(165); PointsY.add(110); Score.add(25);
        PointsX.add(183); PointsY.add(110); Score.add(25);
        PointsX.add(200); PointsY.add(110); Score.add(25);

        PointsX.add(220); PointsY.add(80); Score.add(25);
        PointsX.add(220); PointsY.add(110); Score.add(25);
        PointsX.add(220); PointsY.add(135); Score.add(25);


        PointsX.add(130); PointsY.add(80); Score.add(25);
        PointsX.add(148); PointsY.add(80); Score.add(25);
        PointsX.add(183); PointsY.add(80); Score.add(25);
        PointsX.add(200); PointsY.add(80); Score.add(25);

        for (int i = 0; i < 16; i++) {
            if (i == 2 || i == 13) continue;
            if (i == 4) i = 12;
            PointsX.add(16 + (i * 20));
            PointsY.add(80); Score.add(25);

            if (i == 1 || i == 3 || i == 12 || i == 14) {
                PointsX.add(16 + (i * 20));
                PointsY.add(100); Score.add(25);
            }

            if (i == 0 || i == 3 || i == 12 || i == 15) {
                PointsX.add(16 + (i * 20));
                PointsY.add(62); Score.add(25);
            }
        }

    }
    public void setPointsPanelFour() {
        for (int i = 0; i < 50; i++) {
            PointsX.add(15 + (20 * i));
            PointsY.add(50);
            Score.add(25);

            if (i != 2 && i != 4  && i != 11 && i != 13 && i != 19 && i != 21 && i != 28 && i != 38 && i != 41 && i != 45 && i != 47) {
                PointsX.add(15 + (20 * i));
                PointsY.add(80);
                Score.add(25);
            }

            if (i != 0 && i != 2 && i != 4 && i != 11 && i != 15 && i != 16 && i != 17 && i != 21 && i != 28
                    && i != 32 && i != 33 && i != 34 && i != 38 && i != 45 && i != 47 && i != 49) {
                PointsX.add(15 + (20 * i));
                PointsY.add(120);
                Score.add(25);
            }


            if (i != 2 && i != 13 && i != 19 && i != 30 && i != 36 && i != 47) {
                PointsX.add(15 + (20 * i));
                PointsY.add(160);
                Score.add(25);
            }

            if (i != 4 && (i < 6 || i > 9) && i != 11 && i != 21 && (i < 23 || i > 26) && i != 28 && (i < 39 || i > 43) && i != 45) {
                PointsX.add(15 + (20 * i));
                PointsY.add(190);
                Score.add(25);
            }

            if (i != 1 && i != 14 && i != 16 && i != 17 && i != 32 && i != 33 && i != 35 && i != 48) {
                PointsX.add(15 + (20 * i));
                PointsY.add(225);
                Score.add(25);
            }

            if (i != 4 && i != 11 && i != 19 && i != 23 && i != 26 && i != 30 && i != 41 && i != 45) {
                PointsX.add(15 + (20 * i));
                PointsY.add(260);
                Score.add(25);
            }

            if (i != 16 && i != 21 && i != 28 && i != 33) {
                PointsX.add(15 + (20 * i));
                PointsY.add(290);
                Score.add(25);
            }

            if (i != 1 && i != 4 && i != 11 && i != 14 && i != 18 && i != 21 && i != 28 && i != 31 && i != 41 && i != 45 && i != 48) {
                PointsX.add(15 + (20 * i));
                PointsY.add(320);
                Score.add(25);
            }

            if (i != 6 && i != 16 && i != 33 && i != 39 && i != 43) {
                PointsX.add(15 + (20 * i));
                PointsY.add(355);
                Score.add(25);
            }

        }

    }
    public void setPointsPanelFive() {
        for (int i = 0; i < 16; i++) {
            PointsX.add(15 + (20*i));
            PointsY.add(50);
            Score.add(25);

            if (i != 1 && i != 2 && i != 4 && i != 6 && i != 9 && i != 11 && i != 13 && i != 14) {
                PointsX.add(15 + (20*i));
                PointsY.add(75);
                Score.add(25);
            }

            if (i != 8) {
                PointsX.add(15 + (20*i));
                PointsY.add(125);
                Score.add(25);
            }

            if (i != 4 && i != 11) {
                PointsX.add(15 + (20*i));
                PointsY.add(155);
                Score.add(25);
            }

            if ((i < 6 || i > 9) && i != 2 && i != 13) {
                PointsX.add(15 + (20*i));
                PointsY.add(190);
                Score.add(25);
            }

        }

        PointsX.add(15); PointsY.add(100); Score.add(25);
        PointsX.add(115); PointsY.add(100); Score.add(25);
        PointsX.add(145); PointsY.add(100); Score.add(25);
        PointsX.add(185); PointsY.add(100); Score.add(25);
        PointsX.add(215); PointsY.add(100); Score.add(25);
        PointsX.add(315); PointsY.add(100); Score.add(25);
    }
}
