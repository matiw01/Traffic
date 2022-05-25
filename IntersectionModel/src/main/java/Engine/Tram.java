package Engine;

import javafx.scene.paint.Color;

import java.util.ArrayList;
import java.util.HashMap;

public class Tram{
    private final int numberOfPeople;
    private int velocity;
    private final int maxVelocity;
    private final int accelerationTime;
    private int lastChange;
    private TramPath location;
    private final TramTarget target;
    private final int length;
    private final ArrayList<TramPath> parts = new ArrayList<>();

    public Tram(TramPath location, TramTarget target){
        if(target != TramTarget.BOTTOM && !(location.getLocation().getPos_y()>50)){this.maxVelocity = 3;}
        else{this.maxVelocity = 2;}
        this.accelerationTime = 3;
        this.lastChange = 0;
        this.velocity = 2;
        this.location = location;
        this.target = target;
        if(Math.random()<0.33){
            this.length = 6;
        }
        else if(Math.random()<0.66){
            this.length = 7;
        }
        else{
            this.length = 8;
        }
        this.numberOfPeople = 10 + (int)(Math.random()*40*this.length/8);
        this.parts.add(this.location);
    }

    public void move(HashMap<Vector, LightsGroup> lights){
        if(this.lastChange == this.accelerationTime){
            this.velocity = Math.min(this.maxVelocity, this.velocity+1);
            this.lastChange = 0;
        }
        this.lastChange++;
        TramPath rem = this.location;
        ArrayList<TramPath> locations = new ArrayList<>();
        for(int i = this.parts.size()-1; i>=0; i--){locations.add(this.parts.get(i));}
        for(int v = 0; v<this.velocity; v++){
            TramPath check = this.location.getNext(this.target);
            if(check != null){
                if(check.isOccupied() || !(lights.get(check.getLocation()) == null || lights.get(check.getLocation()).getState()>0)) {
                    this.velocity = 0;
                    this.lastChange = 0;
                    this.location = rem;
                    break;
                }
                else{
                    locations.add(check);
                    this.location = check;
                }

            }
            else{
                break;
            }
        }
        for(int i = 0; i<this.parts.size(); i++){
            if(i+this.velocity < locations.size()){
                this.parts.get(this.parts.size()-1-i).setOccupied(false);
                this.parts.set(this.parts.size()-1-i, locations.get(i+this.velocity));
                this.parts.get(this.parts.size()-1-i).setOccupied(true);
            }
            else{
                this.parts.get(this.parts.size()-1-i).setOccupied(false);
                this.parts.set(this.parts.size()-1-i, new Rails(-1,-1)); //nie istnieje to pole - substytut nulla tutaj
            }
        }
        for(int i = 0; i<this.velocity; i++) {
            if(this.parts.size()+i < this.length){
                this.parts.add(locations.get(this.velocity-1-i));
                this.parts.get(this.parts.size()-1).setOccupied(true);
            }
        }
    }

    public int getNumberOfPeople(){return this.numberOfPeople;}
    public int getVelocity(){return this.velocity;}
    public int getMaxVelocity(){return this.maxVelocity;}
    public TramPath getLocation(){return this.location;}
    public TramTarget getTarget(){return this.target;}
    public Color getColor(){return Color.GREEN;}
    public ArrayList<TramPath> getTramParts(){return this.parts;}
    private int getRange(){
        int sum = 0;
        int vel = this.velocity;
        while(vel > 0){
            sum += vel;
            vel--;
        }
        return sum;
    }
}