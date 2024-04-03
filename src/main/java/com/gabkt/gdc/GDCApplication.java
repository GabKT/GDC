package com.gabkt.gdc;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class GDCApplication extends Application {
    @Override
    public void start(Stage stage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(GDCApplication.class.getResource("View.fxml"));
        Scene scene = new Scene(fxmlLoader.load());
        stage.setTitle("GCD v1.0");
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {
        launch();
    }
}