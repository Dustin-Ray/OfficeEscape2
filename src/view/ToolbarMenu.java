package view;

import controller.PropertyChangeEnabledUserControls;

import javax.sound.sampled.*;
import javax.swing.*;
import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;
import java.io.File;
import java.io.IOException;

/**
 * Class containing a toolbar with menu options to display at the top
 * of the screen.
 * @author Dustin Ray
 * @version Summer 2021
 */
public class ToolbarMenu extends JMenuBar implements PropertyChangeEnabledUserControls {

    /** The menu bar to be displayed at the top of the window. */
    private final JMenuBar myMenuBar;
    /** Game soundtrack audio file. */
    private Clip myAudioClip;

    private final PropertyChangeSupport myPcs;

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
            IllegalAccessException, UnsupportedAudioFileException, LineUnavailableException, IOException {

        setupUI();
        myPcs = new PropertyChangeSupport(this);
        myMenuBar = new JMenuBar();
        setupFileMenu();
        setupMusicControls();
        setupHelpMenu();
        myMenuBar.setVisible(true);


    }

    /** Returns this menubar object. */
    public JMenuBar getMyMenuBar() {return myMenuBar;}

    /** Initializes about menu and adds to menu bar */
    private void setupHelpMenu() {
        JMenu helpMenu = new JMenu("Help");
        JMenuItem about  = new JMenuItem("About");
        JMenuItem howToPlay = new JMenuItem("How To Play");
        JMenuItem cheat = new JMenuItem("Cheats");
        helpMenu.add(about);
        helpMenu.add(howToPlay);
        helpMenu.add(cheat);

        about.addActionListener(e -> fireAboutPropertyChange());
        howToPlay.addActionListener(e -> fireHowPropertyChange());
        cheat.addActionListener(e -> fireCheatPropertyChange());

        myMenuBar.add(helpMenu);
    }

    /** Initializes file menu and adds to menu bar */
    private void setupFileMenu() {
        JMenu fileMenu = new JMenu("File");
        JMenuItem mainMenu = new JMenuItem("Main Menu");
        JMenuItem newGame = new JMenuItem("New Game");
        JMenuItem loadGame = new JMenuItem("Load Game");
        JMenuItem saveGame = new JMenuItem("Save Game");
        JMenuItem closeGame = new JMenuItem("Exit Game");

        fileMenu.add(mainMenu);
        fileMenu.add(newGame);
        fileMenu.add(loadGame);
        fileMenu.add(saveGame);
        fileMenu.add(closeGame);

        saveGame.addActionListener(e -> fireSavePropertyChange());
        loadGame.addActionListener(e -> fireLoadPropertyChange());
        newGame.addActionListener(e -> fireNewGamePropertyChange());
        mainMenu.addActionListener(e -> fireMainMenuPropertyChange());
        closeGame.addActionListener(e -> System.exit(0));

        myMenuBar.add(fileMenu);
    }


    private void fireCheatPropertyChange() {
        myPcs.firePropertyChange(PropertyChangeEnabledUserControls.CHEAT, null, "cheat menu");
    }

    private void fireHowPropertyChange() {
        myPcs.firePropertyChange(PropertyChangeEnabledUserControls.HOW, null, "how to play menu");
    }

    private void fireAboutPropertyChange() {
        myPcs.firePropertyChange(PropertyChangeEnabledUserControls.ABOUT, null, "about menu");
    }

    private void fireSavePropertyChange() {
        myPcs.firePropertyChange(PropertyChangeEnabledUserControls.SAVE, null, "save");
    }

    private void fireLoadPropertyChange() {
        myPcs.firePropertyChange(PropertyChangeEnabledUserControls.LOAD, null, "load");
    }

    private void fireNewGamePropertyChange() {
        myPcs.firePropertyChange(PropertyChangeEnabledUserControls.NEW, null, "new game");

    }
    private void fireMainMenuPropertyChange() {
        myPcs.firePropertyChange(PropertyChangeEnabledUserControls.MAIN, null, "main menu");
    }

    /**
     * Adds a property change listener.
     * @param theListener the listen to add.
     */
    @Override
    public void addPropertyChangeListener(final PropertyChangeListener theListener) {
        myPcs.addPropertyChangeListener(theListener);
    }


    /**
     * Removes a property change listener.
     * @param theListener the listen to remove.
     */
    @Override
    public void removePropertyChangeListener(final PropertyChangeListener theListener) {
        myPcs.removePropertyChangeListener(theListener);
    }


    /** Initializes music controls menu and adds to menu bar */
    private void setupMusicControls() throws UnsupportedAudioFileException, IOException, LineUnavailableException {
        final JMenu musicControls = new JMenu("Music Controls");
        JMenuItem startMusic = new JMenuItem("Play");
        JMenuItem stopMusic = new JMenuItem("Stop");
        musicControls.add(stopMusic);
        musicControls.add(startMusic);
        loadSound();
        stopMusic.addActionListener(e -> {stopSound();});
        startMusic.addActionListener(e -> {playSound();});
        myMenuBar.add(musicControls);
    }

    /** Loads a sound file into memory for playback. */
    private void loadSound() throws UnsupportedAudioFileException, IOException, LineUnavailableException {

        File audioFile = new File("src/res/assets/music/Jordan F - Our Destiny, Above Us.wav");
        AudioInputStream audioStream = AudioSystem.getAudioInputStream(audioFile);

        AudioFormat format = audioStream.getFormat();
        DataLine.Info info = new DataLine.Info(Clip.class, format);
        myAudioClip = (Clip) AudioSystem.getLine(info);
        myAudioClip.open(audioStream);
        FloatControl volume = (FloatControl) myAudioClip.getControl(FloatControl.Type.MASTER_GAIN);
        volume.setValue((float) -4);
        myAudioClip.loop(100);
        myAudioClip.stop();
    }

    /** Plays the current sound from the last stop point. */
    private void playSound() {myAudioClip.start();}

    /** Stops the current sound playback. */
    private void stopSound() {myAudioClip.stop();
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
