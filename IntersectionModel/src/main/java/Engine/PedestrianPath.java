package Engine;

import javafx.scene.paint.Color;

import java.util.ArrayList;

public class PedestrianPath{
    private final int type; // 0 - sidewalk, 1 - crosswalk, 2 - destination
    private final ArrayList<PedestrianPath> next = new ArrayList<>();
    private final Vector location;
    private final Color color;

    public PedestrianPath(int type, int x, int y){
        this.type = type;
        switch(type){
            case 0: this.color = Color.DARKGRAY; break;
            case 1: this.color = Color.WHITE; break;
            default: this.color = Color.DARKCYAN; break;
        }
        this.location = new Vector(x,y);
    }

    public int getType(){return this.type;}
    public PedestrianPath getNext(Pedestrian person){

        return this; //placeholder
    }
    public Vector getLocation(){return this.location;}
    public Color getColor(){return this.color;}
}
