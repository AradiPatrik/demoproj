package hu.csakegysor.imcsb.di;

import com.google.gson.Gson;
import com.google.inject.AbstractModule;
import com.google.inject.Singleton;
import hu.csakegysor.imcsb.controller.DashboardController;
import hu.csakegysor.imcsb.controller.MainController;
import hu.csakegysor.imcsb.controller.SearchController;
import hu.csakegysor.imcsb.service.navigation.Navigator;
import hu.csakegysor.imcsb.service.navigation.NavigatorImpl;
import hu.csakegysor.imcsb.view.FXMLLoaderProvider;
import hu.csakegysor.imcsb.view.viewfactory.ViewCache;
import hu.csakegysor.imcsb.view.viewfactory.ViewCacheImpl;
import hu.csakegysor.imcsb.view.viewfactory.ViewFactory;
import hu.csakegysor.imcsb.view.viewfactory.ViewFactoryImpl;

public class MainModule extends AbstractModule {
    @Override
    protected void configure() {
        bind(MainController.class).in(Singleton.class);
        bind(DashboardController.class).in(Singleton.class);
        bind(SearchController.class).in(Singleton.class);
        bind(FXMLLoaderProvider.class);
        bind(ViewCache.class).to(ViewCacheImpl.class);
        bind(Gson.class);

        bind(Navigator.class).to(NavigatorImpl.class).in(Singleton.class);
        bind(ViewFactory.class).to(ViewFactoryImpl.class).in(Singleton.class);
    }
}
