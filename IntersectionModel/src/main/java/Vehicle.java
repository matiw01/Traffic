public class Vehicle { //Change name
    //TODO Constuctor
    protected int numberOfPeople;
    protected float breakParameter = 0.1f;
    protected int maxVelocity;
    protected int velocity;
    protected VehicleTarget target;
    protected int travelTime = 0;
    protected int waitingTime = 0;
    protected Road currentPosition;
    protected int length;
    public void move(){
        int i = 0;
        Road current = currentPosition;
        //TODO
        while (i < velocity){
            current = currentPosition.getNext(target);
            if (currentPosition.isAvailable(velocity))
            i += 1;
        }
    }//TODO
    protected void randomBreak(){if (Math.random() < breakParameter) velocity -= 1;}
    protected void overTake(){}
    protected void accelerate(){velocity = Math.max(velocity+1, maxVelocity);}
}
