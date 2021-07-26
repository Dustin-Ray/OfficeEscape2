package view;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.io.File;
import java.io.IOException;

import static controller.PropertyChangeEnabledUserControls.PROPERTY_PROXIMITY_DOOR_A;
import static controller.PropertyChangeEnabledUserControls.PROPERTY_PROXIMITY_DOOR_B;
import static controller.PropertyChangeEnabledUserControls.PROPERTY_PROXIMITY_DOOR_C;
import static controller.PropertyChangeEnabledUserControls.PROPERTY_PROXIMITY_DOOR_D;

public class ConsolePanel extends JPanel implements PropertyChangeListener {


    final JTextArea consoleScreen;

    BufferedImage myBackground1;
    public ConsolePanel() throws IOException, FontFormatException {

        myBackground1 = ImageIO.read(new File("src/res/backgrounds/console.png"));
        this.setLayout(null);
        this.repaint();
        Font fontTest = Font.createFont(Font.TRUETYPE_FONT, new File("src/res/fonts/expansiva/Expansiva.otf"));
        fontTest = fontTest.deriveFont(Font.PLAIN, 14);
        consoleScreen = new JTextArea("this is a test...");
        consoleScreen.setVisible(true);
        consoleScreen.setForeground(Color.WHITE);
        consoleScreen.setBackground(Color.BLACK);
        consoleScreen.setBounds(830, 50, 360, 245);
        consoleScreen.setLayout(null);
        consoleScreen.setFont(fontTest);
        this.add(consoleScreen);
    }


    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D mainBackground = (Graphics2D) g;
        mainBackground.drawImage(myBackground1, 768, 0, null);

    }


    @Override
    public void propertyChange(final PropertyChangeEvent theEvent) {
        switch (theEvent.getPropertyName()) {
            case PROPERTY_PROXIMITY_DOOR_A -> {
                consoleScreen.setText("Property change fired: " + "\n" + "next to door A");
            }
            case PROPERTY_PROXIMITY_DOOR_B -> {
                consoleScreen.setText("Property change fired: " + "\n" + "next to door B");
            }
            case PROPERTY_PROXIMITY_DOOR_C -> {
                consoleScreen.setText("Property change fired: " + "\n" + "next to door C");
            }
            case PROPERTY_PROXIMITY_DOOR_D -> {
                consoleScreen.setText("Property change fired: " + "\n" + "next to door D");
            }
        }
    }

    public void setupText() throws IOException, FontFormatException {



    }

}
