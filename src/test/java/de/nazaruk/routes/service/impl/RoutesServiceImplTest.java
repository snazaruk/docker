package de.nazaruk.routes.service.impl;

import com.google.common.collect.Sets;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class RoutesServiceImplTest {

    private final Map<Integer, Set<Integer>> busRoutes;

    {
        busRoutes = new HashMap<>();
        busRoutes.put(0, Sets.newHashSet(0, 1, 2, 3, 4));
        busRoutes.put(1, Sets.newHashSet(3, 1, 6, 5));
        busRoutes.put(2, Sets.newHashSet(0, 6, 4));
    }

    @Mock
    private BusRoutesConfiguration busRoutesConfiguration;

    @InjectMocks
    private RoutesServiceImpl routesService;

    @Before
    public void setUp() {
        when(busRoutesConfiguration.getBusRoutes()).thenReturn(busRoutes);
    }

    @Test
    public void directRoutes() {
        assertTrue(routesService.isDirectRouteAvailable(0, 1));
        assertTrue(routesService.isDirectRouteAvailable(0, 2));
        assertTrue(routesService.isDirectRouteAvailable(3, 5));
        assertTrue(routesService.isDirectRouteAvailable(6, 4));
    }

    @Test
    public void notDirectRoutes() {
        assertFalse(routesService.isDirectRouteAvailable(1, 1));
        assertFalse(routesService.isDirectRouteAvailable(1, 10));
        assertFalse(routesService.isDirectRouteAvailable(4, 5));
        assertFalse(routesService.isDirectRouteAvailable(23, 31));
    }

}
