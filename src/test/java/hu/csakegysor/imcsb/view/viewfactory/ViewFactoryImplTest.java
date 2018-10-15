package hu.csakegysor.imcsb.view.viewfactory;

import hu.csakegysor.imcsb.view.FXMLLoaderProvider;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import org.junit.Before;
import org.junit.Test;

import java.io.IOException;
import java.io.InputStream;
import java.util.Optional;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;
import static org.junit.Assert.*;

public class ViewFactoryImplTest {
    private static final String TEST_FXML = "fxml/test.fxml";
    private static final String NON_EXISTENT_FXML = "non_existent_fxml";
    private ViewFactory viewFactory;
    private FXMLLoaderProvider mockLoaderProvider;
    private ViewCache mockViewCache;
    private Node mockNode;
    private FXMLLoader mockLoader;

    @Before
    public void setup() {
        mockLoaderProvider = mock(FXMLLoaderProvider.class);
        mockViewCache = mock(ViewCache.class);
        mockLoader = mock(FXMLLoader.class);
        mockNode = mock(Node.class);
        viewFactory = new ViewFactoryImpl(mockLoaderProvider, mockViewCache);
    }

    @Test
    public void testGettingAnExistingFxmlFile_ShouldReturnTheNode_LoadedByTheFXMLLoader() throws IOException {
        when(mockLoaderProvider.get()).thenReturn(mockLoader);
        when(mockLoader.load(any(InputStream.class))).thenReturn(mockNode);
        when(mockViewCache.get(TEST_FXML)).thenReturn(Optional.empty());
        assertEquals(mockNode, viewFactory.get(TEST_FXML));
    }

    @Test
    public void testWhenAskedForACachedValue_ViewFactoryShouldReturnIt() {
        when(mockViewCache.get(TEST_FXML)).thenReturn(Optional.of(mockNode));
        assertEquals(mockNode, viewFactory.get(TEST_FXML));
    }

    @Test(expected = RuntimeException.class)
    public void testWhenAskedForANonExistentResource_AppShouldCrash() {
        when(mockViewCache.get(NON_EXISTENT_FXML)).thenReturn(Optional.empty());
        viewFactory.get(NON_EXISTENT_FXML);
    }

    @Test(expected = RuntimeException.class)
    public void testWhenFxmlLoadingFails_AppShouldCrash() throws IOException {
        when(mockLoaderProvider.get()).thenReturn(mockLoader);
        when(mockViewCache.get(TEST_FXML)).thenReturn(Optional.empty());
        when(mockLoader.load(any(InputStream.class))).thenThrow(IOException.class);
        viewFactory.get(TEST_FXML);
    }
}