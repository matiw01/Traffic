public class StraightRoad extends AbstractRoad implements Road{
    protected final Road straight;
    protected final Road previous;
    protected final Road right;
    protected final Road left;

    StraightRoad(Road straight, Road previous, Road left, Road right){
        this.straight = straight;
        this.previous = previous;
        this.left = left;
        this.right = right;
        this.isChangingPoint = false;
    }

    @Override
    public Road getNext(VehicleTarget target) {return straight;}
}
