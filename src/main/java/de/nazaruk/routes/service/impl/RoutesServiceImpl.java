package de.nazaruk.routes.service.impl;

import de.nazaruk.routes.service.RoutesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Map;
import java.util.Set;

@Service
public class RoutesServiceImpl implements RoutesService {

    @Autowired
    private BusRoutesConfiguration busRoutesConfiguration;

    @Override
    public boolean isDirectRouteAvailable(int dep_sid, int arr_sid) {
        Map<Integer, Set<Integer>> routes = busRoutesConfiguration.getRoutes();
        Set<Integer> arrivalIds = routes.get(dep_sid);
        return arrivalIds != null && arrivalIds.contains(arr_sid);
    }
}
