package com.example.money_beast.components.dashboard;

import javafx.scene.chart.BarChart;
import javafx.scene.chart.CategoryAxis;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.XYChart;

import java.util.Map;

public class PerformaceChart {

    Map<String, Map<String, Double>> data;

    public PerformaceChart(Map<String, Map<String, Double>> data) {
        this.data = data;
    }

    public BarChart<String, Number>  createChart(){
        // USED HINT FROM CHAT GPT , HOW TO CREATE BAR CHART IN JAVA FX !!!
        CategoryAxis xAxis = new CategoryAxis();
        NumberAxis yAxis = new NumberAxis();

        // Create bar chart and set axis
        BarChart<String, Number> barChart = new BarChart<>(xAxis, yAxis);

        barChart.setTitle("Performace Chart 2023");
        barChart.setLegendVisible(false);
        xAxis.setLabel("Month");
        yAxis.setLabel("Value");

        // Create data series and add data
        XYChart.Series<String, Number> series1 = new XYChart.Series<>();
//        series1.setName("Income");
        series1.getData().add(new XYChart.Data<>("January", data.get("1").get("sissetulekud")));
        series1.getData().add(new XYChart.Data<>("February",  data.get("2").get("sissetulekud")));
        series1.getData().add(new XYChart.Data<>("March",  data.get("3").get("sissetulekud")));
        series1.getData().add(new XYChart.Data<>("April",  data.get("4").get("sissetulekud")));
        series1.getData().add(new XYChart.Data<>("May",  data.get("5").get("sissetulekud")));
        series1.getData().add(new XYChart.Data<>("June",  data.get("6").get("sissetulekud")));
        series1.getData().add(new XYChart.Data<>("July",  data.get("7").get("sissetulekud")));
        series1.getData().add(new XYChart.Data<>("August",  data.get("8").get("sissetulekud")));
        series1.getData().add(new XYChart.Data<>("September",  data.get("9").get("sissetulekud")));
        series1.getData().add(new XYChart.Data<>("October",  data.get("10").get("sissetulekud")));
        series1.getData().add(new XYChart.Data<>("November",  data.get("11").get("sissetulekud")));
        series1.getData().add(new XYChart.Data<>("December",  data.get("12").get("sissetulekud")));

        XYChart.Series<String, Number> series2 = new XYChart.Series<>();
//        series2.setName("Outcome");
        series2.getData().add(new XYChart.Data<>("January", data.get("1").get("väljaminekud")));
        series2.getData().add(new XYChart.Data<>("February", data.get("2").get("väljaminekud")));
        series2.getData().add(new XYChart.Data<>("March", data.get("3").get("väljaminekud")));
        series2.getData().add(new XYChart.Data<>("April", data.get("4").get("väljaminekud")));
        series2.getData().add(new XYChart.Data<>("May", data.get("5").get("väljaminekud")));
        series2.getData().add(new XYChart.Data<>("June", data.get("6").get("väljaminekud")));
        series2.getData().add(new XYChart.Data<>("July", data.get("7").get("väljaminekud")));
        series2.getData().add(new XYChart.Data<>("August", data.get("8").get("väljaminekud")));
        series2.getData().add(new XYChart.Data<>("September", data.get("9").get("väljaminekud")));
        series2.getData().add(new XYChart.Data<>("October", data.get("10").get("väljaminekud")));
        series2.getData().add(new XYChart.Data<>("November", data.get("11").get("väljaminekud")));
        series2.getData().add(new XYChart.Data<>("December", data.get("12").get("väljaminekud")));



//         Add data series to bar chart
        barChart.getData().addAll(series1, series2);


        series1.getData().forEach(data -> {
            data.getNode().setStyle("-fx-bar-fill: green; -fx-text-fill: green");
        });

        series2.getData().forEach(data -> {
            data.getNode().setStyle("-fx-bar-fill: red; -fx-text-fill: red");
        });


        barChart.setMaxSize(700, 700);

        return barChart;
    }

}
