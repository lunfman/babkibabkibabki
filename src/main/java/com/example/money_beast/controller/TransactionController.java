package com.example.money_beast.controller;

import com.example.money_beast.components.transactions.Transaction;
import com.example.money_beast.wallet.Rahakott;
import com.example.money_beast.wallet.Tehing;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.TreeMap;



public class TransactionController {

    TreeMap<LocalDate, List<Transaction>> allTransactions;
    Rahakott wallet;

    public TransactionController(Rahakott wallet) {
        this.allTransactions = new TreeMap<>();
        this.wallet = wallet;
    }

    /**
     * removes empty key
     * @param date
     */
    public void removeEmptyKey(LocalDate date){
        if(allTransactions.get(date).size() == 0){
            allTransactions.remove(date);
        }
    }

    /**
     * Adds new transaction in TransactionController
     * Adds new transaction to the wallet
     * @param date transactions date
     * @param tr Transaction UI class
     */
    public void addTransaction(LocalDate date, Transaction tr){
        if(allTransactions.containsKey(date)){
            allTransactions.get(date).add(tr);
        }else {
            List<Transaction> tl = new ArrayList<>();
            tl.add(tr);
            allTransactions.put(date, tl);
        }

        if(!wallet.getTehinguInfo().contains(tr.getTehing())){
            wallet.lisaTehing(tr.getTehing());
        }

    }

    /**
     * Updates transaction of the wallets class
     * @param tr Transaction UI class
     */
    public void updateTehing(Transaction tr){
        Tehing tehing = tr.getTehing();
        tehing.setKategooria(tr.getCategoryName());
        tehing.setKuupäev(tr.getDate());
        tehing.setMaksumus(tr.getValue());
        tehing.setSissetulek(tr.isIncome());
    }

    /**
     * Updates ui transaction and transaction from the wallet
     * @param date of transaction used as key value
     * @param tr Transaction UI class
     */
    public void  updateTransaction( LocalDate date, Transaction tr){
        if(allTransactions.containsKey(date)){
            List<Transaction> thisDateTransactions = allTransactions.get(date);
            if(!(tr.getDate() == date)){
                thisDateTransactions.remove(tr);
                addTransaction(tr.getDate(), tr);
                removeEmptyKey(date);
            }
        }

        updateTehing(tr);
    }

    /**
     * Deletes transactions from the TransactionController
     * Deletes transactions from the wallet
     * @param date of transaction used as key value
     * @param tr Transaction UI class
     */
    public void deleteTransaction(LocalDate date, Transaction tr){
        allTransactions.get(date).remove(tr);
        wallet.eemaldaTehing(tr.getTehing());
        removeEmptyKey(date);
    }

    public TreeMap<LocalDate, List<Transaction>> getAllTransactions() {
        return allTransactions;
    }
}
