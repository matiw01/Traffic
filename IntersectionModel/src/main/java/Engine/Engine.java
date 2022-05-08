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
        this.roadsMap = roadsMap;
//        Vehicle car1 = new Vehicle(2, 3, null, roadsMap[0][45], 2);
//        Vehicle car2 = new Vehicle(2, 3, null, roadsMap[0][46], 2);
//        Vehicle car3 = new Vehicle(2, 3, null, roadsMap[0][47], 2);
//        Vehicle car4 = new Vehicle(2, 3, null, roadsMap[46][66], 2);
//        Vehicle car5 = new Vehicle(2, 3, null, roadsMap[45][66], 2);
//        Vehicle car6 = new Vehicle(2, 3, null, roadsMap[67][19], 2);
//        Vehicle car7 = new Vehicle(2, 3, null, roadsMap[67][20], 2);
//        Vehicle car8 = new Vehicle(2, 3, null, roadsMap[67][21], 2);
//        Vehicle car9 = new Vehicle(2, 5, VehicleTarget.PuszkinaOut, roadsMap[0][44], 2);
        Vehicle car10 = new Vehicle(2, 5, VehicleTarget.PuszkinaOut, roadsMap[44][66], 2);

//        vehiclesArrayList.add(car1);
//        vehiclesArrayList.add(car2);
//        vehiclesArrayList.add(car3);
//        vehiclesArrayList.add(car4);
//        vehiclesArrayList.add(car5);
//        vehiclesArrayList.add(car6);
//        vehiclesArrayList.add(car7);
//        vehiclesArrayList.add(car8);
//        vehiclesArrayList.add(car9);
        vehiclesArrayList.add(car10);

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
                Thread.sleep(500);
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
