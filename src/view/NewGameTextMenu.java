package view;

import javax.swing.*;
import java.awt.*;

public class NewGameTextMenu extends JComponent {


    public NewGameTextMenu() {
        final JLabel newGame = new JLabel("New Game");
        newGame.setVisible(true);
        newGame.setForeground(Color.WHITE);
        newGame.setBounds(10, 10, 170, 20);
        newGame.setLayout(null);
        this.add(newGame);
    }



}
