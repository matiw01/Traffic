package Engine;

import javafx.scene.paint.Color;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;

public class PedestrianPath{
    private final int type; // 0 - sidewalk, 1 - crosswalk, 2 - destination
    private final ArrayList<PedestrianPath> next = new ArrayList<>();
    public final ArrayList<Integer> targetDistance = new ArrayList<>();
    private final Vector location;
    private final Color color;
    private boolean occupied;
    public static final ArrayList<PedestrianTarget> targets = new ArrayList<>(Arrays.asList(PedestrianTarget.BOTTOM_LEFT, PedestrianTarget.BOTTOM_CENTER_LEFT, PedestrianTarget.BOTTOM_CENTER_RIGHT, PedestrianTarget.BOTTOM_RIGHT, PedestrianTarget.TOP_LEFT, PedestrianTarget.TOP_CENTER, PedestrianTarget.TOP_RIGHT, PedestrianTarget.LEFT_DOWN, PedestrianTarget.LEFT_UP, PedestrianTarget.RIGHT_DOWN, PedestrianTarget.RIGHT_CENTER_DOWN, PedestrianTarget.RIGHT_CENTER_UP, PedestrianTarget.RIGHT_UP));
    public PedestrianTarget info;
    private final HashMap<PedestrianTarget, PedestrianPath> forward = new HashMap<>();

    public PedestrianPath(int type, int x, int y, PedestrianTarget info){
        this.type = type;
        switch(type){
            case 0 -> this.color = Color.DARKGRAY;
            case 1 -> this.color = Color.WHITE;
            default -> this.color = Color.DARKCYAN;
        }
        this.location = new Vector(x,y);
        this.occupied = false;
        for(PedestrianTarget ignored : PedestrianTarget.values()){
            this.targetDistance.add(1000);
        }
        this.info = info;
    }

    public int getType(){return this.type;}
    public PedestrianPath getNext(Pedestrian person){return this.forward.get(person.getTarget());}
    public Vector getLocation(){return this.location;}
    public Color getColor(){return this.color;}
    public void setNext(PedestrianPath point){
        this.next.add(point);
    }
    public boolean getOccupied(){return this.occupied;}
    public void setOccupied(boolean state){this.occupied = state;}
    public void BFS(ArrayList<PedestrianPath> all){
        int index = 0;
        for(int i = 0; i<targets.size(); i++){if(targets.get(i) == this.info){index = i; break;}}
        ArrayDeque<PedestrianPath> queue = new ArrayDeque<>();
        queue.add(this);
        this.targetDistance.set(index, -1);
        while(!queue.isEmpty()){
            PedestrianPath el = queue.pop();
            if(!el.getOccupied()) {
                int dist = el.targetDistance.get(index);
                for(PedestrianPath a : el.next){
                    queue.add(a);
                    a.targetDistance.set(index, Math.min(dist+1, a.targetDistance.get(index)));
                }
                el.setOccupied(true);
            }
        }
        for(PedestrianPath a : all){a.setOccupied(false);}
    }
    public void redirect(){
        for(PedestrianTarget target : PedestrianTarget.values()){
            int index = 0;
            for(int i = 0; i<targets.size(); i++){if(targets.get(i) == target){index = i; break;}}
            int min = 1000;
            PedestrianPath rem = this;
            for(PedestrianPath a : this.next){
                if(a.targetDistance.get(index) < min){
                    rem = a;
                    min = a.targetDistance.get(index);
                }
            }
            this.forward.put(target, rem);
        }
    }
}
