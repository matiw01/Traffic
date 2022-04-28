public class StraightRoad extends AbstractRoad implements Road{

    private Road previous;
    private boolean occupied = false;


    @Override
    public Road getNext(VehicleTarget target) {return straight;}

    @Override
    public Road getPrevious() {
        return null;
    }

    @Override
    public boolean isAvailable(int velocity) {

        return false;
    }
}
