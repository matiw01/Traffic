package Engine;

import java.util.HashMap;
import java.util.Map;

public class RoadChangingPoint extends AbstractRoad implements Road{
    private final Map<VehicleTarget, Road> turn;
    RoadChangingPoint(Road straight, Road previous, HashMap<VehicleTarget, Road> turn, Vector position){
        this.straight = straight;
        this.previous = previous;
        this.turn = turn;
        this.isChangingPoint = true;
        this.position = position;
    }
    @Override
    public Road getNext(VehicleTarget target) {
        return turn.get(target);}

    @Override
    public void setNext(VehicleTarget target, Road road) {
        turn.put(target, road);
    }
}