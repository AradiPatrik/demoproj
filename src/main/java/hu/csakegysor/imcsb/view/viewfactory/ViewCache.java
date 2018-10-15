package hu.csakegysor.imcsb.view.viewfactory;
import javafx.scene.Node;
import java.util.Optional;

public interface ViewCache {
    void add(String key, Node view);
    Optional<Node> get(String key);
}
