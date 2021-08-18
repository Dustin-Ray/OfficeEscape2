package view;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;

public abstract class AbstractInfoPanel extends JPanel {

    /**background image for panel.  */
    protected BufferedImage myBackGroundImage;
    /** Custom font. */
    protected Font myFont;
    /** Path to image background file. */
    protected final String myPath;

    /**
     * Constructor.
     * @param theWidth width of frame.
     * @param theHeight height of frame.
     */
    public AbstractInfoPanel(final int theWidth, final int theHeight, final String thePath) {

        myPath = thePath;
        setPreferredSize(new Dimension(theWidth, theHeight));
        setBackground(Color.BLACK);
        setVisible(true);
        readFiles();

        repaint();
    }


    /** Reads in the image files to display in this Panel.*/
    private void readFiles() {
        try {
            myBackGroundImage = ImageIO.read(new File(myPath));
            myFont = Font.createFont(Font.TRUETYPE_FONT, new File("src/res/fonts/Expansiva.otf"));
            myFont = myFont.deriveFont(Font.PLAIN, 35);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


}
