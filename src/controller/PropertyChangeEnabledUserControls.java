package controller;

import java.beans.PropertyChangeListener;

public interface PropertyChangeEnabledUserControls {

    String PROPERTY_PLAYER = "player";

    void addPropertyChangeListener(PropertyChangeListener theListener);
    void addPropertyChangeListener(String thePropertyName, PropertyChangeListener theListener);
    void removePropertyChangeListener(PropertyChangeListener theListener);
    void removePropertyChangeListener(String thePropertyName,
                                      PropertyChangeListener theListener);


}
