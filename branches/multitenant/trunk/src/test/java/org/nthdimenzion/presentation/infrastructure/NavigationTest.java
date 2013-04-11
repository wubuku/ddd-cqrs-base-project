package org.nthdimenzion.presentation.infrastructure;

import com.google.common.collect.ImmutableMap;
import org.junit.Test;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;
import org.zkoss.zk.ui.Execution;

import java.util.Collections;

import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

@ActiveProfiles({"standalone"})
public class NavigationTest {

    @Test
    public void testRedirectUrl() {
        Execution execution = mock(Execution.class);
        ResourceBundleViewResolver resolver = mock(ResourceBundleViewResolver.class);
        Navigation navigation = new Navigation(resolver);
        when(resolver.resolveViewName("test")).thenReturn("test.zul");

        String url = navigation.buildUrl("test", Collections.<String, Object>emptyMap());

        assertTrue("test.zul".equals(url));
    }


    @Test
    public void testRedirectUrlWithParams() {
        Execution execution = mock(Execution.class);
        ResourceBundleViewResolver resolver = mock(ResourceBundleViewResolver.class);
        Navigation navigation = new Navigation(resolver);
        when(resolver.resolveViewName("test")).thenReturn("test.zul");

        String url = navigation.buildUrl("test", ImmutableMap.of("id", "2", "name", "sud"));

        assertTrue("test.zul?id=2&name=sud".equals(url));
    }
}

