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
    MenuPanel myCurrentMenuPanel;
    MainMenuPanel myMainMenuPanel;


    public OfficeEscapeView() throws
            ClassNotFoundException,
            InstantiationException,
            IllegalAccessException,
            UnsupportedLookAndFeelException, IOException, FontFormatException {

        super("Office Escape 9: The Story Continues");

        myCurrentRoomPanel = FileLoader.readCity(this);

        myCurrentMenuPanel = new MenuPanel();
        myMainMenuPanel = new MainMenuPanel();
        setupUI();
        setupFrame();
        addMenuPanel();
        addMainMenuPanel();
        this.setVisible(true);

    }

    private void addMainMenuPanel() {
        myMainMenuPanel.setFocusable(true);
        this.add(myMainMenuPanel);
        myMainMenuPanel.setBounds(0, 0, 1250, 768);

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
        myCurrentRoomPanel.setFocusable(true);
        this.remove(myMainMenuPanel);
        this.add(myCurrentRoomPanel);
        this.repaint();
        myCurrentRoomPanel.requestFocusInWindow();
        myCurrentRoomPanel.setBounds(0, 0, 1250, 768);

    }
    /**Returns to main menu. */
    private void returnToMainMenu() {
        myCurrentRoomPanel.setFocusable(false);
        this.getContentPane().remove(myCurrentRoomPanel);
        addMainMenuPanel();
        this.repaint();
    }

    /**
     * Closes the current process.
     */
    private void exitGame() {
        System.exit(0);
    }
}
