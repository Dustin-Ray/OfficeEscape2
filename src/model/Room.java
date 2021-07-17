package model;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.io.IOException;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

public class Room extends JPanel implements Runnable {

    private UserProfile usrProf;
    /**
     * The size in pixels of a side of one "square" on the grid.
     */
    private static final int SQUARE_SIZE = 96;

    /**
     * The terrain grid for the simulation.
     */
    private final Terrain[][] myGrid;
    private final Icons imgLibrary;

    public Room(final Terrain[][] theGrid) throws IOException {

        imgLibrary = new Icons();
        this.myGrid = theGrid.clone();
        setBackground(Color.BLACK);
        this.setFocusable(true);
        this.addKeyListener(new Room.InputHandler());
        Initialize();
        Thread animate = new Thread(this);
        animate.start();
        this.setBounds(0,0,768, 768);
    }

    public void Initialize() throws IOException {
        usrProf = new UserProfile(0, 0, Direction.SOUTH);
    }

    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2d = (Graphics2D) g;
        g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING,
                RenderingHints.VALUE_ANTIALIAS_ON);
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

                    case LEFT_WALL:
                        theGraphics.drawImage(imgLibrary.LEFT_WALL, leftx , topy, null);
                        break;
                    case RIGHT_WALL:
                        theGraphics.drawImage(imgLibrary.RIGHT_WALL, leftx , topy, null);
                        break;
                    case TOP_LEFT_CORNER:
                        theGraphics.drawImage(imgLibrary.TOP_LEFT_CORNER, leftx , topy, null);
                        break;
                    case TOP_RIGHT_CORNER:
                        theGraphics.drawImage(imgLibrary.TOP_RIGHT_CORNER, leftx , topy, null);
                        break;
                    case BOTTOM_LEFT_CORNER:
                        theGraphics.drawImage(imgLibrary.BOTTOM_LEFT_CORNER, leftx , topy, null);
                        break;
                    case BOTTOM_RIGHT_CORNER:
                        theGraphics.drawImage(imgLibrary.BOTTOM_RIGHT_CORNER, leftx , topy, null);
                        break;
                    case TOP_WALL:
                        theGraphics.drawImage(imgLibrary.TOP_WALL, leftx , topy, null);
                        break;
                    case DESK_FACING_RIGHT:
                        theGraphics.drawImage(imgLibrary.DESK_FACING_RIGHT, leftx , topy, null);
                        break;

                }
            }
        }


    }

    /**
     * Tests whether the square at the given x/y position exists on the map.
     *
     * @param theX The x position.
     * @param theY The y position.
     * @return true if the position exists on the map, false otherwise.
     */
    private boolean isValidIndex(final int theY, final int theX) {
        return 0 <= theY && theY < myGrid.length
                && 0 <= theX && theX < myGrid[theY].length;
    }

    /**
     * Generates a read-only neighbors map for the specified vehicle.
     *
     * @param theMover The vehicle.
     * @return The neighbors map.
     */
    private Map<Direction, Terrain> generateNeighbors(final UserProfile theMover) {
        final int x = theMover.getX();
        final int y = theMover.getY();
        final Map<Direction, Terrain> result = new HashMap<Direction, Terrain>();

        for (final Direction dir : Direction.values()) {
            if (isValidIndex(y + dir.dy(), x + dir.dx())) {
                result.put(dir, myGrid[y + dir.dy()][x + dir.dx()]);
            }
        }
        return Collections.unmodifiableMap(result);
    }



    public void advance() {
        final Map<Direction, Terrain> neighbors = generateNeighbors(usrProf);
        System.out.println(neighbors.toString());
        if (usrProf.canPass(neighbors.get(usrProf.getDirection()))) {
            usrProf.setX(usrProf.getX() + usrProf.speedKeyX);
            usrProf.setY(usrProf.getY() + usrProf.speedKeyY);
        }
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
            if(e.getKeyCode() == KeyEvent.VK_LEFT ) {
                usrProf.setDirection(Direction.WEST);
                usrProf.img = usrProf.chair_left;
                usrProf.speedKeyX = -10; //when move is, called change the speed

            }
            if(e.getKeyCode() == KeyEvent.VK_RIGHT) {
                usrProf.setDirection(Direction.EAST);
                usrProf.img = usrProf.chair_right;
                usrProf.speedKeyX = 10;
            }

            if(e.getKeyCode() == KeyEvent.VK_UP) {
                usrProf.setDirection(Direction.NORTH);
                usrProf.img = usrProf.chair_up;
                usrProf.speedKeyY = -10; //when move is, called change the speed
            }
            if(e.getKeyCode() == KeyEvent.VK_DOWN) {
                usrProf.setDirection(Direction.SOUTH);
                usrProf.img = usrProf.chair_down;
                usrProf.speedKeyY = 10;
            }
        }
        public void keyReleased(KeyEvent e) {
            if(e.getKeyCode() == KeyEvent.VK_LEFT){
                usrProf.speedKeyX = 0;
            }
            if(e.getKeyCode() == KeyEvent.VK_RIGHT){
                usrProf.speedKeyX = 0;
            }
            if(e.getKeyCode() == KeyEvent.VK_UP){
                usrProf.speedKeyY = 0;
            }
            if(e.getKeyCode() == KeyEvent.VK_DOWN){
                usrProf.speedKeyY = 0;
            }
        }
    }
}

