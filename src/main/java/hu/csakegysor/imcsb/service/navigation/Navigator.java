package hu.csakegysor.imcsb.service.navigation;

import javafx.scene.layout.Pane;

public interface Navigator {
    void navigateTo(final Destination destination);
    void setNavHost(final Pane navHost);
    Pane getNavHost();
}
