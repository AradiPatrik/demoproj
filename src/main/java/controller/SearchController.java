package controller;

import javafx.fxml.Initializable;
import java.net.URL;
import java.util.ResourceBundle;

public class SearchController implements Initializable {
    public SearchController() {
        System.out.println("Search controller constructed");
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        System.out.println("SEARCH Initialized");
    }
}
