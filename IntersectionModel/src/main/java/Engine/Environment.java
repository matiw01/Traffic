package Engine;

public class Environment{
    private final int type; // type: grass, etc for coloring

    public Environment(int type){
        this.type = type;
    }

    public int getType(){return this.type;}
}
