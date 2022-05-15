package Engine;

import javafx.scene.paint.Color;

public class Tram{
    //private final int type; mozna kiedys dodac jako linie
    private final int numberOfPeople = 1 + (int)(Math.random()*40);
    private int velocity;
    private final int maxVelocity;
    private TramPath location;
    private final TramTarget target;

    public Tram(TramPath location, TramTarget target){
        this.maxVelocity = 1; //!!!!!!!!!!!
        this.velocity = this.maxVelocity;
        this.location = location;
        this.target = target;
    }

    public void move(){
        TramPath last = this.location;
        this.location = this.location.getNext(this.target);
        last.setOccupied(false);
        if(this.location != null){
            this.location.setOccupied(true);
        }
    }

    public int getNumberOfPeople(){return this.numberOfPeople;}
    public int getVelocity(){return this.velocity;}
    public int getMaxVelocity(){return this.maxVelocity;}
    public TramPath getLocation(){return this.location;}
    public TramTarget getTarget(){return this.target;}
    public Color getColor(){return Color.GREEN;}
}