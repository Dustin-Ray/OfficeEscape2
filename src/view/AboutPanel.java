/*
University of Washington, Tacoma
TCSS 360 Software Development and Quality Assurance Techniques

Instructor: Tom Capaul
Academic Quarter: Summer 2021
Assignment: Group Project
Team members: Dustin Ray, Raz Consta, Reuben Keller
 */

package view;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

/**
 * Panel to display how to play information.
 * @author Reuben Keller
 * @author Dustin Ray
 * @version Summer 2021
 */
public class AboutPanel extends JPanel {

    /** Width of panel. */
    private final int myWidth;
    /** Height of panel. */
    private final int myHeight;
    /** Custom font. */
    private Font myFont;
    /** Background image for panel. */
    private final BufferedImage myBackGround;

    /**
     * Constructor.
     * @param theWidth width of panel.
     * @param theHeight height of panel.
     * @throws IOException if resource cannot be loaded.
     */
    public AboutPanel(final int theWidth, final int theHeight) throws IOException {

        myBackGround = ImageIO.read(new File("src/res/assets/about_menu.png"));
        myWidth = theWidth;
        myHeight = theHeight;
        setupPanel();
        readFiles();
        setVisible(true);
    }

    /** Sets panel size. */
    private void setupPanel() {
        setPreferredSize(new Dimension(myWidth, myHeight));
        setBackground(Color.BLACK);
    }

    /**Reads in external assets. */
    private void readFiles() {
        try {
            myFont = Font.createFont(Font.TRUETYPE_FONT, new File("src/res/fonts/Expansiva.otf"));
            myFont = myFont.deriveFont(Font.PLAIN, 35);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        myFont = myFont.deriveFont(Font.PLAIN, 20);
        g.setFont(myFont);
        g.drawImage(myBackGround, 0,0,this);
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
