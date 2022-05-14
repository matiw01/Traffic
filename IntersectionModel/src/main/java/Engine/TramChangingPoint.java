package Engine;

import javafx.scene.paint.Color;

import java.util.ArrayList;

public class TramChangingPoint implements TramPath{
    private final Vector location;
    private boolean occupied = false;
    private ArrayList<TramPath> next = new ArrayList<>();

    public TramChangingPoint(int x, int y){this.location = new Vector(x, y);}

    @Override
    public TramPath getNext(int i){
        if(i>=this.next.size()){return null;}
        return this.next.get(i);
    }
    public TramPath getNext(TramTarget target){
        switch(target){
            case LEFT:{
                if(this.next.get(0).getLocation().getPos_x() < this.next.get(1).getLocation().getPos_x()){
                    return this.next.get(0);
                }
                else if(this.next.get(0).getLocation().getPos_x() > this.next.get(1).getLocation().getPos_x()){
                    return this.next.get(1);
                }
                else{
                    if(this.next.get(0).getLocation().getPos_y() > this.next.get(1).getLocation().getPos_y()){
                        return this.next.get(0);
                    }
                    else{
                        return this.next.get(1);
                    }
                }
            }
            case RIGHT:{
                if(this.next.get(0).getLocation().getPos_x() > this.next.get(1).getLocation().getPos_x()){
                    return this.next.get(0);
                }
                else{
                    return this.next.get(1);
                }
            }
            default:{
                if(this.next.get(0).getLocation().getPos_x() == this.getLocation().getPos_x()-1){
                    return this.next.get(0);
                }
                else{
                    return this.next.get(1);
                }
            }
        }
    }

    @Override
    public void setNext(TramPath tramPath){this.next.add(tramPath);}

    @Override
    public Vector getLocation(){return this.location;}

    @Override
    public void setOccupied(boolean occupied){this.occupied = occupied;}

    @Override
    public boolean isOccupied(){return this.occupied;}

    @Override
    public boolean isChangingPoint(){return true;}

    @Override
    public Color getColor() {
        return Color.PURPLE;
    }
}