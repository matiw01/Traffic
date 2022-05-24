package Engine;

import java.util.ArrayList;

public class Zone {
//  squared waiting time multiplied by number of passengers
    int waitingDisappointment = 0;
//    boolean orientation;
    Vector lowerLeft;
    Vector upperRight;
    int carNumber = 0;
    ArrayList<Vehicle> vehicleList = new ArrayList();
//    TODO add lights groups
    public Zone( Vector lowerLeft, Vector upperRight){
        this.lowerLeft = lowerLeft;
        this.upperRight = upperRight;
    }

    public Vector getLowerLeft(){return this.lowerLeft;}
    public Vector getUpperRight(){return this.upperRight;}
    public int getCarNumber(){return this.carNumber;}

    public boolean isInZone(Vehicle vehicle){
        boolean inZone = vehicle.getPosition().getPos_x() >= lowerLeft.getPos_x() && vehicle.getPosition().getPos_x() <= upperRight.getPos_x()
                && vehicle.getPosition().getPos_y() >= lowerLeft.getPos_y() && vehicle.getPosition().getPos_y() <= upperRight.getPos_x();
        if (inZone){
            this.waitingDisappointment += Math.pow(vehicle.waitingTime, 2) * vehicle.numberOfPeople;
        }
        return inZone;
    }

    public void reset(){
        this.waitingDisappointment = 0;
    }




}
