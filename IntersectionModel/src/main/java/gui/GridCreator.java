package gui;

import Engine.*;
import javafx.scene.layout.GridPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

import java.util.ArrayList;
import java.util.LinkedList;

public class GridCreator implements IEngineObserver{
    GridPane grid;
    int columnWidth = 50;
    int rowWidth = 50;
    int sqrSize = 10;
    int width;
    int height;
    Intersection intersection;
    ArrayList<Road> arrayRoadList;
    ArrayList<PedestrianPath> pedestrianPathArrayList;
    ArrayList<Vehicle> vehicleArrayList;
    public GridCreator(int width, int height, GridPane grid, Intersection intersection){
        this.width = width;
        this.height = height;
        this.grid = grid;
        this.intersection = intersection;
        this.arrayRoadList = intersection.getRoadArrayList();
        this.pedestrianPathArrayList = intersection.getPedestrianPathArrayList();
    }

    @Override
    public void stepMade(ArrayList<Vehicle> vehiclesArrayList) {
        createGrid(vehiclesArrayList);
    }

    private void createGrid(ArrayList<Vehicle> vehiclesArrayList){
        ArrayList<Road> arrayRoadList = intersection.getRoadArrayList();
        grid.setGridLinesVisible(false);
        grid.getColumnConstraints().clear();
        grid.getRowConstraints().clear();
        grid.getChildren().clear();
        grid.setGridLinesVisible(true);

        for (Road road : arrayRoadList){
            if (!road.isOccupied()) {
                Rectangle rect = new Rectangle(0,0,sqrSize,sqrSize);
                rect.setFill(Color.BLACK);
                grid.add(rect, road.getPosition().getPos_x(), road.getPosition().getPos_y());
            }
        }

        ArrayList<PedestrianPath> pedestrianPathArrayList = intersection.getPedestrianPathArrayList();
        for(PedestrianPath point : pedestrianPathArrayList){
            Rectangle rect = new Rectangle(0,0,sqrSize,sqrSize);
            rect.setFill(point.getColor());
            grid.add(rect, point.getLocation().getPos_x(), point.getLocation().getPos_y());
        }

        ArrayList<Pedestrian> pedestrianArrayList = intersection.getPedestrianArrayList();
        for(Pedestrian pedestrian : pedestrianArrayList){
            Rectangle rect = new Rectangle(0,0,sqrSize,sqrSize);
            rect.setFill(Color.PINK);
            grid.add(rect, pedestrian.getLocation().getLocation().getPos_x(), pedestrian.getLocation().getLocation().getPos_y());
        }

        ArrayList<TramPath> tramPathArrayList = intersection.getTramPathArrayList();
        for(TramPath tramPath : tramPathArrayList){
            Rectangle rect = new Rectangle(0,0,sqrSize,sqrSize);
            //rect.setFill(tramPath.getColor());
            rect.setFill(Color.CYAN);
            grid.add(rect, tramPath.getLocation().getPos_x(), tramPath.getLocation().getPos_y());
        }

        int flag;
        ArrayList<Tram> toRemove = new ArrayList<>();
        ArrayList<Tram> tramsArrayList = intersection.getTramsArrayList();
        for(Tram tram : tramsArrayList){
            flag = 0;
            for(TramPath tp : tram.getTramParts()){
                Rectangle rect = new Rectangle(0,0,sqrSize,sqrSize);
                rect.setFill(tram.getColor());
                if(tp.getLocation().getPos_x()>=0){
                    grid.add(rect, tp.getLocation().getPos_x(), tp.getLocation().getPos_y());
                    flag = 1;
                }
            }
            if(flag == 0){
                toRemove.add(tram);
            }
        }
        for(Tram tram : toRemove){
            tramsArrayList.remove(tram);
        }
        intersection.setTramsArrayList(tramsArrayList);

        ArrayList<LightsGroup> lightsGroups = intersection.getPedestrianLightsGroupsArrayList();
        for (LightsGroup lightsGroup : lightsGroups){
            LinkedList<TrafficLights> lights = lightsGroup.getLights();
            for (TrafficLights trafficLight : lights){
                Rectangle rect = new Rectangle(0,0,sqrSize,sqrSize);
                if (lightsGroup.state == 1) rect.setFill(Color.GREEN);
                else rect.setFill(Color.RED);
                grid.add(rect, trafficLight.getLocation().getPos_x(), trafficLight.getLocation().getPos_y());
            }
        }
        ArrayList<LightsGroup> vLightsGroups = intersection.getVehicleLightsGroupsArrayList();
        for (LightsGroup lightsGroup : vLightsGroups){
            LinkedList<TrafficLights> lights = lightsGroup.getLights();
            for (TrafficLights trafficLight : lights){
                Rectangle rect = new Rectangle(0,0,sqrSize,sqrSize);
                if (lightsGroup.state == 1) rect.setFill(Color.GREEN);
                else rect.setFill(Color.RED);
                grid.add(rect, trafficLight.getLocation().getPos_x(), trafficLight.getLocation().getPos_y());
            }
        }

        ArrayList<LightsGroup> tLightsGroups = intersection.getTramLightsGroupsArrayList();
        for (LightsGroup lightsGroup : tLightsGroups){
            LinkedList<TrafficLights> lights = lightsGroup.getLights();
            for (TrafficLights trafficLight : lights){
                Rectangle rect = new Rectangle(0,0,sqrSize,sqrSize);
                if (lightsGroup.state == 1) rect.setFill(Color.GREEN);
                else rect.setFill(Color.RED);
                grid.add(rect, trafficLight.getLocation().getPos_x(), trafficLight.getLocation().getPos_y());
            }
        }

        for (Vehicle vehicle : vehiclesArrayList){
            Rectangle rect1 = new Rectangle(0,0,sqrSize,sqrSize);
            Rectangle rect2 = new Rectangle(0,0,sqrSize,sqrSize);
            rect1.setFill(Color.YELLOW);
            rect2.setFill(Color.YELLOW);
            grid.add(rect1, vehicle.getPosition().getPos_x(), vehicle.getPosition().getPos_y());
            if (vehicle.getTailPosition() != null) grid.add(rect2, vehicle.getTailPosition().getPos_x(), vehicle.getTailPosition().getPos_y());
        }

//        for ( Zone zone : intersection.getZoneLinkedList()){
//            Rectangle rect1 = new Rectangle(sqrSize,sqrSize);
//            rect1.setFill(Color.BLUE);
//            Rectangle rect2 = new Rectangle(sqrSize,sqrSize);
//            rect2.setFill(Color.BLUE);
//            grid.add(rect1, zone.getLowerLeft().getPos_x(), zone.getLowerLeft().getPos_y());
//            grid.add(rect2, zone.getUpperRight().getPos_x(), zone.getUpperRight().getPos_y());
//        }
        /*
        ArrayList<Environment> environmentElements = intersection.getEnvironmentElements();
        for(Environment element : environmentElements){
            Rectangle rect = new Rectangle(0,0,20,20);
            rect.setStroke(element.getColor());
            grid.add(rect, element.getLocation().getPos_x(), element.getLocation().getPos_y());
        }*/
    }
}
