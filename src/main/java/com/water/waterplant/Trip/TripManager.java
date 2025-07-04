package com.water.waterplant.Trip;

import com.water.waterplant.enums.TRIPSTATUS;
import com.water.waterplant.vo.Trip;

import java.util.List;

public interface TripManager {
    public String startTrip(Trip trip);
    public List<Trip> getTrips(TRIPSTATUS tripstatus);
    public String endTrip(Trip trip);
}
