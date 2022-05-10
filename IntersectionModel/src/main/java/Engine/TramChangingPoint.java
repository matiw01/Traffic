package Engine;

import java.util.ArrayList;

public class TramChangingPoint implements TramPath{
    private final Vector location;
    private boolean occupied = false;
    private ArrayList<TramPath> next = new ArrayList<>();

    public TramChangingPoint(int x, int y){this.location = new Vector(x, y);}

    @Override
    public TramPath getNext(Tram tram){ //TODO
        return this;
    }

    @Override
    public Vector getLocation(){return this.location;}

    @Override
    public void setOccupied(boolean occupied){this.occupied = occupied;}

    @Override
    public boolean isOccupied(){return this.occupied;}

    @Override
    public boolean isChangingPoint(){return true;}
}
