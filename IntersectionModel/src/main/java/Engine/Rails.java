package Engine;

public class Rails implements TramPath{
    private final Vector location;
    private boolean occupied = false;
    private TramPath next;

    public Rails(int x, int y){this.location = new Vector(x, y);}

    public TramPath getNext(Tram tram){return this.next;}

    public void setNext(TramPath next){this.next = next;}

    @Override
    public Vector getLocation(){return this.location;}

    @Override
    public void setOccupied(boolean occupied){this.occupied = occupied;}

    @Override
    public boolean isOccupied(){return occupied;}

    @Override
    public boolean isChangingPoint(){return false;}
}