package view;

import javax.swing.*;
import java.awt.*;
import java.io.IOException;


public class MenuPanel extends JPanel{

    public MenuPanel() throws IOException, FontFormatException {

        setupPanel();
        setupLabels();
        this.setVisible(true);
    }
    public void setupPanel() {
        this.setSize(1250, 768);
    }

    /**
     *
     * @throws IOException
     * @throws FontFormatException
     */
    public void setupLabels() throws IOException, FontFormatException {

//        Font fontTest = Font.createFont(Font.TRUETYPE_FONT, Objects.requireNonNull(getClass()
//                .getClassLoader()
//                .getResourceAsStream("res/images/fonts/expansiva/Expansiva.otf")));

//        fontTest = fontTest.deriveFont(Font.PLAIN, 38);

        final JLabel title1 = new JLabel("office escape 2: ");
//        title1.setFont(fontTest);
        title1.setForeground(Color.white);

        title1.setBounds(500, 350,600, 50);
        this.add(title1);

        final JLabel newGame = new JLabel("new: ");
//        newGame.setFont(fontTest);
        newGame.setForeground(Color.white);
        newGame.setBounds(1000, 100, 600, 50);
        this.add(newGame);

        final JLabel loadGame = new JLabel("load: ");
//        loadGame.setFont(fontTest);
        loadGame.setForeground(Color.white);
        loadGame.setBounds(1000, 150, 600, 50);
        this.add(loadGame);
    }

}
