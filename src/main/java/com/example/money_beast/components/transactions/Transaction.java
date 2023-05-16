package com.example.money_beast.components.transactions;

import com.example.money_beast.components.TransactionsPage;
import com.example.money_beast.controller.TransactionController;
import com.example.money_beast.wallet.Tehing;
import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

import java.time.LocalDate;

public class Transaction {
    TransactionController tc;
    int defaultWidth = 400;
    LocalDate date;
    HBox transactionBody;
    double value;
    String categoryName;
    TransactionsPage tp;
    Tehing tehing;
    Stage stage;
    boolean income;
    public Transaction(double value, String categoryName, boolean income, Stage stage, LocalDate date, TransactionController tc, TransactionsPage tp, Tehing tehing) {
        this.transactionBody = new  HBox();
        this.categoryName = categoryName;
        this.value = value;
        this.income = income;
        this.stage = stage;
        this.date = date;
        this.tc = tc;
        this.tp = tp;
        this.tehing = tehing;
        setStyle();
        createLabels();

            // chat gpt double click -> on double click search
        transactionBody.setOnMouseClicked(event -> {
        if (event.getClickCount() == 2) {
            AddTransactionWindow atw = new AddTransactionWindow(this, tc);
            atw.showAddTransaction();
            this.createLabels();
            transactionBody.requestLayout();
            tp.update();
            tp.getHp().update();
            tp.getSp().update();

//            System.out.println("Double clicked! ->" + value + " " + categoryName);
            // perform your action here
        }
    });
    }

    public void setStyle(){
        transactionBody.setSpacing(100);
        transactionBody.setStyle("-fx-background-color: white; -fx-background-radius: 10;");
        transactionBody.setMaxWidth(defaultWidth);
        transactionBody.setAlignment(Pos.CENTER_RIGHT);
    }

    public void createLabels(){

        transactionBody.getChildren().clear();

        Label amount = new Label(String.valueOf(value));

        if(income){
            amount.setTextFill(Color.GREEN);
        }else {
            amount.setTextFill(Color.RED);
        }


        Label category = new Label(categoryName);

        HBox left = new HBox();
        left.setPrefWidth(defaultWidth/2.0);
        left.setAlignment(Pos.CENTER_LEFT);
        HBox right = new HBox();
        right.setAlignment(Pos.CENTER_RIGHT);
        right.setPrefWidth(defaultWidth/2.0);
        left.getChildren().add(category);
        right.getChildren().add(amount);



        HBox.setHgrow(amount, null);
        transactionBody.setAlignment(Pos.CENTER_LEFT);
        transactionBody.getChildren().addAll(left, right);
    }

    public HBox getTransaction(){
        return transactionBody;
    }

    public double getValue() {
        return value;
    }

    public LocalDate getDate() {
        return date;
    }

    public boolean isIncome() {
        return income;
    }

    public Stage getStage() {
        return stage;
    }

    public String getCategoryName() {
        return categoryName;
    }

    public Tehing getTehing() {
        return tehing;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public void setCategoryName(String categoryName) {
        this.categoryName = categoryName;
    }

    public void setIncome(boolean income) {
        this.income = income;
    }

    public void setValue(double value) {
        this.value = value;
    }

    @Override
    public String toString() {
        return "Transaction{" +
                "tc=" + tc +
                ", defaultWidth=" + defaultWidth +
                ", date=" + date +
                ", transactionBody=" + transactionBody +
                ", value=" + value +
                ", categoryName='" + categoryName + '\'' +
                ", stage=" + stage +
                ", income=" + income +
                '}';
    }
}
