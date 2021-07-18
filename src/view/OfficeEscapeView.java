package view;

import model.FileLoader;

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

    RoomPanel myCurrentRoomPanel;
    NewGameTextMenu myCurrentNewGameText;

    public OfficeEscapeView() throws
            ClassNotFoundException,
            InstantiationException,
            IllegalAccessException,
            UnsupportedLookAndFeelException, IOException, FontFormatException {

        super("Office Escape v9");

        myCurrentRoomPanel = FileLoader.readCity(this);
        myCurrentNewGameText = new NewGameTextMenu();
        setupUI();
        setupFrame();
        addMenuPanel();
        addNewGameTextMenu();
        this.setVisible(true);

    }

    private void addNewGameTextMenu() throws IOException, FontFormatException {

        Font fontTest = Font.createFont(Font.TRUETYPE_FONT, new File("src/res/fonts/expansiva/Expansiva.otf"));
        fontTest = fontTest.deriveFont(Font.PLAIN, 30);

        final JLabel newGame = new JLabel("New Game");
        final JLabel loadGame = new JLabel("Load Game");

        newGame.setVisible(true);
        newGame.setForeground(Color.WHITE);
        newGame.setBounds(815, 100, 300, 40);
        newGame.setLayout(null);
        newGame.setFont(fontTest);

        loadGame.setVisible(true);
        loadGame.setForeground(Color.WHITE);
        loadGame.setBounds(815, 190, 300, 40);
        loadGame.setLayout(null);
        loadGame.setFont(fontTest);



        this.add(newGame);
        this.add(loadGame);

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


    private void setupFrame() throws IOException {

        BufferedImage image = ImageIO.read(new File("src/res/backgrounds/mainmenu.png"));
        BufferedImage image2 = ImageIO.read(new File("src/res/backgrounds/mainmenu.png"));

        ImagePanel imgPanel = new ImagePanel(image);

        imgPanel.setImage(image2);
        this.setContentPane(imgPanel);

        this.setSize(1250, 800);
        this.setLocation(0, 0);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setResizable(false);
    }


    private void addMenuPanel() throws IOException {
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

        this.add(myCurrentRoomPanel);
        this.repaint();
        myCurrentRoomPanel.requestFocusInWindow();
        myCurrentRoomPanel.setBounds(0, 0, 1250, 768);
        myCurrentRoomPanel.setFocusable(true);
    }
    /**Returns to main menu. */
    private void mainMenu() {
        this.getContentPane().remove(myCurrentRoomPanel);
        this.repaint();
    }

    /**
     * Closes the current process.
     */
    private void exitGame() {
        System.exit(0);
    }
}
