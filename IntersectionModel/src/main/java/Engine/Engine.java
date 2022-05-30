package Engine;

import gui.ChartMaintainer;
import gui.IEngineObserver;
import javafx.application.Platform;
import javafx.util.Pair;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;

import static Engine.PedestrianTarget.getLocation;

public class Engine implements Runnable{
    int flow = 0;
    int disappointment = 0;
    int minLightsLength = 10;
    boolean nextChange = true;
    boolean allRed = false;
    int lastChange = 0;
    int time = 0;
    int horizontalGreen = 40;
    int verticalGreen = 20;
    int redGap = 4;
    int lightsCycle = horizontalGreen + verticalGreen + 2*redGap;
    boolean shouldRun = false;
    Map<VehicleTarget, Pair<Double, Road>> probVehDir;
    IEngineObserver engineObserver;
    ChartMaintainer flowChart;
    ChartMaintainer disappointmentChart;
    ArrayList<Pedestrian> pedestrians = new ArrayList<>();
    ArrayList<PedestrianPath> pedestrianTargets = new ArrayList<>();
    Intersection intersection;
    Road[][] roadsMap;
    ArrayList<Vehicle> vehiclesArrayList = new ArrayList<>();
    ArrayList<Tram> tramsArrayList = new ArrayList<>();
    final ArrayList<LightsGroup> pedestrianLightsGroupArrayList;
    final HashMap<Vector, LightsGroup> pedestrianLightsGroupHashMap;
    final HashMap<Vector, LightsGroup> vehicleLightsGroupHashMap;
    final HashMap<Vector, LightsGroup> tramLightsGroupHashMap;
    final LinkedList<CarGenerator> carGenerators;
    final LinkedList<Zone> zoneLinkedList;
    final LinkedList<TramZone> tramZoneLinkedList;
    private final LinkedList<LightsGroup> vertical;
    private final LinkedList<LightsGroup> horizontal;

    public Engine(IEngineObserver gridCreator , HashMap<VehicleTarget, Pair<Double, Road>> probVehDir, Road[][] roadsMap, ChartMaintainer flowChart,
                  ChartMaintainer disappointmentChart ,ArrayList<PedestrianPath> pedestrianPaths, Intersection intersection, LinkedList<CarGenerator> carGenerators){
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
        this.tramLightsGroupHashMap = this.intersection.getTramLightsHashMap();
        this.carGenerators = carGenerators;
        this.zoneLinkedList = intersection.getZoneLinkedList();
        this.tramZoneLinkedList = intersection.getTramZoneLinkedList();
        this.vertical = intersection.getVerticalLights();
        this.horizontal = intersection.getHorizontalLights();
        for (LightsGroup lightsGroup : horizontal){lightsGroup.changeState();}
        this.flowChart = flowChart;
        this.disappointmentChart = disappointmentChart;

    }

    public void run(){
        int i = 0;
        while(true){
            if(shouldRun){
                i++;
                time++;
                generateNewVehicles();
                moveCars();
                generatePedestrians();
                movePedestrians(i);
                generateTrams();
                moveTrams();
//                handleLights changing lights on fixed times
//                handleLights();
//                handleLightsOptimally changing lights based on number of cars waiting
                if (calculateDisappointment() || allRed) handleLightsOptimally();
                lastChange ++;
                Platform.runLater(this::notifyObserver);
                for(Zone zone : zoneLinkedList){zone.reset();}
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
        vehiclesArrayList.removeIf(vehicle -> {
                if (!vehicle.move(this.vehicleLightsGroupHashMap)) flow += vehicle.numberOfPeople;
                return !vehicle.move(this.vehicleLightsGroupHashMap);});
    }

    private void generatePedestrians(){
        if(Math.random() < 0.08){
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

    private void movePedestrians(int i){
        LinkedList<Pedestrian> toRemove = new LinkedList<>();
        for(Pedestrian pedestrian : pedestrians){
            if(i%2 == 0 || this.pedestrianLightsGroupHashMap.get(pedestrian.getLocation().getLocation()) != null){
                pedestrian.move(this.pedestrianLightsGroupHashMap);
                if(pedestrian.getLocation().isDestination() && ((PedestrianDestination)pedestrian.getLocation()).getWhere() == pedestrian.getTarget()){
                    toRemove.add(pedestrian);
                }
            }
        }
        for(Pedestrian pedestrian : toRemove){
            pedestrians.remove(pedestrian);
        }
    }

    public void generateTrams(){
        if(Math.random() < 0.03 && this.tramsArrayList.size() < 3){
            TramTarget start = TramTarget.getRandom();
            TramTarget end = TramTarget.getRandom();
            while(start == end){end = TramTarget.getRandom();}
            if(start == TramTarget.LEFT && intersection.getAtLocation(0, 32).isOccupied()){return;}
            else if(start == TramTarget.RIGHT && intersection.getAtLocation(67, 31).isOccupied()){return;}
            else if(start == TramTarget.BOTTOM && intersection.getAtLocation(34, 66).isOccupied()){return;}

            switch(start){
                case LEFT -> tramsArrayList.add(new Tram(intersection.getAtLocation(0, 32), end));
                case RIGHT -> tramsArrayList.add(new Tram(intersection.getAtLocation(67, 31), end));
                default -> tramsArrayList.add(new Tram(intersection.getAtLocation(34, 66), end));
            }
            this.intersection.setTramsArrayList(tramsArrayList);
        }
    }

    public void moveTrams(){
        for(Tram tram : tramsArrayList){
            tram.move(tramLightsGroupHashMap);
        }
        int flag;
        ArrayList<Tram> toRemove = new ArrayList<>();
        ArrayList<Tram> tramsArrayList = intersection.getTramsArrayList();
        for(Tram tram : tramsArrayList){
            flag = 0;
            for(TramPath tp : tram.getTramParts()){
                if(tp.getLocation().getPos_x()>=0){
                    flag = 1;
                }
            }
            if(flag == 0){
                toRemove.add(tram);
            }
        }

        for(Tram tram : toRemove){
            flow += tram.getNumberOfPeople();
            tramsArrayList.remove(tram);
        }
        intersection.setTramsArrayList(tramsArrayList);
    }

    public void handleLights() {
        calculateDisappointment();
        int tmp = time % lightsCycle;
        if (tmp < horizontalGreen) {
            for (LightsGroup lightsGroup : horizontal) {
                lightsGroup.setState(1);
            }
            for (LightsGroup lightsGroup : vertical) {
                lightsGroup.setState(0);
            }
        } else if (tmp < horizontalGreen + redGap) {
            for (LightsGroup lightsGroup : horizontal) {
                lightsGroup.setState(0);
            }
            for (LightsGroup lightsGroup : vertical) {
                lightsGroup.setState(0);
            }
        } else if (tmp < horizontalGreen + redGap + verticalGreen) {
            for (LightsGroup lightsGroup : horizontal) {
                lightsGroup.setState(0);
            }
            for (LightsGroup lightsGroup : vertical) {
                lightsGroup.setState(1);
            }
        } else if (tmp < horizontalGreen + redGap + verticalGreen + redGap) {
            for (LightsGroup lightsGroup : horizontal) {
                lightsGroup.setState(0);
            }
            for (LightsGroup lightsGroup : vertical) {
                lightsGroup.setState(0);
            }
        }
    }

    private void handleLightsOptimally(){
        if (lastChange > minLightsLength || allRed){
            if (allRed && lastChange >= redGap){
                allRed = false;
                lastChange = 0;
                if (nextChange){
                    for( LightsGroup lightsGroup : vertical){lightsGroup.setState(1);}
                    for( LightsGroup lightsGroup : horizontal){lightsGroup.setState(0);}
                } else{
                    for( LightsGroup lightsGroup : vertical){lightsGroup.setState(0);}
                    for( LightsGroup lightsGroup : horizontal){lightsGroup.setState(1);}
                }
            nextChange = !nextChange;
                return;
            }
            if (lastChange > minLightsLength) {
                lastChange = 0;
                allRed = true;
                for (LightsGroup lightsGroup : vertical) {
                    lightsGroup.setState(0);
                }
                for (LightsGroup lightsGroup : horizontal) {
                    lightsGroup.setState(0);
                }
            }
        }
        }

    public boolean calculateDisappointment(){
        int verticalDisappointment = 0;
        int horizontalDisappointment = 0;

        for (Zone zone : zoneLinkedList){
            for (Vehicle vehicle : vehiclesArrayList){zone.isInZone(vehicle);}
        }
        for (TramZone zone : tramZoneLinkedList){
            zone.calculateDisappointment(tramsArrayList);
        }

        for (Zone zone : zoneLinkedList){
            if (zone.vertical) verticalDisappointment += zone.waitingDisappointment;
            else horizontalDisappointment += zone.waitingDisappointment;}

        for ( TramZone zone : tramZoneLinkedList){
            if (zone.isVertical()) verticalDisappointment += zone.getWaitingDisappointment();
            else horizontalDisappointment += zone.getWaitingDisappointment();
        }
        this.disappointment = verticalDisappointment + horizontalDisappointment;
//        System.out.println("Dissappointment");
//        System.out.println(disappointment);
//        System.out.println("Diff");
//        System.out.println(Math.abs(verticalDisappointment - horizontalDisappointment));
        return Math.abs(verticalDisappointment - horizontalDisappointment) > 2000;
    }

    public void setShouldRun(boolean shouldRun){
        this.shouldRun = shouldRun;
    }
    private void notifyObserver(){
        engineObserver.stepMade(vehiclesArrayList);
        flowChart.stepMade((double) flow / time);
        disappointmentChart.stepMade(disappointment);
    }
}
