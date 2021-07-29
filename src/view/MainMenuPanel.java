package view;

import javax.imageio.ImageIO;
import javax.sound.sampled.*;
import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

/**
 * Loads the main menu panel which is displayed at startup and when
 * the return to main menu file option is selected.
 * @author Dustin Ray
 * @version Summer 2021
 */
public class MainMenuPanel extends JPanel implements Runnable {

    /**The starting x position for the background2, puts it behind the computer monitor. */
    private int x = 700;
    /** the y position for the frame. */
    private int y;
    /** Values to represent the change in x and y position when a key event occurs. */
    public int speedKeyX, speedKeyY;
    /** Main background image. */
    BufferedImage myBackground1;
    /** Image sits behind main background image. */
    BufferedImage myBackground2;

    /**
     * Constructor for class.
     * @throws IOException If any resource cannot be loaded.
     * @throws FontFormatException if font cannot be loaded.
     */
    public MainMenuPanel() throws IOException, FontFormatException, UnsupportedAudioFileException, LineUnavailableException {

        this.setLayout(null);

        myBackground1 = ImageIO.read(new File("src/res/backgrounds/mainmenu.png"));
        myBackground2 = ImageIO.read(new File("src/res/backgrounds/mainmenu2.png"));
        this.setBackground(Color.BLACK);

        this.addKeyListener(new InputHandler());
        Thread animate = new Thread(this);
        animate.start();
        this.setPreferredSize(new Dimension(1200, 768));

        Font fontTest = Font.createFont(Font.TRUETYPE_FONT, new File("src/res/fonts/Expansiva.otf"));
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

    /** */
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

    private void advance() {
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