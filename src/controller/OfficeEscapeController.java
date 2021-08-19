/*
University of Washington, Tacoma
TCSS 360 Software Development and Quality Assurance Techniques

Instructor: Tom Capaul
Academic Quarter: Summer 2021
Assignment: Group Project
Team members: Raz Consta, Reuben Keller, Dustin Ray
 */

package controller;

import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;
import javax.swing.*;
import java.awt.*;
import java.io.IOException;

/**
 * Initiates view controller which is entry point for application.
 * @author Raz Consta
 * @author Reuben Keller
 * @author Dustin Ray
 * @version Summer 2021
 */
public class OfficeEscapeController {

    /** Constructor. Calls run method. */
    public OfficeEscapeController() {
        run();
    }

    /** Initiates view controller. */
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


