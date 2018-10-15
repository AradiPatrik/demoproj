package hu.csakegysor.imcsb.controller;

import hu.csakegysor.imcsb.utils.L;
import javafx.fxml.Initializable;
import java.net.URL;
import java.util.ResourceBundle;

public class SearchController implements Initializable {
    private static final String SEARCH_CONTROLLER_INITIALIZED = "SearchController initialized";
    private static final String SEARCH_CONTROLLER_CONSTRUCTED = "SearchController constructed";

    public SearchController() {
        L.info(SEARCH_CONTROLLER_CONSTRUCTED);
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        L.info(SEARCH_CONTROLLER_INITIALIZED);
    }
}
