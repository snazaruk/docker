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

    private Map<Integer, Set<Integer>> routes;

    @PostConstruct
    private void loadRoutes() {
        String fileName = "data.txt";

        try (Stream<String> stream = Files.lines(Paths.get(fileName))) {
            stream.forEach(e -> {
                if (routes == null) {
                    routes = new HashMap<>(Integer.parseInt(e));
                } else {
                    String[] stringIds = e.split("\\s+");
                    //first id is route id which we do not need
                    if (stringIds.length > 2) {
                        int[] ids = Arrays.stream(stringIds).mapToInt(Integer::parseInt).toArray();

                        for (int i = 1; i < ids.length - 1; i++) {
                            for (int j = i + 1; j < ids.length; j++) {
                                Set<Integer> arrivalIds = routes.get(ids[i]);
                                if (arrivalIds == null) {
                                    arrivalIds = new HashSet<>();
                                }
                                arrivalIds.add(ids[j]);
                                routes.put(ids[i], arrivalIds);
                            }
                        }
                    }
                }
            });
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public Map<Integer, Set<Integer>> getRoutes() {
        return routes;
    }


}
