module com.example.money_beast {
    requires javafx.controls;
    requires javafx.fxml;


    opens com.example.money_beast to javafx.fxml;
    exports com.example.money_beast;
}