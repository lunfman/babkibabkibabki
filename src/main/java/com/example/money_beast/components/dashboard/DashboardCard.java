package com.example.money_beast.components.dashboard;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;

public class DashboardCard {
    VBox container;
    Label title, body;
    String titleText, bodyText;

    public DashboardCard(String titleText, String bodyText) {
        this.bodyText = bodyText;
        this.titleText = titleText;
    }

    public void setStyle(){
        title.setFont(new Font("Arial", 15));
        title.setTextFill(Color.GRAY);

        body.setFont(new Font("Arial", 20));
        body.setTextFill(Color.BLACK);


        container.setStyle("-fx-background-color: white; -fx-background-radius: 10;");

        container.setMaxWidth(300);
        container.setPadding(new Insets(20, 20, 20, 20));
        container.setAlignment(Pos.CENTER);
        container.setPrefSize(200,75);
        container.setMaxWidth(200);
    }

    public void createCard(){
        container = new VBox();
        title = new Label(titleText);
        body = new Label(bodyText);
        setStyle();
        container.getChildren().addAll(title, body);
    }

    public VBox getCard(){
        createCard();
        return container;
    }
}
