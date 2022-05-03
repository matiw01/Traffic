public class Pedestrian {
    private final int type;
    private int velocity;
    private final int maxVelocity;
    private PedestrianPath location;
    private final PedestrianTarget target;
    public Pedestrian(){
        //this.location
        this.target = PedestrianTarget.getRandom();
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

    public void move(){
        PedestrianPath prev = this.location;
        this.location = location.getNext(this);
        if(location == prev){this.velocity = 0;} //for now instant slowing down for cyclists
        else{
            this.velocity = Math.min(this.velocity + 1, this.maxVelocity);
        }
    }

    public int getType(){return this.type;}
    public int getVelocity(){return this.velocity;}
    public int getMaxVelocity(){return this.maxVelocity;}
    public PedestrianPath getLocation(){return this.location;}
    public PedestrianTarget getTarget(){return this.target;}
}
