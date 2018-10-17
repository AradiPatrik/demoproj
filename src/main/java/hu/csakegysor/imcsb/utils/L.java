package hu.csakegysor.imcsb.utils;

import hu.csakegysor.imcsb.app.App;
import hu.csakegysor.imcsb.controller.MainController;
import hu.csakegysor.imcsb.view.viewfactory.ViewCacheImpl;
import hu.csakegysor.imcsb.view.viewfactory.ViewFactoryImpl;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.util.logging.LogManager;
import java.util.logging.Logger;

public class L {
    private static final Logger l = Logger.getLogger("hu.csakegysor.logger");
    private static final String COULD_NOT_CONFIGURE_LOGGER_FORMAT = "Could not configure logger!!! :: exception message: %s";

    private static String prefixMessageWithCallingClass(String m) {
        return String.format(
                "[%s] %s",
                Thread.currentThread().getStackTrace()[3].toString(),
                m
        );
    }

    /**
     * Use this method for logging out CRASH reasons
     * for example before converting checked exceptions to unchecked
     * @param m
     */
    public static void severe(String m) {
        l.severe(prefixMessageWithCallingClass(m));
    }

    /**
     * Use this method for logging out RECOVERABLE ERROR reasons
     * for example file not found, but could be created
     * @param m
     */
    public static void warning(String m) {
        l.warning(prefixMessageWithCallingClass(m));
    }

    /**
     * Use this method for logging out USEFUL INFORMATION,
     * that other developers might benefit from
     * @param m
     */
    public static void info(String m) {
        l.info(prefixMessageWithCallingClass(m));
    }

    /**
     * Use this method for logging out DETAILS OF AN OPERATION
     * for example, what data/parameter was received
     * @param m
     */
    public static void fine(String m) {
        l.fine(prefixMessageWithCallingClass(m));
    }

    /**
     * Use this method for logging out DEBUG INFORMATION
     * for example, code tracing
     * @param m
     */
    public static void finest(String m) {
        l.finest(prefixMessageWithCallingClass(m));
    }

    public static void setup() {
        URL resource = L.class.getClassLoader().getResource("logging.properties");
        try(InputStream stream = resource.openStream()) {
            LogManager.getLogManager().readConfiguration(stream);
        } catch (IOException e) {
            throw new RuntimeException(String.format(COULD_NOT_CONFIGURE_LOGGER_FORMAT, e.getMessage()));
        }
    }

}
