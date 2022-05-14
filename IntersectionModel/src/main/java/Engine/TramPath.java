package Engine;

import javafx.scene.paint.Color;

public interface TramPath{
    public Vector getLocation();
    public void setOccupied(boolean occupied);
    public boolean isOccupied();
    //public boolean isAvailable(int velocity);
    public boolean isChangingPoint();
    public Color getColor();
    public void setNext(TramPath tramPath);
    public TramPath getNext(int i);
    public TramPath getNext(TramTarget target);
}