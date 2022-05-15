package Engine;

import java.util.LinkedList;

public class LightsGroup{
    public int state = 0; // 0,1,2 - r y g
    public int lastChange = 0; //s od ost zm
    private final LinkedList<TrafficLights> lights;
    public LightsGroup(LinkedList<TrafficLights> lights){
        this.lights = lights;
    }

    public int getState(){return this.state;}
    public int getLastChange(){return this.lastChange;}
    public LinkedList<TrafficLights> getLights(){return this.lights;}

    public void setState(int state){
        this.state = state;
        this.lastChange = 0;
    }
    public void incrementLastChange(){this.lastChange++;}
}
