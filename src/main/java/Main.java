import controllers.MainController;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import services.navigation.NavigatorImpl;

public class Main extends Application {

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        FXMLLoader loader = new FXMLLoader();
        Parent root = loader.load(getClass().getResource("main.fxml").openStream());
        Pane navHost = (Pane)root.lookup("#navHost");
        primaryStage.setTitle("IMCSB");
        primaryStage.setScene(new Scene(root, 600, 400));
        primaryStage.setResizable(true);
        primaryStage.show();

        MainController controller = loader.getController();
        controller.setNavigator(new NavigatorImpl(navHost));
    }
}
