package view;

import controller.UserController;
import model.Direction;
import model.room.Room;
import model.room.Terrain;
import res.Icons;

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

    private static final String DOOR_PATH = "src/res/assets/";

    private static final int DOOR_A_X = 96;

    private static final int DOOR_B_X = 288;

    private static final int DOOR_C_X = 480;

    private static final int DOOR_D_X = 672;


    /** Controller object that uses keyboard input to manipulate player sprite.  */
    public final UserController userControls;

    /** The size in pixels of a side of one "square" on the grid. */
    private static final int SQUARE_SIZE = 48;

   /** The terrain grid for the simulation. */
    private Terrain[][] myGrid;

    /** A library of graphics to use for rendering the current room. */
    private final Icons imgLibrary;

    /** The current room being rendered. */
    public Room myCurrentRoom;

    /** A value to get the ID of the current room displayed on the panel. */
    public int myRoomID;

    private BufferedImage myFloorMap;

    /**
     * Constructor for class.
     *
     * @param theRoom Current room to load into the panel.
     * @throws IOException if room is not able to be loaded.
     */
    public RoomPanel(final Room theRoom) throws IOException {
        super();
        loadRoom(theRoom);

        myFloorMap = ImageIO.read(new File(DOOR_PATH + "maps/floor_map_0.png"));

        userControls = new UserController(288,384, Direction.EAST, myGrid);
        imgLibrary = new Icons();
        setBackground(Color.BLACK);
        this.setFocusable(true);
        int DELAY = 10;
        Timer timer = new Timer(DELAY, this);
        timer.start();
        addKeyListener(new TAdapter());
        myRoomID = myCurrentRoom.getRoomID();
        repaint();

    }

    /** Helper method that can be called externally to switch rooms.  */
    public void loadRoom(final Room theRoom) {
        myCurrentRoom = theRoom;
        myRoomID = myCurrentRoom.getRoomID();
        this.myGrid = theRoom.getTerrain();
        repaint();
    }

    public int getCurrentRoomID() {
        return myRoomID;
    }


    /** Overrides swing paintComponent to draw GUI elements. */
    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2d = (Graphics2D) g;
        drawMap(g2d);
//        try {
//            BufferedImage closed = ImageIO.read(new File(DOOR_PATH + "door_closed.png"));
//            BufferedImage open = ImageIO.read(new File(DOOR_PATH + "door_open.png"));
//            // initialize all doors to closed
//            g2d.drawImage(closed, DOOR_A_X, 0, this);
//            g2d.drawImage(closed, DOOR_B_X, 0, this);
//            g2d.drawImage(closed, DOOR_C_X, 0, this);
//            g2d.drawImage(closed, DOOR_D_X, 0, this);
//            // open doors as needed
//            if (myCurrentRoom.hasRoomA()) {
//                g2d.drawImage(open, DOOR_A_X, 0, this);
//            }
//            if (myCurrentRoom.hasRoomB()) {
//                g2d.drawImage(open, DOOR_B_X, 0, this);
//            }
//            if (myCurrentRoom.hasRoomC()) {
//                g2d.drawImage(open, DOOR_C_X, 0, this);
//            }
//            if (myCurrentRoom.hasRoomD()) {
//                g2d.drawImage(open, DOOR_D_X, 0, this);
//            }
//        } catch (Exception e) {
//            e.printStackTrace();
//        }

        g2d.drawImage(myFloorMap, 96 , 0, this);

        g2d.drawImage(userControls.getPlayer().getImage(),
                userControls.getPlayer().getX(),
                userControls.getPlayer().getY(),
                this);
    }

    /** Method to move player sprite when keys are pressed or released. */
    @Override
    public void actionPerformed(ActionEvent e) {
        userControls.advance();
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
            userControls.keyReleased(e);
            repaint();}

        /**
         * Handles key releases.
         * @param e is the key release event.
         */
        @Override
        public void keyPressed(KeyEvent e) {
            userControls.keyPressed(e);
            repaint();}
    }

    /** Resets user controller to initial state. */
    public void resetUserController() throws IOException {
        this.userControls.getPlayer().reset();
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
                    case RED_ZONE -> theGraphics.drawImage(imgLibrary.RED_ZONE, leftx, topy, null);
                    case FLOOR_1 -> theGraphics.drawImage(imgLibrary.FLOOR_1, leftx, topy, null);
                }
            }
        }
        repaint();
    }
}

