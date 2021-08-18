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


    /**
     * Reads in the image files to display in this Panel.
     */
    private void readFiles() {
        try {
            myBackGroundImage = ImageIO.read(new File("src/res/assets/howtoplay.png"));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        g.drawImage(myBackGroundImage, 0,0, null);
        repaint();
    }

}
