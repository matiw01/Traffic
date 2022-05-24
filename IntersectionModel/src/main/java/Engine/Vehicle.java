package Engine;

import java.util.HashMap;

public class Vehicle {
    protected int numberOfPeople;
    protected float breakParameter = 0.1f;
    protected int maxVelocity;
    protected int velocity;
    protected VehicleTarget target;
    protected int travelTime = 0;
    protected int waitingTime = 0;
    protected Road currentPosition;
    protected Road currentTail;
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

    public boolean move(HashMap<Vector, LightsGroup> lightsGroupHashMap){
        accelerate();
        Road current = currentPosition;
        Road tail = currentTail;
        int i = 0;
        this.travelTime++;
        while (i < velocity && current != null) {
            if (current.getNext(target) == null){
                if (tail != null) tail.setOccupied(false);
                current.setOccupied(false);
                return false;
            }
            if (lightsGroupHashMap.get(current.getNext(target).getPosition()) != null){
                if (lightsGroupHashMap.get(current.getNext(target).getPosition()).getState() == 0) {
                    currentTail = tail;
                    currentPosition = current;
                    waitingTime ++;
                    return true;
                }}
//            if (lightsGroupHashMap.get(current.getNext(target).getPosition()) != null){
//                if (lightsGroupHashMap.get(current.getNext(target).getPosition()).getState() == 0){
//                    currentTail = tail;
//                    currentPosition = current;
//                    return true;
//                }
//            }
            if (!current.getNext(target).isOccupied()){
                if (currentTail != null) tail.setOccupied(false);
                current.setOccupied(false);
                tail = current;
                current = current.getNext(target);
                current.setOccupied(true);
                waitingTime = 0;
                if (currentTail != null) tail.setOccupied(true);}
            else {
                currentTail = tail;
                currentPosition = current;
                currentPosition.setOccupied(true);
                waitingTime++;
                if (currentTail != null) currentTail.setOccupied(true);
                return true;
            }
            i++;
        }
        currentTail = tail;
        currentPosition = current;
        currentPosition.setOccupied(true);
        currentTail.setOccupied(true);
        randomBreak();
        return true;
    }

    public Vector getPosition(){return currentPosition.getPosition();}
    public Vector getTailPosition(){
        if (currentTail != null) return currentTail.getPosition();
        else return null;}
    protected void randomBreak(){if (Math.random() < breakParameter) velocity -= 1;}
    protected Road overTake(Road current){
        if (current.getLeft() == null) return null;
        if (current.getLeft().isAvailable(velocity)){return current.getLeft();}
        return null;
    }
    protected void accelerate(){velocity = Math.min(velocity+1, maxVelocity);}
}