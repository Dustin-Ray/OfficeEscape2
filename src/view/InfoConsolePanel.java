package view;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.io.File;
import java.io.IOException;

import static controller.PropertyChangeEnabledUserControls.*;

/**
 * Represents the console panel to the right of the game screen. Player
 * interacts with the console to answer trivia questions and see information
 * about the game state such as current room, etc.
 * @author Dustin Ray
 * @version Summer 2021
 */
public class InfoConsolePanel extends JPanel implements PropertyChangeListener {


    /** Area to display text output.  */
    JLabel myCurrentRoomNumber;
    /** Displays current room info, trivia questions, menu options. */
    BufferedImage myInfoConsole;

    /**
     * Constructor for class.
     * @throws IOException If any resources cannot be loaded.
     * @throws FontFormatException if font cannot be loaded.
     */
    public InfoConsolePanel() throws IOException, FontFormatException {
        myInfoConsole = ImageIO.read(new File("src/res/assets/info_console.png"));
        this.setLayout(null);
        setupText();
    }

    /**
     * Draws graphics to panel.
     * @param g the graphics to draw to the panel.
     */
    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D infoDisplayConsole = (Graphics2D) g;
        infoDisplayConsole.drawImage(this.myInfoConsole, 768, 480, null);
    }

    /**
     * Changes state of panel when property change fired.
     * @param theEvent is the event to listen for.
     */
    @Override
    public void propertyChange(final PropertyChangeEvent theEvent) {

    }

    /**
     * Sets up text elements in panel.
     * @throws IOException if class resource cannot be loaded.
     * @throws FontFormatException if font cannot be loaded.
     */
    public void setupText() throws IOException, FontFormatException {

        Font fontTest = Font.createFont(Font.TRUETYPE_FONT, new File("src/res/fonts/expansiva/Expansiva.otf"));
        fontTest = fontTest.deriveFont(Font.PLAIN, 18);
        myCurrentRoomNumber = new JLabel("Room Number: ");
        myCurrentRoomNumber.setVisible(true);
        myCurrentRoomNumber.setForeground(Color.WHITE);
        myCurrentRoomNumber.setBackground(Color.BLACK);
        myCurrentRoomNumber.setBounds(830, 480, 360, 40);
        myCurrentRoomNumber.setLayout(null);
        myCurrentRoomNumber.setFont(fontTest);
        this.add(myCurrentRoomNumber);

    }

}
