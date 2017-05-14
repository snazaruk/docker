package de.nazaruk.routes.service.impl;

import com.google.common.collect.Sets;
import de.nazaruk.routes.service.RoutesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Map;
import java.util.Set;

@Service
public class RoutesServiceImpl implements RoutesService {

    @Autowired
    private BusRoutesConfiguration busRoutesConfiguration;

    @Override
    public boolean isDirectRouteAvailable(int dep_sid, int arr_sid) {
        if (dep_sid == arr_sid) {
            return false;
        }

        Map<Integer, Set<Integer>> routes = busRoutesConfiguration.getBusRoutes();
        HashSet<Integer> expectedStationIds = Sets.newHashSet(dep_sid, arr_sid);

        return routes.values().stream().anyMatch(e -> e.containsAll(expectedStationIds));
    }
}
