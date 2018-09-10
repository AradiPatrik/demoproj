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

    private ObservableList<Student> students;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        provideData();
        studentListView.setItems(students);
        studentListView.setCellFactory(e -> new StudentListCell());
    }

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
    }
}
