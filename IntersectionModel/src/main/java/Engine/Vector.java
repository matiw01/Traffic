package Engine;

import java.util.Objects;

public class Vector {
    final int pos_x;
    final int pos_y;
    private final int hashCode;

    public Vector(int x, int y){
        this.pos_x = x;
        this.pos_y = y;
        this.hashCode = Objects.hash(x, y);
    }

    public int getPos_x(){return pos_x;}
    public int getPos_y(){return pos_y;}

    public String toString(){return "("+pos_x+","+pos_y+")";}

    @Override
    public boolean equals(Object object){
        if(object == null){return false;}
        if(object.getClass() != this.getClass()){return false;}
        final Vector other = (Vector)object;
        return this.pos_x == other.pos_x && this.pos_y == other.pos_y;
    }

    @Override
    public int hashCode(){return this.hashCode;}
}
