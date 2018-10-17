package hu.csakegysor.imcsb.controller;

import de.saxsys.javafx.test.JfxRunner;
import hu.csakegysor.imcsb.service.navigation.Destination;
import hu.csakegysor.imcsb.service.navigation.Navigator;
import javafx.scene.control.Button;
import javafx.scene.layout.AnchorPane;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@RunWith(JfxRunner.class)
public class MainControllerTest {
    private MainController mainController;
    private Navigator mockNavigator;
    private Button mockNavDashboardButton;
    private Button mockNavSearchButton;
    private AnchorPane mockNavHost;

    @Before
    public void setup() {
        mockNavigator = mock(Navigator.class);
        mockNavDashboardButton = mock(Button.class);
        mockNavSearchButton = mock(Button.class);
        mockNavHost = mock(AnchorPane.class);
        mainController = new MainController(mockNavigator);
        mainController.buttonNavDashboard = mockNavDashboardButton;
        mainController.buttonNavSearch = mockNavSearchButton;
        mainController.navHost = mockNavHost;
    }

    @Test
    public void controllerWiredUpEverything() {
        mainController.initialize(null, null);
        verify(mockNavigator, times(1)).setNavHost(mockNavHost);
        verify(mockNavigator, times(1)).navigateTo(Destination.DASHBOARD);
        verify(mockNavigator, times(1)).setOnNavigationListener(any());
        verify(mockNavDashboardButton, times(1)).setOnAction(any());
        verify(mockNavSearchButton, times(1)).setOnAction(any());
    }
}