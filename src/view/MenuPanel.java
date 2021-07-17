package view;

import javax.swing.*;

public class MenuPanel extends JMenuBar {


    public final JMenu fileMenu = new JMenu("File");
    public final JMenuItem mainMenu = new JMenuItem("Main Menu");
    public final JMenuItem newGame = new JMenuItem("New Game");
    public final JMenuItem loadGame = new JMenuItem("Load Game");
    public final JMenuItem saveGame = new JMenuItem("Save Game");
    public final JMenuItem closeGame = new JMenuItem("Exit Game");

    public MenuPanel() {

        final JMenuBar menubar = new JMenuBar();

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

    }



}
