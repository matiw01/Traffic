package gui;


import javafx.application.Platform;
import javafx.scene.chart.LineChart;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.XYChart;

public class ChartMaintainer {
    int epoch = 0;

    LineChart<Integer, Double> lineChart;
    XYChart.Series<Integer,Double> value;

    String xLabel;
    String yLabel;
    public ChartMaintainer(String xLabel, String yLabel){
        this.xLabel = xLabel;
        this.yLabel = yLabel;
        NumberAxis xAxis = new NumberAxis();
        xAxis.setLabel(xLabel);

        NumberAxis yAxis = new NumberAxis();
        yAxis.setLabel(yLabel);

        this.value = new XYChart.Series();
        this.value.setName(yLabel);

        this.lineChart = new LineChart(xAxis, yAxis);

        lineChart.setCreateSymbols(false);

        lineChart.getData().add(value);
    }

    public LineChart createChart(){
        return lineChart;
    }

    public void stepMade(double value){
        Platform.runLater(() -> {
            epoch ++;
            this.value.getData().add(new XYChart.Data(epoch, value));
        });
    }
}
