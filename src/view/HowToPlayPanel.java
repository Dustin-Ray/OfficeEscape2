package view;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.nio.Buffer;

public class HowToPlayPanel extends JPanel {

    private final int myWidth;

    private final int myHeight;

    private BufferedImage image;

    private static final String PATH = "res/assets/howtoplay.png";


    public HowToPlayPanel(final int theWidth, final int theHeight) {
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



    /**
     * Reads in the image files to display in this Panel.
     */
    private void readFiles() {
        try {
            image = ImageIO.read(new File(PATH));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    public void draw(Graphics g) {
        Graphics2D g2d = (Graphics2D) g;
        g.drawImage(image, myWidth, myHeight, null);
    }

    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        draw(g);
    }



}
