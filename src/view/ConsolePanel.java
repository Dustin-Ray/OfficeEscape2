package view;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class ConsolePanel extends JPanel {

    BufferedImage myBackground1;
    public ConsolePanel() throws IOException, FontFormatException {

        myBackground1 = ImageIO.read(new File("src/res/backgrounds/console.png"));
        this.setLayout(null);
        this.repaint();
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


    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D mainBackground = (Graphics2D) g;
        mainBackground.drawImage(myBackground1, 0, 0, null);

    }


    public void setupText() throws IOException, FontFormatException {



    }

}
