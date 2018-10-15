package view.viewfactory;

import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import view.FXMLLoaderProvider;

import javax.inject.Inject;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.util.logging.Level;
import java.util.logging.Logger;

public class ViewFactoryImpl implements ViewFactory {
    private static final String PATH_DOES_NOT_EXIST_FORMAT = "Path does not exist %s";
    private static final String STREAM_READ_FAILED_FORMAT = "Something went wrong with stream reading of URL: %s\nException message: %s";
    private static final String LOAD_STARTED = "LOAD STARTED";
    private static final String LOADED_FROM_CACHE = "LOADED FROM CACHE";
    private final FXMLLoaderProvider loaderProvider;
    private final Logger logger;
    private final ViewCache viewCache;

    @Inject
    public ViewFactoryImpl(
            final FXMLLoaderProvider loaderProvider,
            final ViewCache viewCache,
            final Logger logger) {
        this.loaderProvider = loaderProvider;
        this.viewCache = viewCache;
        this.logger = logger;
    }

    public Node get(String resourcePath) {
        logger.log(Level.FINE, LOAD_STARTED);
        return viewCache.get(resourcePath).orElseGet(() -> loadNodeAndAddToCache(resourcePath));
    }

    private Node loadNodeAndAddToCache(String resourcePath) {
        logger.log(Level.FINE, LOADED_FROM_CACHE);
        Node node = loadNodeFromFXML(getUrlFromString(resourcePath));
        viewCache.add(resourcePath, node);
        return node;
    }

    private Node loadNodeFromFXML(URL resource) {
        try (InputStream resourceStream = resource.openStream()) {
            return loadFXML(resourceStream);
        } catch (IOException cause) {
            throw new RuntimeException(
                    String.format(STREAM_READ_FAILED_FORMAT, resource.toString(), cause.getMessage())
            );
        }
    }

    private URL getUrlFromString(String resourcePath) {
        URL resource = getClass().getClassLoader().getResource(resourcePath);
        if (resource == null) {
            throw new RuntimeException(String.format(PATH_DOES_NOT_EXIST_FORMAT, resourcePath));
        }
        return resource;
    }

    private Node loadFXML(InputStream resourceStream) throws IOException {
        FXMLLoader loader = loaderProvider.get();
        return loader.load(resourceStream);
    }
}
