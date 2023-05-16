package com.example.money_beast.controller;

import com.example.money_beast.components.transactions.Transaction;
import com.example.money_beast.wallet.Rahakott;
import com.example.money_beast.wallet.Tehing;
import javafx.scene.control.Label;

import java.util.List;

public class Abi {
    /**
     * method returns a sum from transaction list
     * @param transactions list of transactions
     * @return difference of income and outcome
     */
    public static double getSumFromTransactions(List<Transaction> transactions){
        double sum = 0;
        for(Transaction tr: transactions){
            sum += tr.getValue() * (tr.isIncome() ? 1 : -1);
        }
        return sum;
    }

    public static void updateInfoLabel(List<Tehing> tehingud, Label info, String msg){
        if(tehingud.size() == 0){
            info.setText(msg);
        }else {
            info.setText("");
        }
    }

    public static void updateInfoLabel(boolean onOlemas, Label info, String msg){
        if(!onOlemas){
            info.setText(msg);
        }else {
            info.setText("");
        }
    }
}
