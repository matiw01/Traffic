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
            if (current.getNext(target) == null){
                System.out.println("out of map");
                //TODO wyjebywac samochód z listy jak wyjedzie za mape (i liczyć sttystyki docelowo)
                return;
            }
            if (current.getNext(target).isAvailable(velocity))
                current.setOccupied(false);
            current = current.getNext(target);
            i++;
        }
        currentPosition = current;
        currentPosition.setOccupied(true);
        randomBreak();
    }
    public Vector getPosition(){return currentPosition.getPosition();}
    protected void randomBreak(){if (Math.random() < breakParameter) velocity -= 1;}
    protected Road overTake(Road current){
        if (current.getLeft() == null) return null;
        if (current.getLeft().isAvailable(velocity)){return current.getLeft();}
        return null;
    }
    protected void accelerate(){velocity = Math.min(velocity+1, maxVelocity);}
}