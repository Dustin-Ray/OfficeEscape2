package controller;

import java.beans.PropertyChangeListener;

public interface PropertyChangeEnabledUserControls {

    String PROPERTY_PROXIMITY_DOOR_A = "proximity to door A";
    String PROPERTY_PROXIMITY_DOOR_B = "proximity to door B";
    String PROPERTY_PROXIMITY_DOOR_C = "proximity to door C";
    String PROPERTY_PROXIMITY_DOOR_D = "proximity to door D";
    String PROPERTY_PROXIMITY_NO_DOOR = "not near a door";
    String XY_POSITION = "xy position change";
    String NEIGHBOR_CHANGE = "terrain neighboring sprite has changed";


    void addPropertyChangeListener(PropertyChangeListener theListener);
    void addPropertyChangeListener(String thePropertyName, PropertyChangeListener theListener);
    void removePropertyChangeListener(PropertyChangeListener theListener);
    void removePropertyChangeListener(String thePropertyName,
                                      PropertyChangeListener theListener);


}
