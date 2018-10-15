package hu.csakegysor.imcsb.controller;

import hu.csakegysor.imcsb.utils.L;
import javafx.fxml.Initializable;

import java.net.URL;
import java.util.ResourceBundle;

public class DashboardController implements Initializable {
    private static final String DASHBOARD_CONTROLLER_INITIALIZED = "DashboardController initialized";
    private static final String DASHBOARD_CONTROLLER_CONSTRUCTED = "DashboardController constructed";

    public DashboardController() {
        L.info(DASHBOARD_CONTROLLER_CONSTRUCTED);
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        L.info(DASHBOARD_CONTROLLER_INITIALIZED);
    }
}
