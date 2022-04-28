interface Road{
    public boolean occupied = false;
    public Road getNext(VehicleTarget target);
    public Road getNext();
    public Road getPrevious();
    public boolean isAvailable(int velocity);
}