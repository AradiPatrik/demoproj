package hu.csakegysor.imcsb.service.navigation;

import hu.csakegysor.imcsb.view.viewfactory.ViewFactory;
import javafx.collections.ObservableList;
import javafx.scene.Node;
import javafx.scene.layout.Pane;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

public class NavigatorImplTest {
    private Navigator navigator;
    private ViewFactory mockViewFactory;
    private Pane mockPane;
    private ObservableList<Node> mockChildren;
    private Node mockNode;
    private Navigator.OnNavigationListener mockNavigationListener;

    @Before
    public void setup() {
        mockViewFactory = mock(ViewFactory.class);
        mockPane = mock(Pane.class);
        mockChildren = (ObservableList<Node>)mock(ObservableList.class);
        mockNode = mock(Node.class);
        mockNavigationListener = mock(Navigator.OnNavigationListener.class);
        navigator = new NavigatorImpl(mockViewFactory);
    }

    @Test
    public void testSetNavHost_ShouldWork() {
        navigator.setNavHost(mockPane);
        assertEquals(mockPane, navigator.getNavHost());
    }

    @Test
    public void navigateToShouldAddTheRequestedDestination_RemovingThePreviousIfPresent() {
        when(mockPane.getChildren()).thenReturn(mockChildren);
        when(mockViewFactory.get(any(String.class))).thenReturn(mockNode);
        when(mockChildren.isEmpty()).thenReturn(true).thenReturn(false);

        navigator.setOnNavigationListener(mockNavigationListener);
        navigator.setNavHost(mockPane);
        navigator.navigateTo(Destination.DASHBOARD);
        assertEquals(Destination.DASHBOARD, navigator.getCurrentDestination());
        navigator.navigateTo(Destination.SEARCH);
        assertEquals(Destination.SEARCH, navigator.getCurrentDestination());

        verify(mockChildren, times(1)).remove(0);
        verify(mockViewFactory, times(1)).get(Destination.DASHBOARD.getPath());
        verify(mockViewFactory, times(1)).get(Destination.SEARCH.getPath());
        verify(mockChildren, times(2)).add(any(Node.class));
        verify(mockNavigationListener, times(1)).onNavigation(Destination.DASHBOARD);
        verify(mockNavigationListener, times(1)).onNavigation(Destination.SEARCH);
    }
}