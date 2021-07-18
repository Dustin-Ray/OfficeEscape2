package view;

import model.FileLoader;

import javax.swing.*;
import java.awt.*;
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
    MenuPanel myCurrentMenuPanel;
    MainMenuPanel myMainMenuPanel;
    SecretMainMenuPanel mySecretMainMenuPanel;

    public OfficeEscapeView() throws
            ClassNotFoundException,
            InstantiationException,
            IllegalAccessException,
            UnsupportedLookAndFeelException, IOException, FontFormatException {

        super("Office Escape v9");

        myCurrentRoomPanel = FileLoader.readCity(this);
        myCurrentNewGameText = new NewGameTextMenu();
        myCurrentMenuPanel = new MenuPanel();
        myMainMenuPanel = new MainMenuPanel();
        mySecretMainMenuPanel = new SecretMainMenuPanel();
        setupUI();
        setupFrame();


        addMenuPanel();
        addSecretMainMenuPanel();
        addMainMenuPanel();
        addNewGameTextMenu();
        this.setVisible(true);

    }

    private void addMainMenuPanel() {
        this.add(myMainMenuPanel);
        myMainMenuPanel.setBounds(0, 0, 1250, 768);
        myMainMenuPanel.setFocusable(true);
    }


    private void addSecretMainMenuPanel() {

        this.add(mySecretMainMenuPanel);
        myMainMenuPanel.setBounds(0, 0, 1250, 768);
        myMainMenuPanel.setFocusable(true);
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
        myCurrentNewGameText.setFocusable(true);

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
        this.setSize(1250, 800);
        this.setLocation(0, 0);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setResizable(false);
    }

    private void addMenuPanel() throws IOException {

        this.setJMenuBar(myCurrentMenuPanel.menubar);
        myCurrentMenuPanel.setVisible(true);
        myCurrentMenuPanel.newGame.addActionListener(e -> {
            try {
                addRoom();
            } catch (FileNotFoundException fileNotFoundException) {
                fileNotFoundException.printStackTrace();
            }
        });
        myCurrentMenuPanel.closeGame.addActionListener(e -> exitGame());
        myCurrentMenuPanel.mainMenu.addActionListener(e -> returnToMainMenu());

    }

    private void addRoom() throws FileNotFoundException {

        this.add(myCurrentRoomPanel);
        this.repaint();
        myCurrentRoomPanel.requestFocusInWindow();
        myCurrentRoomPanel.setBounds(0, 0, 1250, 768);
        myCurrentRoomPanel.setFocusable(true);
    }
    /**Returns to main menu. */
    private void returnToMainMenu() {
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
