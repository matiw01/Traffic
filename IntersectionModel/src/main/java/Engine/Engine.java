package Engine;

import gui.IEngineObserver;
import javafx.application.Platform;
import javafx.util.Pair;

import java.util.*;

import static Engine.PedestrianTarget.getLocation;

public class Engine implements Runnable{
    boolean shouldRun = false;
    Map<VehicleTarget, Pair<Double, Road>> probVehDir;
    IEngineObserver engineObserver;
    ArrayList<Road> roadArrayList;
    ArrayList<Vehicle> vehiclesArrayList = new ArrayList<>();
    ArrayList<Pedestrian> pedestrians = new ArrayList<>();
    ArrayList<PedestrianPath> pedestrianTargets = new ArrayList<>();
    Intersection intersection;

    public Engine(IEngineObserver gridCreator , HashMap<VehicleTarget, Pair<Double, Road>> probVehDir, ArrayList<Road> roadArrayList, ArrayList<PedestrianPath> pedestrianPaths, Intersection intersection){
        engineObserver = gridCreator;
        this.probVehDir = probVehDir;
        System.out.println(probVehDir);
        this.roadArrayList = roadArrayList;
        for(PedestrianPath a : pedestrianPaths){
            if(a.getType() == 2){
                this.pedestrianTargets.add(a);
            }
        }
        this.intersection = intersection;
    }

    public void run(){
        while (true) {
            if (shouldRun){
                generateNewVehicles();
                moveCars();
                generatePedestrians();
                movePedestrians();
                Platform.runLater(()->notifyObserver());
            }
            try {
                Thread.sleep(100);
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
        if(Math.random() < 0.05){
            PedestrianTarget start = PedestrianTarget.getRandom();
            PedestrianTarget end = PedestrianTarget.getRandom();
            while(start == end){end = PedestrianTarget.getRandom();}
            for(PedestrianPath a : this.pedestrianTargets){
                if(a.getLocation().getPos_x() == getLocation(start).getPos_x() && a.getLocation().getPos_y() == getLocation(start).getPos_y()){
                    pedestrians.add(new Pedestrian(a, end));
                    break;
                }
            }
        this.intersection.setPedestrianArrayList(pedestrians);
        }
    }

    private void movePedestrians(){
        LinkedList<Pedestrian> toRemove = new LinkedList<>();
        for(Pedestrian pedestrian : pedestrians){
            pedestrian.move();
            if(pedestrian.getLocation().info == pedestrian.getTarget()){
                toRemove.add(pedestrian);
            }
        }
        for(Pedestrian pedestrian : toRemove){
            pedestrians.remove(pedestrian);
        }
    }

    public void setShouldRun(boolean shouldRun){
        this.shouldRun = shouldRun;
    }

    private void notifyObserver(){
        engineObserver.stepMade(vehiclesArrayList);
    }
}
