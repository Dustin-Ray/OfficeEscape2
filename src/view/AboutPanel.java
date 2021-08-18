/*
University of Washington, Tacoma
TCSS 360 Software Development and Quality Assurance Techniques

Instructor: Tom Capaul
Academic Quarter: Summer 2021
Assignment: Group Project
Team members: Dustin Ray, Raz Consta, Reuben Keller
 */

package view;

import javax.swing.*;
import java.awt.*;
import java.io.File;

public class AboutPanel extends JPanel {

    private final int myWidth;

    private final int myHeight;

    private Font f;


    public AboutPanel(final int theWidth, final int theHeight) {
        myWidth = theWidth;
        myHeight = theHeight;
        setupPanel();
        readFiles();
    }

    private void setupPanel() {
        setPreferredSize(new Dimension(myWidth, myHeight));
        setBackground(Color.BLACK);
        setVisible(true);
    }


    private void readFiles() {
        try {
            f = Font.createFont(Font.TRUETYPE_FONT, new File("src/res/fonts/Expansiva.otf"));
            f = f.deriveFont(Font.PLAIN, 35);
        } catch (Exception e) {

        }
    }


    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        g.setFont(f);
        g.setColor(Color.WHITE);
        g.drawString("Office Escape 2: The Sequel", 50, 50);


        f = f.deriveFont(Font.PLAIN, 24);
        g.setFont(f);
        g.drawString("TCSS 360 Software Development and Quality Assurance", 50, 150);
        g.drawString("Techniques", 50, 180);
        g.drawString("Instructor: Tom Capaul", 50, 240);
        g.drawString("Academic Quarter: Summer 2021", 50, 300);
        g.drawString("Assignment: Trivia Maze", 50, 360);
        g.drawString("Created by Raz Consta, Reuben Keller, Dustin Ray", 50, 420);
        g.drawString("GUI made using Java's Swing", 50, 480);
    }

}
