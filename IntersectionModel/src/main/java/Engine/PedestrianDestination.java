package Engine;

import javafx.scene.paint.Color;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;

public class PedestrianDestination implements PedestrianPathT{
    public static final ArrayList<PedestrianTarget> targets = new ArrayList<>(Arrays.asList(PedestrianTarget.BOTTOM_LEFT, PedestrianTarget.BOTTOM_CENTER_LEFT, PedestrianTarget.BOTTOM_CENTER_RIGHT, PedestrianTarget.BOTTOM_RIGHT, PedestrianTarget.TOP_LEFT, PedestrianTarget.TOP_CENTER, PedestrianTarget.TOP_RIGHT, PedestrianTarget.LEFT_DOWN, PedestrianTarget.LEFT_UP, PedestrianTarget.RIGHT_DOWN, PedestrianTarget.RIGHT_CENTER_DOWN, PedestrianTarget.RIGHT_CENTER_UP, PedestrianTarget.RIGHT_UP));
    private final ArrayList<PedestrianPathT> next = new ArrayList<>();
    private final ArrayList<Integer> targetDistance = new ArrayList<>();
    private final HashMap<PedestrianTarget, PedestrianPathT> forward = new HashMap<>();
    private final Vector location;
    private final Color color = Color.DARKCYAN;
    private final PedestrianTarget where;
    private boolean visited = false;

    public PedestrianDestination(int x, int y, PedestrianTarget where){
        this.location = new Vector(x,y);
        for(PedestrianTarget ignored : PedestrianTarget.values()){
            this.targetDistance.add(1000);
        }
        this.where = where;
    }

    @Override
    public void setNext(PedestrianPathT point){this.next.add(point);}

    @Override
    public PedestrianPathT getNext(Pedestrian person){return null;}

    @Override
    public Vector getLocation(){return this.location;}

    @Override
    public Color getColor(){return this.color;}

    @Override
    public boolean isDestination(){return true;}

    @Override
    public boolean isCrossing(){return false;}

    @Override
    public boolean isVisited(){return this.visited;}

    @Override
    public void setVisited(boolean val){this.visited = val;}

    @Override
    public ArrayList<Integer> getTargetDistance(){return this.targetDistance;}

    @Override
    public ArrayList<PedestrianPathT> getNext(){return this.next;}

    public void BFS(ArrayList<PedestrianPath> all){
        int index = 0;
        for(int i = 0; i<targets.size(); i++){if(targets.get(i) == this.where){index = i; break;}}
        ArrayDeque<PedestrianPathT> queue = new ArrayDeque<>();
        queue.add(this);
        this.targetDistance.set(index, -1);
        while(!queue.isEmpty()){
            PedestrianPathT el = queue.pop();
            if(!el.isVisited()){
                int dist = el.getTargetDistance().get(index);
                for(PedestrianPathT a : el.getNext()){
                    queue.add(a);
                    a.getTargetDistance().set(index, Math.min(dist+1, a.getTargetDistance().get(index)));
                }
                el.setVisited(true);
            }
        }
        for(PedestrianPath a : all){a.setOccupied(false);}
    }

    @Override
    public void redirect(){
        for(PedestrianTarget target : PedestrianTarget.values()){
            int index = 0;
            for(int i = 0; i<targets.size(); i++){if(targets.get(i) == target){index = i; break;}}
            int min = 1000;
            PedestrianPathT rem = this;
            for(PedestrianPathT a : this.next){
                if(a.getTargetDistance().get(index) < min){
                    rem = a;
                    min = a.getTargetDistance().get(index);
                }
            }
            this.forward.put(target, rem);
        }
    }
}