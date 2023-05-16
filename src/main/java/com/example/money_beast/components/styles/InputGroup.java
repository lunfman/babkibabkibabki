package com.example.money_beast.components.styles;

import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;

public class InputGroup {

    int innerContainerWidth = 150;
    String description, prefil;
    TextField input;
    Label info;

    HBox container;

    public InputGroup(String description) {
        this.description = description;
        this.prefil = "";
    }

    public InputGroup(String description, String prefil){
        this.description = description;
        this.prefil = prefil;
    }

    public void setStyle(){
        container.setSpacing(10);
    }

    public void createGroup(){
        container = new HBox();
        info = new Label(description);
        input = new TextField();
        input.setPrefWidth(innerContainerWidth);
        input.setText(prefil);

        HBox leftContainer = new HBox();
        leftContainer.getChildren().add(info);
        leftContainer.setPrefWidth(innerContainerWidth);
        leftContainer.setAlignment(Pos.CENTER_LEFT);

        HBox righContainer = new HBox();
        righContainer.setPrefWidth(innerContainerWidth);
        righContainer.getChildren().add(input);
        righContainer.setAlignment(Pos.CENTER_RIGHT);

        setStyle();

        container.getChildren().addAll(leftContainer, righContainer);
    }

    public HBox getGroup(){
        createGroup();
        return container;
    }

    public void setInnerContainerWidth(int innerContainerWidth) {
        this.innerContainerWidth = innerContainerWidth;
    }

    public TextField getInput() {
        return input;
    }
}
