package com.example.money_beast.components.styles;

import javafx.scene.layout.HBox;

/**
 * Creates simple visual paddings by using empty Hbox
 */
public class Padding {
    public static HBox getTopPadding(int padding) {
        HBox hb = new HBox();
        hb.setPrefHeight(padding);
        return hb;
    }

    public static HBox getWidthpadding(int padding){
        HBox hb = new HBox();
        hb.setPrefWidth(padding);
        return hb;
    }
}
