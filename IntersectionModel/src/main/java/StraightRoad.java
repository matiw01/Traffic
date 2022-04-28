public class StraightRoad implements Road{
    private Road next;
    boolean occupied = false;
    VehicleTarget target = null;
    @Override
    public Road getNext(VehicleTarget target) {return next;}
}
