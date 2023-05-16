package com.example.money_beast.components;

import com.example.money_beast.controller.Abi;
import com.example.money_beast.wallet.Rahakott;
import javafx.geometry.Pos;
import javafx.scene.chart.PieChart;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class StatsPage {

    Rahakott wallet;

    HBox hbox;

    Label info;

    public StatsPage(Rahakott wallet) {
        this.wallet = wallet;
    }

    public void update(){
        hbox.getChildren().clear();
        hbox.getChildren().add(getPieChart());
        hbox.requestLayout();
        updateInfoLabel();
    }

    public void updateInfoLabel(){
        int currentYear = LocalDate.now().getYear();
        LocalDate startOfTheYear = LocalDate.of(currentYear, 1, 1);
        LocalDate endOfTheYear = LocalDate.of(currentYear, 12, 31);
        double currentYearOutcome = wallet.koguVäljaminek(startOfTheYear, endOfTheYear);
        boolean isOutcome = currentYearOutcome > 0;
        Abi.updateInfoLabel(isOutcome, info, "Keep it up, you have not spent a penny!");
    }
    // used hint from chat gpt how to create a pie chart in javafx
    public StackPane getPieChart(){

        info = new Label();
        updateInfoLabel();


        List<PieChart.Data> chartData = new ArrayList<>();
        Map<String, Double> data = wallet.kategooriaKaupa(LocalDate.of(2023, 1, 1),LocalDate.of(2023, 12, 1));
        for (String category: data.keySet()){
            chartData.add(new PieChart.Data(category, data.get(category)));
        }

        // Add the data to the pie chart
        PieChart pieChart = new PieChart();
        for(PieChart.Data slice: chartData){
            pieChart.getData().add(slice);
        }

        // Create a StackPane to hold the pie chart
        StackPane mainContainer = new StackPane();
        mainContainer.getChildren().add(info);
        mainContainer.getChildren().add(pieChart);

        // Set the size of the pie chart
        pieChart.setMaxSize(800, 800);

        return mainContainer;
    }

    public HBox getHorizontalPieChar(){
        hbox = new HBox();
        hbox.setAlignment(Pos.CENTER);
        hbox.getChildren().add(getPieChart());
        return  hbox;
    }

}
