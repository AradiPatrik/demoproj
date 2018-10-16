package hu.csakegysor.imcsb.controller;

import hu.csakegysor.imcsb.service.navigation.Destination;
import hu.csakegysor.imcsb.service.navigation.Navigator;
import hu.csakegysor.imcsb.utils.L;
import javafx.application.Platform;
import javafx.beans.value.ObservableValue;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.Bounds;
import javafx.scene.control.Button;
import javafx.scene.layout.Pane;

import javax.inject.Inject;
import java.net.URL;
import java.util.ResourceBundle;

public class MainController implements Initializable {
    private static final String MAIN_CONTROLLER_CONSTRUCTED = "MainController constructed";
    private static final String MAIN_CONTROLLER_INITIALIZED = "MainController initialized";
    private static final int ROOT_INDEX = 0;

    @FXML
    public Button buttonNavDashboard;
    @FXML
    public Button buttonNavSearch;
    @FXML
    public Pane navHost;
    private Navigator navigator;

    @Inject
    public MainController(Navigator navigator) {
        L.info(MAIN_CONTROLLER_CONSTRUCTED);
        this.navigator = navigator;
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        L.info(MAIN_CONTROLLER_INITIALIZED);
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
