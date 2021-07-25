package controller;


import model.room.Room;
import view.OfficeEscapeView;

import javax.swing.*;
import java.awt.*;
import java.io.IOException;
import java.util.List;
import java.util.*;

public class OfficeEscapeController {

    private static final int DEFAULT_ROW_DIM = 4;
    private static final int DEFAULT_COL_DIM = 4;

    public OfficeEscapeController() throws IOException {

        run();
    }


    public void run() {

        HashMap<Room, HashSet<Room>> rooms = getExtractedRooms(getConnectedRooms());
        List<Room> roomArray = rooms.keySet().stream().toList();


        EventQueue.invokeLater(() -> {
            try {
                new OfficeEscapeView(roomArray.get(0));
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
