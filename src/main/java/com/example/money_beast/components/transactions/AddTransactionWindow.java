package com.example.money_beast.components.transactions;

import com.example.money_beast.components.TransactionsPage;
import com.example.money_beast.components.styles.InputGroup;
import com.example.money_beast.controller.FormValidation;
import com.example.money_beast.controller.TransactionController;
import com.example.money_beast.data.DefaultCategories;
import com.example.money_beast.exceptions.InvalidForm;
import com.example.money_beast.wallet.Tehing;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

import java.time.LocalDate;

public class AddTransactionWindow {
    int innerContainerWidth = 150;
    Stage modal;
    boolean incomeCat;

    String  category, addText;
    LocalDate date;
    double value;

    ComboBox<String> comboBox;

    Label exceptionInfo;

    VBox modalContainer;
    HBox modalMainContainer;

    TransactionController tc;

    InputGroup transactionInput;
    DatePicker datePicker;

    Stage stage;

    Transaction transaction;

    TransactionsPage tp;

    Button deleteButton;

    /**
     * Creates pop up window when user click on "+" button -> add new transaction
     * @param tc TransactionController
     * @param stage Main stage
     * @param tp reference to TransactionsPage
     */
    public AddTransactionWindow(TransactionController tc, Stage stage, TransactionsPage tp) {
        this.category = "";
        this.value = 0;
        this.addText = "Add";
        this.date = null;
        this.tc = tc;
        this.stage = stage;
        this.tp = tp;
        this.incomeCat = true;

    }

    /**
     * Creates pop up window, when user double-clicks on the transaction
     * This constructor allows to pre-fill the form
     * @param transaction ref to clicked transaction
     * @param tc ref to transaction controller
     */
    public AddTransactionWindow(Transaction transaction, TransactionController tc) {
        this.transaction = transaction;
        this.category = transaction.getCategoryName();
        this.value = transaction.getValue();
        this.addText = "Update";
        this.date = transaction.getDate();
        this.stage = transaction.getStage();
        this.tc = tc;
        this.incomeCat = transaction.isIncome();

    }

    public void  setStyle(){
        modalContainer.setPadding(new Insets(10, 20, 10, 20));
        modalContainer.setSpacing(10);
    }

    public HBox createButtonGroup(Button btn1, Button btn2){
        HBox buttonGroup = new HBox();
        buttonGroup.getChildren().addAll(btn1, btn2);
        buttonGroup.setAlignment(Pos.CENTER);
        return buttonGroup;
    }

    /**
     * Layout of two elements
     * @param el1 Java fx Node
     * @param el2 Java fx Node
     * @return Hbox
     */
    public HBox createTwoElementHorizontalContainer(Node el1, Node el2){
        HBox hBox = new HBox();

        HBox leftContainer = new HBox();
        leftContainer.getChildren().add(el1);
        leftContainer.setPrefWidth(innerContainerWidth);
        leftContainer.setAlignment(Pos.CENTER_LEFT);

        HBox righContainer = new HBox();
        righContainer.setPrefWidth(innerContainerWidth);
        righContainer.getChildren().add(el2);
        righContainer.setAlignment(Pos.CENTER_RIGHT);

        hBox.getChildren().addAll(leftContainer, righContainer);
        return hBox;
    }

    public void createIncomeComboBox(){
        comboBox.getItems().clear();
        comboBox.getItems().addAll(DefaultCategories.getIncomeCategories());
    }

    public void createOutcomeComboBox(){
        comboBox.getItems().clear();
        comboBox.getItems().addAll(DefaultCategories.getOutcomeCategories());
    }

    public HBox createComboBox(){
        comboBox = new ComboBox<>();
        comboBox.setPrefWidth(150);
        Label categoryLabel = new Label("Category: ");

        // if category not empty pre-fill value, used when second contructor used
        if(!category.equals("")){
            comboBox.setValue(category);
        }

        // update combo-box list based on current state income or outcome pressed
        if(incomeCat){
            createIncomeComboBox();
        } else {
            createOutcomeComboBox();
        }

        return createTwoElementHorizontalContainer(categoryLabel, comboBox);
    }

    public boolean validateForm(){
        String valueInput = transactionInput.getInput().getText();
        String category = comboBox.getValue();
        LocalDate date = datePicker.getValue();

        try {
            FormValidation fv = new FormValidation(category, valueInput, date);
            fv.validate();
        }catch (InvalidForm e){
            // display error message to the user
            exceptionInfo.setText(e.getMessage());
            return false;
        }

        return true;

    }

    /**
     * Method check, which type of action currently required
     * Add if user wants to add new transaction  or update transaction
     */
    public void saveTransaction(){
        if(addText.equals("Add")){
            addTransaction();
        }else {
            updateTransaction();
        }
    }

    /**
     * Creates new transaction from user inputs and adds to TransactionController
     */
    public void createTransaction(){
        Double valueInput = Double.parseDouble(transactionInput.getInput().getText());
        String category = comboBox.getValue();
        LocalDate date = datePicker.getValue();
        Tehing tehing = new Tehing(valueInput, category, incomeCat, date);
        Transaction transaction = new Transaction(valueInput, category, incomeCat, stage, date, tc, tp, tehing);

        tc.addTransaction(date, transaction);
    }

    /**
     * validates form and creates new transaction
     */
    public void addTransaction(){
        if(!validateForm()){
            return;
        }
        createTransaction();
        modal.close();
    }

    /**
     * validates form and updates old transaction
     */
    public void updateTransaction(){
        if(!validateForm()){
            return;
        }

        Double valueInput = Double.parseDouble(transactionInput.getInput().getText());
        String category = comboBox.getValue();
        LocalDate updatedDate = datePicker.getValue();
        transaction.setDate(updatedDate);
        transaction.setCategoryName(category);
        transaction.setIncome(incomeCat);
        transaction.setValue(valueInput);
        tc.updateTransaction(date, transaction);
        modal.close();
    }

    /**
     * deletes transaction
     */
    public void deleteTransaction(){
        tc.deleteTransaction(date, transaction);
        modal.close();
    }


    /**
     * Creates layout of the modal
     */
    public void createLayout(){
        // if user updates transaction -> render delete button
        if(addText.equals("Update")){
            deleteButton = new Button("Delete");
            deleteButton.setOnAction(event -> deleteTransaction());
        }

        exceptionInfo = new Label();
        exceptionInfo.setStyle("-fx-text-fill: red;");


        Button income = new Button("Income");
        income.setOnAction(event -> toogleCategory("income"));


        Button outcome = new Button("Outcome");
        outcome.setOnAction(event -> toogleCategory("outcome"));

        HBox incOutGroup = createButtonGroup(income, outcome);

        HBox categoryBox = createComboBox();

        transactionInput = new InputGroup("Amount: ");
        HBox transactionValue = transactionInput.getGroup();
        // if upates transaction -> set transactions value
        if (value != 0){
            transactionInput.getInput().setText(String.valueOf(value));
        }

        modalMainContainer = new HBox();
        modalMainContainer.setAlignment(Pos.CENTER);
        modalContainer = new VBox();
        modalContainer.setAlignment(Pos.CENTER);


        Label dateLabel = new Label("Date: ");
        datePicker = new DatePicker();
        // if upates transaction -> set date to current value
        if(!(date == null)){
            datePicker.setValue(date);
        }
        HBox dateBox = createTwoElementHorizontalContainer(dateLabel, datePicker);

        Button closeBtn = new Button("Cancel");
        closeBtn.setOnAction(ev -> modal.close());

        // addText =  Update or Add
        Button addBtn = new Button(addText);

        addBtn.setOnAction(event -> saveTransaction());

        HBox buttonGroup = createButtonGroup(closeBtn, addBtn);

        setStyle();

        // adds delete button to layout if user updates transaction
        if(addText.equals("Update")){
            modalContainer.getChildren().addAll(exceptionInfo,deleteButton,incOutGroup, categoryBox,transactionValue,dateBox, buttonGroup);

        }else {
            modalContainer.getChildren().addAll(exceptionInfo,incOutGroup, categoryBox,transactionValue,dateBox, buttonGroup);

        }
        modalMainContainer.getChildren().add(modalContainer);
    }

    // changes state of category
    public void toogleCategory(String category){
        if(category.equals("outcome") && incomeCat){
            incomeCat = false;
            createOutcomeComboBox();
        }else if(category.equals("income") && !incomeCat){
            incomeCat = true;
            createIncomeComboBox();
        }
    }

    /**
     * Displays modal window
     */
    public void showAddTransaction(){
        // Create a new stage for the pop-up window
        modal = new Stage();
        modal.initOwner(stage);
        modal.initModality(Modality.APPLICATION_MODAL);
        modal.initStyle(StageStyle.UNDECORATED);
        createLayout();


        Scene popupScene = new Scene(modalMainContainer, 300, 300);
        modal.setScene(popupScene);

        // CHAT GPT !!!!!!
        // This part of the code from chat GPT! (calculates where the pop up window should be placed (in the center)
        double parentX = stage.getScene().getWindow().getX();
        double parentY = stage.getScene().getWindow().getY();
        // calculate X and Y coordinates of popup window
        double popupX = parentX + (stage.getWidth() / 2) - (popupScene.getWidth() / 2);
        double popupY = parentY + (stage.getHeight() / 2) - (popupScene.getHeight() / 2);

        modal.setX(popupX);
        modal.setY(popupY);

        // Show the pop-up window
        modal.showAndWait();
    }

}
