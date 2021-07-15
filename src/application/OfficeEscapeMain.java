package application;
import view.OfficeEscapeView;

import javax.swing.*;
import java.awt.*;
import java.io.IOException;

public class OfficeEscapeMain {

    public static void main(String[] theArgs) {

        EventQueue.invokeLater(new Runnable() {
            @Override
            public void run() {
                try {
                    new OfficeEscapeView();
                } catch (final ClassNotFoundException | InstantiationException | IllegalAccessException | UnsupportedLookAndFeelException | IOException | FontFormatException e) {
                    e.printStackTrace();
                }
            }
        });
        System.out.println("Succeeds to this point");
    }
}
