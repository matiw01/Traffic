package Engine;

import javafx.util.Pair;
import java.util.LinkedList;

public class CarGenerator {
    LinkedList<Pair<Double, VehicleTarget>> probabilities;
    Vector position;

    public CarGenerator(LinkedList<Pair<Double, VehicleTarget>> probabilities, Vector position){
        this.probabilities = probabilities;
        this.position = position;
    }
    public Pair<Vector, VehicleTarget> generateCar(){
        double prob = Math.random();
        for (Pair<Double, VehicleTarget> pair : probabilities){
            if ( prob < pair.getKey() ){
                return new Pair<>(position, pair.getValue());
            }
        }
        return null;
    }
}