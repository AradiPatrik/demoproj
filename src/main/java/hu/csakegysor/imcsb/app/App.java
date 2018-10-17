package hu.csakegysor.imcsb.app;

import com.google.inject.Guice;
import com.google.inject.Injector;
import hu.csakegysor.imcsb.di.MainModule;
import hu.csakegysor.imcsb.utils.L;
import hu.csakegysor.imcsb.view.viewfactory.ViewFactory;
import javafx.application.Application;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class App extends Application {
    private static final String MAIN_FXML = "main.fxml";
    private static final String APP_NAME = "IMCSB";
    private static final int WIDTH = 600;
    private static final int HEIGHT = 400;

    @Override
    public void start(Stage primaryStage) {
        Injector injector = Guice.createInjector(new MainModule());
        setupLogging();
        setupStage(primaryStage, injector);
    }

    private void setupLogging() {
        L.setup();
    }

    private void setupStage(Stage primaryStage, Injector injector) {
        Parent root = (Parent) injector.getInstance(ViewFactory.class).get(MAIN_FXML);
        primaryStage.setTitle(APP_NAME);
        primaryStage.setScene(new Scene(root, WIDTH, HEIGHT));
        primaryStage.setResizable(true);
        primaryStage.show();
    }

}

