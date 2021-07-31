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
    /** A list of trivia answers delivered from trivia class. */
    private ArrayList<String> myTriviaAnswers;
    private JTextArea myShortAnswerTextArea;

    private Font myCustomFont;



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

        myCustomFont = Font.createFont(Font.TRUETYPE_FONT, new File("src/res/fonts/Expansiva.otf"));


        this.setBackground(Color.BLACK);
        setupText();
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
        myTriviaAnswers = theTrivia.getAnswers();
        myConsoleScreenTextArea2.setText("QUESTION: \n" + theTrivia.getQuestion());
        if (theTrivia.getType() != 3) {
            for (int i= 0; i < myTriviaAnswers.size(); i++) {
                myAnswerLabelList.get(i).setText("   " + myTriviaAnswers.get(i));
                myAnswerLabelList.get(i).setVisible(true);
            }
        } else if(theTrivia.getType() == 3) {setupShortAnswer();}
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
        Font labelFont = myCustomFont.deriveFont(Font.PLAIN, 13);

        int labelYPosition = 500;
        for (JLabel answerLabel : myAnswerLabelList) {
            answerLabel.setVisible(false);
            answerLabel.setForeground(Color.WHITE);
            answerLabel.setOpaque(true);
            answerLabel.setBackground(Color.GRAY);
            answerLabel.setLayout(null);
            answerLabel.setBounds(820, labelYPosition, 360, 50);
            labelYPosition += 55;
            answerLabel.setFont(labelFont);
            this.add(answerLabel);
            answerLabel.repaint();
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
    }

    /** Shows the short answer entry area when a short answer trivia event is triggered. */
    private void setupShortAnswer(){

        Font shortAnswerFont = myCustomFont.deriveFont(Font.PLAIN, 13);
        myShortAnswerTextArea.setText("Enter answer here");
        myShortAnswerTextArea.setForeground(Color.WHITE);
        myShortAnswerTextArea.setBounds(820, 500, 360, 50);
        myShortAnswerTextArea.setLayout(null);
        myShortAnswerTextArea.setBackground(Color.GRAY);
        myShortAnswerTextArea.setLineWrap(true);
        myShortAnswerTextArea.setWrapStyleWord(true);
        myShortAnswerTextArea.setEditable(true);
        myShortAnswerTextArea.setFont(shortAnswerFont);
        this.add(myShortAnswerTextArea);
        myShortAnswerTextArea.setVisible(true);
        myShortAnswerTextArea.repaint();
    }
    /** Sets up text elements in panel.*/
    private void setupText() {

        Font textArea1font = myCustomFont.deriveFont(Font.PLAIN, 18);
        myConsoleScreenTextArea1 = new JTextArea("TEXT AREA 1");
        myConsoleScreenTextArea1.setVisible(true);
        myConsoleScreenTextArea1.setForeground(Color.WHITE);
        myConsoleScreenTextArea1.setBounds(830, 50, 350, 18);
        myConsoleScreenTextArea1.setLayout(null);
        myConsoleScreenTextArea1.setFont(textArea1font);
        myConsoleScreenTextArea1.setBackground(Color.BLACK);
        myConsoleScreenTextArea1.setLineWrap(true);
        myConsoleScreenTextArea1.setWrapStyleWord(true);
        myConsoleScreenTextArea1.setEditable(false);
        this.add(myConsoleScreenTextArea1);

        Font textArea2font = myCustomFont.deriveFont(Font.PLAIN, 14);
        myConsoleScreenTextArea2 = new JTextArea("TEXT AREA 2");
        myConsoleScreenTextArea2.setVisible(true);
        myConsoleScreenTextArea2.setForeground(Color.WHITE);
        myConsoleScreenTextArea2.setBounds(830, 80, 350, 100);
        myConsoleScreenTextArea2.setLayout(null);
        myConsoleScreenTextArea2.setFont(textArea2font);
        myConsoleScreenTextArea2.setBackground(Color.BLACK);
        myConsoleScreenTextArea2.setLineWrap(true);
        myConsoleScreenTextArea2.setWrapStyleWord(true);
        myConsoleScreenTextArea1.setEditable(false);
        this.add(myConsoleScreenTextArea2);

        myRoomID = new JTextArea("Room ID: " + "\n" + "0");
        myRoomID.setVisible(true);
        myRoomID.setForeground(Color.WHITE);
        myRoomID.setBounds(830, 235, 80, 50);
        Font roomIDFont = myCustomFont.deriveFont(Font.PLAIN, 12);
        myRoomID.setFont(roomIDFont);
        myRoomID.setBackground(Color.BLACK);
        myConsoleScreenTextArea1.setEditable(false);
        this.add(myRoomID);
    }
}
