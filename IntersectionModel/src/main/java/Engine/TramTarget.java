package Engine;

import java.util.Random;

public enum TramTarget{
    LEFT,
    RIGHT,
    BOTTOM;

    public static TramTarget getRandom(){return TramTarget.values()[new Random().nextInt(TramTarget.values().length)];}
    public static Vector getLocation(TramTarget target){
        switch(target){
            case LEFT: return new Vector(0, 31);
            case RIGHT: return new Vector(67, 32);
            default: return new Vector(34, 66); //BOTTOM
        }
    }
}