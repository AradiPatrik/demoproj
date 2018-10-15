package hu.csakegysor.imcsb.view.viewfactory;

import hu.csakegysor.imcsb.utils.L;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import hu.csakegysor.imcsb.view.FXMLLoaderProvider;

import javax.inject.Inject;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;

public class ViewFactoryImpl implements ViewFactory {
    private static final String PATH_DOES_NOT_EXIST_FORMAT = "Path does not exist %s";
    private static final String STREAM_READ_FAILED_FORMAT = "Something went wrong with stream reading of URL: %s\nException message: %s";
    private static final String LOADING_FROM_FXML_FORMAT = "LOADING_FROM_FXML %s";
    private final FXMLLoaderProvider loaderProvider;
    private final ViewCache viewCache;

    @Inject
    public ViewFactoryImpl(
            final FXMLLoaderProvider loaderProvider,
            final ViewCache viewCache) {
        this.loaderProvider = loaderProvider;
        this.viewCache = viewCache;
    }

    public Node get(String resourcePath) {
        return viewCache.get(resourcePath).orElseGet(() ->
                loadNodeAndAddToCache(resourcePath));
    }

    private Node loadNodeAndAddToCache(String resourcePath) {
        L.fine(String.format(LOADING_FROM_FXML_FORMAT, resourcePath));
        Node node = loadNodeFromFXML(getUrlFromString(resourcePath));
        viewCache.add(resourcePath, node);
        return node;
    }

    private Node loadNodeFromFXML(URL resource) {
        try (InputStream resourceStream = resource.openStream()) {
            return loadFXML(resourceStream);
        } catch (IOException cause) {
            final String errorMessage = String.format(STREAM_READ_FAILED_FORMAT, resource.toString(), cause.getMessage());
            L.severe(errorMessage);
            throw new RuntimeException(errorMessage);
        }
    }

    private URL getUrlFromString(String resourcePath) {
        URL resource = getClass().getClassLoader().getResource(resourcePath);
        if (resource == null) {
            final String errorMessage = String.format(PATH_DOES_NOT_EXIST_FORMAT, resourcePath);
            L.severe(errorMessage);
            throw new RuntimeException(errorMessage);
        }
        return resource;
    }

    private Node loadFXML(InputStream resourceStream) throws IOException {
        FXMLLoader loader = loaderProvider.get();
        return loader.load(resourceStream);
    }
}
