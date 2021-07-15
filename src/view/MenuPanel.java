package view;

import javax.swing.*;
import java.awt.*;
import java.io.IOException;
import java.util.Objects;

public class MenuPanel extends JPanel{

    public JPanel myPanel;

    public MenuPanel() throws IOException, FontFormatException {
        setupPanel();
        setupLabels();
        myPanel.setVisible(true);
    }

    public void setupPanel() {
        myPanel = new JPanel();
        myPanel.setSize(1250, 650);


    }

    /**
     *
     * @throws IOException
     * @throws FontFormatException
     */
    public void setupLabels() throws IOException, FontFormatException {

        Font fontTest = Font.createFont(Font.TRUETYPE_FONT, Objects.requireNonNull(getClass()
                .getClassLoader()
                .getResourceAsStream("res/images/fonts/expansiva/Expansiva.otf")));

        fontTest = fontTest.deriveFont(Font.PLAIN, 38);

        final JLabel title1 = new JLabel("office escape 2: ");
        title1.setFont(fontTest);
        title1.setForeground(Color.black);
        title1.setBounds(700, 20, 600, 50);
        myPanel.add(title1);

        final JLabel newGame = new JLabel("new: ");
        newGame.setFont(fontTest);
        newGame.setForeground(Color.black);
        newGame.setBounds(1000, 100, 600, 50);
        myPanel.add(newGame);

        final JLabel loadGame = new JLabel("load: ");
        loadGame.setFont(fontTest);
        loadGame.setForeground(Color.black);
        loadGame.setBounds(1000, 150, 600, 50);
        myPanel.add(loadGame);
    }

}
