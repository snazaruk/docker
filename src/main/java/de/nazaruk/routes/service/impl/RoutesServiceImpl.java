package de.nazaruk.statistics.service.impl;

import de.nazaruk.statistics.service.RoutesService;
import org.springframework.stereotype.Service;

@Service
public class RoutesServiceImpl implements RoutesService {

    @Override
    public boolean isDirectRouteAvailable(int dep_sid, int arr_sid) {
        return false;
    }
}
