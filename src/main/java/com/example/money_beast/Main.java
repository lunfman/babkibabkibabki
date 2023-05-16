package com.example.money_beast;

import com.example.money_beast.components.HomePage;
import com.example.money_beast.components.SideBar;
import com.example.money_beast.components.StatsPage;
import com.example.money_beast.components.TransactionsPage;
import com.example.money_beast.wallet.Rahakott;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.layout.*;
import javafx.stage.Stage;

import java.io.FileNotFoundException;
import java.io.IOException;

public class Main extends Application {

    Scene scene;

    @Override
    public void start(Stage stage) throws IOException {
        stage.setTitle("MoneyBeast");

        Rahakott rahakott = new Rahakott();
        try {
            rahakott.loeFailist("src/data/andmed.txt");
        }catch (FileNotFoundException ignored){
            System.out.println("File not found -> new user");
        }


        BorderPane pl = new BorderPane();
        HomePage hp = new HomePage(rahakott);
        HBox homePage = hp.getHorizontalHomePage();

        StatsPage sp = new StatsPage(rahakott);
        HBox stats = sp.getHorizontalPieChar();

        HBox tr = new TransactionsPage(stage, rahakott, hp, sp).getHorizontalTransactionPage();


        VBox sideb = new SideBar(pl, homePage, tr, stats).getSideBar();



        pl.setLeft(sideb);
        pl.setCenter(homePage);
        scene = new Scene(pl, 800, 600);
        stage.setScene(scene);
        stage.show();

        stage.setOnCloseRequest(event -> {
            try {
                rahakott.salvestaFaili("src/data/andmed.txt");
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
            System.out.println("Salvesta");
        });
    }

    public static void main(String[] args) {
        launch();
    }
}