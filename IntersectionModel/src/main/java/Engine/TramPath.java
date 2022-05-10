package Engine;

public interface TramPath{
    public TramPath getNext(Tram tram);
    public Vector getLocation();
    public void setOccupied(boolean occupied);
    public boolean isOccupied();
    //public boolean isAvailable(int velocity);
    public boolean isChangingPoint();
}
