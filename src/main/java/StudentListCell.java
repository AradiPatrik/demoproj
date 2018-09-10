import com.jfoenix.controls.JFXListCell;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Label;
import javafx.scene.control.ListCell;
import javafx.scene.layout.HBox;

import java.io.IOException;

public class StudentListCell extends JFXListCell<Student> {
    @FXML
    private Label studentIdLabel;
    @FXML
    private Label studentNameLabel;
    @FXML
    private Label studentGenderLabel;
    @FXML
    private HBox studentDataHBox;

    private FXMLLoader fxmlLoader;

    @Override
    protected void updateItem(Student item, boolean empty) {
        if (item != null && !empty) {
            if (fxmlLoader == null) {
                fxmlLoader = new FXMLLoader(getClass().getResource("/student_cell.fxml"));
                fxmlLoader.setController(this);
                try {
                    fxmlLoader.load();
                } catch (IOException e) {
                    throw new RuntimeException(e.getMessage());
                }
            }
            studentNameLabel.setText(item.getName());
            studentIdLabel.setText(String.valueOf(item.getStudentId()));
            studentGenderLabel.setText(item.getGender().getStringValue());
            setGraphic(studentDataHBox);
        }
    }
}
