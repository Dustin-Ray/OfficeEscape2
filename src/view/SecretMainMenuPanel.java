package view;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class SecretMainMenuPanel extends JPanel {



    BufferedImage myBackground2;

    public SecretMainMenuPanel() throws IOException {

        this.setPreferredSize(new Dimension(1200, 768));
        myBackground2 = ImageIO.read(new File("src/res/backgrounds/mainmenu2.png"));

        this.repaint();
        this.setVisible(true);
    }
    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);

        Graphics2D g2d = (Graphics2D) g;
        g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING,
                RenderingHints.VALUE_ANTIALIAS_ON);
        g2d.drawImage(myBackground2, 0, 0, null);

    }


}
