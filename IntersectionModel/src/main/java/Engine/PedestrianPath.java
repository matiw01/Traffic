package Engine;

import javafx.scene.paint.Color;
import java.util.ArrayList;

public interface PedestrianPath {
    void setNext(PedestrianPath point);
    PedestrianPath getNext(Pedestrian person);
    Vector getLocation();
    Color getColor();
    boolean isDestination();
    boolean isCrossing();
    boolean isVisited();
    void setVisited(boolean val);
    ArrayList<Integer> getTargetDistance();
    ArrayList<PedestrianPath> getNext();
    void redirect();
}