package Engine;

import javafx.scene.paint.Color;

import java.util.ArrayList;

public class Tram{
    private final int numberOfPeople;
    private int velocity;
    private final int maxVelocity;
    private TramPath location;
    private final TramTarget target;
    private final int length;
    private final ArrayList<TramPath> parts = new ArrayList<>();

    public Tram(TramPath location, TramTarget target){
        this.maxVelocity = 3;
        this.velocity = this.maxVelocity;
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

    public void move(){
        this.location = this.location.getNext(this.target);

        if(this.parts.size() < this.length){
            this.parts.add(this.parts.get(this.parts.size()-1));
        }
        for(int i = this.parts.size()-1; i>0; i--){
            this.parts.get(i).setOccupied(false);
            this.parts.set(i, this.parts.get(i-1));
        }
        if(this.parts.size()-1 < this.length){
            this.parts.get(this.parts.size()-1).setOccupied(true);
        }
        this.parts.set(0, this.location);
        if(this.location != null){
            this.location.setOccupied(true);
        }
        else{
            this.parts.remove(0);
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