import com.jfoenix.controls.JFXListCell;
import de.jensd.fx.glyphs.fontawesome.FontAwesomeIconView;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;

import java.io.IOException;

public class NavigationListCell extends JFXListCell<NavigationItem> {
    @FXML
    HBox navigationItemContainer;
    @FXML
    Label name;
    @FXML
    FontAwesomeIconView iconView;

    private FXMLLoader fxmlLoader;

    @Override
    protected void updateItem(NavigationItem item, boolean empty) {
        if (item != null && !empty) {
            if (fxmlLoader == null) {
                fxmlLoader = new FXMLLoader(getClass().getResource("/navigation_cell.fxml"));
                fxmlLoader.setController(this);
                try {
                    fxmlLoader.load();
                } catch (IOException e) {
                    throw new RuntimeException(e.getMessage());
                }
            }
            iconView.setGlyphName(item.getGlyphName());
            name.setText(item.getName());
            setGraphic(navigationItemContainer);
        }
    }
}
