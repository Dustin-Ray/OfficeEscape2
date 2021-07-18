package controller;


import view.OfficeEscapeView;

import javax.swing.*;
import java.awt.*;
import java.io.IOException;
import java.util.List;
import java.util.Map;

public class OfficeEscapeMain {

    public static void main(String[] theArgs) {
        Map<Integer, List<Integer>> connectedRooms = getConnectedRooms();

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

    public static Map<Integer, List<Integer>> getConnectedRooms() {
        GraphManager manager = new GraphManager(3, 3);
        return manager.getConnectedRoomsMap();
    }

}
