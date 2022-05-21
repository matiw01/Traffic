package Engine;

import javafx.scene.paint.Color;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;

import static java.util.Collections.*;

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
        this.velocity = 1;
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

    public void move(){
        ArrayList<TramPath> locations = new ArrayList<>();
        for(TramPath tp : this.parts){locations.add(tp);}
        for(int v = 0; v<this.velocity; v++){
            this.location = this.location.getNext(this.target);
            if(this.location != null){locations.add(this.location);}
            else{
                this.location = locations.get(locations.size()-1);
                break;
            }
        }
        if(this.parts.size() < this.length){
            this.parts.add(locations.get(this.velocity-1));
            this.parts.get(this.parts.size()-1).setOccupied(true);
        }
        for(int i = 0; i<this.parts.size(); i++){
            if(i+this.velocity < locations.size()){
                this.parts.get(i).setOccupied(false);
                this.parts.set(i, locations.get(i+this.velocity));
                this.parts.get(i).setOccupied(true);
            }
            else{

                this.parts.get(i).setOccupied(false);
                this.parts.set(i, new Rails(-1,-1)); //nie istnieje to pole - substytut nulla tutaj
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