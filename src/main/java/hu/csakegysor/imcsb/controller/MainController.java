package hu.csakegysor.imcsb.controller;

import hu.csakegysor.imcsb.service.navigation.Destination;
import hu.csakegysor.imcsb.service.navigation.Navigator;
import hu.csakegysor.imcsb.utils.L;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.layout.AnchorPane;

import javax.inject.Inject;
import java.io.FileInputStream;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.logging.LogManager;

public class MainController implements Initializable {
    private static final String MAIN_CONTROLLER_CONSTRUCTED = "MainController constructed";
    private static final String MAIN_CONTROLLER_INITIALIZED = "MainController initialized";
    private static final int ROOT_INDEX = 0;

    @FXML
    public Button buttonNavDashboard;
    @FXML
    public Button buttonNavSearch;
    @FXML
    public AnchorPane navHost;
    private Navigator navigator;

    @Inject
    MainController(Navigator navigator) {
        L.info(MAIN_CONTROLLER_CONSTRUCTED);
        this.navigator = navigator;
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        L.info(MAIN_CONTROLLER_INITIALIZED);
        navigator.setNavHost(navHost);
        navigator.navigateTo(Destination.DASHBOARD);
        setNavHostChildToMachNavHostDimensions();
        navigator.setOnNavigationListener(e -> setNavHostChildToMachNavHostDimensions());
        buttonNavDashboard.setOnAction(e -> navigator.navigateTo(Destination.DASHBOARD));
        buttonNavSearch.setOnAction(e -> navigator.navigateTo(Destination.SEARCH));
    }



    private void setNavHostChildToMachNavHostDimensions() {
        AnchorPane.setBottomAnchor(navHost.getChildrenUnmodifiable().get(ROOT_INDEX), 0.0);
        AnchorPane.setTopAnchor(navHost.getChildrenUnmodifiable().get(ROOT_INDEX), 0.0);
        AnchorPane.setRightAnchor(navHost.getChildrenUnmodifiable().get(ROOT_INDEX), 0.0);
        AnchorPane.setLeftAnchor(navHost.getChildrenUnmodifiable().get(ROOT_INDEX), 0.0);
    }
}
