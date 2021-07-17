package view;

import model.FileLoader;
import model.Room;

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

    
    public OfficeEscapeView() throws
            ClassNotFoundException,
            InstantiationException,
            IllegalAccessException,
            UnsupportedLookAndFeelException, IOException, FontFormatException {


        super("Office Escape v9");
        setupUI();
        setupFrame();
        addRoom();
        addMenuPanel();
        this.setVisible(true);

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


//        this.setContentPane(new ImagePanel(image));
        this.setSize(1250, 800);
        this.setLocation(0, 0);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setResizable(false);

    }


    private void addMenuPanel() throws IOException, FontFormatException {
        this.add(new MenuPanel());
    }

    private void addRoom() throws FileNotFoundException {

        Room room = FileLoader.readCity(this);
        this.add(room);

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
