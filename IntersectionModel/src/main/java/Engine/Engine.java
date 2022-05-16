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
    ArrayList<Pedestrian> pedestrians = new ArrayList<>();
    ArrayList<PedestrianPath> pedestrianTargets = new ArrayList<>();
    Intersection intersection;
    Road[][] roadsMap;
    ArrayList<Vehicle> vehiclesArrayList = new ArrayList<>();
    ArrayList<Tram> tramsArrayList = new ArrayList<>();
    final ArrayList<LightsGroup> lightsGroupArrayList;
    final HashMap<Vector, LightsGroup> lightsGroupHashMap;
    final LinkedList<CarGenerator> carGenerators;

    public Engine(IEngineObserver gridCreator , HashMap<VehicleTarget, Pair<Double, Road>> probVehDir, Road[][] roadsMap,
                  ArrayList<PedestrianPath> pedestrianPaths, Intersection intersection, LinkedList<CarGenerator> carGenerators){
        engineObserver = gridCreator;
        this.probVehDir = probVehDir;
        this.roadsMap = roadsMap;
        for(PedestrianPath a : pedestrianPaths){
            if(a.getType() == 2){
                this.pedestrianTargets.add(a);
            }
        }
        this.intersection = intersection;
        this.lightsGroupArrayList = this.intersection.getLightsGroupsArrayList();
        this.lightsGroupHashMap = this.intersection.getLightsHashMap();
        this.carGenerators = carGenerators;


//        Vehicle car1 = new Vehicle(2, 3, null, roadsMap[0][45], 2);
//        Vehicle car2 = new Vehicle(2, 3, null, roadsMap[0][46], 2);
//        Vehicle car3 = new Vehicle(2, 3, null, roadsMap[0][47], 2);
//        Vehicle car4 = new Vehicle(2, 3, null, roadsMap[46][66], 2);
//        Vehicle car5 = new Vehicle(2, 3, null, roadsMap[45][66], 2);
//        Vehicle car10 = new Vehicle(2, 5, VehicleTarget.McDonalds, roadsMap[44][66], 2);
//        Vehicle car6 = new Vehicle(2, 3, null, roadsMap[67][19], 2);
//        Vehicle car7 = new Vehicle(2, 3, null, roadsMap[67][20], 2);
//        Vehicle car8 = new Vehicle(2, 3, null, roadsMap[67][21], 2);
//        Vehicle car15 = new Vehicle(2, 3, VehicleTarget.Prawo, roadsMap[67][22], 2);
//        Vehicle car9 = new Vehicle(2, 5, VehicleTarget.McDonalds, roadsMap[0][44], 2);
//        Vehicle car11 = new Vehicle(2, 5, VehicleTarget.Rokicinska, roadsMap[20][0], 2);
//        Vehicle car12 = new Vehicle(2, 5, VehicleTarget.McDonalds, roadsMap[21][0], 2);
//        Vehicle car13 = new Vehicle(2, 5, VehicleTarget.Prawo, roadsMap[21][5], 2);
//        Vehicle car14 = new Vehicle(2, 5, VehicleTarget.PuszkinaOut, roadsMap[21][10], 2);
//        Vehicle car16 = new Vehicle(2, 5, VehicleTarget.McDonalds, roadsMap[43][66], 2);

//        vehiclesArrayList.add(car1);
//        vehiclesArrayList.add(car2);
//        vehiclesArrayList.add(car3);
//        vehiclesArrayList.add(car4);
//        vehiclesArrayList.add(car5);
//        vehiclesArrayList.add(car10);
//        vehiclesArrayList.add(car6);
//        vehiclesArrayList.add(car7);
//        vehiclesArrayList.add(car8);
//        vehiclesArrayList.add(car15);
//        vehiclesArrayList.add(car9);
//        vehiclesArrayList.add(car11);
//        vehiclesArrayList.add(car12);
//        vehiclesArrayList.add(car13);
//        vehiclesArrayList.add(car14);
//        vehiclesArrayList.add(car16);

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
        vehiclesArrayList.removeIf(vehicle -> !vehicle.move());
    }

    private void generatePedestrians(){
        if(Math.random() < 0.5){
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
            pedestrian.move(this.lightsGroupHashMap);
            if(pedestrian.getLocation().info == pedestrian.getTarget()){
                toRemove.add(pedestrian);
            }
        }
        for(Pedestrian pedestrian : toRemove){
            pedestrians.remove(pedestrian);
        }
    }

    public void generateTrams(){
        if(Math.random() < 0.1){
            TramTarget start = TramTarget.getRandom();
            TramTarget end = TramTarget.getRandom();
            while(start == end){end = TramTarget.getRandom();}
            switch(start){
                case LEFT: tramsArrayList.add(new Tram(intersection.getAtLocation(0,32), end)); break;
                case RIGHT: tramsArrayList.add(new Tram(intersection.getAtLocation(67,31), end)); break;
                default: tramsArrayList.add(new Tram(intersection.getAtLocation(34,66), end)); break;
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
        //TODO
    }

    public void setShouldRun(boolean shouldRun){
        this.shouldRun = shouldRun;
    }

    private void notifyObserver(){
        engineObserver.stepMade(vehiclesArrayList);
    }
}
