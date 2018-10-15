package service.navigation;

import javafx.scene.Node;
import javafx.scene.layout.Pane;
import view.viewfactory.ViewFactory;

import javax.inject.Inject;

public class NavigatorImpl implements Navigator {
    private static final String NAV_HOST_IS_NULL = "Nav host is null";
    private static final int ROOT_INDEX = 0;

    private Pane navHost;
    private final ViewFactory viewFactory;

    @Inject
    public NavigatorImpl(final ViewFactory viewFactory) {
        this.viewFactory = viewFactory;
    }

    @Override
    public void setNavHost(final Pane navHost) {
        this.navHost = navHost;
    }

    @Override
    public void navigateTo(final Destination destination) {
        switchDestination(viewFactory.get(destination.getPath()));
    }

    private void switchDestination(final Node n) {
        if (navHost == null) {
            throw new RuntimeException(NAV_HOST_IS_NULL);
        }
        if (!navHost.getChildren().isEmpty()) {
            navHost.getChildren().remove(ROOT_INDEX);
        }
        navHost.getChildren().add(n);
    }
}
