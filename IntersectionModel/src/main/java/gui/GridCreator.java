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
            rect.setFill(tramPath.getColor());
            grid.add(rect, tramPath.getLocation().getPos_x(), tramPath.getLocation().getPos_y());
        }

        ArrayList<Tram> tramsArrayList = intersection.getTramsArrayList();
        for(Tram tram : tramsArrayList){
            Rectangle rect = new Rectangle(0,0,sqrSize,sqrSize);
            rect.setFill(tram.getColor());
            grid.add(rect, tram.getLocation().getLocation().getPos_x(), tram.getLocation().getLocation().getPos_y());
        }

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

        for (Vehicle vehicle : vehiclesArrayList){
            Rectangle rect = new Rectangle(0,0,sqrSize,sqrSize);
            rect.setFill(Color.YELLOW);
            grid.add(rect, vehicle.getPosition().getPos_x(), vehicle.getPosition().getPos_y());
        }

        /*
        ArrayList<Environment> environmentElements = intersection.getEnvironmentElements();
        for(Environment element : environmentElements){
            Rectangle rect = new Rectangle(0,0,20,20);
            rect.setStroke(element.getColor());
            grid.add(rect, element.getLocation().getPos_x(), element.getLocation().getPos_y());
        }*/
    }
}
