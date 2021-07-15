package view;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.Objects;

public class OfficeEscapeView extends JFrame implements MouseListener {


    private JFrame myFrame;

    public OfficeEscapeView() throws
            ClassNotFoundException,
            InstantiationException,
            IllegalAccessException,
            UnsupportedLookAndFeelException, IOException, FontFormatException {
        setupUI();
        setupFrame();
        addGamePanel();
//        setupLabels();
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

    /**
     * Sets up GUI components.
     */
    private void setupGUI() throws IOException, FontFormatException {
        setupFrame();
    }

    private void setupFrame() throws IOException, FontFormatException {


        BufferedImage image;
//        image = ImageIO.read(Objects.requireNonNull(OfficeEscapeView.class.getResource("/images/backgrounds/office1.jpg")));

        myFrame = new JFrame("Office Escape v9");
//        myFrame.setContentPane(new ImagePanel(image));
        myFrame.setSize(1250, 650);
        myFrame.setLocation(100, 150);
        myFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        myFrame.setResizable(false);
        myFrame.setVisible(true);

    }

    public void setupLabels() throws IOException, FontFormatException {


        Font fontTest = Font.createFont(Font.TRUETYPE_FONT, Objects.requireNonNull(getClass().getClassLoader().getResourceAsStream("fonts/expansiva/Expansiva.otf")));
        fontTest = fontTest.deriveFont(Font.PLAIN, 38);

        final JLabel title1 = new JLabel("office escape 2: ");
        title1.setFont(fontTest);
        title1.setForeground(Color.black);
        title1.setBounds(700, 20, 600, 50);
        myFrame.add(title1);

        final JLabel newGame = new JLabel("new: ");
        newGame.setFont(fontTest);
        newGame.setForeground(Color.black);
        newGame.setBounds(1000, 100, 600, 50);
        myFrame.add(newGame);

        newGame.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                GamePanel gp = new GamePanel();
            }
        });



        final JLabel loadGame = new JLabel("load: ");
        loadGame.setFont(fontTest);
        loadGame.setForeground(Color.black);
        loadGame.setBounds(1000, 150, 600, 50);
        myFrame.add(loadGame);

        loadGame.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                loadGame.setText("escape");
                loadGame.setForeground(Color.magenta);

                BufferedImage image = new BufferedImage(1920, 1280,
                        BufferedImage.TYPE_INT_ARGB);

                try {
                    image = ImageIO.read(Objects.requireNonNull(OfficeEscapeView.class.getResource("/images/backgrounds/office2.jpg")));
                    myFrame.setContentPane(new ImagePanel(image));
                    setupLabels();
                } catch (IOException | FontFormatException ioException) {
                    ioException.printStackTrace();
                }
                final JLabel plsHelp = new JLabel("Please help");
                plsHelp.setBounds(20, 400, 170, 20);
                myFrame.add(plsHelp);
            }
        });


    }

    private void addGamePanel() {

        myFrame.add(new GamePanel());

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
