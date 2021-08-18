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

    public static final int DELAY = 1000 / 60;

    public static final String PATH = "src/res/maps/map_";

    /** Controller object that uses keyboard input to manipulate player sprite.  */
    private UserController myUserControls;

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
    public RoomPanel(final Room theRoom) {
        super();
        this.loadRoom(theRoom);
        setBackground(Color.BLACK);
        this.setFocusable(true);
        Timer timer = new Timer(DELAY, this);
        timer.start();
        addKeyListener(new TAdapter());
        repaint();
    }

    /** Helper method that can be called externally to switch rooms.  */
    public void loadRoom(final Room theRoom) {
        myCurrentRoom = theRoom;
        myRoomID = getMyCurrentRoom().getRoomID();
        myUserControls = new UserController(new Player(), myCurrentRoom.getMap());
        try {
            myFloorMap = ImageIO.read(new File(PATH + theRoom.getRoomID() + "/map.png"));
        } catch (Exception e) {
            e.printStackTrace();
        }
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
        g2d.drawImage(myFloorMap, 0,0, this);
        Player player = myUserControls.getMyPlayer();
        g2d.drawImage(player.getPlayerSprite(), player.getX(),
                player.getY(), this);
    }


    /** Method to move player sprite when keys are pressed or released. */
    @Override
    public void actionPerformed(ActionEvent e) {
        myUserControls.updatePlayer();
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
            myUserControls.keyReleased(e);
            repaint();}


        /**
         * Handles key releases.
         * @param e is the key release event.
         */
        @Override
        public void keyPressed(KeyEvent e) {
            myUserControls.keyPressed(e);
            repaint();}
    }


    /** Resets user controller to initial state. */
    public void resetUserController() {
        this.myUserControls.getMyPlayer().reset();
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

