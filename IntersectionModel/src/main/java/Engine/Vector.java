package Engine;

//TODO
public class Vector {
    final int pos_x;
    final int pos_y;
    public Vector(int x, int y){
        pos_x = x;
        pos_y = y;
    }

    public int getPos_x(){return pos_x;}
    public int getPos_y(){return pos_y;}

    public String toString(){return "("+pos_x+","+pos_y+")";}

    @Override
    public boolean equals(Object object) {
        if(object == null){return false;}
        if(object.getClass() != this.getClass()){return false;}
        final Vector other = (Vector)object;
        return this.pos_x == other.pos_x && this.pos_y == other.pos_y;
    }
}
