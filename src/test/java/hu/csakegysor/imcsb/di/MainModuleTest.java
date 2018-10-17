package hu.csakegysor.imcsb.di;

import com.google.gson.Gson;
import com.google.inject.Guice;
import com.google.inject.Injector;
import hu.csakegysor.imcsb.controller.DashboardController;
import hu.csakegysor.imcsb.controller.MainController;
import hu.csakegysor.imcsb.controller.SearchController;
import hu.csakegysor.imcsb.data.dao.UserDaoImpl;
import hu.csakegysor.imcsb.service.navigation.Navigator;
import hu.csakegysor.imcsb.view.FXMLLoaderProvider;
import hu.csakegysor.imcsb.view.viewfactory.ViewCache;
import hu.csakegysor.imcsb.view.viewfactory.ViewFactory;
import org.junit.Before;
import org.junit.Test;

public class MainModuleTest {

    private Injector injector;

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
    public void daosShouldHaveBindings() {
        injector.getInstance(UserDaoImpl.class);
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

    @Test
    public void thirdPartyObjectsShouldHaveBindings() {
        injector.getInstance(Gson.class);
    }
}