package com.water.waterplant.Trip;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TripController {

    @PostMapping
    public String startTrip(){
        return "Trip started";

    }


}
