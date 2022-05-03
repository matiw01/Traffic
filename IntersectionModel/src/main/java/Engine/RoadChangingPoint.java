import java.util.HashMap;
import java.util.Map;

public class RoadChangingPoint extends AbstractRoad implements Road{
    private final Road straight;
    private final Road previous;
    private final Map<VehicleTarget, Road> turn;
    RoadChangingPoint(Road straight, Road previous,HashMap<VehicleTarget, Road> turn){
        this.straight = straight;
        this.previous = previous;
        this.turn = turn;
        this.isChangingPoint = true;
    }
    @Override
    public Road getNext(VehicleTarget target) {return turn.get(target);}
}
