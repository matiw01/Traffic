package gui;

import Engine.Vehicle;
import java.util.ArrayList;

public interface IEngineObserver {
    void stepMade(ArrayList<Vehicle> vehiclesArrayList);
}
