package view;

import model.Terrain;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public class GamePanel extends JPanel implements Runnable {

    private UserProfile usrProf;



    /**
     * The size in pixels of a side of one "square" on the grid.
     */
    private static final int SQUARE_SIZE = 48;

    /**
     * The terrain grid for the simulation.
     */
    private Terrain[][] myGrid;


    /**
     * The stroke used for painting.
     */
    private static final BasicStroke STROKE = new BasicStroke(3, BasicStroke.CAP_BUTT,
            BasicStroke.JOIN_MITER, 2,
            new float[] {2, 2, 2, 2}, 0);


    public GamePanel(final Terrain[][] theGrid) {

        this.myGrid = theGrid.clone();

        setBackground(Color.BLACK);
        this.setFocusable(true);
        this.addKeyListener(new InputHandler());

        Initialize();
        Thread animate = new Thread(this);
        animate.start();
    }

    public void Initialize() {
        usrProf = new UserProfile("src/res/icons/4_Modern_Office_singles/48x48/Modern_Office_Singles_48x48_111.png", 0, 0);
    }

    public void paintComponent(Graphics g) {
        super.paintComponent(g);

        Graphics2D g2d = (Graphics2D) g;
        g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING,
                RenderingHints.VALUE_ANTIALIAS_ON);
        g2d.setStroke(STROKE);

        drawMap(g2d);

        g2d.drawImage(usrProf.getImage(), usrProf.getX(), usrProf.getY(), null);
    }


    public void drawMap(final Graphics2D theGraphics) {

        for (int y = 0; y < myGrid.length; y++) {
            final int topy = y * SQUARE_SIZE;

            for (int x = 0; x < myGrid[y].length; x++) {
                final int leftx = x * SQUARE_SIZE;

                switch (myGrid[y][x]) {
                    case FLOOR:
                        theGraphics.setPaint(Color.LIGHT_GRAY);
                        theGraphics.fillRect(leftx, topy, SQUARE_SIZE, SQUARE_SIZE);
                        break;
                }
            }
        }


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

