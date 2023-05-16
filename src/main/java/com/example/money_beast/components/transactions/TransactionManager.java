package com.example.money_beast.components.transactions;

import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

import java.time.LocalDate;
import java.util.List;
import java.util.TreeMap;

/**
 * Class generates all possible TransactionGroups
 */
public class TransactionManager {

    TreeMap<LocalDate, List<Transaction>> alltransactions;
    VBox container;

    public TransactionManager(TreeMap<LocalDate, List<Transaction>> alltransactions) {
        this.alltransactions = alltransactions;
        container = new VBox();
    }


    public void generateAllTransactionGroups(){
        for (LocalDate date: alltransactions.descendingKeySet()){
            HBox group = new TransactionGroup(alltransactions.get(date), date).getGroup();
            container.getChildren().add(group);
        }
    }

    public VBox getContainer(){
        generateAllTransactionGroups();
        return  container;
    }

}
