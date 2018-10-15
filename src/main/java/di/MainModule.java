package di;

import com.google.inject.AbstractModule;
import com.google.inject.Singleton;
import controller.DashboardController;
import controller.MainController;
import controller.SearchController;
import service.navigation.Navigator;
import service.navigation.NavigatorImpl;
import view.FXMLLoaderProvider;
import view.viewfactory.ViewCache;
import view.viewfactory.ViewCacheImpl;
import view.viewfactory.ViewFactory;
import view.viewfactory.ViewFactoryImpl;

public class MainModule extends AbstractModule {
    @Override
    protected void configure() {
        bind(MainController.class).in(Singleton.class);
        bind(DashboardController.class).in(Singleton.class);
        bind(SearchController.class).in(Singleton.class);
        bind(FXMLLoaderProvider.class);
        bind(ViewCache.class).to(ViewCacheImpl.class);

        bind(Navigator.class).to(NavigatorImpl.class).in(Singleton.class);
        bind(ViewFactory.class).to(ViewFactoryImpl.class).in(Singleton.class);
    }
}
