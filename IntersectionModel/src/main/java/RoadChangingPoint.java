import java.util.HashMap;
import java.util.Map;

public class RoadChangingPoint implements Road{
    boolean occupied = false;
    private Road straight = null;
    private Map<VehicleTarget, Road> turn = new HashMap<VehicleTarget, Road>(); //maybe might be final
    @Override
    public Road getNext(VehicleTarget target) {return turn.get(target);}
    public Road getNext(){return straight;}
}
