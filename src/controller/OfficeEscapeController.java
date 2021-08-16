package controller;

import model.room.RoomBuilder;

import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;
import javax.swing.*;
import java.awt.*;
import java.io.IOException;


/**
 *
 */
public class OfficeEscapeController {

    public OfficeEscapeController() {
        run();
    }

    public void run() {
        EventQueue.invokeLater(() -> {
            try {
                new ViewController();
            } catch (final
                    ClassNotFoundException |
                    InstantiationException |
                    IllegalAccessException |
                    UnsupportedLookAndFeelException |
                    IOException |
                    FontFormatException |
                    UnsupportedAudioFileException |
                    LineUnavailableException e) {
                e.printStackTrace();
            }
        });
    }
}


