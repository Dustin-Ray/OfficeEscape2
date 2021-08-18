package view;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class HowToPlayPanel extends JPanel {

    private final int myWidth;

    private final int myHeight;

    private BufferedImage image;

    private static final String PATH = "res/assets/howtoplay.png";


    public HowToPlayPanel(final int theWidth, final int theHeight) {
        myWidth = theWidth;
        myHeight = theHeight;
        setPreferredSize(new Dimension(myWidth, myHeight));
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
            image = ImageIO.read(new File("src/res/assets/howtoplay.png"));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        g.drawImage(image, 0,0, null);
        repaint();
    }

}
