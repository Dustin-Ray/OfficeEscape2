package view;

import controller.PropertyChangeEnabledUserControls;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;
import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;
import java.io.File;
import java.io.IOException;

/**
 * Loads the main menu panel which is displayed at startup and when
 * the return to main menu file option is selected.
 * @author Dustin Ray
 * @version Summer 2021
 */
public class MainMenuPanel extends JPanel implements PropertyChangeEnabledUserControls, Runnable {

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

    /** Property change support object for this class. */
    private PropertyChangeSupport myPC;


    /**
     * Constructor for class.
     * @throws IOException If any resource cannot be loaded.
     * @throws FontFormatException if font cannot be loaded.
     */
    public MainMenuPanel() throws IOException, FontFormatException {

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
        setMouseListeners(loadGame);
        setMouseListeners(newGame);

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

    /**
     * Adds mouse listeners to given labels. Determines how to test the answer
     * correctness depending on the type of trivia question set in the current room.
     * @param theLabel the JLabel to add a mouse click listener to.
     *
     */
    private void setMouseListeners(final JLabel theLabel) {
        String text = theLabel.getText();
        theLabel.addMouseListener(new MouseAdapter() {
            public void mouseClicked(MouseEvent e) {
                if (text.equals("new game")) {
                    System.out.println("new game clicked");
                    fireNewGamePropertyChange();
                } else {
                    System.out.println("load game clicked");
                    fireLoadPropertyChange();
                }
            }
        });
    }


    /** Fires property change to listeners. */
    private void fireLoadPropertyChange() {
        myPC.firePropertyChange(PropertyChangeEnabledUserControls.LOAD, null, "load");
    }
    /** Fires property change to listeners. */
    private void fireNewGamePropertyChange() {
        myPC.firePropertyChange(PropertyChangeEnabledUserControls.NEW_GAME, null, "new game");

    }


    /**
     * Adds a property change listener.
     * @param theListener the listen to add.
     */
    @Override
    public void addPropertyChangeListener(final PropertyChangeListener theListener) {
        myPC = new PropertyChangeSupport(this);
        myPC.addPropertyChangeListener(theListener);
    }


    /**
     * Removes a property change listener.
     * @param theListener the listener to remove.
     */
    @Override
    public void removePropertyChangeListener(final PropertyChangeListener theListener) {
        myPC.removePropertyChangeListener(theListener);
    }


    /** Paints components to screen. */
    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D mainBackground = (Graphics2D) g;
        mainBackground.drawImage(myBackground2, x, y, null);
        mainBackground.drawImage(myBackground1, 0, 0, null);

    }


    /** Sets X position*/
    public void setX(int x) {this.x = x;}

    /** Sets Y position. */
    public void setY(int y) {this.y = y;}

    /** Moves component. */
    private void advance() {
            this.setX(this.x + this.speedKeyX);
            this.setY(this.y + this.speedKeyY);
    }

    /** Runnable loop to capture keyboard input. */
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




    /**Handles key presses.  */
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

        /** Handles key releases. */
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