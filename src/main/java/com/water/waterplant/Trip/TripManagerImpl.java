package com.water.waterplant.Trip;

import com.water.waterplant.enums.TRIPSTATUS;
import com.water.waterplant.vo.Trip;
import jakarta.annotation.PostConstruct;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Service
public class TripManagerImpl implements TripManager{

    private List<Trip> tripList = new ArrayList<>();

    @PostConstruct
    public void init(){
        Trip trip = new Trip();
        trip.setStartTime(LocalDateTime.now());
        trip.setVehicleId(1234);
        trip.setStatus(TRIPSTATUS.START);
        trip.setDriverId(12345);
        tripList.add(trip);
    }

    @Override
    public String startTrip(Trip trip) {
        tripList.add(trip);
        return "Trip started";
    }

    @Override
    public List<Trip> getTrips(TRIPSTATUS tripstatus) {
        return tripList;
    }

    @Override
    public String endTrip(Trip trip) {
        tripList.remove(trip);
        return "Trip ended";
    }
}
