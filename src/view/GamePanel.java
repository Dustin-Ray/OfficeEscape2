package view;

import model.Icons;
import model.Terrain;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.io.IOException;

public class GamePanel extends JPanel implements Runnable {

    private UserProfile usrProf;

    /**
     * The size in pixels of a side of one "square" on the grid.
     */
    private static final int SQUARE_SIZE = 48;

    /**
     * The terrain grid for the simulation.
     */
    private final Terrain[][] myGrid;

    private final Icons imgLibrary;


    /**
     * The stroke used for painting.
     */
    private static final BasicStroke STROKE = new BasicStroke(3, BasicStroke.CAP_BUTT,
            BasicStroke.JOIN_MITER, 2,
            new float[] {2, 2, 2, 2}, 0);


    public GamePanel(final Terrain[][] theGrid) throws IOException {

        imgLibrary = new Icons();
        this.myGrid = theGrid.clone();
        setBackground(Color.BLACK);
        this.setFocusable(true);
        this.addKeyListener(new InputHandler());
        Initialize();
        Thread animate = new Thread(this);
        animate.start();
    }

    public void Initialize() {
        usrProf = new UserProfile(0, 0);
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



                    case FLOOR_2:
                        theGraphics.drawImage(imgLibrary.FLOOR_2, leftx , topy, null);
                        break;
                    case FLOOR_1:
                        theGraphics.drawImage(imgLibrary.FLOOR_1, leftx , topy, null);
                        break;





                    case BOTTOM_WALL:
                        theGraphics.drawImage(imgLibrary.BOTTOM_WALL, leftx , topy, null);
                        break;
                    case TOP_WALL:
                        theGraphics.drawImage(imgLibrary.TOP_WALL, leftx , topy, null);
                        break;

                    case LEFT_WALL:
                        theGraphics.drawImage(imgLibrary.LEFT_WALL, leftx , topy, null);
                        break;
                    case PLANT:
                        theGraphics.drawImage(imgLibrary.FLOOR_2, leftx , topy, null);
                        theGraphics.drawImage(imgLibrary.PLANT, leftx , topy, null);
                        break;
                    case RIGHT_WALL:
                        theGraphics.drawImage(imgLibrary.RIGHT_WALL, leftx , topy, null);
                        break;

                    case COMPUTER:
                        theGraphics.drawImage(imgLibrary.FLOOR_2, leftx , topy, null);
                        theGraphics.drawImage(imgLibrary.COMPUTER_DESK, leftx , topy, null);
                        break;
                    case COMPUTER_DESK:
                        theGraphics.drawImage(imgLibrary.COMPUTER_DESK, leftx , topy, null);
                        theGraphics.drawImage(imgLibrary.PRINTER, leftx , topy, null);

                        break;
                    case PRINTER:
                        theGraphics.drawImage(imgLibrary.COMPUTER_DESK_LEGS, leftx , topy, null);
                        theGraphics.drawImage(imgLibrary.PRINTER, leftx , topy, null);
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

