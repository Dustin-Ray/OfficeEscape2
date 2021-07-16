package view;

import javax.swing.*;
import java.awt.*;
import java.io.IOException;


public class GameFrame extends JFrame {


    private JFrame myFrame;

    public GameFrame() throws
            ClassNotFoundException,
            InstantiationException,
            IllegalAccessException,
            UnsupportedLookAndFeelException, IOException, FontFormatException {
        setupUI();
        setupFrame();
        addGamePanel();

    }

    /**
     * Attempts to set look and feel to system defaults. Reverts to
     * default Swing UI if any error encountered.
     * @throws ClassNotFoundException catches UI setup errors.
     * @throws InstantiationException catches UI setup errors.
     * @throws IllegalAccessException catches UI setup errors.
     * @throws UnsupportedLookAndFeelException catches UI setup errors.
     */
    private void setupUI() throws

            ClassNotFoundException,
            InstantiationException,
            IllegalAccessException,
            UnsupportedLookAndFeelException {
        try {
            // Set System L&F
            UIManager.setLookAndFeel(
                    UIManager.getSystemLookAndFeelClassName());
        } catch (final UnsupportedLookAndFeelException
                | IllegalAccessException
                | InstantiationException
                | ClassNotFoundException e) {
            UIManager.setLookAndFeel(
                    UIManager.getCrossPlatformLookAndFeelClassName());
        }
    }


    private void setupFrame() throws IOException, FontFormatException {

        myFrame = new JFrame("Office Escape v9");
        myFrame.setSize(1250, 650);
        myFrame.setLocation(100, 150);
        myFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        myFrame.setResizable(false);
        myFrame.setVisible(true);

    }


    private void addGamePanel() {
        myFrame.add(new GamePanel(1250, 650));
    }

}
