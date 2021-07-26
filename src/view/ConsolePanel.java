package view;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.io.File;
import java.io.IOException;

import static controller.PropertyChangeEnabledUserControls.*;

public class ConsolePanel extends JPanel implements PropertyChangeListener {


    JTextArea consoleScreenTextArea;

    BufferedImage displayConsole;
    BufferedImage infoConsole;
    public ConsolePanel() throws IOException, FontFormatException {
        displayConsole = ImageIO.read(new File("src/res/assets/console.png"));
        infoConsole = ImageIO.read(new File("src/res/assets/info_console.png"));
        this.setLayout(null);
        setupText();
    }


    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D displayConsole = (Graphics2D) g;
        displayConsole.drawImage(this.displayConsole, 768, 0, null);
        displayConsole.drawImage(this.infoConsole, 768, 480, null);
    }

    @Override
    public void propertyChange(final PropertyChangeEvent theEvent) {
        switch (theEvent.getPropertyName()) {
            case PROPERTY_PROXIMITY_DOOR_A -> {
                consoleScreenTextArea.setText("Property change fired: " + "\n" + "next to door A");
            }
            case PROPERTY_PROXIMITY_DOOR_B -> {
                consoleScreenTextArea.setText("Property change fired: " + "\n" + "next to door B");
            }
            case PROPERTY_PROXIMITY_DOOR_C -> {
                consoleScreenTextArea.setText("Property change fired: " + "\n" + "next to door C");
            }
            case PROPERTY_PROXIMITY_DOOR_D -> {
                consoleScreenTextArea.setText("Property change fired: " + "\n" + "next to door D");
            }
            case PROPERTY_PROXIMITY_NO_DOOR -> {
                consoleScreenTextArea.setText("not near any door");
            }
        }
    }

    public void setupText() throws IOException, FontFormatException {

        Font fontTest = Font.createFont(Font.TRUETYPE_FONT, new File("src/res/fonts/expansiva/Expansiva.otf"));
        fontTest = fontTest.deriveFont(Font.PLAIN, 18);
        consoleScreenTextArea = new JTextArea("this is a test...");
        consoleScreenTextArea.setVisible(true);
        consoleScreenTextArea.setForeground(Color.WHITE);
        consoleScreenTextArea.setBackground(Color.BLACK);
        consoleScreenTextArea.setBounds(830, 50, 360, 245);
        consoleScreenTextArea.setLayout(null);
        consoleScreenTextArea.setFont(fontTest);
        this.add(consoleScreenTextArea);

    }

}
