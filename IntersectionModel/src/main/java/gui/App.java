package gui;

import Engine.Engine;
import Engine.Intersection;
import javafx.scene.Scene;
import javafx.scene.control.ToggleButton;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class App extends javafx.application.Application {
    @Override
    public void start(Stage primaryStage) throws Exception {
        Intersection intersection = new Intersection(69, 68);
        GridPane grid = new GridPane();
        GridCreator gridCreator = new GridCreator(100, 100, grid, intersection);
        Engine engine = new Engine(gridCreator,intersection.getProbVehDir(), intersection.getRoadArrayList());
        Thread simulationThread = new Thread(engine);
        simulationThread.start();
        ToggleButton simulationButton = new ToggleButton("start");
        simulationButton.setOnAction(event -> {
            System.out.println(simulationButton.isSelected());
            engine.setShouldRun(simulationButton.isSelected());
        });
        VBox vBox = new VBox(grid, simulationButton);
        Scene scene = new Scene(vBox, 1000, 1000);
        primaryStage.setScene(scene);
        primaryStage.show();
    }
}
