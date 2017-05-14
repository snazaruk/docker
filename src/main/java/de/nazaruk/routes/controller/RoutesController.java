package de.nazaruk.routes.controller;

import de.nazaruk.routes.service.RoutesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class RoutesController {

    @Autowired
    private RoutesService routesService;

    @RequestMapping(value = "/api/direct", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<DirectRouteResponse> direct(@RequestParam int dep_sid, @RequestParam int arr_sid) {
        boolean isDirect = routesService.isDirectRouteAvailable(dep_sid, arr_sid);
        DirectRouteResponse response = new DirectRouteResponse(dep_sid, arr_sid, isDirect);

        return new ResponseEntity<>(response, HttpStatus.OK);
    }
}
