package view;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;


public class MainMenuPanel extends JPanel implements Runnable {

    private int x = 700;
    private int y;
    public int speedKeyX, speedKeyY;

    BufferedImage myBackground1;
    BufferedImage myBackground2;

    public MainMenuPanel() throws IOException, FontFormatException {

        this.setLayout(null);
        myBackground1 = ImageIO.read(new File("src/res/backgrounds/mainmenu.png"));
        myBackground2 = ImageIO.read(new File("src/res/backgrounds/mainmenu2.png"));
        this.setBackground(Color.BLACK);

        this.addKeyListener(new InputHandler());
        Thread animate = new Thread(this);
        animate.start();
        this.setPreferredSize(new Dimension(1200, 768));

        Font fontTest = Font.createFont(Font.TRUETYPE_FONT, new File("src/res/fonts/expansiva/Expansiva.otf"));
        fontTest = fontTest.deriveFont(Font.PLAIN, 24);



        final JLabel newGame = new JLabel("new game");
        final JLabel loadGame = new JLabel("load game");



        newGame.setVisible(true);
        newGame.setForeground(Color.WHITE);
        newGame.setBounds(180, 220, 300, 40);
        newGame.setLayout(null);
        newGame.setFont(fontTest);


        loadGame.setVisible(true);
        loadGame.setForeground(Color.WHITE);
        loadGame.setBounds(180, 260, 300, 40);
        loadGame.setLayout(null);
        loadGame.setFont(fontTest);

        this.add(newGame);
        this.add(loadGame);
        this.setFocusable(true);

    }

    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D mainBackground = (Graphics2D) g;
        mainBackground.drawImage(myBackground2, x, y, null);
        mainBackground.drawImage(myBackground1, 0, 0, null);

    }

    public void setX(int x) {
        this.x = x;
    }
    public void setY(int y) {
        this.y = y;
    }

    public void advance() {
            this.setX(this.x + this.speedKeyX);
            this.setY(this.y + this.speedKeyY);
    }

    @Override
    public void run() {
        long beforeTime, timeDiff, sleep;
        beforeTime = System.currentTimeMillis();
        while (true) {
            advance();
            repaint();
            timeDiff = System.currentTimeMillis() - beforeTime;
            int DELAY = 25;
            sleep = DELAY - timeDiff;
            if (sleep < 0)
                sleep = 2;
            try {
                Thread.sleep(sleep);
            } catch (InterruptedException e) {
                System.out.println("Animation interrupted!");
            }
            beforeTime = System.currentTimeMillis();
        }
    }

    /*
     * create a inner class to handle key inputs
     */
    private class InputHandler extends KeyAdapter {
        public void keyPressed(KeyEvent e) {
            if (e.getKeyCode() == KeyEvent.VK_LEFT) {
                speedKeyX = -10; //when move is, called change the speed
            }
            if (e.getKeyCode() == KeyEvent.VK_RIGHT) {
                speedKeyX = 10;
            }

            if (e.getKeyCode() == KeyEvent.VK_UP) {
                speedKeyY = -10; //when move is, called change the speed
            }
            if (e.getKeyCode() == KeyEvent.VK_DOWN) {
                speedKeyY = 10;
            }
        }

        public void keyReleased(KeyEvent e) {
            if (e.getKeyCode() == KeyEvent.VK_LEFT) {
                speedKeyX = 0;
            }
            if (e.getKeyCode() == KeyEvent.VK_RIGHT) {
                speedKeyX = 0;
            }
            if (e.getKeyCode() == KeyEvent.VK_UP) {
                speedKeyY = 0;
            }
            if (e.getKeyCode() == KeyEvent.VK_DOWN) {
                speedKeyY = 0;
            }
        }
    }
}