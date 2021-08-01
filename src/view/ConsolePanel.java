package view;

import model.trivia.Trivia;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

import static controller.PropertyChangeEnabledUserControls.NEIGHBOR_CHANGE;
import static controller.PropertyChangeEnabledUserControls.XY_POSITION;

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
    /** Graphics to decorate text area. */
    private final BufferedImage myDisplayConsole;
    /** Graphics to decorate text area. */
    private final BufferedImage myInfoDisplayConsole;
    /** Text area that displays the current room number. */
    private JTextArea myRoomID;
    /** Trivia object to be integrated into a trivia event. */
    private Trivia myTrivia;
    /** A list of answer labels to be displayed when a trivia event is initiated. */
    private ArrayList<JLabel> myAnswerLabelList;
    /** A text area to enter short answers to trivia questions. */
    private final JTextArea myShortAnswerTextArea;
    /** A custom font to be used for GUI elements. */
    private final Font myCustomFont;
    /** A "button" to be used to submit short answers. */
    private final JLabel mySubmitAnswer;
    /** A flag to tell observing classes to load the next room if trivia is correctly answered. */
    private boolean myCorrectlyAnsweredFlag;

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
        myShortAnswerTextArea = new JTextArea(1, 30);
        myConsoleScreenTextArea1 = new JTextArea();
        myConsoleScreenTextArea2 = new JTextArea();
        mySubmitAnswer = new JLabel("Submit");
        myCustomFont = Font.createFont(Font.TRUETYPE_FONT, new File("src/res/fonts/Expansiva.otf"));
        setCorrectlyAnsweredFlag(false);
        this.setBackground(Color.BLACK);
        setupTextArea();
        setupAnswerLabels();
        repaint();
    }

    /** Sets the RoomID box to theRoomID.
     * @param theRoomID  the room ID to be set. */
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
            case XY_POSITION -> myConsoleScreenTextArea1.setText(theEvent.getNewValue().toString());
            case NEIGHBOR_CHANGE -> myConsoleScreenTextArea2.setText(theEvent.getNewValue().toString());
        }
    }

    /** Prompts to begin trivia event. */
    public void triviaPrompt() {myConsoleScreenTextArea1.setText("Press E for Trivia: ");}

    /** Sets text area 2 to display the trivia question and text area 3 to display answer area.
     * @param theTrivia is the trivia question to operate on. */
    public void setTrivia(final Trivia theTrivia) {
        ArrayList<String> triviaAnswers = theTrivia.getAnswers();
        myTrivia = theTrivia;
        myConsoleScreenTextArea2.setText("QUESTION: \n" + theTrivia.getQuestion());
        if (theTrivia.getType() != 3) {
            for (int i = 0; i < triviaAnswers.size(); i++) {
                myAnswerLabelList.get(i).setText(triviaAnswers.get(i));
                myAnswerLabelList.get(i).setHorizontalAlignment(SwingConstants.CENTER);
                myAnswerLabelList.get(i).setVisible(true);
            }
        } else if(theTrivia.getType() == 3) {setupShortAnswer();}
    }


    /** Adds clickable answer labels to info console area. */
    private void setupAnswerLabels() {

        Font labelFont = myCustomFont.deriveFont(Font.PLAIN, 13);

        myAnswerLabelList = new ArrayList<>();
        JLabel answerLabel1 = new JLabel("");
        JLabel answerLabel2 = new JLabel("");
        JLabel answerLabel3 = new JLabel("");
        JLabel answerLabel4 = new JLabel("");
        myAnswerLabelList.add(answerLabel1);
        myAnswerLabelList.add(answerLabel2);
        myAnswerLabelList.add(answerLabel3);
        myAnswerLabelList.add(answerLabel4);

        int labelYPosition = 500;
        for (JLabel answerLabel : myAnswerLabelList) {
            setupLabel(labelFont, answerLabel);
            answerLabel.setBounds(820, labelYPosition, 250, 50);
            labelYPosition += 55;
        }
    }


    /** Resets answer entry area if sprite moves away from a trivia event. */
    public void resetAnswerVisibility() {
        for (JLabel answerLabel : myAnswerLabelList) {
            answerLabel.setVisible(false);
            answerLabel.setText("");
            answerLabel.repaint();
        }
        myShortAnswerTextArea.setVisible(false);
        mySubmitAnswer.setVisible(false);
    }

    /** Shows the short answer entry area when a short answer trivia event is triggered. */
    private void setupShortAnswer(){

        Font shortAnswerFont = myCustomFont.deriveFont(Font.PLAIN, 13);

        initializeTextArea(shortAnswerFont, myShortAnswerTextArea);
        myShortAnswerTextArea.setText("    Enter answer here");
        myShortAnswerTextArea.setBounds(820, 500, 360, 50);
        myShortAnswerTextArea.setEditable(true);

        mySubmitAnswer.setBounds(820, 565, 250, 50);
        mySubmitAnswer.setHorizontalAlignment(SwingConstants.CENTER);
        setupLabel(shortAnswerFont, mySubmitAnswer);
    }

    /**
     * Initializes a given JLabel for use in the panel.
     * @param shortAnswerFont the custom font to use for the label.
     * @param theLabel is the label to initialize.
     */
    private void setupLabel(final Font shortAnswerFont, final JLabel theLabel) {
        theLabel.setForeground(Color.WHITE);
        theLabel.setOpaque(true);
        theLabel.setBackground(Color.GRAY);
        theLabel.setLayout(null);
        theLabel.setFont(shortAnswerFont);
        theLabel.setVisible(true);
        this.add(theLabel);
        theLabel.repaint();
        setMouseListeners(theLabel);
    }

    /**
     * Adds mouse listeners to given labels. Determines how to test the answer
     * correctness depending on the type of trivia question set in the current room.
     * @param theLabel the JLabel to add a mouse click listener to.
     *
     */
    private void setMouseListeners(final JLabel theLabel) {
        theLabel.addMouseListener(new MouseAdapter() {
            public void mouseClicked(MouseEvent e) {
                //if short answer, do this:
                if (myTrivia.getType() == 3) {
                    setCorrectlyAnsweredFlag(myShortAnswerTextArea.getText().equals(myTrivia.getCorrectAnswer()));
                //otherwise, do this:
                } else {setCorrectlyAnsweredFlag(theLabel.getText().equals(myTrivia.getCorrectAnswer()));}
            }
        });
    }

    /** Sets up text elements in panel.*/
    private void setupTextArea() {

        Font textArea1font = myCustomFont.deriveFont(Font.PLAIN, 18);
        myConsoleScreenTextArea1 = new JTextArea();
        myConsoleScreenTextArea1.setBounds(830, 50, 350, 18);
        initializeTextArea(textArea1font, myConsoleScreenTextArea1);

        Font textArea2font = myCustomFont.deriveFont(Font.PLAIN, 14);
        myConsoleScreenTextArea2 = new JTextArea();
        myConsoleScreenTextArea2.setBounds(830, 80, 350, 100);
        initializeTextArea(textArea2font, myConsoleScreenTextArea2);

        myRoomID = new JTextArea("Room ID: " + "\n" + "0");
        Font roomIDFont = myCustomFont.deriveFont(Font.PLAIN, 12);
        initializeTextArea(roomIDFont, myRoomID);
        myRoomID.setBounds(830, 235, 80, 50);

    }

    /**
     * Method initializes any given text area to be displayed on the screen. Ideally
     * called before any set bounds or color changes are made.
     * @param shortAnswerFont the custom font to use for the text area.
     * @param theTextArea the text area to set up.
     */
    private void initializeTextArea(final Font shortAnswerFont, final JTextArea theTextArea) {
        theTextArea.setFont(shortAnswerFont);
        theTextArea.setBackground(Color.BLACK);
        theTextArea.setForeground(Color.WHITE);
        theTextArea.setLayout(null);
        theTextArea.setLineWrap(true);
        theTextArea.setWrapStyleWord(true);
        theTextArea.setEditable(false);
        this.add(theTextArea);
        theTextArea.setVisible(true);
        theTextArea.repaint();
    }

    /** A flag to tell observing classes to load the next room if trivia is correctly answered.
     * @return true if question was answered correctly, false otherwise. */
    public boolean getCorrectlyAnsweredFlag() {return myCorrectlyAnsweredFlag;}

    public void setCorrectlyAnsweredFlag(final boolean myCorrectlyAnsweredFlag) {
        this.myCorrectlyAnsweredFlag = myCorrectlyAnsweredFlag;
    }
}
