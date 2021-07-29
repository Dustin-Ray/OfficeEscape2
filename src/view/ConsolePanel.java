package view;

import model.trivia.Trivia;

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
public class ConsolePanel extends JPanel implements PropertyChangeListener {

    /** Area to display text output.  */
    private JTextArea myConsoleScreenTextArea1;
    /** Area to display text output.  */
    private JTextArea myConsoleScreenTextArea2;
    /** Area to display text output.  */
    private JTextArea myConsoleScreenTextArea3;
    /** Graphics to decorate text area. */
    private final BufferedImage myDisplayConsole;
    /** Graphics to decorate text area. */
    private final BufferedImage myInfoDisplayConsole;
    /** Text area that displays the current room number. */
    private JTextArea myRoomID;

    /**
     * Constructor for class.
     * @throws IOException If any resources cannot be loaded.
     * @throws FontFormatException if font cannot be loaded.
     */
    public ConsolePanel() throws IOException, FontFormatException {
        super();
        this.setLayout(null);
        myDisplayConsole = ImageIO.read(new File("src/res/assets/menu/console.png"));
        myInfoDisplayConsole = ImageIO.read(new File("src/res/assets/menu/info_console.png"));
        this.setBackground(Color.BLACK);
        setupText();
        repaint();
    }

    public void setRoomID(final int theRoomID) {
        myRoomID.setText("Room ID: " + "\n" + theRoomID);
    }

    /**
     * Draws graphics to panel.
     * @param g the graphics to draw to the panel.
     */
    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D displayConsole = (Graphics2D) g;
        displayConsole.drawImage(this.myDisplayConsole, 768, 0, this);
        displayConsole.drawImage(this.myInfoDisplayConsole, 768, 480, this);
    }

    /**
     * Changes state of panel when property change fired.
     * @param theEvent is the event to listen for.
     */
    @Override
    public void propertyChange(final PropertyChangeEvent theEvent) {
        switch (theEvent.getPropertyName()) {
            case PROPERTY_PROXIMITY_DOOR_A, PROPERTY_PROXIMITY_DOOR_D, PROPERTY_PROXIMITY_DOOR_C, PROPERTY_PROXIMITY_DOOR_B -> {
                myConsoleScreenTextArea1.setText("Press E for trivia");
            }
            case XY_POSITION -> {
                myConsoleScreenTextArea1.setText(theEvent.getNewValue().toString());
            }
            case NEIGHBOR_CHANGE -> {
                myConsoleScreenTextArea2.setText(theEvent.getNewValue().toString());
            }
        }
    }

    /** */
    public void setTrivia(final Trivia theTrivia) {
        myConsoleScreenTextArea2.setText("QUESTION: \n" + theTrivia.getQuestion());
        myConsoleScreenTextArea3.setText("CHOOSE YOUR ANSWER: \n" + theTrivia.getIncorrectAnswers().toString());
    }



    /**
     * Sets up text elements in panel.
     * @throws IOException if class resource cannot be loaded.
     * @throws FontFormatException if font cannot be loaded.
     */
    private void setupText() throws IOException, FontFormatException {

        Font fontTest = Font.createFont(Font.TRUETYPE_FONT, new File("src/res/fonts/Expansiva.otf"));
        fontTest = fontTest.deriveFont(Font.PLAIN, 18);
        myConsoleScreenTextArea1 = new JTextArea("TEXT AREA 1");
        myConsoleScreenTextArea1.setVisible(true);
        myConsoleScreenTextArea1.setForeground(Color.WHITE);
        myConsoleScreenTextArea1.setBounds(830, 50, 350, 18);
        myConsoleScreenTextArea1.setLayout(null);
        myConsoleScreenTextArea1.setFont(fontTest);
        myConsoleScreenTextArea1.setBackground(Color.BLACK);
        myConsoleScreenTextArea1.setLineWrap(true);
        myConsoleScreenTextArea1.setWrapStyleWord(true);
        this.add(myConsoleScreenTextArea1);

        fontTest = fontTest.deriveFont(Font.PLAIN, 14);
        myConsoleScreenTextArea2 = new JTextArea("TEXT AREA 2");
        myConsoleScreenTextArea2.setVisible(true);
        myConsoleScreenTextArea2.setForeground(Color.WHITE);
        myConsoleScreenTextArea2.setBounds(830, 80, 350, 210);
        myConsoleScreenTextArea2.setLayout(null);
        myConsoleScreenTextArea2.setFont(fontTest);
        myConsoleScreenTextArea2.setBackground(Color.BLACK);
        myConsoleScreenTextArea2.setLineWrap(true);
        myConsoleScreenTextArea2.setWrapStyleWord(true);
        this.add(myConsoleScreenTextArea2);

        myConsoleScreenTextArea3 = new JTextArea("TEXT AREA 3");
        myConsoleScreenTextArea3.setVisible(true);
        myConsoleScreenTextArea3.setForeground(Color.WHITE);
        myConsoleScreenTextArea3.setBounds(875, 500, 360, 250);
        myConsoleScreenTextArea3.setLayout(null);
        fontTest = fontTest.deriveFont(Font.PLAIN, 14);
        myConsoleScreenTextArea3.setFont(fontTest);
        myConsoleScreenTextArea3.setBackground(Color.BLACK);
        myConsoleScreenTextArea3.setLineWrap(true);
        myConsoleScreenTextArea3.setWrapStyleWord(true);
        this.add(myConsoleScreenTextArea3);
        
        myRoomID = new JTextArea("Room ID: " + "\n" + "0");
        myRoomID.setVisible(true);
        myRoomID.setForeground(Color.WHITE);
        myRoomID.setBounds(790, 700, 80, 50);
        fontTest = fontTest.deriveFont(Font.PLAIN, 12);
        myRoomID.setFont(fontTest);
        myRoomID.setBackground(Color.BLACK);
        this.add(myRoomID);
    }

}
