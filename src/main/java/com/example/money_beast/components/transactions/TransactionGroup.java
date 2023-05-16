package com.example.money_beast.components.transactions;

import com.example.money_beast.controller.Abi;
import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;

import java.time.LocalDate;
import java.util.List;

/**
 * This class creates a group of transactions based on date (group by date)
 */
public class TransactionGroup {

    int defaultWidht = 400;
    List<Transaction> transactions;
    VBox group;
    LocalDate date;

    Label summary, dateLabel;


    double sum;

    public TransactionGroup(List<Transaction> transactions, LocalDate date) {
        this.transactions = transactions;
        this.date = date;
        this.sum = Abi.getSumFromTransactions(transactions);
        createGroup();
    }

    public void setStyle(){
        group.setSpacing(3);
    }

    public void setSummaryColor(){
        if (sum > 0) {
            summary.setTextFill(Color.GREEN);
        } else {
            summary.setTextFill(Color.RED);
        }
    }

    public void createGroup(){
        group = new VBox();
        setStyle();
        dateLabel = new Label(date.toString());
        summary = new Label(String.valueOf(sum));
        summary.setPrefWidth(defaultWidht);
        summary.setAlignment(Pos.CENTER_RIGHT);
        setSummaryColor();

        dateLabel.setPrefWidth(defaultWidht);
        dateLabel.setAlignment(Pos.CENTER);
        group.getChildren().addAll(dateLabel, summary);

        for(Transaction tr: transactions){
            System.out.println(tr.getValue());
            group.getChildren().add(tr.getTransaction());
        }
    }

    public HBox getGroup(){
        HBox hb = new HBox();
        hb.setAlignment(Pos.CENTER);
        hb.getChildren().add(group);
        return hb;
    }
}
