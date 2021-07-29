package view;

import controller.UserController;
import model.Direction;
import model.room.Room;
import model.room.Terrain;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

/**
 * Panel that contains all elements necessary to render a room. Contains a key listener
 * which communicates with user controller to position the sprite on the screen and allow
 * interaction with panel elements.
 *
 * @author Dustin Ray
 * @author Reuben Keller
 * @version Summer 2021
 */
public class RoomPanel extends JPanel implements ActionListener {

    /**Represents path to door image assets. */
    private static final String DOOR_PATH = "src/res/assets/";
    /** An image to represent valid floor that can be traversed by the sprite. Hidden
     * underneath the floor map image. */
    private final BufferedImage FLOOR_1 = ImageIO.read(new File("src/res/assets/black_square.png"));
    /** An image to represent invalid terrain that cannot be traversed by the sprite. Hidden
     * underneath the floor map image. */
    private final BufferedImage RED_ZONE = ImageIO.read(new File("src/res/assets/red_zone.png"));
    /** Controller object that uses keyboard input to manipulate player sprite.  */
    private UserController myUserControls;
    /** The size in pixels of a side of one "square" on the grid. */
    private static final int SQUARE_SIZE = 48;
   /** The terrain grid for the simulation. */
    private Terrain[][] myGrid;
    /** The current room being rendered. */
    private Room myCurrentRoom;
    /** A value to get the ID of the current room displayed on the panel. */
    private int myRoomID;
    /** The graphical floor map for the currently loaded room. */
    private BufferedImage myFloorMap;

    /**
     * Constructor for class.
     *
     * @param theRoom Current room to load into the panel.
     * @throws IOException if room is not able to be loaded.
     */
    public RoomPanel(final Room theRoom) throws IOException {
        super();
        this.loadRoom(theRoom);
        setBackground(Color.BLACK);
        this.setFocusable(true);
        int DELAY = 10;
        Timer timer = new Timer(DELAY, this);
        timer.start();
        addKeyListener(new TAdapter());
        repaint();
    }

    /** Helper method that can be called externally to switch rooms.  */
    public void loadRoom(final Room theRoom) throws IOException {
        myCurrentRoom = theRoom;
        myRoomID = getMyCurrentRoom().getRoomID();
        this.myGrid = theRoom.getTerrain();
        myUserControls = new UserController(480,480, Direction.EAST, myGrid);
        myFloorMap = ImageIO.read(new File(DOOR_PATH + "maps/floor_map_" + theRoom.getRoomID() + ".png"));
        repaint();
    }

    /** Returns the current ID value of the room loaded into this panel.
     * @return int value of currently loaded room ID. */
    public int getCurrentRoomID() {
        return myRoomID;
    }


    /** Overrides swing paintComponent to draw GUI elements. Can be called manually with repaint() */
    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2d = (Graphics2D) g;
        drawMap(g2d);
        //shifts floor map over up and to the left by 1 square to hide red zone boundaries.
        g2d.drawImage(myFloorMap, 48 , 48, this);
        //draws player sprite onto the frame
        g2d.drawImage(getMyUserControls().getPlayer().getPlayerSprite(),
                getMyUserControls().getPlayer().getX(),
                getMyUserControls().getPlayer().getY(),
                this);
    }

    /** Method to move player sprite when keys are pressed or released. */
    @Override
    public void actionPerformed(ActionEvent e) {
        getMyUserControls().advance();
        repaint();
    }

    /** Private inner class to handle keyboard input. */
    private class TAdapter extends KeyAdapter {
        /**
         * Handles key presses.
         * @param e is the keypress event.
         */
        @Override
        public void keyReleased(KeyEvent e) {
            getMyUserControls().keyReleased(e);
            repaint();}

        /**
         * Handles key releases.
         * @param e is the key release event.
         */
        @Override
        public void keyPressed(KeyEvent e) {
            getMyUserControls().keyPressed(e);
            repaint();}
    }

    /** Resets user controller to initial state. */
    public void resetUserController() {
        this.getMyUserControls().getPlayer().reset();
    }

    /**
     * Draws the graphical representation of the terrain grid onto the screen.
     * @param theGraphics is the graphics objects to render to the panel.
     * */
    public void drawMap(final Graphics2D theGraphics) {
        for (int y = 0; y < myGrid.length; y++) {
            final int topy = y * SQUARE_SIZE;
            for (int x = 0; x < myGrid[y].length; x++) {
                final int leftx = x * SQUARE_SIZE;
                switch (myGrid[y][x]) {
                    case RED_ZONE -> theGraphics.drawImage(RED_ZONE, leftx, topy, null);
                    case FLOOR_1 -> theGraphics.drawImage(FLOOR_1, leftx, topy, null);
                }
            }
        }
        repaint();
    }

    /**
     * Gets the current room loaded into this frame.
     * @return the current room loaded into this frame.
     */
    public Room getMyCurrentRoom() {return myCurrentRoom;}

    /**
     * Gets the current user controls object loaded into this frame.
     * @return the current user controller loaded into this frame.
     */
    public UserController getMyUserControls() {return myUserControls;}


}

