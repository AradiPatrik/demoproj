package hu.csakegysor.imcsb.service.navigation;

import hu.csakegysor.imcsb.view.viewfactory.ViewFactory;
import javafx.scene.Node;
import javafx.scene.layout.Pane;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

public class NavigatorImplTest {
    private Navigator navigator;
    private ViewFactory mockViewFactory;
    private Pane mockPane;

    @Before
    public void setup() {
        mockViewFactory = mock(ViewFactory.class);
        mockPane = mock(Pane.class);
        navigator = new NavigatorImpl(mockViewFactory);
    }

    @Test
    public void testSetNavHost_ShouldWork() {
        navigator.setNavHost(mockPane);
        assertEquals(mockPane, navigator.getNavHost());
    }

    @Test
    public void navigateTo() {
        navigator.setNavHost(mockPane);
        navigator.navigateTo(Destination.DASHBOARD);
        verify(mockPane.getChildren(), times(1)).add(any(Node.class));
    }
}