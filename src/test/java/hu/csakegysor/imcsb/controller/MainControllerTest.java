package hu.csakegysor.imcsb.controller;

import hu.csakegysor.imcsb.service.navigation.Navigator;
import javafx.application.Platform;
import javafx.scene.control.Button;
import javafx.scene.layout.Pane;
import org.junit.Before;
import org.junit.Test;

import java.util.concurrent.Semaphore;

import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

public class MainControllerTest {

    Button mockNavButtonDashboard;
    Button mockNavButtonSearch;
    Pane mockNavHost;
    Navigator mockNavigator;
    MainController mainController;

    @Before
    public void setup() {
        mockNavigator = mock(Navigator.class);
        mockNavButtonDashboard = mock(Button.class);
        mockNavButtonSearch = mock(Button.class);
        mockNavHost = mock(Pane.class);
        mainController = new MainController(mockNavigator);
        mainController.buttonNavDashboard = mockNavButtonDashboard;
        mainController.buttonNavSearch = mockNavButtonSearch;
        mainController.navHost = mockNavHost;
    }

    @Test
    public void initialize() throws InterruptedException {
        mainController.initialize(null, null);
        waitForRunLater();
        verify(mockNavigator, times(1)).setNavHost(mockNavHost);
    }

    public static void waitForRunLater() throws InterruptedException {
        Semaphore semaphore = new Semaphore(0);
        Platform.runLater(() -> semaphore.release());
        semaphore.acquire();

    }
}