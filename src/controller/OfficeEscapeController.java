package controller;


import model.graph.GraphManager;
import model.room.Room;
import view.OfficeEscapeView;

import javax.swing.*;
import java.awt.*;
import java.io.IOException;
import java.util.List;
import java.util.*;

public class OfficeEscapeController {

    private static final int DEFAULT_ROW_DIM = 2;
    private static final int DEFAULT_COL_DIM = 2;

    public OfficeEscapeController() throws IOException {
        HashMap<Room, HashSet<Room>> rooms = getExtractedRooms(getConnectedRooms());
        run();
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

    public HashMap<Room, HashSet<Room>> getExtractedRooms(final Map<Integer, List<Integer>> connectedRooms) {
        RoomManager manager = new RoomManager(connectedRooms, DEFAULT_ROW_DIM);
        return manager.extractRooms();
    }

}
