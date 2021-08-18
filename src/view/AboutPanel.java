/*
University of Washington, Tacoma
TCSS 360 Software Development and Quality Assurance Techniques

Instructor: Tom Capaul
Academic Quarter: Summer 2021
Assignment: Group Project
Team members: Dustin Ray, Raz Consta, Reuben Keller
 */

package view;

import java.awt.*;

/**
 * Panel to display how to play information.
 * @author Reuben Keller
 * @author Dustin Ray
 * @version Summer 2021
 */
public class AboutPanel extends AbstractInfoPanel {


    /**
     * Constructor.
     *
     * @param theWidth  width of frame.
     * @param theHeight height of frame.
     * @param thePath a string to the background image for the object.
     */
    public AboutPanel(int theWidth, int theHeight, String thePath) {
        super(theWidth, theHeight, thePath);
    }

    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        myFont = myFont.deriveFont(Font.PLAIN, 20);
        g.setFont(myFont);
        g.drawImage(myBackGroundImage, 0,0,this);
        g.setColor(Color.WHITE);
        g.drawString("Office Escape 2: The Sequel", 50, 50);
        g.drawString("TCSS 360 Software Development and Quality Assurance", 50, 150);
        g.drawString("Techniques", 50, 180);
        g.drawString("Instructor: Tom Capaul", 50, 240);
        g.drawString("Academic Quarter: Summer 2021", 50, 300);
        g.drawString("Assignment: Trivia Maze", 50, 360);
        g.drawString("GUI made using Java Swing framework. ", 50, 420);
        g.drawString("Created by: ", 50, 480);
        g.drawString("          Raz Consta                         Reuben Keller                          Dustin Ray", 50, 525);
        repaint();
    }
}
