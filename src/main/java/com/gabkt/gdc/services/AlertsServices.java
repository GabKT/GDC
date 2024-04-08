package com.gabkt.gdc.services;

import javafx.scene.control.Alert;

public class AlertsServices {

    public static Alert createAlertError(String title, String content){
        Alert a = new Alert(Alert.AlertType.ERROR);
        a.setTitle(title);
        a.setHeaderText("");
        a.setContentText("Error: " + content);
        return a;
    }
}
