package de.nazaruk.routes.service.impl;

import com.google.common.collect.Sets;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import java.io.IOException;
import java.util.Map;
import java.util.Set;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class BusRoutesConfigurationTest {

    @Mock
    private FileSource fileSource;

    @InjectMocks
    private BusRoutesConfiguration busRoutesConfiguration;

    @Test
    public void loadRoutes() throws IOException {
        when(fileSource.getFileName()).thenReturn("data/example");
        busRoutesConfiguration.loadDirectRoutes();
        Map<Integer, Set<Integer>> busRoutes = busRoutesConfiguration.getBusRoutes();

        assertEquals(3, busRoutes.size());
        assertEquals(busRoutes.get(0), Sets.newHashSet(0, 1, 2, 3, 4));
        assertEquals(busRoutes.get(1), Sets.newHashSet(3, 1, 6, 5));
        assertEquals(busRoutes.get(2), Sets.newHashSet(0, 6, 4));
    }

}
