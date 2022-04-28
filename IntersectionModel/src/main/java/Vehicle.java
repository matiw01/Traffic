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
        this.velocity = (int)(Math.random()*maxVelocity + 1);
        this.target = target;
        this.currentPosition = currentPosition;
        this.length = length;
    }

    public void move(){
        accelerate();
        Road current = currentPosition;
        int i = 0;
        while (i < velocity){
            //TODO helper functions to take care of this mess
            if (current.getNext() == null && current.getNext(target) == null) {
                currentPosition = null;
                return;
            }
            if (!current.isChangingPoint()){
                if (!current.getNext().isOccupied()) {
                    current = current.getNext();
                }else {
                    if (!current.getLeft().isChangingPoint() && overTake(current) != null){
                        current = overTake(current);
                    }
                }
            }else if(current.getNext(target).isAvailable(velocity)){
                current = current.getNext(target);
            }else if(!current.getNext(target).isAvailable(velocity) && current.getNext().getNext(target) == current.getNext(target).getNext()){//this condition is fucked
                current = current.getNext();
            }else {
                velocity = 0;
                break;
            }
            i++;
        }
        randomBreak();
    }
    protected Road getPosition(){return currentPosition;}
    protected void randomBreak(){if (Math.random() < breakParameter) velocity -= 1;}
    protected Road overTake(Road current){
        if (current.getLeft() == null) return null;
        if (current.getLeft().isAvailable(velocity)){return current.getLeft();}
        return null;
    }
    protected void accelerate(){velocity = Math.max(velocity+1, maxVelocity);}
}
