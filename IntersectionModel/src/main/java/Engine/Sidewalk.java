package Engine;

import javafx.scene.paint.Color;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;

public class Sidewalk implements PedestrianPath {
    public static final ArrayList<PedestrianTarget> targets = new ArrayList<>(Arrays.asList(PedestrianTarget.BOTTOM_LEFT, PedestrianTarget.BOTTOM_CENTER_LEFT, PedestrianTarget.BOTTOM_CENTER_RIGHT, PedestrianTarget.BOTTOM_RIGHT, PedestrianTarget.TOP_LEFT, PedestrianTarget.TOP_CENTER, PedestrianTarget.TOP_RIGHT, PedestrianTarget.LEFT_DOWN, PedestrianTarget.LEFT_UP, PedestrianTarget.RIGHT_DOWN, PedestrianTarget.RIGHT_CENTER_DOWN, PedestrianTarget.RIGHT_CENTER_UP, PedestrianTarget.RIGHT_UP));
    private final ArrayList<PedestrianPath> next = new ArrayList<>();
    private final ArrayList<Integer> targetDistance = new ArrayList<>();
    private final HashMap<PedestrianTarget, PedestrianPath> forward = new HashMap<>();
    private final Vector location;
    private final Color color = Color.DARKGRAY;
    private boolean visited = false;

    public Sidewalk(int x, int y){
        this.location = new Vector(x,y);
        for(PedestrianTarget ignored : PedestrianTarget.values()){
            this.targetDistance.add(1000);
        }
    }

    @Override
    public void setNext(PedestrianPath point){this.next.add(point);}

    @Override
    public PedestrianPath getNext(Pedestrian person){return this.forward.get(person.getTarget());}

    @Override
    public Vector getLocation(){return this.location;}

    @Override
    public Color getColor(){return this.color;}

    @Override
    public boolean isDestination(){return false;}

    @Override
    public boolean isCrossing(){return false;}

    @Override
    public boolean isVisited(){return this.visited;}

    @Override
    public void setVisited(boolean val){this.visited = val;}

    @Override
    public ArrayList<Integer> getTargetDistance(){return this.targetDistance;}

    @Override
    public ArrayList<PedestrianPath> getNext(){return this.next;}

    @Override
    public void redirect(){
        for(PedestrianTarget target : PedestrianTarget.values()){
            int index = 0;
            for(int i = 0; i<targets.size(); i++){if(targets.get(i) == target){index = i; break;}}
            int min = 1000;
            PedestrianPath rem = this;
            for(PedestrianPath a : this.next){
                if(a.getTargetDistance().get(index) < min){
                    rem = a;
                    min = a.getTargetDistance().get(index);
                }
            }
            this.forward.put(target, rem);
        }
    }
}