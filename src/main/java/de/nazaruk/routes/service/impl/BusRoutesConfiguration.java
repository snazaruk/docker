package de.nazaruk.routes.service.impl;

import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.*;
import java.util.stream.Stream;

@Component
public class BusRoutesConfiguration {

    /**
     * Represents the map of all available direct routes.
     * Key - departure station Id
     * Value - set of all available arrival station Ids
     */
    private Map<Integer, Set<Integer>> directRoutes;

    @PostConstruct
    private void loadDirectRoutes() throws IOException {
        String fileName = "data.txt";

        Stream<String> stream = Files.lines(Paths.get(fileName));
        stream.forEach(e -> {
            if (directRoutes == null) {
                directRoutes = new HashMap<>(Integer.parseInt(e));
            } else {
                loadAvailableRoutes(e);
            }
        });
    }

    /**
     * Extracts direct routes from line. Example: line = "1 3 1 6 5".
     * First element can be skipped, because it represents the bus route id.
     * Then extracted direct routes are:
     * 3 -> (1, 6, 5)
     * 1 -> (6, 5)
     * 6 -> (5)
     * @param line
     */
    private void loadAvailableRoutes(String line) {
        String[] stringIds = line.split("\\s+");
        if (stringIds.length > 2) {
            int[] ids = Arrays.stream(stringIds).mapToInt(Integer::parseInt).toArray();

            //first id is just a route id, which we don't need for building direct routes
            for (int i = 1; i < ids.length - 1; i++) {
                for (int j = i + 1; j < ids.length; j++) {
                    Set<Integer> arrivalIds = directRoutes.get(ids[i]);
                    if (arrivalIds == null) {
                        arrivalIds = new HashSet<>();
                    }
                    arrivalIds.add(ids[j]);
                    directRoutes.put(ids[i], arrivalIds);
                }
            }
        }
    }

    public Map<Integer, Set<Integer>> getDirectRoutes() {
        return directRoutes;
    }

}
