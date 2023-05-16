package com.example.money_beast.components;

import com.example.money_beast.components.dashboard.DashboardCard;
import com.example.money_beast.components.dashboard.PerformaceChart;
import com.example.money_beast.components.styles.Padding;
import com.example.money_beast.wallet.Rahakott;
import javafx.geometry.Pos;
import javafx.scene.chart.BarChart;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

import java.time.LocalDate;

public class HomePage {

    HBox hbox;
    Rahakott wallet;

    public HomePage(Rahakott wallet) {
        this.wallet = wallet;
    }

    public void update(){
        hbox.getChildren().clear();
        hbox.getChildren().add(getMainContainer());
        hbox.requestLayout();
    }

    public VBox getMainContainer(){

        VBox trannsactionsLeht = new VBox();

        int currentYear = LocalDate.now().getYear();
        LocalDate startOfTheYear = LocalDate.of(currentYear, 1, 1);
        LocalDate endOfTheYear = LocalDate.of(currentYear, 12, 31);
        String balance = String.valueOf(wallet.rahaArvel());
        String currentYearOutcome = String.valueOf(wallet.koguVäljaminek(startOfTheYear, endOfTheYear));
        String currentYearIncome = String.valueOf(wallet.koguSissetulek(startOfTheYear, endOfTheYear));

        HBox statsBox = new HBox();
        statsBox.setSpacing(10);
        DashboardCard totalOutcome = new DashboardCard("Total expense", currentYearOutcome);
        DashboardCard totalIncome = new DashboardCard("Total income", currentYearIncome);
        DashboardCard balanceCard = new DashboardCard("Balance", balance);

        statsBox.getChildren().addAll(balanceCard.getCard(), totalIncome.getCard(),totalOutcome.getCard());

        HBox topPadding = Padding.getTopPadding(10);
        HBox topPadding2 = Padding.getTopPadding(10);
        System.out.println(wallet.kuuKaupa(currentYear));
        BarChart<String, Number> performaceChart = new PerformaceChart(wallet.kuuKaupa(currentYear)).createChart();

        trannsactionsLeht.getChildren().addAll(topPadding,statsBox, topPadding2, performaceChart);
        return trannsactionsLeht;
    }

    public HBox getHorizontalHomePage(){
        hbox = new HBox();
        hbox.setAlignment(Pos.CENTER);
        hbox.getChildren().add(getMainContainer());
        return hbox;
    }
}
