package hu.csakegysor.imcsb.service.navigation;

import javafx.scene.layout.Pane;

import java.util.Optional;

public interface Navigator {
    interface OnNavigationListener {
        void onNavigation(final Destination navigatedDestination);
    }
    void navigateTo(final Destination destination);
    void setNavHost(final Pane navHost);
    Pane getNavHost();
    Destination getCurrentDestination();
    void setOnNavigationListener(OnNavigationListener listener);
    OnNavigationListener getOnNavigationListener();
}
