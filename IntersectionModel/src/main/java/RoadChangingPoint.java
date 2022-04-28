import java.util.HashMap;
import java.util.Map;

public class RoadChangingPoint extends AbstractRoad implements Road{
    boolean occupied = false;
    private Road straight = null;
    private Road previous = null;
    private Map<VehicleTarget, Road> turn = new HashMap<VehicleTarget, Road>(); //maybe might be final
    @Override
    public Road getNext(VehicleTarget target) {return turn.get(target);}
    public Road getNext(){return straight;}

    @Override
    public Road getPrevious() {
        return null;
    }

    @Override
    public boolean isAvailable(int velocity) {
        return false;
    }
}
