package Engine;

import java.util.ArrayList;

public class Zone {
//  squared waiting time multiplied by number of passengers
    int waitingDisappointment = 0;
//    boolean orientation;
    Vector lowerLeft;
    Vector upperRight;
    int carNumber = 0;
    LightsGroup connectedLightsGroup;
    ArrayList<Vehicle> vehicleList = new ArrayList();
//    TODO add lights groups
    public Zone( Vector lowerLeft, Vector upperRight, LightsGroup connectedLightsGroup){
        this.lowerLeft = lowerLeft;
        this.upperRight = upperRight;
        this.connectedLightsGroup = connectedLightsGroup;
    }

    public Vector getLowerLeft(){return this.lowerLeft;}
    public Vector getUpperRight(){return this.upperRight;}
    public int getCarNumber(){return this.carNumber;}

    public boolean isInZone(Vehicle vehicle){
        boolean inZone = vehicle.getPosition().getPos_x() >= lowerLeft.getPos_x() && vehicle.getPosition().getPos_x() <= upperRight.getPos_x()
                && vehicle.getPosition().getPos_y() >= lowerLeft.getPos_y() && vehicle.getPosition().getPos_y() <= upperRight.getPos_y();
        if (inZone){
            this.waitingDisappointment += Math.pow(vehicle.waitingTime, 2) * vehicle.numberOfPeople;
        }
        return inZone;
    }

    public void reset(){
        this.waitingDisappointment = 0;
    }

    public LightsGroup getConnectedLightsGroup() {return this.connectedLightsGroup;}

}
