package hu.csakegysor.imcsb.view;

import com.google.inject.Injector;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;

public class FXMLLoaderProviderTest {
    private FXMLLoaderProvider provider;
    private Injector injector;

    @Before
    public void setup() {
        injector = mock(Injector.class);
        provider = new FXMLLoaderProvider(injector);
    }

    @Test
    public void testGetShouldReturnAnFXMLLoader_ConfiguredWithTheInjector() {
        assertNotNull(provider.get().getControllerFactory());
    }
}