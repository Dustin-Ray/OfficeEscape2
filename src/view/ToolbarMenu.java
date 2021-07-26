package view;

import javax.swing.*;

public class ToolbarMenu extends JMenuBar {

    final JMenuBar menubar;
    public final JMenu fileMenu;
    public final JMenuItem mainMenu;
    public final JMenuItem newGame;
    public final JMenuItem loadGame;
    public final JMenuItem saveGame;
    public final JMenuItem closeGame;

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
//            try {
//
////                loadRoom(0);
//                repaint();
//            } catch (IOException fileNotFoundException) {
//                fileNotFoundException.printStackTrace();
//            }
        });
        closeGame.addActionListener(e -> System.exit(0));
//        mainMenu.addActionListener(e -> returnToMainMenu());


    }

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
