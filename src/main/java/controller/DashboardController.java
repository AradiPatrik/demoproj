package controller;

import javafx.fxml.Initializable;

import java.net.URL;
import java.util.ResourceBundle;

public class DashboardController implements Initializable {
    public DashboardController() {
        System.out.println("Dashboard controller constructed");
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        System.out.println("DASHBOARD Initialized");
    }
}
