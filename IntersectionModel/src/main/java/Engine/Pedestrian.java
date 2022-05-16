package Engine;

import java.util.HashMap;

public class Pedestrian {
    private final int type;
    private int velocity;
    private final int maxVelocity;
    private PedestrianPath location;
    private final PedestrianTarget target;
    public Pedestrian(PedestrianPath location, PedestrianTarget target){
        this.location = location;
        this.target = target;
        if(Math.random()<0.5){ //person
            this.type = 0;
            this.velocity = 1;
            this.maxVelocity = 1;
        }
        else{ //cyclist
            this.type = 1;
            this.velocity = 1;
            this.maxVelocity = 3;
        }
    }

    public void move(HashMap<Vector, LightsGroup> lightsGroupHashMap){
        PedestrianPath tile = location.getNext(this);
        if(lightsGroupHashMap.get(this.getLocation().getLocation()) != null || lightsGroupHashMap.get(tile.getLocation()) == null || lightsGroupHashMap.get(tile.getLocation()).getState() == 2){
            this.location = tile;
            this.velocity = Math.min(this.velocity + 1, this.maxVelocity);
        }
        else{
            this.velocity = 0; //for now instant slowing down for cyclists
        }
    }

    public int getType(){return this.type;}
    public int getVelocity(){return this.velocity;}
    public int getMaxVelocity(){return this.maxVelocity;}
    public PedestrianPath getLocation(){return this.location;}
    public PedestrianTarget getTarget(){return this.target;}
}
