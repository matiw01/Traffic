package Engine;

public abstract class AbstractRoad{
    protected Road straight;
    protected Road previous;
    protected Road left;
    protected Road right;
    protected boolean isChangingPoint;
    protected boolean occupied = false;
    protected static int maxSpeed;
    protected Vector position;

    public Road getNext() {return straight;}
    public Road getPrevious() {return previous;}
    public Road getLeft(){return left;}
    public Road getRight(){return right;}
    public Vector getPosition(){return position;}
    public boolean isOccupied() {return occupied;}
    public boolean isChangingPoint(){return isChangingPoint;}

    public void setNext(Road straight){this.straight = straight;}
    public void setPrevious(Road previous){this.previous = previous;}
    public void setLeft(Road left){this.left = left;}
    public void setRight(Road right){this.right = right;}
    public void setOccupied(boolean occupied){this.occupied = occupied;};

    public boolean isAvailable(int velocity) {
        int i = 0;
        return !occupied;
    }
}