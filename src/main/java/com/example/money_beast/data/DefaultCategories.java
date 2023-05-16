package com.example.money_beast.data;

import java.util.ArrayList;
import java.util.List;

public class DefaultCategories {


    public static List<String> getIncomeCategories(){
        List<String> incomeCat = new ArrayList<>();
        incomeCat.add("Salary");
        incomeCat.add("Gift");
        incomeCat.add("Other");

        return incomeCat;
    }

    public static List<String> getOutcomeCategories(){
        List<String> outcomeCat = new ArrayList<>();
        outcomeCat.add("Food");
        outcomeCat.add("Staff");
        outcomeCat.add("Health");
        outcomeCat.add("Services");
        outcomeCat.add("Transportation");

        return outcomeCat;

    }

}
