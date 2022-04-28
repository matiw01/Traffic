import java.util.HashMap;
import java.util.Map;

public class RoadChangingPoint implements Road{
    boolean occupied = false;
    VehicleTarget target = null;//maybe not needed
    private Map<VehicleTarget, Road> turn = new HashMap<VehicleTarget, Road>(); //maybe might be final
    @Override
    public Road getNext(VehicleTarget target) {return turn.get(target);}
}
