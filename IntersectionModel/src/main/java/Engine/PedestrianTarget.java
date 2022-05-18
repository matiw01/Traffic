package Engine;

import java.util.Random;

public enum PedestrianTarget{
    BOTTOM_LEFT,
    BOTTOM_CENTER_LEFT,
    BOTTOM_CENTER_RIGHT,
    BOTTOM_RIGHT,
    TOP_LEFT,
    TOP_CENTER,
    TOP_RIGHT,
    LEFT_DOWN,
    LEFT_UP,
    RIGHT_DOWN,
    RIGHT_CENTER_DOWN,
    RIGHT_CENTER_UP,
    RIGHT_UP;

    public static PedestrianTarget getRandom(){return PedestrianTarget.values()[new Random().nextInt(PedestrianTarget.values().length)];}
    public static Vector getLocation(PedestrianTarget target){
        switch(target){
            case TOP_LEFT: return new Vector(19, 0);
            case TOP_CENTER: return new Vector(34, 0);
            case TOP_RIGHT: return new Vector(48, 0);
            case BOTTOM_LEFT: return new Vector(20, 66);
            case BOTTOM_CENTER_LEFT: return new Vector(32, 66);
            case BOTTOM_CENTER_RIGHT: return new Vector(35, 66);
            case BOTTOM_RIGHT: return new Vector(47, 66);
            case LEFT_UP: return new Vector(0, 18);
            case RIGHT_UP: return new Vector(67, 18);
            case RIGHT_CENTER_DOWN: return new Vector(67, 30);
            case RIGHT_CENTER_UP: return new Vector(67, 33);
            case RIGHT_DOWN: return new Vector(67, 48);
            default: return new Vector(0, 48); //PedestrianTarget.LEFT_DOWN
        }
    }
}