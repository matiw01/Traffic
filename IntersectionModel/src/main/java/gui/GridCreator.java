package gui;

import Engine.*;
import javafx.scene.control.Label;
import javafx.scene.layout.GridPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

import java.util.ArrayList;

public class GridCreator implements IEngineObserver{
    GridPane grid;
    int columnWidth = 50;
    int rowWidth = 50;
    int width;
    int height;
    Intersection intersection;
    public GridCreator(int width, int height, GridPane grid, Intersection intersection){
        this.width = width;
        this.height = height;
        this.grid = grid;
        this.intersection = intersection;
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
        System.out.println("creataing grid");
        /*
        for (Road road : arrayRoadList){
            if (!road.isOccupied()) {
                //Label label = new Label("Road");
                Rectangle rect = new Rectangle(0,0,20,20);
                rect.setStroke(Color.BLACK);
                grid.add(rect, road.getPosition().getPos_x(), road.getPosition().getPos_y());
            }
        }*/
        /*
        for (Vehicle vehicle : vehiclesArrayList){
            //Label label = new Label("Car");
            Rectangle rect = new Rectangle(0,0,20,20);
            rect.setStroke(Color.BLUE);
            grid.add(rect, vehicle.getPosition().getPos_x(), vehicle.getPosition().getPos_y());
        }*/

        ArrayList<PedestrianPath> pedestrianPathArrayList = intersection.getPedestrianPathArrayList();
        for(PedestrianPath point : pedestrianPathArrayList){
            Rectangle rect = new Rectangle(0,0,20,20);
            rect.setStroke(point.getColor());
            grid.add(rect, point.getLocation().getPos_x(), point.getLocation().getPos_y());
        }
        /*
        ArrayList<Pedestrian> pedestrianArrayList = intersection.getPedestrianArrayList();
        for(Pedestrian pedestrian : pedestrianArrayList){
            Rectangle rect = new Rectangle(0,0,20,20);
            rect.setStroke(Color.PINK);
            grid.add(rect, pedestrian.getLocation().getLocation().getPos_x(), pedestrian.getLocation().getLocation().getPos_y());
        }*/

        /*
        ArrayList<Environment> environmentElements = intersection.getEnvironmentElements();
        for(Environment element : environmentElements){
            Rectangle rect = new Rectangle(0,0,20,20);
            rect.setStroke(element.getColor());
            grid.add(rect, element.getLocation().getPos_x(), element.getLocation().getPos_y());
        }*/
    }
}