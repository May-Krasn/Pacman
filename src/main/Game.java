package main;

import javax.swing.*;
import java.awt.Font;
import java.awt.Color;
import java.io.*;
import java.util.Vector;

public class Game implements Runnable, Serializable {

    private final GameFrame window;
    public static GamePanel CurrentPanel = null;
    private final MenuPanel menuPanel;
    private final HighScorePanel highScorePanel;
    private static boolean GameStarted = false;
    KeyHandler keyH = new KeyHandler();

    public static Maze Current_Maze = Maze.First;

    public Game() {

        menuPanel = new MenuPanel();
        highScorePanel = new HighScorePanel();
        HelpPanel helpPanel = new HelpPanel();


        window = new GameFrame(menuPanel);
        byte currentPanel = 0;
        while (true) {

            while (!menuPanel.getStart()) {
                if (GameStarted) {
                    window.remove(CurrentPanel);
                    window.add(menuPanel);
                    window.setSize(600, 700);
                    window.pack();
                    window.repaint();
                    window.revalidate();
                    GameStarted = false;
                }
                if (menuPanel.getHigh() && currentPanel == 0) {
                    currentPanel = 1;
                    window.remove(menuPanel);
                    window.add(highScorePanel);
                    window.revalidate();
                } else if (!menuPanel.getHigh() && !menuPanel.isHelp()) {
                    if (currentPanel != 0) {
                        if (currentPanel == 1) window.remove(highScorePanel);
                        else window.remove(helpPanel);

                        window.add(menuPanel);
                        currentPanel = 0;
                        window.revalidate();
                    }
                } else if (menuPanel.isHelp()) {
                    currentPanel = 2;
                    window.remove(menuPanel);
                    window.add(helpPanel);
                    window.revalidate();
                }
                window.repaint();
            }

            while (menuPanel.getStart() && !GameStarted) {

                window.remove(menuPanel);

                Current_Maze = menuPanel.getCurrent_Maze();
                switch (Current_Maze) {
                    case First -> CurrentPanel = new GamePanelOne(keyH);
                    case Second -> CurrentPanel = new GamePanelTwo(keyH);
                    case Third -> CurrentPanel = new GamePanelThree(keyH);
                    case Fourth -> CurrentPanel = new GamePanelFour(keyH);
                    case Fifth -> CurrentPanel = new GamePanelFive(keyH);
                }

                window.add(CurrentPanel);
                window.repaint();
                window.revalidate();
                window.pack();

                GameStarted = true;
                startGameLoop();
            }

        }

    }

    private void startGameLoop() {
        Thread gameThread = new Thread(this);
        gameThread.start();
    }

    public static short BonusSpeedTimer = -1, EnemySpeedTimer = -1,
            BonusScoreTimer = -1, FreezeBonusTimer = -1;
    boolean BonusSpeed = false, EnemySpeed = false,
            BonusScore = false, FreezeBonus = false;

    @Override
    public void run() {

        // https://www.youtube.com/watch?v=aFS9Whsoecc&list=PL4rzdwizLaxYmltJQRjq18a9gsSyEQQ-0&index=3
        // the guy explains game loop and FPS counter

        int frames = 0;
        long lastCheck = System.currentTimeMillis();
        long now = System.nanoTime();

        int maxFPS = 120;
        double TimePerFrame = (double) 1_000_000_000 / maxFPS;
        long lastFrame = now;
        boolean Closed = false;
        short TimerSpawn = 0;

        while (true) {
            now = System.nanoTime();
            if (now - lastFrame >= TimePerFrame) {
                CurrentPanel.repaint();
                lastFrame = now;
                frames++;

            }

            if (CurrentPanel.getEnemyOne().getHitBox().intersects(
                    CurrentPanel.getPlayer().getHitBox()) ||
                    CurrentPanel.getEnemyTwo().getHitBox().intersects(
                            CurrentPanel.getPlayer().getHitBox())) {

                GamePanel.GameOver = true;
                Player.Dying();
                break;
            }

            if (GamePanel.RestartGame) break;

            CurrentPanel.getPlayer().update();
            CurrentPanel.getEnemyOne().update();
            CurrentPanel.getEnemyTwo().update();

            int currentTileX = (int) CurrentPanel.getPlayer().getX() / 16;
            int currentTileY = (int) CurrentPanel.getPlayer().getY() / 16;

            if (Player.getTileX() != currentTileX || Player.getTileY() != currentTileY) {
                Player.TileX = currentTileX;
                Player.TileY = currentTileY;
                Player.spriteCounter++;
                if (Player.spriteCounter == 3) Player.spriteCounter = 0;
                Player.updateImage();
            }

            CurrentPanel.updatePanel();


            if (CurrentPanel.getEnemyOne().isGettingOut() &&
                    CurrentPanel.getEnemyTwo().isGettingOut() && !Closed) {
                CurrentPanel.closeSpawnPoint();
                Closed = true;
            }

            if (frames % 59 == 0) {
                CurrentPanel.getEnemyOne().ChangeDirection();
                CurrentPanel.getEnemyTwo().ChangeDirection();
            }

            if (System.currentTimeMillis() - lastCheck >= 1000) {
                lastCheck = System.currentTimeMillis();
                System.out.println("FPS: " + frames);
                frames = 0;
                TimerSpawn++;
                GamePanel.Seconds++;

                // ======== BONUSES =======

                if (TimerSpawn == 5) {
                    int x = (int) (Math.random() * (4 - 1) + 1);
                    if (x == 1) addBonus(true);
                    x = (int) (Math.random() * (4 - 1) + 1);
                    if (x == 1) addBonus(false);
                    TimerSpawn = 0;
                }

                if (BonusSpeedTimer != -1) {
                    BonusSpeedTimer -= 1;
                    if (BonusSpeedTimer == -1) {
                        BonusSpeed = false;
                        Player.BonusSpeed = false;
                    }
                }
                if (EnemySpeedTimer != -1) {
                    EnemySpeedTimer -= 1;
                    if (EnemySpeedTimer == -1) {
                        EnemySpeed = false;
                        Player.EnemyBonus = false;
                    }
                }
                if (BonusSpeedTimer != -1) {
                    BonusScoreTimer -= 1;
                    if (BonusScoreTimer == -1) {
                        BonusScore = false;
                        Player.BonusScore = false;
                    }
                }
                if (FreezeBonusTimer != -1) {
                    FreezeBonusTimer -= 1;
                    if (FreezeBonusTimer == -1) {
                        FreezeBonus = false;
                        Player.FreezeBonus = false;
                    }
                }

                // ============= BONUSES =================

                // ----- +speed ------
                if (Player.getBonusSpeed() && !BonusSpeed) BonusSpeed = true;

                // ----- -speed ------
                if (Player.isEnemyBonus() && !EnemySpeed) EnemySpeed = true;

                // ----- score ------
                if (Player.isBonusScore() && !BonusScore) BonusScore = true;

                // ----- freeze ------
                if (Player.isBonusScore() && !FreezeBonus) FreezeBonus = true;
            }

        }

        JLabel restart = new JLabel();
        restart.setFont(new Font("Serif", Font.BOLD, 50));
        restart.setForeground(Color.WHITE);
        restart.setText("Restarting");
        restart.setBounds(100, 100, 300, 70);

        CurrentPanel.add(restart);
        CurrentPanel.repaint();


        try {
            Thread.sleep(2100);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }

        CurrentPanel.remove(restart);

        if (!GamePanel.GameOver) {
            CurrentPanel.restartGame(false);
            GamePanel.GameOver = false;

            CurrentPanel.repaint();
            CurrentPanel.OpenSpawnGhost();
            startGameLoop();
        } else if (Player.getLife() == 0) {
            CurrentPanel.restartGame(true);
            GamePanel.Seconds = 0;
            Player.LifeRestart();

            short NewPlace = CheckRanking(CurrentPanel.getPointsEarned());
            Vector<String> NewRanking = HighScorePanel.getWholeRanking();
            Vector<Integer> NewRankingInts = HighScorePanel.getRanking();
            if (NewPlace != -1) {
                if (NewPlace != 4)
                    for (int i = 4; i > NewPlace; i--) {
                        NewRanking.set(i, NewRanking.get(i-1));
                        NewRankingInts.set(i, NewRankingInts.get(i-1));
                    }

                NewRanking.set(NewPlace, NewHighScore()+" "+CurrentPanel.getPointsEarned());
                NewRankingInts.set(NewPlace, CurrentPanel.getPointsEarned());
            } else {
                NewRanking.add(NewHighScore()+" "+CurrentPanel.getPointsEarned());
                NewRankingInts.add(CurrentPanel.getPointsEarned());
            }

            HighScorePanel.ChangeRanking(NewRanking, NewRankingInts);
            highScorePanel.loadHighScore();

            ButtonPressed = false;

            GamePanel.PointsEarned = 0;

            GamePanel.GameOver = false;
            window.remove(CurrentPanel);
            menuPanel.ChangeStart();
            window.add(menuPanel);
            window.pack();
            window.setSize(600, 700);
            window.repaint();
            window.revalidate();

        } else {
            try {
                Thread.sleep(1500);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }

            CurrentPanel.restartGame(false);
            CurrentPanel.repaint();
            GamePanel.GameOver = false;
            CurrentPanel.OpenSpawnGhost();
            startGameLoop();
        }
    }

    public static Vector<JLabel> Bonuses = new Vector<>();
    public static Vector<Integer> BonusesX = new Vector<>(), BonusesY = new Vector<>(), BonusesScore = new Vector<>();

    public static void addBonus(boolean en) {

        if (en) {
            BonusesX.add(EnemySmallMapOne.getX());
            BonusesY.add(EnemySmallMapOne.getY());
        } else {
            BonusesX.add(EnemySmallMapTwo.getX());
            BonusesY.add(EnemySmallMapTwo.getY());
        }

        int BonusChooser = (int) (Math.random() * (6 - 1) + 1);

        ImageIcon BonusImage;
        switch (BonusChooser) {
            case 1:
                BonusesScore.add(30);
                BonusImage = new ImageIcon("src/sprites/strawberry.png");
                Bonuses.add(new JLabel(BonusImage));
                break;
            case 2:
                BonusesScore.add(20);
                BonusImage = new ImageIcon("src/sprites/orange.png");
                Bonuses.add(new JLabel(BonusImage));
                break;
            case 3:
                BonusesScore.add(10);
                BonusImage = new ImageIcon("src/sprites/apple.png");
                Bonuses.add(new JLabel(BonusImage));
                break;
            case 4:
                BonusesScore.add(50);
                BonusImage = new ImageIcon("src/sprites/fruit.png");
                Bonuses.add(new JLabel(BonusImage));
                break;
            case 5:
                BonusesScore.add(40);
                BonusImage = new ImageIcon("src/sprites/cherry.png");
                Bonuses.add(new JLabel(BonusImage));
                break;
        }
        System.out.println("BONUS");
    }


    boolean ButtonPressed = false;
    public String NewHighScore() {

        JPanel panel = new JPanel();
        JFrame f = new JFrame();

        panel.setSize(600, 200);
        panel.setFocusable(true);
        panel.setBackground(Color.BLACK);
        panel.setLayout(null);

        f.setTitle("New High Score Time");
        f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        f.setSize(600, 200);
        f.setResizable(false);
        f.setLocationRelativeTo(null);
        f.setVisible(true);
        f.add(panel);

        JLabel label = new JLabel("Enter Your Name");
        label.setFont(new Font("Arial", Font.BOLD, 25));
        label.setForeground(Color.WHITE);
        label.setBounds(170, 10, 250, 30);

        JTextField newname = new JTextField(16);
        newname.setBounds(10, 75, 490, 50);

        JButton ButSubmit = new JButton("submit");
        ButSubmit.setBounds(510, 75, 75, 50);

        panel.add(label);
        panel.add(newname);
        panel.add(ButSubmit);

        f.repaint();
        f.revalidate();

        String s;

        while (!ButtonPressed) {
            ButSubmit.addActionListener(e -> ButtonPressed = true);
        }

        s = newname.getText();
        f.dispose();
        return s;
    }

    public short CheckRanking(int Score) {
        Vector<Integer> Rank = HighScorePanel.getRanking();
        short i;
        short NewHighScoreInt = -1;
        for (i = 0; i < Rank.size(); i++)
            if (Rank.get(i) < Score) {
                NewHighScoreInt = i;
                break;
            }
        return NewHighScoreInt;
    }


}
