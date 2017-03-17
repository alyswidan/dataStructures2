package aly.data.sorting;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.chart.LineChart;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.XYChart;
import javafx.stage.Stage;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by ADMIN on 3/17/2017.
 */
public class Plotter extends Application {


    List<Sorter<Integer>>intSorters = new ArrayList<>();
    final int maxsize = (int)1e3,multiplier=10,min=-1000,max=1000;
    @Override public void start(Stage stage) {

        //add new sorters to this list to be plotted
        intSorters.add(new MergeSorter<>());
        intSorters.add(new HeapSorter<>());
        intSorters.add(new InsertionSorter<>());



        stage.setTitle("Sorting");
        //defining the axes
        final NumberAxis xAxis = new NumberAxis();
        final NumberAxis yAxis = new NumberAxis();
        yAxis.setLabel("running time(ms)");
        xAxis.setLabel("size of data set");
        //creating the chart
        final LineChart<Number,Number> lineChart = new LineChart<>(xAxis,yAxis);

        lineChart.setTitle("sorting algorithm's running times");


        List<XYChart.Series<Number,Number>>seriesList = new ArrayList<>();
        RandomListGenerator generator = new RandomListGenerator();

        for (Sorter<Integer> sorter:intSorters) {

            XYChart.Series<Number,Number> series = new XYChart.Series<>();
            seriesList.add(series);
            series.setName(sorter.getClass().getSimpleName());

            for(int n=multiplier,i=1;n<=maxsize;i++,n*=multiplier)
            {
                long st = System.nanoTime();
                sorter.sort(generator.get(n,min,max));
                long en = System.nanoTime();
                series.getData().add(new XYChart.Data<>(n,(en-st)/1000000));
            }
        }

        Scene scene  = new Scene(lineChart,800,600);
        for (XYChart.Series<Number,Number> series:seriesList)
            lineChart.getData().add(series);

        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
