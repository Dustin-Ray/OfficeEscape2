package view;

import javax.swing.*;

/**
 * Class containing a toolbar with menu options to display at the top
 * of the screen.
 * @author Dustin Ray
 * @version Summer 2021
 */
public class ToolbarMenu extends JMenuBar {

    /** The menu bar to be displayed at the top of the window. */
    final JMenuBar menubar;
    /** File menu options. */
    public final JMenu fileMenu;
    /** Returns focus aspect to main menu. */
    public final JMenuItem mainMenu;
    /** Option starts a new game and resets game state. */
    public final JMenuItem newGame;
    /** Loads a saved game state. */
    public final JMenuItem loadGame;
    /** Saves a current game state. */
    public final JMenuItem saveGame;
    /** Closes the game and shuts down the game process. */
    public final JMenuItem closeGame;

    /**
     * Constructor for toolbar.
     * @throws UnsupportedLookAndFeelException If unable to load system default l/f.
     * @throws ClassNotFoundException If unable to load system default l/f.
     * @throws InstantiationException If unable to load system default l/f.
     * @throws IllegalAccessException If unable to load system default l/f.
     */
    public ToolbarMenu() throws
            UnsupportedLookAndFeelException,
            ClassNotFoundException,
            InstantiationException,
            IllegalAccessException {

        setupUI();
        menubar = new JMenuBar();
        fileMenu = new JMenu("File");
        mainMenu = new JMenuItem("Main Menu");
        newGame = new JMenuItem("New Game");
        loadGame = new JMenuItem("Load Game");
        saveGame = new JMenuItem("Save Game");
        closeGame = new JMenuItem("Exit Game");

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
        menubar.setVisible(true);

        newGame.addActionListener(e -> {
        });
        closeGame.addActionListener(e -> System.exit(0));
    }

    /**
     * Sets up the UI for the toolbar.
     * @throws ClassNotFoundException If unable to load system default l/f.
     * @throws InstantiationException If unable to load system default l/f.
     * @throws IllegalAccessException If unable to load system default l/f.
     * @throws UnsupportedLookAndFeelException If unable to load system default l/f.
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

}
