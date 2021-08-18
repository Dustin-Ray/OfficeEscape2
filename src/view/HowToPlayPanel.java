package view;

import java.awt.*;

/**
 * Panel to display how to play information.
 * @author Dustin Ray
 * @version Summer 2021
 */
public class HowToPlayPanel extends AbstractInfoPanel {

    /**
     * Constructor.
     *
     * @param theWidth  width of frame.
     * @param theHeight height of frame.
     * @param thePath a path to the background image file.
     */
    public HowToPlayPanel(int theWidth, int theHeight, String thePath) {
        super(theWidth, theHeight, thePath);
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
