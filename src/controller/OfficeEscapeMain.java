package controller;


import model.Room;
import model.RoomManager;
import model.graph.GraphManager;
import view.OfficeEscapeView;

import javax.swing.*;
import java.awt.*;
import java.io.IOException;
import java.util.List;
import java.util.Map;

public class OfficeEscapeMain {

    private static final int DEFAULT_ROW_DIM = 4;

    private static final int DEFAULT_COL_DIM = 4;


    public static void main(String[] theArgs) {
        Map<Integer, List<Integer>> connectedRooms;
        connectedRooms = getConnectedRooms();
        List<Room> extractedRooms = getExtractedRooms(connectedRooms);

        /*
        System.out statements only here temporarily for testing.
         */
        System.out.println(connectedRooms.toString());
        System.out.println();
        System.out.println(extractedRooms.toString());
        //

        run();
    }

    public static void run() {
        EventQueue.invokeLater(new Runnable() {
            @Override
            public void run() {
                try {
                    new OfficeEscapeView();
                } catch (final ClassNotFoundException
                        | InstantiationException
                        | IllegalAccessException
                        | UnsupportedLookAndFeelException
                        | IOException
                        | FontFormatException e) {
                    e.printStackTrace();
                }
            }
        });
    }


    public static Map<Integer, List<Integer>> getConnectedRooms() {
        GraphManager manager = new GraphManager(DEFAULT_ROW_DIM, DEFAULT_COL_DIM);
        return manager.getConnectedRoomsMap();
    }

    public static List<Room> getExtractedRooms(final Map<Integer, List<Integer>> connectedRooms) {
        RoomManager manager = new RoomManager(connectedRooms, DEFAULT_ROW_DIM, DEFAULT_COL_DIM);
        return manager.extractRooms();
    }

}
