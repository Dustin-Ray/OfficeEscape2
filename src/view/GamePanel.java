package view;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public class GamePanel extends JPanel implements Runnable {

    private UserProfile usrProf;

    private int myXSize;
    private int myYSize;

    public GamePanel(int theXSize, int theYSize) {

        myXSize = theXSize;
        myYSize = theYSize;
        setBackground(Color.BLACK);
        this.setFocusable(true);
        this.addKeyListener(new InputHandler());

        Initialize();
        Thread animate = new Thread(this);
        animate.start();
    }

    public void Initialize() {
        usrProf = new UserProfile("src/res/icons/4_Modern_Office_singles/48x48/Modern_Office_Singles_48x48_111.png", 0, 0, myXSize, myYSize);
    }

    public void paintComponent(Graphics g) {
        super.paintComponent(g);

        Graphics2D g2d = (Graphics2D) g;
        g2d.drawImage(usrProf.getImage(), usrProf.getX(), usrProf.getY(), null);
    }

    @Override
    public void run() {
        long beforeTime, timeDiff, sleep;

        beforeTime = System.currentTimeMillis();

        while (true) {
            usrProf.move();
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
            usrProf.keyPressed(e);
        }

        public void keyReleased(KeyEvent e) {
            usrProf.keyReleased(e);
        }
    }
}

