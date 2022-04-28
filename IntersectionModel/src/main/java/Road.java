interface Road{
    boolean occupied = false;
    public Road getNext(VehicleTarget target);
    public Road getNext();
    public boolean isAvailable(int velocity);
}
