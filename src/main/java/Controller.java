import com.jfoenix.controls.JFXListView;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ListView;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

import java.net.URL;
import java.util.ResourceBundle;

public class Controller implements Initializable {
    @FXML
    private JFXListView<Student> studentListView;

    @FXML
    private JFXListView<NavigationItem> navigationListView;

    @FXML
    private ImageView imageView;

    private ObservableList<Student> students;
    private ObservableList<NavigationItem> navigationItems;

    private void provideData() {
        students = FXCollections.observableArrayList();
        students.addAll(
                new Student(1, "John Doe", Gender.MALE),
                new Student(2, "Jane Doe", Gender.FEMALE),
                new Student(3, "Donte Dunigan", Gender.MALE),
                new Student(4, "Gavin Genna", Gender.MALE),
                new Student(5, "Darin Dear", Gender.FEMALE),
                new Student(6, "Pura Petty", Gender.MALE),
                new Student(7, "Herma Hines", Gender.FEMALE)
        );

        navigationItems = FXCollections.observableArrayList();
        navigationItems.addAll(
                new NavigationItem("Dashboard", "FILE")
        );
    }

    private void loadNavigationDrawerImage() {
        try {
            Image image = new Image(getClass().getResource("books.jpeg").toExternalForm());
            imageView.setImage(image);
        } catch (Exception e) {
            new RuntimeException(e.getMessage());
        }
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        provideData();
      //  loadNavigationDrawerImage();
        studentListView.setItems(students);
        studentListView.setCellFactory(e -> new StudentListCell());
//        navigationListView.setItems(navigationItems);
  //      navigationListView.setCellFactory(e -> new NavigationListCell());
    }
}
