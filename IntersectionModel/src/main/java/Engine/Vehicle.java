package Engine;

public class Vehicle {
    protected int numberOfPeople;
    protected float breakParameter = 0.1f;
    protected int maxVelocity;
    protected int velocity;
    protected VehicleTarget target;
    protected int travelTime = 0;
    protected int waitingTime = 0;
    protected Road currentPosition;
    protected int length;

    Vehicle(int numberOfPeople, int maxVelocity, VehicleTarget target, Road currentPosition, int length){
        this.numberOfPeople = numberOfPeople;
        this.maxVelocity = maxVelocity;
        this.velocity = 0;
        this.target = target;
        this.currentPosition = currentPosition;
        currentPosition.setOccupied(true);
        this.length = length;
    }

    public void move(){
        accelerate();
        Road current = currentPosition;
//        System.out.println("current:");
//        System.out.println(current);
        int i = 0;
        while (i < velocity && current != null) {
            System.out.println(current);
            if (current.getNext() == null){
                System.out.println("out of map");
                return;
            }
            if (current.getNext().isAvailable(velocity))
                current = current.getNext();
            i++;
        }
        currentPosition = current;
        randomBreak();
    }
    public Vector getPosition(){return currentPosition.getPosition();}
    protected void randomBreak(){if (Math.random() < breakParameter) velocity -= 1;}
    protected Road overTake(Road current){
        if (current.getLeft() == null) return null;
        if (current.getLeft().isAvailable(velocity)){return current.getLeft();}
        return null;
    }
    protected void accelerate(){velocity = Math.max(velocity+1, maxVelocity);}
}
