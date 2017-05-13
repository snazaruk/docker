package de.nazaruk.routes.controller;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class DirectRouteResponse {

    private int dep_sid;
    private int arr_sid;
    private boolean direct_bus_route;
}
