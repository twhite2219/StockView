package application;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.chart.LineChart;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.XYChart;
import javafx.stage.Stage;
 
 
public class StockGraph extends Application {
 
    @Override public void start(Stage stage) {
        stage.setTitle("Stock for" + companyName);
        //defining the axes
        final NumberAxis xAxis = new NumberAxis();
        final NumberAxis yAxis = new NumberAxis();
        xAxis.setLabel("Date");
        //creating the chart
        final LineChart<Number,Number> lineChart = 
                new LineChart<Number,Number>(xAxis,yAxis);
                
        lineChart.setTitle("Stock Graph");
        //defining a series
        XYChart.Series series = new XYChart.Series();
        series.setName("Current Price");
        //populating the series with data
        
        for {int 0; 0< numoflines ;0++{
        series.getData().add(new XYChart.Data(date, highest));
        }
        
        
        
        
        
        Scene scene  = new Scene(lineChart,800,600);
        lineChart.getData().add(series);
       
        stage.setScene(scene);
        stage.show();
    }
 
    public static void main(String[] args) {
        launch(args);
    }
}