package com.example.money_beast.components;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.stage.Stage;

public class SideBar {
    private VBox sideBar = new VBox();
    private Stage stage;
    private  HBox first, second, third;
    private BorderPane pealeht;

    private String asukoht;

    private HBox hetkBox;
    private Button homeBtn, transactionsBtn, statsBtn;
    private Button hetkButton;

    private Font bold = Font.font("System", FontWeight.BOLD, 14);

    public SideBar(BorderPane main, HBox first, HBox second, HBox thrid) {
        this.pealeht = main;
        this.first = first;
        this.second = second;
        this.third = thrid;
        this.asukoht = "home";
        this.hetkBox = first;

        setSideBarProperties();
        createSidebar();
        hetkButton = homeBtn;
    }

    public void setSideBarProperties(){
        sideBar.setPadding(new Insets(10, 20, 10, 20));
        sideBar.setFillWidth(true);
        sideBar.setPrefSize(150, 1000);

        sideBar.setStyle("-fx-background-color: white;");
    }

    public void resetPrevBtnColor(){
        hetkButton.setStyle("-fx-background-color: transparent; -fx-border-color: transparent; -fx-text-fill: black;");
    }

    public void handleChange(String tag, Button targetBtn, HBox targetBox){
        if(asukoht.equals(tag)){
            return;
        }
        resetPrevBtnColor();
        pealeht.setCenter(targetBox);
        hetkBox = targetBox;
        asukoht = tag;
        hetkButton = targetBtn;
        targetBtn.setStyle("-fx-background-color: transparent; -fx-border-color: transparent; -fx-text-fill: black;-fx-font: bold 10pt Arial;");
    }

    public Button createMenuBtn(String text){
        Button menuBtn = new Button(text);
        menuBtn.setStyle("-fx-background-color: transparent; -fx-border-color: transparent; -fx-text-fill: black;");
        menuBtn.setPrefSize(200, 10);
        menuBtn.setAlignment(Pos.CENTER_LEFT);

        return menuBtn;
    }

    public void createSidebar(){

        homeBtn = createMenuBtn("Home");
        homeBtn.setStyle("-fx-background-color: transparent; -fx-border-color: transparent; -fx-text-fill: black;-fx-font: bold 10pt Arial;");

        transactionsBtn = createMenuBtn("Transactions");
        statsBtn = createMenuBtn("Stats");

        sideBar.getChildren().addAll(homeBtn, transactionsBtn,statsBtn);

        // events
        homeBtn.setOnAction(event -> handleChange("home", homeBtn, first));
        transactionsBtn.setOnAction(event -> handleChange("transaction", transactionsBtn, second));
        statsBtn.setOnAction(event -> handleChange("stats", statsBtn, third));
    }

    public VBox getSideBar(){
        return sideBar;
    }
}
