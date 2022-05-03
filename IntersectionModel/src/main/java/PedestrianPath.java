import java.util.ArrayList;

public class PedestrianPath{
    private final int type; // 0 - sidewalk, 1 - crosswalk, 2 - destination
    private final ArrayList<PedestrianPath> next = new ArrayList<>();

    public PedestrianPath(int type){
        this.type = type;
    }

    public int getType(){return this.type;}
    public PedestrianPath getNext(Pedestrian person){

        return this; //placeholder
    }
}
