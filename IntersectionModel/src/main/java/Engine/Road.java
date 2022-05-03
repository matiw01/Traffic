package Engine;

public interface Road{
    public boolean occupied = false;
    public Road getNext(VehicleTarget target);
    public Road getNext();
    public Road getPrevious();
    public Road getLeft();
    public Road getRight();
    public Vector getPosition();
    public void setNext(VehicleTarget target,  Road road);
    public void setNext(Road road);
    public void setPrevious(Road road);
    public void setLeft(Road road);
    public void setRight(Road road);
    public void setOccupied(boolean occupied);

    public boolean isOccupied();
    public boolean isAvailable(int velocity);
    public boolean isChangingPoint();
}
