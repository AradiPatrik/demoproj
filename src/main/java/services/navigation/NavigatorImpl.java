package services.navigation;

import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.layout.Pane;

import java.io.IOException;
import java.net.URL;

public class NavigatorImpl implements Navigator {
    private static final String UNSUPPORTED_DESTINATION = "Unsupported destination";
    private static final String DASHBOARD_FXML = "dashboard.fxml";
    private static final String SEARCH_FXML = "search.fxml";
    private static final String COULD_NOT_FOUND = "Could not found ";
    private static final int ROOT_INDEX = 0;
    final Pane navHost;

    public NavigatorImpl(final Pane navHost) {
        this.navHost = navHost;
        navHost.boundsInLocalProperty().addListener((observable, oldValue, newValue) -> {
            if (!navHost.getChildren().isEmpty()) {
                Pane pane = (Pane)navHost.getChildren().get(ROOT_INDEX);
                pane.setPrefWidth(newValue.getWidth());
                pane.setPrefHeight(newValue.getHeight());
            }
        });
    }

    @Override
    public void navigateTo(final Destination destination) {
        try {
            tryToNavigate(destination);
        } catch (IOException e) {
            throw new RuntimeException(e.getMessage());
        }
    }

    private void tryToNavigate(final Destination destination) throws IOException {
        switch (destination) {
            case DASHBOARD:
                URL dashboardPath = getClass().getClassLoader().getResource(DASHBOARD_FXML);
                if (dashboardPath == null) {
                    throw new RuntimeException(COULD_NOT_FOUND + DASHBOARD_FXML);
                }
                switchDestination(FXMLLoader.load(dashboardPath));
                break;
            case SEARCH:
                URL searchPath = getClass().getClassLoader().getResource(SEARCH_FXML);
                if (searchPath == null) {
                    throw new RuntimeException(COULD_NOT_FOUND + SEARCH_FXML);
                }
                switchDestination(FXMLLoader.load(searchPath));
                break;
            default:
                throw new RuntimeException(UNSUPPORTED_DESTINATION);
        }
    }

    private void switchDestination(final Node n) {
        if (navHost == null) {
            throw new RuntimeException("Nav host is null");
        }
        if(!navHost.getChildren().isEmpty()) {
            navHost.getChildren().remove(ROOT_INDEX);
        }
        navHost.getChildren().add(n);

    }
}
