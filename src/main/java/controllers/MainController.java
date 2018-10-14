package controllers;

import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import services.navigation.Destination;
import services.navigation.Navigator;

import java.net.URL;
import java.util.ResourceBundle;

public class MainController implements Initializable {

    public Button buttonNavDashboard;
    public Button buttonNavSearch;
    private Navigator navigator;

    public void setNavigator(final Navigator navigator) {
        this.navigator = navigator;
        navigator.navigateTo(Destination.DASHBOARD);
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        buttonNavDashboard.setOnAction(e -> {
            navigator.navigateTo(Destination.DASHBOARD);
        });
        buttonNavSearch.setOnAction(e -> {
            navigator.navigateTo(Destination.SEARCH);
        });
    }
}
