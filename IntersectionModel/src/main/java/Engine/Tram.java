package Engine;

import javafx.scene.paint.Color;

import java.util.ArrayList;
import java.util.HashMap;

public class Tram{
    private final int numberOfPeople;
    private int velocity;
    private final int maxVelocity;
    private TramPath location;
    private final TramTarget target;
    private final int length;
    private final ArrayList<TramPath> parts = new ArrayList<>();
    private final TramPath start;

    public Tram(TramPath location, TramTarget target){
        this.maxVelocity = 3;
        this.velocity = 2;
        this.location = location;
        this.target = target;
        this.start = location;
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
        ArrayList<TramPath> locations = new ArrayList<>();
        for(int i = this.parts.size()-1; i>=0; i--){locations.add(this.parts.get(i));}
        for(int v = 0; v<this.velocity; v++){
            this.location = this.location.getNext(this.target);
            if(this.location != null){
                if(lights.get(this.location.getLocation()) == null || lights.get(this.location.getLocation()).getState()>0){
                    locations.add(this.location);
                }
                else{
                    break;
                }
            }
            else{
                this.location = locations.get(locations.size()-1);
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
}