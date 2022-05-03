package gui;

import Engine.Vehicle;

import java.util.ArrayList;

public interface IEngineObserver {
    public void stepMade(ArrayList<Vehicle> vehiclesArrayList);
}
