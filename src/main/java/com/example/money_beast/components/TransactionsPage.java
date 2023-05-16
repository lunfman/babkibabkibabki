package com.example.money_beast.components;

import com.example.money_beast.components.styles.Padding;
import com.example.money_beast.components.transactions.AddTransactionWindow;
import com.example.money_beast.components.transactions.Transaction;
import com.example.money_beast.components.transactions.TransactionManager;
import com.example.money_beast.controller.Abi;
import com.example.money_beast.controller.TransactionController;
import com.example.money_beast.wallet.Rahakott;
import com.example.money_beast.wallet.Tehing;

import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.time.LocalDate;

/**
 * Transactions page section
 * class takes Stage , Rahakott , HomePage , StatsPage  params in constructor
 * Rahakott creates a referance to Rahakott object which stores read data from the save files
 * HomePage and StatsPage used here to access update methods from the classes,which allows to rerender content on the
 * pages based on new information
 */
public class TransactionsPage {

    private Stage stage;
    private VBox transactionsContainer;
    private static VBox transactions;

    private static TransactionController tc;

    private Rahakott wallet;

    private HomePage hp;

    private StatsPage sp;

    private Label info;

    public TransactionsPage(Stage stage, Rahakott wallet, HomePage hp, StatsPage sp) {
        this.stage = stage;
        this.wallet = wallet;
        this.hp = hp;
        this.sp = sp;
    }

    public void setStyle(){
        transactionsContainer.setFillWidth(true);
        transactionsContainer.setAlignment(Pos.TOP_CENTER);
        transactionsContainer.setSpacing(10);
        transactionsContainer.setMinWidth(100);
        transactionsContainer.setPrefSize(600, 600);
    }

    public void updateInfoLabel(){
        Abi.updateInfoLabel(wallet.getTehinguInfo(), info, "It looks like you have not added any transactions yet");
    }

    public VBox getTransactionPage(){
        info = new Label();

        tc = new TransactionController(wallet);

        transactionsContainer = new VBox();
        setStyle();

        Button addTransaction = new Button("+");
        addTransaction.setAlignment(Pos.BOTTOM_CENTER);
        updateInfoLabel();

        for (Tehing tehing: wallet.getTehinguInfo()){
            Transaction transaction = new Transaction(tehing.getMaksumus(), tehing.getKategooria(), tehing.isSissetulek(),
                    stage, tehing.getKuupäev(), tc, this, tehing);
            tc.addTransaction(tehing.getKuupäev(), transaction);
        }


        transactions = new TransactionManager(tc.getAllTransactions()).getContainer();
        transactions.setSpacing(10);

        ScrollPane scrollPane = new ScrollPane(transactions);
        scrollPane.setFitToWidth(true);
        scrollPane.setPrefHeight(400);
        scrollPane.setStyle("-fx-background-color: transparent; -fx-background-insets: 0; -fx-padding: 0; -fx-border-width: 0; -fx-control-inner-background: transparent;");

        HBox padding = Padding.getTopPadding(30);

        transactionsContainer.getChildren().addAll(padding, addTransaction, info,scrollPane);


        addTransaction.setOnAction(e -> {
            AddTransactionWindow atw = new AddTransactionWindow(tc, stage, this);
            atw.showAddTransaction();
            transactions.getChildren().clear();
            transactions.getChildren().add(new TransactionManager(tc.getAllTransactions()).getContainer());
            transactions.requestLayout();
            hp.update();
            sp.update();
            updateInfoLabel();
        });


        return transactionsContainer;
    }

    public void update(){
        transactions.getChildren().clear();
        transactions.getChildren().add(new TransactionManager(tc.getAllTransactions()).getContainer());
        transactions.requestLayout();
        updateInfoLabel();
    }

    public HBox getHorizontalTransactionPage(){

        HBox hbox = new HBox();
        hbox.setAlignment(Pos.CENTER);
        hbox.getChildren().add(getTransactionPage());
        return hbox;
    }

    public HomePage getHp() {
        return hp;
    }

    public StatsPage getSp() {
        return sp;
    }
}
