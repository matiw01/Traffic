package Engine;

import javafx.scene.paint.Color;

import java.util.ArrayList;

public interface PedestrianPathT{
    void setNext(PedestrianPathT point);
    PedestrianPathT getNext(Pedestrian person);
    Vector getLocation();
    Color getColor();
    boolean isDestination();
    boolean isCrossing();
    boolean isVisited();
    void setVisited(boolean val);
    ArrayList<Integer> getTargetDistance();
    ArrayList<PedestrianPathT> getNext();
    void redirect();
}