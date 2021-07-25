package view;

import javax.swing.*;
import java.awt.*;
import java.io.File;
import java.io.IOException;

public class ConsolePanel extends JPanel {

    public ConsolePanel() throws IOException, FontFormatException {
        setBackground(Color.BLACK);
        setupText();
        this.setLayout(null);
    }

    public void setupText() throws IOException, FontFormatException {

        Font fontTest = Font.createFont(Font.TRUETYPE_FONT, new File("src/res/fonts/expansiva/Expansiva.otf"));
        fontTest = fontTest.deriveFont(Font.PLAIN, 32);

        final JLabel testLabel = new JLabel("this is a test...");

        testLabel.setVisible(true);
        testLabel.setForeground(Color.WHITE);
        testLabel.setBounds(800, 40, 300, 40);
        testLabel.setLayout(null);
        testLabel.setFont(fontTest);

        this.add(testLabel);

    }

}
