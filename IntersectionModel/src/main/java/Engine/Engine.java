package Engine;

import gui.IEngineObserver;
import javafx.application.Platform;
import javafx.util.Pair;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

public class Engine implements Runnable{
    boolean shouldRun = false;
    Map<VehicleTarget, Pair<Double, Road>> probVehDir;
    IEngineObserver engineObserver;
    Road[][] roadsMap;
    ArrayList<Vehicle> vehiclesArrayList = new ArrayList<Vehicle>();

    public Engine(IEngineObserver gridCreator , HashMap<VehicleTarget, Pair<Double, Road>> probVehDir, Road[][] roadsMap){
        engineObserver = gridCreator;
        this.probVehDir = probVehDir;
        System.out.println(probVehDir);
        this.roadsMap = roadsMap;
        Vehicle car1 = new Vehicle(2, 3, null, roadsMap[0][45], 2);
        Vehicle car2 = new Vehicle(2, 3, null, roadsMap[0][46], 2);
        vehiclesArrayList.add(car1);
        vehiclesArrayList.add(car2);
    }

    public void run(){
        while (true) {
            if (shouldRun){
                System.out.println("engine running");
//                generateNewVehicles();
                moveCars();
                Platform.runLater(()->notifyObserver());
            }
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    private void generateNewVehicles(){
        Arrays.asList(VehicleTarget.values())
                .forEach(target -> {
                    if (probVehDir.get(target) != null && Math.random() < probVehDir.get(target).getKey()){
                        System.out.println(probVehDir.get(target).getValue().getNext());
                        vehiclesArrayList.add(new Vehicle(2, 3, target, probVehDir.get(target).getValue(), 1));
                    }
                });
    }

    private void moveCars(){
        for (Vehicle vehicle : vehiclesArrayList){
            vehicle.move();
        }
    }

    private void generatePedestrians(){
        //TODO
    }

    public void setShouldRun(boolean shouldRun){
        this.shouldRun = shouldRun;
    }

    private void notifyObserver(){
        engineObserver.stepMade(vehiclesArrayList);
    }
}
