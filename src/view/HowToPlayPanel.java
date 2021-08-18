package view;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;

/**
 * Panel to display how to play information.
 * @author Dustin Ray
 * @version Summer 2021
 */
public class HowToPlayPanel extends JPanel {

    /**background image for panel.  */
    private BufferedImage myBackGroundImage;
    /** Custom font. */
    private Font myFont;

    /**
     * Constructor.
     * @param theWidth width of frame.
     * @param theHeight height of frame.
     */
    public HowToPlayPanel(final int theWidth, final int theHeight) {
        setPreferredSize(new Dimension(theWidth, theHeight));
        setBackground(Color.BLACK);
        setVisible(true);
        readFiles();

        repaint();
    }


    /** Reads in the image files to display in this Panel.*/
    private void readFiles() {
        try {
            myBackGroundImage = ImageIO.read(new File("src/res/assets/howtoplay.png"));
            myFont = Font.createFont(Font.TRUETYPE_FONT, new File("src/res/fonts/Expansiva.otf"));
            myFont = myFont.deriveFont(Font.PLAIN, 35);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        g.drawImage(myBackGroundImage, 0,0, null);
        myFont = myFont.deriveFont(Font.PLAIN, 20);
        g.setFont(myFont);
        g.setColor(Color.WHITE);
        g.drawString("Use arrow keys to move chair around the room:", 50, 250);
        g.drawString("Press e on the", 828, 100);
        g.drawString("keyboard to start", 828, 150);
        g.drawString("a trivia question:", 828, 200);
        g.drawString("Hint: Press e then q to get a cheat code!", 50, 700);
        repaint();
    }

}
