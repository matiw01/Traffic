package Engine;

import javafx.scene.paint.Color;

public class Environment{
    private final int type; // type: grass, etc for coloring
    private final Color color;
    private final Vector location;
    public Environment(int type, int x, int y){
        this.type = type;
        switch(type){
            case 0: this.color = Color.GREEN; break;
            case 1: this.color = Color.DARKGREEN; break;
            case 2: this.color = Color.GREY; break;
            case 3: this.color = Color.LIGHTGRAY; break;
            default: this.color = Color.WHITE; break;
        }
        this.location = new Vector(x,y);
    }

    public int getType(){return this.type;}
    public Color getColor(){return this.color;}
    public Vector getLocation(){return this.location;}
}
