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
    /** A counter to keep track of correctly answered questions. */
    private JTextArea myNextRoomText;

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
    public void setRoomID(final int theRoomID) {myRoomID.setText("Room ID: " + "\n" + theRoomID);}

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
        }
        else if(theTrivia.getType() == 3) {setupShortAnswer();}
    }


    /** Increments a counter to keep track of correctly answered questions. */
    public void setNextRoomText(final String nextRoomID) {
        if (nextRoomID != null) {myNextRoomText.setText("Next Room ID: " + "\n\n" + nextRoomID);}
    }

    /** Displays cheat info to the screen. */
    public void setCheatText(final String answer, final String route) {
        myConsoleScreenTextArea2.setText("Answer: " + answer + "\nOptimal path: " + route);
    }

    /** Adds clickable answer labels to info console area. */
    private void setupAnswerLabels() {
        myAnswerLabelList = new ArrayList<>();
        JLabel answerLabel1 = new JLabel("");
        JLabel answerLabel2 = new JLabel("");
        JLabel answerLabel3 = new JLabel("");
        JLabel answerLabel4 = new JLabel("");
        myAnswerLabelList.add(answerLabel1);
        myAnswerLabelList.add(answerLabel2);
        myAnswerLabelList.add(answerLabel3);
        myAnswerLabelList.add(answerLabel4);

        int labelYPosition = 514;
        for (JLabel answerLabel : myAnswerLabelList) {
            setupLabel(answerLabel, labelYPosition);
            labelYPosition += 55;
        }
    }

    public void setNextRoomVisible() {myNextRoomText.setVisible(true);}
    /** Resets answer entry area if sprite moves away from a trivia event. */
    public void resetAnswerVisibility() {
        for (JLabel answerLabel : myAnswerLabelList) {
            answerLabel.setVisible(false);
            answerLabel.setText("");
            answerLabel.repaint();
        }
        myShortAnswerTextArea.setVisible(false);
        mySubmitAnswer.setVisible(false);
        myNextRoomText.setVisible(false);
    }

    /** Shows the short answer entry area when a short answer trivia event is triggered. */
    private void setupShortAnswer(){

        initializeTextArea(myShortAnswerTextArea, 13, 888, 514, 360, 50);
        myShortAnswerTextArea.setText("    Enter answer here");
        myShortAnswerTextArea.setEditable(true);
        mySubmitAnswer.setHorizontalAlignment(SwingConstants.CENTER);
        setupLabel(mySubmitAnswer, 579);
    }

    /**
     * Initializes a given JLabel for use in the panel.
     * @param theLabel is the label to initialize.
     * @param theYPosition is the desired y position on the screen to place the label.
     */
    private void setupLabel(final JLabel theLabel,
                            final int theYPosition) {

        Font customFont = myCustomFont.deriveFont(Font.PLAIN, 13);
        theLabel.setForeground(Color.WHITE);
        theLabel.setOpaque(true);
        theLabel.setBackground(Color.GRAY);
        theLabel.setLayout(null);
        theLabel.setFont(customFont);
        theLabel.setVisible(true);
        this.add(theLabel);
        theLabel.setBounds(888, theYPosition, 240, 50);
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



        myConsoleScreenTextArea1 = new JTextArea();
        initializeTextArea(myConsoleScreenTextArea1, 18,  830, 50, 350, 18);

        myConsoleScreenTextArea2 = new JTextArea();
        initializeTextArea(myConsoleScreenTextArea2, 14,  830, 80, 350, 100);

        myRoomID = new JTextArea("Room ID: " + "\n" + "0");
        initializeTextArea(myRoomID, 12,830, 235, 80, 50);


        myNextRoomText = new JTextArea("""
                Next Room ID:\s

                """);
        initializeTextArea(myNextRoomText, 12,920, 235, 200, 50);
    }

    /**
     * Method initializes any given text area to be displayed on the screen. Ideally
     * called before any set bounds or color changes are made.
     * @param theFontSize the size to set the custom font.
     * @param theTextArea the text area to set up.
     * @param theXPosition is the desired x position on the screen to place the text area.
     * @param theYPosition is the desired y position on the screen to place the text area.
     * @param theWidth is the desired width of the text area.
     * @param theHeight is the desired height of the text area.
     */
    private void initializeTextArea(final JTextArea theTextArea,
                                    final int theFontSize,
                                    final int theXPosition,
                                    final int theYPosition,
                                    final int theWidth,
                                    final int theHeight) {

        Font customFont = myCustomFont.deriveFont(Font.PLAIN, theFontSize);
        theTextArea.setFont(customFont);
        theTextArea.setBackground(Color.BLACK);
        theTextArea.setForeground(Color.WHITE);
        theTextArea.setLayout(null);
        theTextArea.setLineWrap(true);
        theTextArea.setWrapStyleWord(true);
        theTextArea.setEditable(false);
        this.add(theTextArea);
        theTextArea.setVisible(true);
        theTextArea.setBounds(theXPosition, theYPosition, theWidth, theHeight);
        theTextArea.repaint();
    }

    /** A flag to tell observing classes to load the next room if trivia is correctly answered.
     * @return true if question was answered correctly, false otherwise. */
    public boolean getCorrectlyAnsweredFlag() {return myCorrectlyAnsweredFlag;}

    /**
     * Set the flag so that the next room can be loaded if the question is answered correctly.
     * @param myCorrectlyAnsweredFlag a boolean to set the flag with.
     */
    public void setCorrectlyAnsweredFlag(final boolean myCorrectlyAnsweredFlag) {
        this.myCorrectlyAnsweredFlag = myCorrectlyAnsweredFlag;
    }
}
