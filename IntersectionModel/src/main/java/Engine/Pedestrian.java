package Engine;

import java.util.HashMap;

public class Pedestrian {
    private int velocity;
    private PedestrianPath location;
    private final PedestrianTarget target;
    public Pedestrian(PedestrianPath location, PedestrianTarget target){
        this.location = location;
        this.target = target;
    }

    public void move(HashMap<Vector, LightsGroup> lightsGroupHashMap){
        PedestrianPath tile = location.getNext(this);
        if(this.getLocation().isCrossing() || !tile.isCrossing() || lightsGroupHashMap.get(tile.getLocation()).getState() > 0){this.location = tile;}
    }
    public PedestrianPath getLocation(){return this.location;}
    public PedestrianTarget getTarget(){return this.target;}
}
