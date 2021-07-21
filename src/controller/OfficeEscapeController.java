package controller;


import model.graph.GraphManager;
import model.room.Room;
import view.OfficeEscapeView;

import javax.swing.*;
import java.awt.*;
import java.io.IOException;
import java.util.List;
import java.util.Map;

public class OfficeEscapeController {

    private static final int DEFAULT_ROW_DIM = 4;
    private static final int DEFAULT_COL_DIM = 4;

    public OfficeEscapeController() throws IOException {
        Map<Integer, List<Integer>> connectedRooms;
        connectedRooms = getConnectedRooms();
        List<Room> extractedRooms = getExtractedRooms(connectedRooms);
        run();
//        System.out statements only here temporarily for testing.
//        System.out.println(connectedRooms);
//        System.out.println();
//        System.out.println(extractedRooms);

    }


    public void run() {
        EventQueue.invokeLater(() -> {
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
        });
    }


    public Map<Integer, List<Integer>> getConnectedRooms() {
        GraphManager manager = new GraphManager(DEFAULT_ROW_DIM, DEFAULT_COL_DIM);
        return manager.getConnectedRoomsMap();
    }

    public List<Room> getExtractedRooms(final Map<Integer, List<Integer>> connectedRooms) {
        RoomManager manager = new RoomManager(connectedRooms, DEFAULT_ROW_DIM, DEFAULT_COL_DIM);
        return manager.extractRooms();
    }

}
