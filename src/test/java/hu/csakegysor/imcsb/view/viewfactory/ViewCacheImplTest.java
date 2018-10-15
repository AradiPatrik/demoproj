package hu.csakegysor.imcsb.view.viewfactory;

import javafx.scene.Node;
import org.junit.Before;
import org.junit.Test;

import java.util.Optional;

import static org.mockito.Mockito.*;

import static org.junit.Assert.*;

public class ViewCacheImplTest {
    public static final String UNIQUE_KEY = "UNIQUE_KEY";
    private ViewCache viewCache;

    @Before
    public void setup() {
        viewCache = new ViewCacheImpl();
    }

    @Test
    public void testAfterAddingAViewToTheCache_ItWillReturnTheSameNodeWhenQueriedFor() {
        Node mockNode = mock(Node.class);
        viewCache.add(UNIQUE_KEY, mockNode);
        assertTrue(viewCache.get(UNIQUE_KEY).isPresent());
        assertEquals(mockNode, viewCache.get(UNIQUE_KEY).get());
    }

    @Test
    public void testQueryForANonExistentKey_WillReturnNone() {
        assertEquals(Optional.empty(), viewCache.get(UNIQUE_KEY));
    }
}