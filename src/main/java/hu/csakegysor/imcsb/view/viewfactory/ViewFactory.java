package hu.csakegysor.imcsb.view.viewfactory;

import javafx.scene.Node;

public interface ViewFactory {
    Node get(String resourcePath);
}
