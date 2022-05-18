package Engine;

public interface Road{
    boolean occupied = false;
    Road getNext(VehicleTarget target);
    Road getNext();
    Road getPrevious();
    Road getLeft();
    Road getRight();
    Vector getPosition();
    void setNext(VehicleTarget target,  Road road);
    void setNext(Road road);
    void setPrevious(Road road);
    void setLeft(Road road);
    void setRight(Road road);
    void setOccupied(boolean occupied);
    boolean isOccupied();
    boolean isAvailable(int velocity);
    boolean isChangingPoint();
}