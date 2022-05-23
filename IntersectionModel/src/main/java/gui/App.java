package gui;

import Engine.*;
import Engine.Road;
import Engine.VehicleTarget;
import Engine.CarGenerator;
import Engine.Intersection;
import javafx.scene.Scene;
import javafx.scene.control.ToggleButton;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;
import javafx.util.Pair;

import java.util.LinkedList;

public class App extends javafx.application.Application {
    @Override
    public void start(Stage primaryStage) throws Exception {
        Intersection intersection = new Intersection(69, 69);
        Road[][] roadsMap = intersection.getMap();
        GridPane grid = new GridPane();
        GridCreator gridCreator = new GridCreator(100, 100, grid, intersection);

        //Crating carGenerators
        LinkedList<CarGenerator> carGenerators = new LinkedList<>();

        LinkedList<Pair<Double, VehicleTarget>> probabilities1 = new LinkedList<>();
        probabilities1.add(new Pair<>(0.2, VehicleTarget.Rokicinska));
        probabilities1.add(new Pair<>(0.4, VehicleTarget.McDonalds));
        probabilities1.add(new Pair<>(0.6, VehicleTarget.Prawo));
        CarGenerator carGenerator1 = new CarGenerator(probabilities1, new Vector(67, 22));
        carGenerators.add(carGenerator1);

        LinkedList<Pair<Double, VehicleTarget>> probabilities2 = new LinkedList<>();
        probabilities2.add(new Pair<>(0.2, VehicleTarget.Rokicinska));
        CarGenerator carGenerator2 = new CarGenerator(probabilities2, new Vector(67, 21));
        carGenerators.add(carGenerator2);

        LinkedList<Pair<Double, VehicleTarget>> probabilities3 = new LinkedList<>();
        probabilities3.add(new Pair<>(0.2, VehicleTarget.Rokicinska));
        CarGenerator carGenerator3 = new CarGenerator(probabilities3, new Vector(67, 20));
        carGenerators.add(carGenerator3);

        LinkedList<Pair<Double, VehicleTarget>> probabilities4 = new LinkedList<>();
        probabilities4.add(new Pair<>(0.2, VehicleTarget.PuszkinaOut));
        CarGenerator carGenerator4 = new CarGenerator(probabilities4, new Vector(67, 19));
        carGenerators.add(carGenerator4);

        LinkedList<Pair<Double, VehicleTarget>> probabilities5 = new LinkedList<>();
        probabilities5.add(new Pair<>(0.2, VehicleTarget.Rokicinska));
        probabilities5.add(new Pair<>(0.4, VehicleTarget.McDonalds));
        CarGenerator carGenerator5 = new CarGenerator(probabilities5, new Vector(43, 66));
        carGenerators.add(carGenerator5);

        LinkedList<Pair<Double, VehicleTarget>> probabilities6 = new LinkedList<>();
        probabilities6.add(new Pair<>(0.2, VehicleTarget.PuszkinaOut));
        probabilities6.add(new Pair<>(0.4, VehicleTarget.Rokicinska));
        CarGenerator carGenerator6 = new CarGenerator(probabilities6, new Vector(44, 66));
        carGenerators.add(carGenerator6);

        LinkedList<Pair<Double, VehicleTarget>> probabilities7 = new LinkedList<>();
        probabilities7.add(new Pair<>(0.2, VehicleTarget.PuszkinaOut));
        CarGenerator carGenerator7 = new CarGenerator(probabilities7, new Vector(45, 66));
        carGenerators.add(carGenerator7);

        LinkedList<Pair<Double, VehicleTarget>> probabilities8 = new LinkedList<>();
        probabilities8.add(new Pair<>(0.2, VehicleTarget.Prawo));
        CarGenerator carGenerator8 = new CarGenerator(probabilities8, new Vector(46, 66));
        carGenerators.add(carGenerator8);

        LinkedList<Pair<Double, VehicleTarget>> probabilities9 = new LinkedList<>();
        probabilities9.add(new Pair<>(0.2, VehicleTarget.Prawo));
        probabilities9.add(new Pair<>(0.4, VehicleTarget.PuszkinaOut));
        probabilities9.add(new Pair<>(0.6, VehicleTarget.Rokicinska));
        CarGenerator carGenerator9 = new CarGenerator(probabilities9, new Vector(46, 66));
        carGenerators.add(carGenerator9);

        LinkedList<Pair<Double, VehicleTarget>> probabilities10 = new LinkedList<>();
        probabilities10.add(new Pair<>(0.2, VehicleTarget.Prawo));
        probabilities10.add(new Pair<>(0.4, VehicleTarget.PuszkinaOut));
        probabilities10.add(new Pair<>(0.6, VehicleTarget.Rokicinska));
        CarGenerator carGenerator10 = new CarGenerator(probabilities10, new Vector(0, 44));
        carGenerators.add(carGenerator10);

        LinkedList<Pair<Double, VehicleTarget>> probabilities11 = new LinkedList<>();
        probabilities11.add(new Pair<>(0.2, VehicleTarget.Prawo));
        CarGenerator carGenerator11 = new CarGenerator(probabilities11, new Vector(0, 45));
        carGenerators.add(carGenerator11);

        LinkedList<Pair<Double, VehicleTarget>> probabilities12 = new LinkedList<>();
        probabilities12.add(new Pair<>(0.2, VehicleTarget.Prawo));
        CarGenerator carGenerator12 = new CarGenerator(probabilities12, new Vector(0, 46));
        carGenerators.add(carGenerator12);

        LinkedList<Pair<Double, VehicleTarget>> probabilities13 = new LinkedList<>();
        probabilities13.add(new Pair<>(0.2, VehicleTarget.McDonalds));
        CarGenerator carGenerator13 = new CarGenerator(probabilities13, new Vector(0, 47));
        carGenerators.add(carGenerator13);

        LinkedList<Pair<Double, VehicleTarget>> probabilities14 = new LinkedList<>();
        probabilities14.add(new Pair<>(0.2, VehicleTarget.Rokicinska));
        probabilities14.add(new Pair<>(0.4, VehicleTarget.McDonalds));
        CarGenerator carGenerator14 = new CarGenerator(probabilities14, new Vector(20, 0));
        carGenerators.add(carGenerator14);

        LinkedList<Pair<Double, VehicleTarget>> probabilities15 = new LinkedList<>();
        probabilities15.add(new Pair<>(0.2, VehicleTarget.McDonalds));
        probabilities15.add(new Pair<>(0.4, VehicleTarget.Prawo));
        probabilities15.add(new Pair<>(0.6, VehicleTarget.PuszkinaOut));
        CarGenerator carGenerator15 = new CarGenerator(probabilities15, new Vector(21, 0));
        carGenerators.add(carGenerator15);


        Engine engine = new Engine(gridCreator,intersection.getProbVehDir(), intersection.getMap(), intersection.getPedestrianPathArrayList(), intersection, carGenerators);
        Thread simulationThread = new Thread(engine);
        simulationThread.start();
        ToggleButton simulationButton = new ToggleButton("start");
        simulationButton.setOnAction(event -> {
            engine.setShouldRun(simulationButton.isSelected());
        });
        VBox vBox = new VBox(grid, simulationButton);
        Scene scene = new Scene(vBox, 680, 700);
        primaryStage.setScene(scene);
        primaryStage.show();
        primaryStage.setOnCloseRequest((WindowEvent we) -> {
            System.exit(0);
        });
    }
}
