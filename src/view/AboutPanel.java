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

public class AboutPanel extends JPanel {

    private final int myWidth;

    private final int myHeight;

    private Font f;
    private final BufferedImage myBackGround;

    public AboutPanel(final int theWidth, final int theHeight) throws IOException {

        myBackGround = ImageIO.read(new File("/home/dustinr/Documents/School Related/TCSS/TCSS 360/OfficeEscape2/src/res/assets/about_menu.png"));

        myWidth = theWidth;
        myHeight = theHeight;
        setupPanel();
        readFiles();
        setVisible(true);
    }

    private void setupPanel() {
        setPreferredSize(new Dimension(myWidth, myHeight));
        setBackground(Color.BLACK);

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
        f = f.deriveFont(Font.PLAIN, 20);
        g.setFont(f);
        Graphics2D g2d = (Graphics2D) g;
        g2d.drawImage(myBackGround, 0,0,this);
        g.setColor(Color.WHITE);
        g.drawString("Office Escape 2: The Sequel", 50, 50);
        g.drawString("TCSS 360 Software Development and Quality Assurance", 50, 150);
        g.drawString("Techniques", 50, 180);
        g.drawString("Instructor: Tom Capaul", 50, 240);
        g.drawString("Academic Quarter: Summer 2021", 50, 300);
        g.drawString("Assignment: Trivia Maze", 50, 360);
        g.drawString("GUI made using Java's Swing", 50, 420);
        g.drawString("Created by: ", 50, 480);
        g.drawString("          Raz Consta                         Reuben Keller                          Dustin Ray", 50, 510);
        repaint();
    }

}
