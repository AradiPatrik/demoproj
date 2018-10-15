package view;

import com.google.inject.Inject;
import com.google.inject.Injector;
import com.google.inject.Provider;
import javafx.fxml.FXMLLoader;

public class FXMLLoaderProvider implements Provider<FXMLLoader> {
    private final Injector injector;

    @Inject
    public FXMLLoaderProvider(Injector injector) {
        this.injector = injector;
    }

    @Override
    public FXMLLoader get() {
        FXMLLoader fxmlLoader = new FXMLLoader();
        fxmlLoader.setControllerFactory(injector::getInstance);
        return fxmlLoader;
    }
}
