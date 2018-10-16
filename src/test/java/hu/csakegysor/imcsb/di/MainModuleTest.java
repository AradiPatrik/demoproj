package hu.csakegysor.imcsb.di;

import com.google.inject.Guice;
import com.google.inject.Injector;
import hu.csakegysor.imcsb.controller.DashboardController;
import hu.csakegysor.imcsb.controller.MainController;
import hu.csakegysor.imcsb.controller.SearchController;
import hu.csakegysor.imcsb.service.navigation.Navigator;
import hu.csakegysor.imcsb.view.FXMLLoaderProvider;
import hu.csakegysor.imcsb.view.viewfactory.ViewCache;
import hu.csakegysor.imcsb.view.viewfactory.ViewFactory;
import javafx.fxml.FXMLLoader;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class MainModuleTest {

    Injector injector;

    @Before
    public void setup() {
        injector = Guice.createInjector(new MainModule());
    }

    @Test
    public void testControllersShouldHaveBindings() {
        injector.getInstance(DashboardController.class);
        injector.getInstance(MainController.class);
        injector.getInstance(SearchController.class);
    }

    @Test
    public void servicesShouldHaveBindings() {
        injector.getInstance(Navigator.class);
    }

    @Test
    public void viewsShouldHaveBindings() {
        injector.getInstance(ViewCache.class);
        injector.getInstance(ViewFactory.class);
    }

    @Test
    public void viewUtilitiesShouldHaveBindings() {
        injector.getInstance(FXMLLoaderProvider.class);
    }
}