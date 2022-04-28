public abstract class Vehicle {
    protected float breakParameter = 0.1f;
    protected int maxVelocity;
    protected int velocity;
    protected VehicleTarget target;
    protected int travelTime = 0;
    protected int waitingTime = 0;
    protected Road currentPosition;
    public void move(){
        int i = 0;
        Road current = currentPosition;
        //TO-DO
        while (i < velocity){
            current = currentPosition.getNext(target);
            i += 1;
        }
    };//TO-DO
    protected void randomBreak(){if (Math.random() < breakParameter) velocity -= 1;};
    protected void overTake(){};
    protected void accelerate(){velocity = Math.max(velocity+1, maxVelocity);};
}
