package view;

import model.FileLoader;
import model.Room;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;


/**
 * Main GUI class
 * @author Dustin Ray
 */
public class OfficeEscapeView extends JFrame {

    Room myCurrentRoom;

    public OfficeEscapeView() throws
            ClassNotFoundException,
            InstantiationException,
            IllegalAccessException,
            UnsupportedLookAndFeelException, IOException, FontFormatException {

        super("Office Escape v9");
        myCurrentRoom = FileLoader.readCity(this);
        setupUI();
        setupFrame();
//        addRoom();
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

        BufferedImage image = ImageIO.read(new File("src/res/backgrounds/mainmenu.png"));
        this.setContentPane(new ImagePanel(image));
        this.setSize(1250, 800);
        this.setLocation(0, 0);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setResizable(false);
    }


    private void addMenuPanel() throws IOException, FontFormatException {
        final JMenuBar menubar = new JMenuBar();
        final JMenu fileMenu = new JMenu("File");
        final JMenuItem mainMenu = new JMenuItem("Main Menu");
        final JMenuItem newGame = new JMenuItem("New Game");
        final JMenuItem loadGame = new JMenuItem("Load Game");
        final JMenuItem saveGame = new JMenuItem("Save Game");
        final JMenuItem closeGame = new JMenuItem("Exit Game");

        fileMenu.add(mainMenu);
        fileMenu.add(newGame);
        fileMenu.add(loadGame);
        fileMenu.add(saveGame);
        fileMenu.add(closeGame);

        final JMenu aboutMenu = new JMenu("About");
        final JMenuItem howToPlay = new JMenuItem("How To Play");
        aboutMenu.add(howToPlay);

        final JMenu highScores = new JMenu("High Scores");

        menubar.add(fileMenu);
        menubar.add(aboutMenu);
        menubar.add(highScores);

        newGame.addActionListener(e -> {
            try {
                addRoom();
            } catch (FileNotFoundException fileNotFoundException) {
                fileNotFoundException.printStackTrace();
            }
        });
        closeGame.addActionListener(e -> exitGame());
        mainMenu.addActionListener(e -> mainMenu());
        this.setJMenuBar(menubar);
    }

    private void addRoom() throws FileNotFoundException {

        this.add(myCurrentRoom);
        this.repaint();
        myCurrentRoom.requestFocusInWindow();
        myCurrentRoom.setBounds(0, 0, 1250, 768);
        myCurrentRoom.setFocusable(true);
    }
    /**Returns to main menu. */
    private void mainMenu() {
        this.getContentPane().remove(myCurrentRoom);
        this.repaint();
    }

    /**
     * Closes the current process.
     */
    private void exitGame() {
        System.exit(0);
    }
}
