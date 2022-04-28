public class AbstractRoad {
    protected Road straight;
    protected Road previous;
    protected Road left;
    protected Road right;
    protected boolean isChangingPoint;
    protected boolean occupied = false;
    protected static int maxSpeed;

    public Road getNext() {return straight;}
    public Road getPrevious() {return previous;}
    public Road getLeft(){return left;}
    public Road getRight(){return right;}
    public boolean isOccupied() {return occupied;}
    public boolean isChangingPoint(){return isChangingPoint;}

    public boolean isAvailable(int velocity) {
        int i = 0;
        Road current = (Road)this;
        while (i < maxSpeed - velocity){
            current = current.getPrevious();
            if (current.isOccupied()){
                return false;
            }
            i++;
        }
        return true;
    }
}
