package view;

import controller.UserController;
import model.Direction;
import model.room.Terrain;
import res.Icons;

import javax.swing.*;
import javax.swing.event.ChangeListener;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.io.IOException;

import static controller.PropertyChangeEnabledUserControls.PROPERTY_PROXIMITY;

public class RoomPanel extends JPanel implements ActionListener, PropertyChangeListener {

    public final UserController userControls;
    /** The size in pixels of a side of one "square" on the grid. */
    private static final int SQUARE_SIZE = 96;
    /** The terrain grid for the simulation. */
    private final Terrain[][] myGrid;
    private final Icons imgLibrary;


    public RoomPanel(final Terrain[][] theGrid) throws IOException {
        super();
        this.myGrid = theGrid.clone();
        userControls = new UserController(192,384, Direction.EAST, myGrid);
        this.setLayout(null);
        imgLibrary = new Icons();
        setBackground(Color.BLACK);
        this.setFocusable(true);
        int DELAY = 10;
        Timer timer = new Timer(DELAY, this);
        timer.start();
        addKeyListener(new TAdapter());
        repaint();
    }

    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2d = (Graphics2D) g;
        drawMap(g2d);
        g2d.drawImage(userControls.getPlayer().getImage(),
                userControls.getPlayer().getX(),
                userControls.getPlayer().getY(),
                this);
        repaint();
    }


    @Override
    public void actionPerformed(ActionEvent e) {
        userControls.advance();
        repaint();
    }

    private class TAdapter extends KeyAdapter {
        @Override
        public void keyReleased(KeyEvent e) {
            userControls.keyReleased(e);
            repaint();}
        @Override
        public void keyPressed(KeyEvent e) {
            userControls.keyPressed(e);
            repaint();}
    }

    @Override
    public void propertyChange(final PropertyChangeEvent theEvent) {
        switch (theEvent.getPropertyName()) {
            case PROPERTY_PROXIMITY:
                System.out.println("Next to door property change fired.");
                repaint();
                break;
        }
    }

    public void resetUserProfile() throws IOException {
        this.userControls.getPlayer().reset();
    }

    public void drawMap(final Graphics2D theGraphics) {

        for (int y = 0; y < myGrid.length; y++) {
            final int topy = y * SQUARE_SIZE;

            for (int x = 0; x < myGrid[y].length; x++) {
                final int leftx = x * SQUARE_SIZE;
                switch (myGrid[y][x]) {
                    case DOOR_CLOSED_A, DOOR_CLOSED_B, DOOR_CLOSED_C, DOOR_CLOSED_D -> theGraphics.drawImage(imgLibrary.DOOR_CLOSED, leftx, topy, null);
                    case DOOR_OPEN -> theGraphics.drawImage(imgLibrary.DOOR_OPEN, leftx, topy, null);
                    case WHITE_BOARD -> theGraphics.drawImage(imgLibrary.WHITE_BOARD, leftx, topy, null);
                    case WARHOL -> theGraphics.drawImage(imgLibrary.WARHOL, leftx, topy, null);
                    case VENDING_MACHINE -> theGraphics.drawImage(imgLibrary.VENDING_MACHINE, leftx, topy, null);
                    case RED_ZONE -> theGraphics.drawImage(imgLibrary.RED_ZONE, leftx, topy, null);
                    case FLOOR_1 -> theGraphics.drawImage(imgLibrary.FLOOR_1, leftx, topy, null);
                    case TOP_WALL -> theGraphics.drawImage(imgLibrary.TOP_WALL, leftx, topy, null);
                    case DESK_1 -> theGraphics.drawImage(imgLibrary.DESK_1, leftx, topy, null);
                    case DESK_2 -> theGraphics.drawImage(imgLibrary.DESK_2, leftx, topy, null);
                    case DESK_3 -> theGraphics.drawImage(imgLibrary.DESK_3, leftx, topy, null);
                    case DEAD_CHAIR -> theGraphics.drawImage(imgLibrary.DEAD_CHAIR, leftx, topy, null);
                    case PLANT_CHAIR -> theGraphics.drawImage(imgLibrary.PLANT_CHAIR, leftx, topy, null);
                }
            }
        }
        repaint();
    }
}

