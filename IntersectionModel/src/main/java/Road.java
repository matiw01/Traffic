interface Road{
    boolean occupied = false;
    VehicleTarget target = null;
    public Road getNext(VehicleTarget target);
}
