import java.util.Random;

public enum PedestrianTarget{
    BOTTOM_LEFT,
    BOTTOM_RIGHT,
    TOP_LEFT,
    TOP_RIGHT,
    LEFT_DOWN,
    LEFT_UP,
    RIGHT_DOWN,
    RIGHT_UP;

    public static PedestrianTarget getRandom(){
        return PedestrianTarget.values()[new Random().nextInt(PedestrianTarget.values().length)];
    }
}
