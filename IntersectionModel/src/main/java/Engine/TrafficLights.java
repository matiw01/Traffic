package Engine;

public class TrafficLights{
    private final Vector location;
    public TrafficLights(int x, int y){
        this.location = new Vector(x, y);
    }
    public Vector getLocation(){return location;}
}
