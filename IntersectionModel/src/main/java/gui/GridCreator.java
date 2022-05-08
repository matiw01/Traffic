package gui;

import Engine.Intersection;
import Engine.PedestrianPath;
import Engine.Road;
import Engine.Vehicle;
import javafx.scene.layout.GridPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

import java.util.ArrayList;

public class GridCreator implements IEngineObserver{
    GridPane grid;
    int columnWidth = 10;
    int rowWidth = 10;
    int sqrSize = 10;
    int width;
    int height;
    ArrayList<Road> arrayRoadList;
    Intersection intersection;
    ArrayList<PedestrianPath> pedestrianPathArrayList;
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

        grid.setGridLinesVisible(false);
        grid.getColumnConstraints().clear();
        grid.getRowConstraints().clear();
        grid.getChildren().clear();
        grid.setGridLinesVisible(true);
<<<<<<< HEAD
        System.out.println("creataing grid");

=======
        /*
>>>>>>> 37a3ee1def1745084170e98aa5683bab1be39afc
        for (Road road : arrayRoadList){
            if (!road.isOccupied()) {
                //Label label = new Label("Road");
                Rectangle rect = new Rectangle(0,0,sqrSize,sqrSize);
                rect.setStroke(Color.BLACK);
                grid.add(rect, road.getPosition().getPos_x(), road.getPosition().getPos_y());
            }
        }
        /*
        for (Vehicle vehicle : vehiclesArrayList){
            //Label label = new Label("Car");
            Rectangle rect = new Rectangle(0,0,20,20);
            rect.setStroke(Color.BLUE);
            grid.add(rect, vehicle.getPosition().getPos_x(), vehicle.getPosition().getPos_y());
        }*/


        for(PedestrianPath point : pedestrianPathArrayList){
<<<<<<< HEAD
            Rectangle rect = new Rectangle(0,0,sqrSize,sqrSize);
=======
            Rectangle rect = new Rectangle(0,0,10,10);
>>>>>>> 37a3ee1def1745084170e98aa5683bab1be39afc
            rect.setStroke(point.getColor());
            grid.add(rect, point.getLocation().getPos_x(), point.getLocation().getPos_y());
        }

        ArrayList<Pedestrian> pedestrianArrayList = intersection.getPedestrianArrayList();
        for(Pedestrian pedestrian : pedestrianArrayList){
            Rectangle rect = new Rectangle(0,0,10,10);
            rect.setStroke(Color.PINK);
            grid.add(rect, pedestrian.getLocation().getLocation().getPos_x(), pedestrian.getLocation().getLocation().getPos_y());
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
