package Engine;

import javafx.scene.paint.Color;

public class Rails implements TramPath{
    private final Vector location;
    private boolean occupied = false;
    private TramPath next;

    public Rails(int x, int y){this.location = new Vector(x, y);}

    @Override
    public TramPath getNext(int i){return this.next;}
    public TramPath getNext(){return this.next;}
    public TramPath getNext(TramTarget target){return this.next;}

    @Override
    public void setNext(TramPath next){this.next = next;}

    @Override
    public Vector getLocation(){return this.location;}

    @Override
    public void setOccupied(boolean occupied){this.occupied = occupied;}

    @Override
    public boolean isOccupied(){return occupied;}

    @Override
    public boolean isChangingPoint(){return false;}

    @Override
    public Color getColor() {
        return Color.CYAN;
    }
}