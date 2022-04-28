public class StraightRoad implements Road{
    private Road next;
    boolean occupied = false;
    @Override
    public Road getNext(VehicleTarget target) {return next;}
    @Override
    public Road getNext() {return next;}

    @Override
    public boolean isAvailable(int velocity) {

        return false;
    }
}
