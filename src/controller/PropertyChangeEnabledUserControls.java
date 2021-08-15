package controller;

import java.beans.PropertyChangeListener;

/**
 * Interface defining behavior os user controller.
 * @author Dustin Ray
 * @version Summer 2021
 */
public interface PropertyChangeEnabledUserControls {

    /** Proximity constant to fire to listeners if sprite approaches a door. */
    String PROPERTY_PROXIMITY_DOOR_A = "A";
    /** Proximity constant to fire to listeners if sprite approaches a door. */
    String PROPERTY_PROXIMITY_DOOR_B = "B";
    /** Proximity constant to fire to listeners if sprite approaches a door. */
    String PROPERTY_PROXIMITY_DOOR_C = "C";
    /** Proximity constant to fire to listeners if sprite approaches a door. */
    String PROPERTY_PROXIMITY_DOOR_D = "D";
    /** X/Y position constant to fire to listeners when user controller changes position of the
     * player sprite. */
    String XY_POSITION = "xy position change";
    /** Property constant to fire when terrain surrounding the player sprite has changed. */
    String NEIGHBOR_CHANGE = "terrain neighboring sprite has changed";

    String SAVE = "save";


    String LOAD = "load";

    /**
     * Adds a property change listener.
     * @param theListener the listen to add.
     */
    void addPropertyChangeListener(PropertyChangeListener theListener);
    /**
     * Removes a property change listener.
     * @param theListener the listen to remove.
     */
    void removePropertyChangeListener(PropertyChangeListener theListener);

}
