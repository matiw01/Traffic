package Engine;

public class StraightRoad extends AbstractRoad implements Road{
    protected Road straight;
    protected Road previous;
    protected Road right;
    protected Road left;

    StraightRoad(Road straight, Road previous, Road left, Road right, Vector position){
        this.position = position;
        this.straight = straight;
        this.previous = previous;
        this.left = left;
        this.right = right;
        this.isChangingPoint = false;
    }

    @Override
    public Road getNext(VehicleTarget target) {return straight;}

    @Override
    public void setNext(VehicleTarget target, Road road) {this.straight = road;}
}
