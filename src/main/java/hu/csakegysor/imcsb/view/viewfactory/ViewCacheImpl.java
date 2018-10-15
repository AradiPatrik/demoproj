package hu.csakegysor.imcsb.view.viewfactory;

import javafx.scene.Node;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

public class ViewCacheImpl implements ViewCache {
    private final Map<String, Node> nodeCache = new HashMap<>();

    @Override
    public void add(String key, Node view) {
        nodeCache.put(key, view);
    }

    @Override
    public Optional<Node> get(String key) {
        return nodeCache.containsKey(key) ? Optional.of(nodeCache.get(key)) : Optional.empty();
    }
}
