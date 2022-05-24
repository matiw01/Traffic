package Engine;

import java.util.ArrayList;

public class TramZone{
    private int waitingDisappointment = 0;
    private final Vector lowerLeft;
    private final Vector upperRight;
    public TramZone(Vector lowerLeft, Vector upperRight){
        this.lowerLeft = lowerLeft;
        this.upperRight = upperRight;
    }

    public int calculateDisappointment(ArrayList<Tram> trams){
        for(Tram tram : trams){
            if(isBetween(tram)){
                this.waitingDisappointment += tram.getNumberOfPeople(); //time of waiting not included rn - only nr o passengers matters
            }
        }
        return this.waitingDisappointment;
    }
    private boolean isBetween(Tram tram){return this.lowerLeft.getPos_x()<=tram.getLocation().getLocation().getPos_x() && tram.getLocation().getLocation().getPos_x()<=this.upperRight.getPos_x() && this.lowerLeft.getPos_y()<=tram.getLocation().getLocation().getPos_y() && tram.getLocation().getLocation().getPos_y()<=this.upperRight.getPos_y();}
    public int getWaitingDisappointment(){return waitingDisappointment;}
    public void reset(){this.waitingDisappointment = 0;}
}
