package Engine;

import java.util.ArrayList;

public class TramZone{
    private int waitingDisappointment = 0;
    private final Vector lowerLeft;
    private final Vector upperRight;
    private boolean vertical;
    public TramZone(Vector lowerLeft, Vector upperRight, boolean vertical){
        this.lowerLeft = lowerLeft;
        this.upperRight = upperRight;
        this.vertical = vertical;
    }

    public int calculateDisappointment(ArrayList<Tram> trams){
        for(Tram tram : trams){
            if(isInZone(tram)){
                this.waitingDisappointment += tram.getNumberOfPeople(); //time of waiting not included rn - only nr o passengers matters
            }
        }
        return this.waitingDisappointment;
    }
    private boolean isInZone(Tram tram){return this.lowerLeft.getPos_x()<=tram.getLocation().getLocation().getPos_x() && tram.getLocation().getLocation().getPos_x()<=this.upperRight.getPos_x() && this.lowerLeft.getPos_y()<=tram.getLocation().getLocation().getPos_y() && tram.getLocation().getLocation().getPos_y()<=this.upperRight.getPos_y();}
    public int getWaitingDisappointment(){return waitingDisappointment;}
    public void reset(){this.waitingDisappointment = 0;}
    public boolean isVertical(){return this.vertical;}

}
