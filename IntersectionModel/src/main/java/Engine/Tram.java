package Engine;

public class Tram{
    //private final int type; mozna kiedys dodac jako linie
    private final int numberOfPeople = 1 + (int)(Math.random()*40);
    private int velocity;
    private final int maxVelocity;
    private Rails location;
    private final TramTarget target;

    public Tram(Rails location, TramTarget target){
        this.maxVelocity = 4;
        this.velocity = this.maxVelocity;
        this.location = location;
        this.target = target;
    }

    public void move(){
    }

    public int getNumberOfPeople(){return this.numberOfPeople;}
    public int getVelocity(){return this.velocity;}
    public int getMaxVelocity(){return this.maxVelocity;}
    public Rails getLocation(){return this.location;}
    public TramTarget getTarget(){return this.target;}
}
