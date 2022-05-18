package Engine;

import javafx.scene.paint.Color;

public interface TramPath{
    Vector getLocation();
    void setOccupied(boolean occupied);
    boolean isOccupied();
    boolean isChangingPoint();
    Color getColor();
    void setNext(TramPath tramPath);
    TramPath getNext(int i);
    TramPath getNext(TramTarget target);
}