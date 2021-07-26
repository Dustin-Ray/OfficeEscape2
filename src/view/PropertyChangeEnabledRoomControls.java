package view;

import java.beans.PropertyChangeListener;

public interface PropertyChangeEnabledRoomControls {
    String PROPERTY_ROOM_ID_CHANGE = "room number";
    void addPropertyChangeListener(PropertyChangeListener theListener);
    void addPropertyChangeListener(String thePropertyName, PropertyChangeListener theListener);
    void removePropertyChangeListener(PropertyChangeListener theListener);
    void removePropertyChangeListener(String thePropertyName,
                                      PropertyChangeListener theListener);


}
