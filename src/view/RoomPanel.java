package view;

import controller.UserController;
import model.map.Direction;
import model.map.Player;
import model.room.Room;
import model.map.Terrain;

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


public class RoomPanel extends JPanel implements ActionListener {

    public static final String PATH = "src/res/maps/map_";

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
     * @throws IOException if any resource used by this class cannot be loaded.
     */
    public RoomPanel(final Room theRoom) throws IOException {
        super();
        this.loadRoom(theRoom);
        setBackground(Color.BLACK);
        this.setFocusable(true);
        int DELAY = 1;
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

        myUserControls = new UserController(new Player(384, 384), myCurrentRoom.getMap(), Direction.EAST, myGrid);
        myFloorMap = ImageIO.read(new File(PATH + theRoom.getRoomID() + "/floor_map.png"));
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
//        drawMap(g2d);


        g2d.drawImage(myFloorMap, 0,0, this);
        g2d.drawImage(getMyUserControls().getMyPlayer().getPlayerSprite(),
                getMyUserControls().getMyPlayer().getX(),
                getMyUserControls().getMyPlayer().getY(),
                this);
    }

    /** Method to move player sprite when keys are pressed or released. */
    @Override
    public void actionPerformed(ActionEvent e) {
        getMyUserControls().updatePlayer();
        getMyUserControls().checkDoorProximity();
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
        this.getMyUserControls().getMyPlayer().reset();
    }


    /**
     * Gets the current room loaded into this frame.
     * @return the current room loaded into this frame.
     */
    public Room getMyCurrentRoom() {
        return myCurrentRoom;
    }


    /**
     * Gets the current user controls object loaded into this frame.
     * @return the current user controller loaded into this frame.
     */
    public UserController getMyUserControls() {
        return myUserControls;
    }

}

