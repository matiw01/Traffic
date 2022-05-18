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
    ArrayList<Pedestrian> pedestrians = new ArrayList<>();
    ArrayList<PedestrianPath> pedestrianTargets = new ArrayList<>();
    Intersection intersection;
    Road[][] roadsMap;
    ArrayList<Vehicle> vehiclesArrayList = new ArrayList<>();
    ArrayList<Tram> tramsArrayList = new ArrayList<>();
    final ArrayList<LightsGroup> pedestrianLightsGroupArrayList;
    final HashMap<Vector, LightsGroup> pedestrianLightsGroupHashMap;
    final HashMap<Vector, LightsGroup> vehicleLightsGroupHashMap;
    final LinkedList<CarGenerator> carGenerators;

    public Engine(IEngineObserver gridCreator , HashMap<VehicleTarget, Pair<Double, Road>> probVehDir, Road[][] roadsMap,
                  ArrayList<PedestrianPath> pedestrianPaths, Intersection intersection, LinkedList<CarGenerator> carGenerators){
        engineObserver = gridCreator;
        this.probVehDir = probVehDir;
        this.roadsMap = roadsMap;
        for(PedestrianPath a : pedestrianPaths){
            if(a.isDestination()){
                this.pedestrianTargets.add(a);
            }
        }
        this.intersection = intersection;
        this.pedestrianLightsGroupArrayList = this.intersection.getPedestrianLightsGroupsArrayList();
        this.pedestrianLightsGroupHashMap = this.intersection.getPedestrianLightsHashMap();
        this.vehicleLightsGroupHashMap = this.intersection.getVehicleLightsHashMap();
        this.carGenerators = carGenerators;
    }

    public void run(){
        while(true){
            if(shouldRun){
                handleLights();
                generateNewVehicles();
                moveCars();
                generatePedestrians();
                movePedestrians();
                generateTrams();
                moveTrams();
                Platform.runLater(this::notifyObserver);
            }
            try{
                Thread.sleep(500);
            }
            catch(InterruptedException e){
                e.printStackTrace();
            }
        }
    }

    private void generateNewVehicles(){
        for (CarGenerator carGenerator : carGenerators) {
            Pair<Vector, VehicleTarget> posTar = carGenerator.generateCar();
            if (posTar != null) vehiclesArrayList.add(new Vehicle(2, 3, posTar.getValue(), roadsMap[posTar.getKey().pos_x][posTar.getKey().pos_y], 2));
        }
    }

    private void moveCars(){
        vehiclesArrayList.removeIf(vehicle -> !vehicle.move(this.vehicleLightsGroupHashMap));
    }

    private void generatePedestrians(){
        if(Math.random() < 0.2){
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
            pedestrian.move(this.pedestrianLightsGroupHashMap);
            if(pedestrian.getLocation().isDestination() && ((PedestrianDestination)pedestrian.getLocation()).getWhere() == pedestrian.getTarget()){
                toRemove.add(pedestrian);
            }
        }
        for(Pedestrian pedestrian : toRemove){
            pedestrians.remove(pedestrian);
        }
    }

    public void generateTrams(){
        if(Math.random() < 0.08){
            TramTarget start = TramTarget.getRandom();
            TramTarget end = TramTarget.getRandom();
            while(start == end){end = TramTarget.getRandom();}
            switch(start){
                case LEFT -> tramsArrayList.add(new Tram(intersection.getAtLocation(0, 32), end));
                case RIGHT -> tramsArrayList.add(new Tram(intersection.getAtLocation(67, 31), end));
                default -> tramsArrayList.add(new Tram(intersection.getAtLocation(34, 66), end));
            }
            this.intersection.setTramsArrayList(tramsArrayList);
        }
    }

    public void moveTrams(){
        LinkedList<Tram> toRemove = new LinkedList<>();
        for(Tram tram : tramsArrayList){
            tram.move();
            if(tram.getLocation() == null){
                toRemove.add(tram);
            }
        }
        for(Tram tram : toRemove){
            tramsArrayList.remove(tram);
        }
        intersection.setTramsArrayList(tramsArrayList);
    }

    public void handleLights(){
        for (LightsGroup lightsGroup : intersection.getVehicleLightsGroupsArrayList()){
            lightsGroup.incrementLastChange();
            if (lightsGroup.getLastChange() >= 5){
                lightsGroup.changeState();
            }
        }

        for (LightsGroup lightsGroup : intersection.getPedestrianLightsGroupsArrayList()){
            lightsGroup.incrementLastChange();
            if (lightsGroup.getLastChange() >= 5){
                lightsGroup.changeState();
            }
        }
    }

    public void setShouldRun(boolean shouldRun){
        this.shouldRun = shouldRun;
    }
    private void notifyObserver(){
        engineObserver.stepMade(vehiclesArrayList);
    }
}
