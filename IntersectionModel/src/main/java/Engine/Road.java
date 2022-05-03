interface Road{
    public boolean occupied = false;
    public Road getNext(VehicleTarget target);
    public Road getNext();
    public Road getPrevious();
    public Road getLeft();
    public Road getRight();
    public boolean isOccupied();
    public boolean isAvailable(int velocity);
    public boolean isChangingPoint();
}
