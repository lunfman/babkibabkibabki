package com.example.money_beast.controller;

import com.example.money_beast.exceptions.InvalidForm;

import java.time.LocalDate;

public class FormValidation {

    String category;
    String value;
    LocalDate date;

    public FormValidation(String category, String value, LocalDate date) {
        this.category = category;
        this.value = value;
        this.date = date;
    }


    public void validate(){
        validateCategory();
        validateValue();
        validateDate();
    }

    public void validateCategory(){
        if(category == null){
            throw  new InvalidForm("The category is not selected");
        }
    }

    public void validateValue(){
        if(value.equals("")){
            throw new InvalidForm("The value input field is empty");
        }

        if(value.charAt(0) == '-'){
            throw new InvalidForm("'-' Not allowed in the amount field");
        }

        try {
            double intVal = Double.parseDouble(value);
        }catch (NumberFormatException e){
            throw new InvalidForm("Please provide the number");
        }
    }

    public void validateDate(){
        if(date == null){
            throw new InvalidForm("The date was not specified");
        }
    }
}
