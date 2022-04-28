public class AbstractRoad {
    protected Road straight;
    protected Road previous;


    public Road getNext() {return straight;}
    public boolean isAvailable(int velocity) {
        return false;
    }
}
