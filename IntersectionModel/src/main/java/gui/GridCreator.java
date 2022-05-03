package gui;

import Engine.Intersection;
import Engine.Road;
import Engine.Vehicle;
import javafx.scene.control.Label;
import javafx.scene.layout.GridPane;

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
        for (Road road : arrayRoadList){
            if (!road.isOccupied()) {
                Label label = new Label("Road");
                grid.add(label, road.getPosition().getPos_x(), road.getPosition().getPos_y());
            }
        }
        for (Vehicle vehicle : vehiclesArrayList){
            Label label = new Label("Car");
            grid.add(label, vehicle.getPosition().getPos_x(), vehicle.getPosition().getPos_y());
        }
    }
}
