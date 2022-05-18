package Engine;

import javafx.scene.paint.Color;

public class Environment{
    private final Color color;
    private final Vector location;

    public Environment(int type, int x, int y){
        switch(type){
            case 0: this.color = Color.GREEN; break; //grass
            case 1: this.color = Color.DARKGREEN; break; //grass
            case 2: this.color = Color.GREY; break; //building
            case 3: this.color = Color.LIGHTGRAY; break; //building
            default: this.color = Color.WHITE; break; //building
        }
        this.location = new Vector(x,y);
    }

    public Color getColor(){return this.color;}
    public Vector getLocation(){return this.location;}
}
