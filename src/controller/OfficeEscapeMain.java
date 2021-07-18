package controller;


import view.OfficeEscapeView;

import javax.swing.*;
import java.awt.*;
import java.io.IOException;
import java.util.List;
import java.util.Map;

public class OfficeEscapeMain {

    public static void main(String[] theArgs) {
        Map<Integer, List<Integer>> connectedRooms = getConnectedRooms(4, 4);

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

    public static Map<Integer, List<Integer>> getConnectedRooms(final int rows,  final int cols) {
        GraphManager manager = new GraphManager(rows, cols);
        return manager.getConnectedRoomsMap();
    }

}
