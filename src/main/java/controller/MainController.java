package controller;

import javafx.application.Platform;
import javafx.beans.value.ObservableValue;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.Bounds;
import javafx.scene.control.Button;
import javafx.scene.layout.Pane;
import service.navigation.Destination;
import service.navigation.Navigator;

import javax.inject.Inject;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;

public class MainController implements Initializable {
    private static final String MAIN_CONTROLLER_CONSTRUCTED = "Main Controller constructed";
    private static final int ROOT_INDEX = 0;

    @FXML
    private Button buttonNavDashboard;
    @FXML
    private Button buttonNavSearch;
    @FXML
    private Pane navHost;
    private Navigator navigator;

    @Inject
    public MainController(Navigator navigator, Logger logger) {
        logger.log(Level.FINE, MAIN_CONTROLLER_CONSTRUCTED);
        this.navigator = navigator;
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        navigator.setNavHost(navHost);
        navHost.boundsInLocalProperty().addListener(this::resizeDestinationHolder);
        Platform.runLater(() -> navigator.navigateTo(Destination.DASHBOARD));
        buttonNavDashboard.setOnAction(e -> navigator.navigateTo(Destination.DASHBOARD));
        buttonNavSearch.setOnAction(e -> navigator.navigateTo(Destination.SEARCH));
    }

    private void resizeDestinationHolder(ObservableValue<? extends Bounds> bounds, Bounds oldValue, Bounds newValue) {
        if (!this.navHost.getChildren().isEmpty()) {
            Pane pane = (Pane) this.navHost.getChildren().get(ROOT_INDEX);
            pane.setPrefWidth(newValue.getWidth());
            pane.setPrefHeight(newValue.getHeight());
        }
    }
}
