package view;

import model.FileLoader;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.image.BufferedImage;
import java.io.FileNotFoundException;
import java.io.IOException;

/**
 * Main GUI class
 * @author Dustin Ray
 */
public class OfficeEscapeView extends JFrame implements MouseListener {

    private JFrame myFrame;
    public OfficeEscapeView() throws
            ClassNotFoundException,
            InstantiationException,
            IllegalAccessException,
            UnsupportedLookAndFeelException, IOException, FontFormatException {
        setupUI();
        setupFrame();
        addRoom();
//        addMenuPanel();
        myFrame.setVisible(true);

    }

    /**
     * Attempts to set look and feel to system defaults. Reverts to
     * default Swing UI if any error encountered.
     *
     * @throws ClassNotFoundException          catches UI setup errors.
     * @throws InstantiationException          catches UI setup errors.
     * @throws IllegalAccessException          catches UI setup errors.
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

        BufferedImage image;
//        image = ImageIO.read(Objects.requireNonNull(OfficeEscapeView.class.getResource("/images/backgrounds/office1.jpg")));

        myFrame = new JFrame("Office Escape v9");
//        myFrame.setContentPane(new ImagePanel(image));
        myFrame.setSize(1250, 768);
        myFrame.setLocation(100, 150);
        myFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        myFrame.setResizable(false);

    }


    private void addMenuPanel() throws IOException, FontFormatException {
        myFrame.add(new MenuPanel());
    }

    private void addRoom() throws FileNotFoundException {

        GamePanel room = FileLoader.readCity(myFrame);
        myFrame.add(room);

    }

    @Override
    public void mouseClicked(MouseEvent e) {

    }

    @Override
    public void mousePressed(MouseEvent e) {

    }

    @Override
    public void mouseReleased(MouseEvent e) {

    }

    @Override
    public void mouseEntered(MouseEvent e) {

    }

    @Override
    public void mouseExited(MouseEvent e) {

    }
}
