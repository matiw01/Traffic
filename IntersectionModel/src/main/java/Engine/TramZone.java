package Engine;

import java.util.ArrayList;

public class TramZone{
    private int waitingDisappointment = 0;
    private final Vector lowerLeft;
    private final Vector upperRight;
    private final TramTarget target;
    private final boolean to;
    private boolean vertical;
    public TramZone(Vector lowerLeft, Vector upperRight, TramTarget target, boolean to, boolean vertical){
        this.lowerLeft = lowerLeft;
        this.upperRight = upperRight;
        this.target = target;
        this.to = to;
        this.vertical = vertical;
    }

    public int calculateDisappointment(ArrayList<Tram> trams){
        for(Tram tram : trams){
            if(isInZone(tram)){
                this.waitingDisappointment += tram.getWaitingTime()*tram.getWaitingTime()*tram.getNumberOfPeople(); //time of waiting not included rn - only nr of passengers matters
            }
        }
        return this.waitingDisappointment;
    }
    private boolean isInZone(Tram tram){
        if(this.to){
            if(tram.getTarget() != this.target){return false;}
        }
        else{
            if(tram.getTarget() == this.target){return false;}
        }
        return this.lowerLeft.getPos_x()<=tram.getLocation().getLocation().getPos_x() &&
                tram.getLocation().getLocation().getPos_x()<=this.upperRight.getPos_x() &&
                this.lowerLeft.getPos_y()<=tram.getLocation().getLocation().getPos_y() &&
                tram.getLocation().getLocation().getPos_y()<=this.upperRight.getPos_y();
    }
    public int getWaitingDisappointment(){return waitingDisappointment;}
    public void reset(){this.waitingDisappointment = 0;}
    public TramTarget getTarget(){return this.target;}
    public boolean isTo(){return this.to;}
    public boolean isVertical(){return this.vertical;}

}
