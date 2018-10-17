package hu.csakegysor.imcsb.service.navigation;

import javafx.scene.Node;
import javafx.scene.layout.Pane;
import hu.csakegysor.imcsb.view.viewfactory.ViewFactory;

import javax.inject.Inject;
import java.util.Optional;

public class NavigatorImpl implements Navigator {
    private static final String NAV_HOST_IS_NULL = "Nav host is null";
    private static final int ROOT_INDEX = 0;

    private Pane navHost;
    private final ViewFactory viewFactory;
    private Destination currentDestination;
    private OnNavigationListener listener;

    @Inject
    NavigatorImpl(final ViewFactory viewFactory) {
        this.viewFactory = viewFactory;
    }

    @Override
    public void setNavHost(final Pane navHost) {
        this.navHost = navHost;
    }

    @Override
    public Pane getNavHost() {
        return this.navHost;
    }

    @Override
    public Destination getCurrentDestination() {
        return this.currentDestination;
    }

    @Override
    public void setOnNavigationListener(OnNavigationListener listener) {
        this.listener = listener;
    }

    @Override
    public OnNavigationListener getOnNavigationListener() {
        return listener;
    }

    @Override
    public void navigateTo(final Destination destination) {
        currentDestination = destination;
        switchDestination(viewFactory.get(destination.getPath()));
        if (listener != null) {
            listener.onNavigation(destination);
        }
    }

    private void switchDestination(final Node n) {
        if (!navHost.getChildren().isEmpty()) {
            navHost.getChildren().remove(ROOT_INDEX);
        }
        navHost.getChildren().add(n);
    }
}
